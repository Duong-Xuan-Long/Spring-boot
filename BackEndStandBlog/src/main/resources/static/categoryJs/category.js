async function saveCategory(id){
try{
    const inputEl=document.getElementById(`input-${id}`);
    let res=await axios.put(`http://localhost:8080/admin/categories/${id}`,{
        name:inputEl.value,
    })
    categories.find(c=>c.id==id).name=res.data.name;
    render(categories);
}catch(e){
console.log(e)
    }
}
async function deleteCategory(id,index){
    try{
        await axios.delete(`http://localhost:8080/admin/categories/${id}`)
        console.log(categories);
        categories.splice(index,1);
        render(categories);
    }catch(e){
    console.log(e)
        }
    }
const tbody=document.querySelector('tbody');
let render=function(categories){
    tbody.innerHTML='';
    let html=''
    categories.forEach((category,index)=>{
    html+=`<tr>
               <td>${category.name}</td>
               <td>
               <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#modal-default-${category.id}">Sửa</button>
               <button class="btn btn-danger" onclick="deleteCategory(${category.id},${index})">Xóa</button>
               </td>
               <div class="modal fade" id="modal-default-${category.id}" style="display: none;" aria-hidden="true">
               <div class="modal-dialog">
               <div class="modal-content">
               <div class="modal-header">
               <h4 class="modal-title">Default Modal</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">×</span>
                </button>
                  </div>
                  <div class="modal-body">
                 <input id="input-${category.id}" type="text" value=${category.name} style="width:90%;" class="mx-4 p-2 form-control"></input>
                                                               </div>
                                                               <div class="modal-footer justify-content-between">
                                                                   <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                   <button type="button" class="btn btn-primary" id="btn-save" onclick="saveCategory(${category.id})">Save changes</button>
                                                               </div>
                                                           </div>

                                                       </div>

                                                   </div>
                                               </tr>`
    });
    tbody.innerHTML=html;
    };

async function createEl(){
    try{
    const createInput=document.getElementById('create-input');

           let res= await axios.post(`http://localhost:8080/admin/categories/create`,{
           name:createInput.value,
           })
           categories.push(res.data);
            render(categories);
        }catch(e){
        console.log(e)
        }
}
