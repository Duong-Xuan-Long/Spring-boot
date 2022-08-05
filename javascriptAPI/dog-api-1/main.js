//1.So cach goi API
//Su dung fetch API co san cua javascript
//Su dung axios(package ben thu 3)->import
//Su dung AJAX(jquery)->import

//Thao tac: bam nut->goi API->co ket qua->Hien thi 

const btnRandom=document.getElementById("btn-random");
const imageEl=document.getElementById("image");

// btnRandom.addEventListener("click",async ()=>{
//         try{
//             let response=await fetch("https://dog.ceo/api/breeds/image/random");
//             console.log(response);
//             let responseJSON=await response.json();
//             console.log(responseJSON);

//             imageEl.src=responseJSON.message;
//         }catch(error){
//             console.log(error);
//         }
// })

btnRandom.addEventListener("click",async ()=>{
    try{
        let response=await axios.get("https://dog.ceo/api/breeds/image/random");
        console.log(response);

        imageEl.src=response.data.message;
    }catch(error){
        console.log(error);
    }
})