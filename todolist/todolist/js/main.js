const URL_API = "https://todolist-ok.herokuapp.com/api/todos";
const todoListEL = document.querySelector(".todo-list");
const all = document.getElementById("all");
const unactive = document.getElementById("unactive");
const active = document.getElementById("active");
const todoInput = document.getElementById("todo-input");
const btnAdd = document.getElementById("btn-add");
const todoInputDiv = document.querySelector(".todo-input");
//Danh sach API;
//1.Lay danh sach tat ca cac cong viec
const getTodoAPI = () => {
    return axios.get(URL_API);
};
//2 xoa cv
const deleteTodoAPI = (id) => {
    return axios.delete(`${URL_API}/${id}`);
};
//3 Lay danh sach cac cong viec unactive
const getUnactive = () => {
    return axios.get(`${URL_API}?status=false`);
};
//4 Lay danh sach cac cong viec active
const getActive = () => {
    return axios.get(`${URL_API}?status=true`);
};
//Luu lai cong viec
let todos = [];
//Ham xu li
//1.Lay danh sach tat ca cac cong viec
const getTodo = async () => {
    try {
        let res = await getTodoAPI();
        console.log(res);
        todos = res.data;
        renderTodo(todos);
    } catch (error) {
        console.log(error);
    }
};
//2.Delete cv
const deleteTodo = async (id) => {
    try {
        let isConfirm = confirm("Ban co muon xoa khong");
        if (isConfirm) {
            await deleteTodoAPI(id); //Xoa tren server
            //Xoa tren mang ban dau(splice,filter)
            todos = todos.filter((t) => t.id != id);
            renderTodo(todos);
        }
    } catch (error) {
        console.log(error);
    }
};
//3.get all
all.addEventListener("click", async () => {
    try {
        let res = await getTodoAPI();
        todos = res.data;
        renderTodo(todos);
    } catch (error) {
        console.log(error);
    }
});
//4.getUnactive
unactive.addEventListener("click", async () => {
    try {
        let res = await getUnactive();
        todos = res.data;
        renderTodo(todos);
    } catch (error) {
        console.log(error);
    }
});
//5.getActive
active.addEventListener("click", async () => {
    try {
        let res = await getActive();
        todos = res.data;
        renderTodo(todos);
    } catch (error) {
        console.log(error);
    }
});
//6.Add new
btnAdd.addEventListener("click", async () => {
    if (btnAdd.textContent == "THÊM") {
        let todoInputValue = todoInput.value;
        try {
            let res = await axios.post(URL_API, {
                title: todoInputValue,
            });
            todos.push(res.data);
            renderTodo(todos);
            todoInput.value = "";
        } catch (error) {
            console.log(error);
        }
    }
});
//7.Update todo
const updateTodo = (id) => {
    try {
        let index;
        btnAdd.textContent = "CẬP NHẬT";
        let todoUpdate;
        for (let i = 0; i < todos.length; i++) {
            if (todos[i].id == id) {
                todoUpdate = todos[i];
                index=i;
                break;
            }
        }
        todoInput.value = todoUpdate.title;
        btnAdd.addEventListener("click", async () => {
            console.log(id);
            if (btnAdd.textContent == "CẬP NHẬT") {
                let res = await axios.put(`${URL_API}/${id}`, {
                    title: todoInput.value,
                    status: todoUpdate.status,
                });
                console.log(todoInput.value);
                todos = todos.filter((t) => t.id != res.data.id);
                todos.splice(index,0,res.data);
                renderTodo(todos);
                btnAdd.textContent = "THÊM";
                todoInput.value="";
                console.log(todos);
            }
        });
    } catch (error) {
        console.log(error);
    }
};
//8.UpdateStatus
const toggleStatus=async(id)=>{
   try {
    let index;
    let todoUpdate;
    for (let i = 0; i < todos.length; i++) {
        if (todos[i].id == id) {
            todoUpdate = todos[i];
            index=i;
            break;
        }
    }
    let res = await axios.put(`${URL_API}/${id}`, {
        title: todoUpdate.title,
        status: !todoUpdate.status,
    });
    todos = todos.filter((t) => t.id != res.data.id);
    todos.splice(index,0,res.data);
    renderTodo(todos);
   } catch (error) {
    console.log(error);
   }
}
const renderTodo = (arr) => {
    todoListEL.innerHTML = "";
    if (arr.length == 0) {
        todoListEL.innerHTML = "Không có công việc naog trong danh sách";
        return;
    }
    let html = "";
    arr.forEach((t) => {
        html += `
            <div class="todo-item ${t.status ? "active-todo" : ""}">
            <div class="todo-item-title">
                <input type="checkbox" ${t.status ? "checked" : ""} onclick="toggleStatus(${t.id})"/>
                <p>${t.title}</p>
            </div>
            <div class="option">
                <button class="btn btn-update" onclick="updateTodo(${t.id})">
                    <img src="./img/pencil.svg" alt="icon" />
                </button>
                <button class="btn btn-delete" onclick="deleteTodo(${t.id})">
                    <img src="./img/remove.svg" alt="icon" />
                </button>
            </div>
            </div>
        `;
        todoListEL.innerHTML = html;
    });
};
getTodo();
