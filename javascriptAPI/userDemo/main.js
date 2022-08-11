const userNameInput=document.getElementById("user-name");
const password=document.getElementById("pass-word");
const btnSubmit=document.getElementById("btn-submit");
const form=document.getElementById("form");
const showMessage=document.getElementById("show-message");
const image=document.querySelector("img");
btnSubmit.addEventListener("click",async()=>{
    try {
        let userNameInputValue=userNameInput.value;
        let passwordValue=password.value;
        let res=await axios.post(`http://localhost:8080/login`,{
                "username":userNameInputValue,
                "password":passwordValue
        }); 
        console.log(res);
        if(res.data.username!=null){
            renderSignIn(res.data);   
        }
    } catch (error) {
        alert(error.response.data.message);
    }
})
const renderSignIn=(obj)=>{
    form.classList.add('hidden');
    btnSubmit.classList.add('hidden');
    showMessage.innerHTML=`<p class="text-monospace">Xin chào ${obj.username}</p>`+`<p class="text-monospace">${obj.email}</p>`+`<p class="text-monospace">${obj.avatar}</p>`;
    image.src="./tải xuống.jpg";
}