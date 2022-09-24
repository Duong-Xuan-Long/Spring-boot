      //Initialize Select2 Elements
    $(".select2").select2();

    // Initialize editor
    const easyMDE = new EasyMDE({
      element: document.getElementById("content"),
      spellChecker: false,
      maxHeight: "500px",
      renderingConfig: {
        codeSyntaxHighlighting: true,
      },
    });
var URL_API="http://localhost:8080/api/admin/blogs";
const saveBlog=document.getElementById('save-blog');
const titleInput=document.getElementById('title');
const contentInput=document.getElementById('content');
const descriptionInput=document.getElementById('description');
const statusInput=document.getElementById('status');
const categoryInput=$('#category')

saveBlog.addEventListener('click',async()=>{
try {
        let res=await axios.post(URL_API,{
            title : titleInput.value,
            description :descriptionInput.value ,
            content:easyMDE.value(),
            status:Number(statusInput.value),
            categoryIds:categoryInput.val().map(e=>Number(e))
            })
    } catch (error) {
        console.log(error);
    }
//categoryInput.find('option:selected').toArray().map(c=>Number(c.val()))
