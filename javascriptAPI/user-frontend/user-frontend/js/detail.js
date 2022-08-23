//{id}/forgot-password
const URL_API = "http://localhost:8080/api/v1/users";
const params = new URLSearchParams(window.location.search);
const forgotButton = document.getElementById("btn-forgot-password");
const confirmButton = document.getElementById("btn-change-password");
const oldPasswordInput = document.getElementById("old-password");
const newPasswordInput = document.getElementById("new-password");
const avatarPreview = document.getElementById("avatar-preview");
const nameInput = document.getElementById("name");
const phoneInput = document.getElementById("phone");
const addressInput = document.getElementById("address");
const show = async () => {
    try {
        let res = await axios.get(`${URL_API}/${params.get('id')}`);
        let res1 = await axios.get("https://provinces.open-api.vn/api/p/");
        renderProvince(res1.data, res.data);
        nameInput.value = res.data.name;
        phoneInput.value = res.data.phone;
    } catch (error) {
        console.log(error)
    }
}
const renderProvince = (arr, res) => {
    addressInput.innerHTML = "";
    let html = `<option hidden>${res.address}</option>`;
    arr.forEach(p => {
        html += `<option value="${p.name}">${p.name}</option>`;
    });
    addressInput.innerHTML = html;
}
show();
// const init=async()=>{
//     try {
//         await getUsers();
//         await getProvince();
//     } catch (error) {

//     }
// }
let images = [];
const btnModalImage = document.getElementById("btn-modal-image");
const imageContainerEl = document.querySelector(".image-container");
const btnChoseImage = document.getElementById("btn-chose-image");
const btnDeleteImage = document.getElementById("btn-delete-image");
const modalImageEl = document.getElementById("modal-image");
const avatar = document.getElementById("avatar");
btnModalImage.addEventListener("click", async () => {
    try {
        let res = await axios.get(`${URL_API}/${params.get('id')}/files`);
        console.log(res.data);
        images = res.data;
        renderImage(images);
    } catch (error) {
        console.log(error);
    }
})
//upload anh
avatar.addEventListener("change", async (e) => {
    try {
        //Lay ra file upload
        let file = e.target.files[0];
        //Tao formdata
        let formData = new FormData();
        formData.append("file", file);
        //tao api
        let res = await axios.post(`${URL_API}/${params.get('id')}/files`, formData);
        images.unshift(res.data);
        //render lai image
        renderImage(images);
    } catch (error) {
        console.log(error);
    }
})
const renderImage = arr => {
    imageContainerEl.innerHTML = "";
    let html = "";
    arr.forEach(e => {
        html += `
        <div class="image-item" onclick="chooseImage(this)">
            <img src="http://localhost:8080${e}" alt="">
        </div>`;
    });
    imageContainerEl.innerHTML = html;
}
//chon anh
const chooseImage = (ele) => {
    //Xoa het image duoc chon truoc do
    const imageSelected = document.querySelector(".selected")
    if (imageSelected) {
        imageSelected.classList.remove("border-3", "border-primary", "selected");
    }
    //highlight image vua duoc click
    ele.classList.add("border-3", "border-primary", "selected");
    btnChoseImage.disabled = false;
    btnDeleteImage.disabled = false;
    
}
//chon anh
btnChoseImage.addEventListener("click", async () => {
    try {
        const selectedUrl = document.querySelector(".selected img");
        let avatarString = selectedUrl.src.substring(21);
        let res = await axios.put(`${URL_API}/${params.get('id')}/update-avatar`, {
            avatar: avatarString,
        })
        avatarPreview.src = `http://localhost:8080${avatarString}`;
    } catch (error) {
        console.log(error);
    }
})
//Xoa anh
btnDeleteImage.addEventListener("click", async () => {
    const selectedUrl = document.querySelector(".selected img");
    //Xoa anh
    let fileId = selectedUrl.src.substring(selectedUrl.src.lastIndexOf("/") + 1);
    try {
        await axios.delete(`${URL_API}/${params.get('id')}/files/${fileId}`);
        images = images.filter(img => img.substring(img.lastIndexOf("/") + 1) != fileId);
        renderImage(images);
    } catch (error) {
        console.log(error);
    }
})

//Khi dong modal thi disable 2 nut
modalImageEl.addEventListener('hidden.bs.modal', () => {
    btnChoseImage.disabled = true;
    btnDeleteImage.disabled = true;
})
//quen mk
forgotButton.addEventListener("click", async () => {
    try {
        await axios.get(`${URL_API}/${params.get('id')}/forgot-password`);
    } catch (error) {
        console.log(error);
    }
})
//doi mat khau
confirmButton.addEventListener("click", async () => {
    try {
        await axios.put(`${URL_API}/${params.get('id')}/update-password`, {
            oldPassword: oldPasswordInput.value,
            newPassword: newPasswordInput.value,
        });
    } catch (error) {
        console.log(error);
    }
})
//cap nhat
const btnSave = document.getElementById("btn-save");
btnSave.addEventListener("click", async () => {
    try {
        let res = await axios.put(`${URL_API}/${params.get('id')}/update-user`, {
            name: nameInput.value,
            phone: phoneInput.value,
            address: addressInput.value
        });
        console.log(res);
    } catch (error) {
        console.log(error);
    }
})
