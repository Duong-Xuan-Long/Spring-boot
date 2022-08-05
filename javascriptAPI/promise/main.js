// //khoi tao ham promise
// let promise=new Promise((resolve,reject)=>{

// })
// console.log(promise);
// let promiseSuccess=new Promise((resolve,reject)=>{
//     resolve("thuc hien cong viec thanh cong");
// })
// console.log(promiseSuccess);
// let promiseError=new Promise((resolve,reject)=>{
//     reject("thuc hien that bai");
// })
// console.log(promiseError);
// //Hua cuoi nam neu co tren 30tr se mua iphone 13 promax
// let money=35;
// const buyIphone=()=>{
//     return new Promise((resolve,reject)=>{
//         if(money>=30){
//             resolve("du tien mua iphone");
//         }else{
//             reject("khong du");
//         }
//     })
// }

// const buyAirpod = ()=>{
//     return new Promise(function (resolve, reject) {
//         if (money - 30 - 4 >= 0) {
//             resolve("Mua thêm airpod")
//         } else {
//             reject("Không đủ tiền mua airpod")
//         }
//     })
// }
//console.log(buyIphone());
// buyIphone()
// .then(res=>console.log(res))
// .catch(error=>console.log(error));

// buyAirpod()
// .then(res=>console.log(res))
// .catch(error=>console.log(error));

// buyIphone()
// .then(res=>{
//     console.log(res)
//     return buyAirpod();
// }).then(res1=>{
//     console.log(res1);
// })
// .catch(error=>console.log(error))
// .finally(()=>{
//     console.log("Ve nha");
// })
// money=20;
function sleep(ms){
    return new Promise(
        function(resolve){
            setTimeout(resolve,ms);
        }
    )
}

sleep(1000).then(function(){
    console.log(1);
    return sleep(1000);
}).then(function(){
    console.log(2);
    return sleep(1000);
})
