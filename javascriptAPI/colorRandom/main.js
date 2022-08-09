const colorNameButton=document.getElementById("btn-random-color-name");
colorNameButton.addEventListener("click",async()=>{
    try {
        let res=await axios.get("http://localhost:8080/random-color?type=1");
        console.log(res);

        document.body.style.backgroundColor=res.data;
    } catch (error) {
        alert(error.response.data.message);
    }
})
console.log(colorNameButton.dataset.type);