console.log(window.location.href);
let URL=" https://midterm-test-ok.herokuapp.com/api/courses/onlab";
const cardBox=document.getElementById("cardbox");
const inputChoice=document.querySelectorAll(".choice");
const titleFind=document.getElementById("titleFind");
let courses=[];
//1. Api get
const getApi=()=>{
    return axios.get(URL);
}
//1. get all
const k=async()=>{
    try {
        let res=await getApi();
        console.log(res);
        renderCourse(res.data);
    } catch (error) {
        console.log(error);
    }
}
//chon topic
Array.from(inputChoice).forEach(radio=>
    radio.addEventListener("click",async()=>{
        let titleFindValue=titleFind.value;
        let res=await axios.get(`${URL}?topic=${radio.value}&&title=${titleFindValue}`);
        renderCourse(res.data);
    })
    )
    titleFind.addEventListener("keypress",async(event)=>{
        if(event.key == "Enter"){
            let titleFindValue=titleFind.value;
        let res=await axios.get(`${URL}?title=${titleFindValue}`);
        renderCourse(res.data);
        }
    });
const renderCourse=(arr)=>{
    cardBox.innerHTML="";
    let html="";
    arr.forEach(t => {
        html+=`<div class="col-md-4">
        <a href="./detail.html?id=${t.id}">
            <div class="course-item shadow-sm rounded mb-4">
                <div class="course-item-image">
                    <img src="https://media.techmaster.vn/api/static/8028/bpfneoc51co8tcg6lek0"
                        alt="khoa hoc">
                </div>
                <div class="course-item-info p-3">
                    <h2 class="fs-5 mb-3 text-dark">${t.title}</h2>
                    <p class="type fw-light text-black-50">${t.type}</p>
                </div>
            </div>
        </a>
    </div>`;
    });
    cardBox.innerHTML=html;
}
k();