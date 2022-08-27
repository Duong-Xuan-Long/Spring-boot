package com.example.templatecourseadmin.service;

import com.example.templatecourseadmin.exception.BadRequestException;
import com.example.templatecourseadmin.exception.NotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FileService {
    private Path rootPath= Paths.get("uploads");

    public FileService(){
        createFolder(rootPath.toString());
    }
    //tao folder
    public void createFolder(String path){
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }
    public String uploadFile(MultipartFile file) {
        //Tao folder tuong ung userId
        //Validate file
        validateFile(file);

        //Tao fileId
        String fileId=String.valueOf(System.currentTimeMillis());
        File serverFile=new File(rootPath+"/"+fileId);
        try {
            // Sử dụng Buffer để lưu dữ liệu từ file
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

            stream.write(file.getBytes());
            stream.close();

            return "/api/v1/files/" + fileId;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi upload file");
        }
    }
    public void validateFile(MultipartFile file){
        //kiem tra ten
        String fileName=file.getOriginalFilename();
        if(fileName==null||fileName.equals("")){
            throw new BadRequestException("ten file khong duoc de trong");
        }
        //Kiem tra duoi
        //avatar.png->png
        //image.jpg->jpg
        String fileExtension=getExtensionFIle(fileName);
        if(!checkFileExtension(fileExtension)){
            throw new BadRequestException("file khong hop le");
        }
        //kiem tra size
        //upload khong vuot qua 3MB
        double size=(double)file.getSize()/1_000_000;
        if(size>3){
            throw new BadRequestException("kich thuoc size khong duoc vuot qua 3MB");
        }
    }
    //Lay duoi file
    public String getExtensionFIle(String fileName){
        int lastIndex=fileName.lastIndexOf(".");
        if(lastIndex==-1) return "";
        return fileName.substring(lastIndex+1);
    }
    //Kiem tra duoi file
    public boolean checkFileExtension(String fileExtension){
        List<String> extensions=new ArrayList<>(List.of("png","jpg","jpeg"));
        return extensions.contains(fileExtension);
    }
    //doc file
    public byte[] readFile(String fileId) {

        try {
            // Lấy ra đường dẫn file tương ứng với user_id và file_id
            Path file = rootPath.resolve(fileId);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()) {
                InputStream stream = resource.getInputStream();
                byte[] bytes = StreamUtils.copyToByteArray(stream);
                stream.close();
                return bytes;
            } else {
                throw new RuntimeException("Không thể đọc file " + fileId);
            }

        } catch (Exception e) {
            throw new RuntimeException("Không thể đọc file " + fileId);
        }
    }

}
