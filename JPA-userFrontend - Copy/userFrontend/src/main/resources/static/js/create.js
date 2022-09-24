
const API_URL="http://localhost:8080/api/v1/users";
//province
const provinceEl=document.querySelector("#address");
const createBtn=document.querySelector("#btn-save");
const nameInput=document.querySelector("#name");
const emailInput=document.querySelector("#email");
const phoneInput=document.querySelector("#phone");
const passwordInput=document.querySelector("#password");
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
//Lay danh sach thanh pho
const getProvince=async ()=>{
    try {
        let res=await axios.get("https://provinces.open-api.vn/api/p/");
        console.log(res);
        renderProvince(res.data);
    } catch (error) {
        console.log(error);
    }
}
const renderProvince=arr=>{
    provinceEl.innerHTML="";
    let html="<option hidden>--Chon tinh thanh pho</option>";
    arr.forEach(p=> {
        html+=`<option value="${p.name}">${p.name}</option>`;
    });
    provinceEl.innerHTML=html;
}
getProvince();
createBtn.addEventListener("click",async()=>{
    try {
        let nameInputValue=nameInput.value;
        let emailInputValue=emailInput.value;
        let phoneInputValue=phoneInput.value;
        let passwordInputValue=passwordInput.value;
        let addressInputValue=provinceEl.value;
        let res=await axios.post("http://localhost:8080/api/v1/users",{
            name:nameInputValue,
            email:emailInputValue,
            phone:phoneInputValue,
            address:addressInputValue,
            password:passwordInputValue
        })
        console.log(res.data);
        toastr.success("Xin chao cac ban");
    } catch (error) {
        console.log(error);
        toastr.error("trung tai khoan");
    }
}) 
