const URL_API = "/api/todos";

// Truy cập vào các thành phần
const todoListEl = document.querySelector(".todo-list");
console.log(todos);
// Danh sách API ======
// 1. Lấy danh sách tất cả cv
const getTodoAPI = () => {
    return axios.get(URL_API); // trả về promise
}

// 2. Xóa cv
const deleteTodoAPI = (id) => {
    return axios.delete(`${URL_API}/${id}`); // trả về promise
}


// Hàm xử lý =====
// 1. Lấy danh sách tất cả cv
const getTodo = async () => {
    try {
        let res = await getTodoAPI();
        console.log(res);

        todos = res.data; // lưu lại
        renderTodo(todos) // res.data = array
    } catch (error) {
        console.log(error);
    }
}

// 2. Xóa cv
const deleteTodo = async (id) => {
    try {
        let isConfirm = confirm("Bạn có muốn xóa không?");
        if (isConfirm) {
            await deleteTodoAPI(id); // Xóa trên server;

            // Xóa trên mảng ban đầu (splice, filter)
            todos = todos.filter(t => t.id != id);

            // Hiển thị lại trên giao diện
            renderTodo(todos);
        }
    } catch (error) {
        console.log(error)
    }
}

// Hiển thị ds todo ra ngoài giao diện
const renderTodo = arr => {
    todoListEl.innerHTML = "";

    if (arr.length == 0) {
        todoListEl.innerHTML = "Không ";
        return;
    }

    let html = "";
    arr.forEach(t => {
        html += `
            <div class="todo-item ${t.status ? "active-todo" : ""}">
                <div class="todo-item-title">
                    <input type="checkbox" ${t.status ? "checked" : ""}/>
                    <p>${t.title}</p>
                </div>
                <div class="option">
                    <button class="btn btn-update">
                        <img src="./img/pencil.svg" alt="icon" />
                    </button>
                    <button class="btn btn-delete" onclick="deleteTodo(${t.id})">
                        <img src="./img/remove.svg" alt="icon" />
                    </button>
                </div>
            </div>
        `
    });

    todoListEl.innerHTML = html;
}

