const breedListEl=document.getElementById("breed-list");

//https://dog.ceo/api/breeds/list/all
//Lay danh sach giong loai va hien thi
const getBreedList= async()=>{
    try{
        let res=await axios.get("https://dog.ceo/api/breeds/list/all");
       // console.log(res);
        renderBreedList(res.data.message);
    }catch(error){
        console.log(error);
    }
}
const renderBreedList=(obj)=>{
    let keys=Object.keys(obj);
    console.log(keys);
    let html="";
    keys.forEach(key=>{
        html+=`<option value=${key}>${key}</option>`;
    })
    breedListEl.innerHTML=html;
}
getBreedList();
const btn=document.getElementById("btn");
const imageEl=document.getElementById("image");
btn.addEventListener("click",async()=>{
    try{
        let value=breedListEl.options[breedListEl.selectedIndex].value;
        let response=await axios.get(`https://dog.ceo/api/breed/${value}/images/random`);
        console.log(response);
        imageEl.src=response.data.message;
    }catch(error){
        console.log(error);
    }
});