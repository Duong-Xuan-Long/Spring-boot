// function sum(a,b){
//     return a+b;
// }
// const sum1=function(a,b){
//     return a+b;
// }
// const sum2=(a,b)=>{
//     return a+b;
// }
// //Hoc them TypeScript
// Công việc 3 : Giả sử sau 3s thì thực hiện xong
// function funcC() {
//     setTimeout(function () {
//         console.log("three");
//     }, 3000)
// }

// // Công việc 4 : Giả sử sau 4s thì thực hiện xong
// function funcD() {
//     setTimeout(function () {
//         console.log("four");
//     }, 4000)
// }

// // Tổng thời gian thực hiện hết 2 cv3 và cv4 là 4s
// // Thực hiện công việc
// funcD();
// funcC();
// let number=[1,2,3,4,5];
// number.forEach((number,index)=>{
//     if(index==2){
//         return;
//     }
//     console.log(number,index);
// });

//Mo ta cong viec 1
const doTask1=(name,callback)=>{
    console.log("bat dau cong viec");
    console.log(`thuc hien cong viec:${name}`);
    setTimeout(()=>{
        callback();
    },3000);
}
const doTask2=(name,callback)=>{
    console.log(`thuc hien cong viec:${name}`);
    setTimeout(()=>{
        callback();
    },4000);
}
const doTask3=(name,callback)=>{
    console.log(`thuc hien cong viec:${name}`);
    setTimeout(()=>{
        callback();
    },5000);
}
const finish=()=>{
    console.log("Ket thuc cong viec");
}
//thuc hien dong thoi
// doTask1("lam bai tap",finish);
// doTask1("di choi",finish);
// doTask1("da bong",finish);

//function k ten:anonymous function
doTask1("nhat rau",()=>{
    doTask2("rua rau",()=>{
        doTask3("luoc rau",finish);
    })
})