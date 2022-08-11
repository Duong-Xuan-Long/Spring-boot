let URL="http://localhost:8080/api/courses";
const phongLab=document.getElementById("phong-lab");
const trucTuyen=document.getElementById("truc-tuyen");
let courses=[];
//1. Api get
const getApi=()=>{
    return axios.get(URL);
}
phongLab.addEventListener("click",async()=>{
    try {
        let res=await getApi();
        console.log(res);
    } catch (error) {
        console.log(error);
    }
})