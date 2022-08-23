const API_URL="http://localhost:8080/api/v1/users"
//truy cap vao cac thanh phan
const tableContentEl=document.querySelector("tbody");
const  searchInput=document.getElementById("search");
//Luu lai user
let users=[];
//LAy danh sach user 
const getUsers=async()=>{
    try {
        let res=await axios.get(API_URL);
        users=res.data;
        console.log(users);
        renderPaginationAndUser(users);
    } catch (error) {
        console.log(error);
    }
}
//Tim kiem user
searchInput.addEventListener("change",async()=>{
    try {
        let searchInputValue=searchInput.value;
        let res= await axios.get(`${API_URL}/search?name=${searchInputValue}`);
        users=res.data;
        console.log(users);
        renderPaginationAndUser(res.data);
    } catch (error) {
        console.log(error);
    }
})
// Xoa user
const deleteUser=async(id)=>{
    try {
        let res=await axios.delete(`${API_URL}/${id}`);
        users=users.filter((t)=>t.id!=id);
        renderPaginationAndUser(users);
    } catch (error) {
        console.log(error);
    }
}
const renderUser=(arr,pagination)=>{
    tableContentEl.innerHTML="";
    html="";
    arr.forEach((u,i) => {
        html+=`<tr>
        <td>${pagination.pageSize*(pagination.pageNumber-1)+(i+1)}</td>
        <td>${u.name}</td>
        <td>${u.email}</td>
        <td>${u.phone}</td>
        <td>${u.address}</td>
        <td>
            <a href="./detail.html?id=${u.id}" class="btn btn-success">Xem chi tiết</a>
            <button class="btn btn-danger" onclick="deleteUser(${u.id})">Xóa</button>
        </td>
    </tr>`
    });
    tableContentEl.innerHTML=html;
}
//Hien thi phan trang
const renderPaginationAndUser=arr=>{
    $('.pagination-container').pagination({
        dataSource: arr,
        pageSize:5,
        // showPrevious:false,
        // showNext:false,
        callback: function(data, pagination) {
            renderUser(data,pagination);
        }
    })
}
getUsers();
