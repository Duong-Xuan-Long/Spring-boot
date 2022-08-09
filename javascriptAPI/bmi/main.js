const btnGet=document.getElementById("btn-get");
const btnPost=document.getElementById("btn-post");
const inputHeight=document.getElementById("height");
const inputWeight=document.getElementById("weight");
const h1=document.querySelector("h1");
btnGet.addEventListener("click",async()=>{
     try {
        let height=inputHeight.value;
        let weight=inputWeight.value;
        let res=await axios.get(`http://localhost:8080/bmi-get?height=${height}&weight=${weight}`);
        let result=check(res.data);
         h1.textContent=result;
     } catch (error) {
        console.log(error);
        h1.textContent="chua nhap";
     }
})
const check=(bmi)=>{
   if(bmi<18.5) return"Thiếu cân";
   else if(bmi>=18.5&&bmi<=24.9) return "Bình thường" ;
   else if(25 <= bmi&&bmi <= 29.9) return "Thừa cân" ;
   else return "Béo phì";
}
btnPost.addEventListener("click",async()=>{
   try {
      let res=await axios.post(`http://localhost:8080/bmi-post`,{
         "height":2,
       "weight":70
      });
      console.log(res);
      let result=check(res.data);
         h1.textContent=result;
   } catch (error) {
      console.log(error);
   }
})