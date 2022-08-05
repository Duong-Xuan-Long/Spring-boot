const breedListEl=document.getElementById("breed-list");

//https://dog.ceo/api/breeds/list/all
//Lay danh sach giong loai va hien thi
const getBreedList= async()=>{
    try{
        let res=await axios.get("https://dog.ceo/api/breeds/list/all");
        console.log(res);
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
const subList=document.getElementById("sub-breed-list");
btn.addEventListener("click",async()=>{
    try {
        let value=breedListEl.options[breedListEl.selectedIndex].value;
        let res=await axios.get(`https://dog.ceo/api/breed/${value}/list`);
        console.log(res);
        renderSublist(res.data.message);
    } catch (error) {
        console.log(error);
    }
})
function renderSublist(obj){
    let values=Object.values(obj);
    let li="";
    if(values.length!=0){
        values.forEach((value)=>{
            li+=`<li><a href="#">${value}</a></li>`;
        });
        subList.innerHTML=li;
        const linka=document.querySelectorAll("a");
Array.from(linka).forEach(a=>{
    a.addEventListener("click",async()=>{
        try {
            let k=a.textContent;
            let value=breedListEl.options[breedListEl.selectedIndex].value;
            
        let res=await axios.get(`https://dog.ceo/api/breed/${value}/${k}/images/random`);
        imageEl.src=res.data.message;
        } catch (error) {
            console.log(error);
        }
    })
})
    }
    else{
        subList.innerHTML=`<li>Không có sub breed</li>`;
    }
}
