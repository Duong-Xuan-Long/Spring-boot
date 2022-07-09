// console.log("hello world");
// let add=(a,b)=>a+b;
// console.log(add(5,4));
// function check(a,b,c){
//     if(c==a+b) return true;
//     return false;
// }
// let tong = (a,b,c)=>{
//     return c==a+b?true:false;
// }
// let k=check(1,2,3);
// console.log(check(1,2,3));
// // console.log(tong(1,2,5));
// function check(month){
//     switch(month){
//         case 1:
//         case 2:
//         case 3:
//             console.log("Mua xuan");
//             break;
//         case 4:
//         case 5:
//         case 6:
//             console.log("Mua ha");
//             break;
//         case 7 :
//         case 8 :
//         case 9 :
//             console.log("Mua thu");
//             break;
//         case 10 :
//         case 11 :
//         case 12 :
//             console.log("Mua dong");
//             break;
//         default :
//             console.log("Khong phai la so");
//             break;
//     }
// }
// check(12);
function sum(a,b){
    if(a==b) return 0;
    let sum=0;
    if(a<b){
    for(let k=a+1;k<b;k++){
        sum+=k;
    }
    }
    else {
        for(let k=b+1;k<a;k++){
            sum+=k;
        }
    }
    
    return sum;
}
// function sum(a,b){
//     let min=Math.min(a,b);
//     let max=Math.max(a,b);
//     min = a<b?a:b;
// }
// function check_prime_number(n){
//         if(n<=1) return false;
//         else if(n==2) return true;
//         else if(n%2==0) return false;
//         for(let i=3;i<Math.sqrt(n);i++){
//             if(n%i==0) return false;
//         }
//         return true;
// }
// function sum_prime_number(n){
//     let sum=0;
//         for(let i=0;i<=n;i++){
//             if(check_prime_number(i)){
//                 sum+=i;
//             }
//         }
//     return sum;
// }
function kiem_tra_songuyento(n){
    if(n==1) return false;
    else if(n==2) return true;
    let count=0;
    for(let i=1;i<=n;i++){
        if(n%i==0) count++;
    }
    if(count==2) return true;
    return false;
}
function tong_songuyento(n){
    let sum=0;
    for(let i=1;i<=n;i++){
        if(kiem_tra_songuyento(i)) sum+=i;
    }
    return sum;
}
function sum_9(k){
    let sum=0;
    for(let i=1;i<=k;i++){
        if(k%i==0) sum+=i;
    }
    return sum;
}
function sum_10(k){
    let sum=0;
    for(let i=1;i<k;i++){
        if(k%i==0) sum+=i;
    }
    return sum==k;
}
function print(){
    for(let i=1;i<=1000;i++){
        if(sum_10(i)) console.log(i);
    }
}

//console.log(sum_9(6));
// console.log(kiem_tra_songuyento(5));
// console.log(tong_songuyento(17));
// console.log(sum_10(12));
// console.log(Math.floor(Math.random(3)));
