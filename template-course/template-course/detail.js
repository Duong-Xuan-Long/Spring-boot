const detailBox=document.getElementById("detail-box");
const params=new URLSearchParams(window.location.search);
console.log(params.get('id'));
const detail=async(id)=>{
    try {
        let res=await axios.get(` https://midterm-test-ok.herokuapp.com/api/course-detail/${id}`);
        renderDetail(res.data);
        console.log(res);
    } catch (error) {
        console.log(error);
    }
}
const renderDetail=(obj)=>{
    detailBox.innerHTML="";
    let html=`<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <div class="mb-4">
                <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="./course-list.html">Khóa học</a></li>
                        <li class="breadcrumb-item active" aria-current="page">${obj.course.title} ${obj.course.type}</li>
                    </ol>
                </nav>
            </div>

            <div class="main p-4 shadow-sm">
                <h2 class="course-title fs-5">${obj.course.title} ${obj.course.type}</h2>

                <hr>

                <div class="supporter d-flex align-items-center">
                    <div class="supporter-image">
                        <img src=${obj.supporter.avatar} alt="tư vấn viên" class="rounded-circle w-75 h-75">
                    </div>
                    <div class="supporter-info">
                        <p>
                            <b>Tư vấn viên :</b>
                            ${obj.supporter.name}
                        </p>
                        <p>
                            <b>Email :</b>
                            ${obj.supporter.email}
                        </p>
                        <p>
                            <b>Số điện thoại :</b>
                            ${obj.supporter.phone}
                        </p>
                    </div>
                </div>

                <hr>

                <div class="course-description">
                    <p>${obj.course.description}</p>
                </div>
            </div>
        </div>
    </div>
</div>`
detailBox.innerHTML=html;
}
detail(params.get('id'));