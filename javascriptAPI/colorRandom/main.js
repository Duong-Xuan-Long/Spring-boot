const colorNameButton=document.getElementById("btn-random-color-name");
colorNameButton.addEventListener("click",async()=>{
    try {
        let res=await axios.get("https://springboot-sample-project-1.herokuapp.com/random-color?type=1");
        console.log(res);

        document.body.style.backgroundColor=res.data;
    } catch (error) {
        alert(error.response.data.message);
    }
})
console.log(colorNameButton.dataset.type);