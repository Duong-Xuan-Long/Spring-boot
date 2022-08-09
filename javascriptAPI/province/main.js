const provinceEl=document.querySelector("#province");
const districtEl=document.querySelector("#district");
const communeEl=document.querySelector("#commune");
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
//LAy quan huyen
const getDistrict=async (provinceCode)=>{
    try {
        let res=await axios.get(`https://provinces.open-api.vn/api/p/${provinceCode}?depth=2`);
        console.log(res);
        renderDistrict(res.data.districts);
    } catch (error) {
        console.log(error);
    }
}
//Lay xa phuong 
const getCommune=async (districtCode)=>{
    try {
        let res=await axios.get(`https://provinces.open-api.vn/api/d/${districtCode}?depth=2`);
        console.log(res);
        renderCommune(res.data.wards);
    } catch (error) {
        console.log(error);
    }
}
//hien thi xa phuong
const renderCommune=arr=>{
    communeEl.innerHTML="";
    let html="<option hidden>--Chon xa phuong</option>";
    arr.forEach(p=> {
        html+=`<option value=${p.code}>${p.name}</option>`;
    });
    communeEl.innerHTML=html;
}
//hien thi quan huyen
const renderDistrict=arr=>{
    districtEl.innerHTML="";
    let html="<option hidden>--Chon quan huyen</option>";
    arr.forEach(p=> {
        html+=`<option value=${p.code}>${p.name}</option>`;
    });
    districtEl.innerHTML=html;
}
//hien thi thanhg pho
const renderProvince=arr=>{
    provinceEl.innerHTML="";
    let html="<option hidden>--Chon tinh thanh pho</option>";
    arr.forEach(p=> {
        html+=`<option value=${p.code}>${p.name}</option>`;
    });
    provinceEl.innerHTML=html;
}
provinceEl.addEventListener("change",()=>{
    let provinceCode=provinceEl.value;
    console.log(provinceCode);
    getDistrict(provinceCode);
    let districtCode=districtEl.value;
    getCommune(districtCode);
})

districtEl.addEventListener("change",()=>{
    let districtCode=districtEl.value;
    getCommune(districtCode);
})
getProvince();