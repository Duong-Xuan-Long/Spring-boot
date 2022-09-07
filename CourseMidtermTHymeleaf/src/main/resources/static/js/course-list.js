//select element
const tbody=document.querySelector("tbody");
let API_URL="http://localhost:8080/api/v1/courses";
//get courses-list API
const getAPI=()=>{
    return axios.get("http://localhost:8080/api/v1/courses");
}
//Tao mot day
//get lists
//render courses-list
const renderCourseList=(arr)=>{
    tbody.innerHTML="";
    let html="";
    arr.forEach((e,i) => {
        html+=`<tr>
        <td>${i+1}</td>
        <td>
            <a href="./course-edit.html?id=${e.id}">${e.title}</a>
        </td>
        <td class=${e.type=="onlab"?"text-info":"text-warning"}>${e.type}</td>
        <td>${e.topics}</td>
    </tr>`;
    });
    tbody.innerHTML=html;
}

