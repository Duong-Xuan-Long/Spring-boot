//const param=new URLSearchParams(window.location.search);
let URL=window.location.href;
let idURL=(URL).substring(URL.lastIndexOf("/")+1);
        // Truy cập vào các thành phần
        const courseName = document.getElementById("course-name");
        const courseDescription = document.getElementById("course-description");
        const courseType = document.getElementById("course-type");
        const courseTopic = $("#course-topic");
        // const courseTopics=document.getElementById("course-topic");
        const courseSupporter = document.getElementById("course-supporter");
        const courseThumbnailPreview = document.getElementById("course-logo-preview");
        const courseLogoInput=document.getElementById("course-logo-input");
        const btnUpdate=document.getElementById("btn-update");
        const btnDelete=document.getElementById("btn-delete");
        // Kích hoạt select2
        courseTopic.select2({
            placeholder: "- Chọn chủ đề",
        });

//show
const show=async()=>{
    try {
        let resId=await axios.get(`http://localhost:8080/api/v1/courses/${idURL}`);
        courseName.value=resId.data.title;
        courseDescription.value=resId.data.description;
        renderCourseType(resId.data);
        renderTopics(resId.data);
        renderSupporter(resId.data);
        courseThumbnailPreview.src=`http://localhost:8080${resId.data.image}`;
    } catch (error) {
        console.log(error);
    }
};
show();
//render supporter
const renderSupporter=async(obj)=>{
    let res=await axios.get(`http://localhost:8080/api/v1/supporters`);
    let arr=res.data;
    courseSupporter.innerHTML="";
    let html="";
    let supporterName;
    let valueS;
    for(let i=0;i<arr.length;i++){
        if(obj.supporterId==arr[i].id){
            supporterName=arr[i].name;
            valueS=arr[i].id;
        }
    }
    html=`<option value=${valueS} selected>-${supporterName}</option>`;
    arr.forEach((s,i) => {
        if(s.name!=supporterName){
            html+=`<option value=${s.id}>${s.name}</option>`;   
        }
    });
    courseSupporter.innerHTML=html;
}
// render courseType
let typeNames=["online","onlab"];
const renderCourseType=(obj)=>{
    courseType.innerHTML="";
    let html="";
     html=`<option value=${obj.type} selected>${obj.type}</option>`;
     typeNames.forEach(e=>{
        if(e!=obj.type){
            html+=`<option value=${e}>${e}</option>`;
        }
     })
    courseType.innerHTML+=html;
}
//render Topics
let topicNames=["Backend","Frontend","Mobile","Lập trình web","Database","Devops"];
const renderTopics=(obj)=>{
    courseTopic.empty();
    let html="";
    let topicArr=obj.topics;
    topicArr.forEach(topic=>{
        let valueCheck=check(topic);
        html+=`<option value=${valueCheck} selected>${topic}</option>`;
    })
    topicNames.forEach(t=>{
        if(!topicArr.includes(t)){
            html+=`<option value=${check(t)}>${t}</option>`
        }
    });
    courseTopic.append(html);
}
//check topic
const check=(topic)=>{
    switch(topic){
        case "Backend": return "1" ;break;
        case "Frontend": return "2" ;break;
        case "Mobile": return "3" ;break;
        case "Lập trình web": return "4" ;break;
        case "Database": return "5" ;break;
        case "Devops": return "6" ;break;
    }
}
//doi anh
courseLogoInput.addEventListener("change",async(e)=>{
    try {
        let file=e.target.files[0];
        let formData=new FormData();
        formData.append("file",file);
        let res=await axios.post(`http://localhost:8080/api/v1/courses/${idURL}/files`,formData);
        console.log(res.data);
        courseThumbnailPreview.src=`http://localhost:8080${res.data}`;
    } catch (error) {
        console.log(error);
    }
})
//update 
btnUpdate.addEventListener("click",async()=>{
    try {
        console.log(courseSupporter.value);
        let text=courseTopic.find('option:selected').toArray().map(item => item.text);
        let res=await axios.put(`http://localhost:8080/api/v1/courses/${idURL}`,{
            title:courseName.value,
            description:courseDescription.value,
            type:courseType.value,
            topics:text,
            supporterId:courseSupporter.value
        })
    } catch (error) {
        console.log(error);
    }
})
//delete
btnDelete.addEventListener("click",async()=>{
    try {
        await axios.delete(`http://localhost:8080/api/v1/courses/${idURL}`);
    } catch (error) {
        console.log(error);
    }
})
