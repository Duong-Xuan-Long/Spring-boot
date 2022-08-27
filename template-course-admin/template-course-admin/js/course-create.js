const courseTopic = $("#course-topic ");

// Kích hoạt select2
courseTopic.select2({
    placeholder: "- Chọn chủ đề",
});
//Hien thi toast
toastr.options = {
    "closeButton": false,
    "debug": false,
    "newestOnTop": false,
    "progressBar": false,
    "positionClass": "toast-top-center",
    "preventDuplicates": false,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}
//select  element
const courseName=document.getElementById("course-name");
const courseDescription=document.getElementById("course-description");
const courseType=document.getElementById("course-type");
const courseSupporter=document.getElementById("course-supporter");
const btnCreate=document.getElementById("btn-create");
//render ho tro vien
const getSupporters=async()=>{
    try {
        let res=await axios.get("http://localhost:8080/api/v1/supporters");
        renderSupporter(res.data);
    } catch (error) {
        console.log(error);
    }
}
//render supporter
const renderSupporter=(arr)=>{
    courseSupporter.innerHTML="";
    let html="";
    html="<option hidden>- Chon tư vấn viên</option>";
    arr.forEach((s,i) => {
        html+=`<option value=${s.id}>${s.name}</option>`;
    });
    courseSupporter.innerHTML=html;
}
getSupporters();
//ham
btnCreate.addEventListener("click",async()=>{
    try {
        let res=await axios.post("http://localhost:8080/api/v1/courses",{
            title:courseName.value,
            description:courseDescription.value,
            type:courseType.value,
            topics:courseTopic.find('option:selected').toArray().map(item => item.text),
            supporterId:courseSupporter.value
        })
        console.log(res.data);
        toastr.success("Xin chao cac ban");
    } catch (error) {
        toastr.error("khong thanh cong")
    }
})
