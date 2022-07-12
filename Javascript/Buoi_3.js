// function delay(time) {
//     return new Promise(resolve => setTimeout(resolve, time));
//   }
// function count_down(day1,hour1,minute1,second1){
//     let day=day1;
//     let hour=hour1;
//     let minute=minute1;
//     let second=second1;
//     if(second==-1) {
//         minute=minute-1;
//         second=59;
//     };
//     if(minute==-1){
//         hour=hour-1;
//         minute=59;
//     }
//     if(hour==-1){
//         day=day-1;
//         hour=24;
//     }
//     console.log("days"+" "+day+" "+"hours"+" "+hour+" "+"minutes"+" "+minute+" "+"seconds"+" "+second);
//     delay(1000).then(() =>count_down(day,hour,minute,second-1));
// }
// count_down(13,10,5,5);


// let k=[10,2,3,4,5,6,7,8,9];
// function findmax(k){
//     k.sort();
//     return k[k.length-1];
// }
// console.log(findmax(k));
// function findmin(k){
//     k.sort();
//     return k[0];
// }
// function change(k){
//     let v=[];
//     for(let i=0;i<k.length;i++){
//         v[i]=k[i]%2;
//     }
//     return v;
// }
// function copy(str){
//     let arr=[];
//     arr[0]=str;
//     for(let i=1;i<10;i++){
//         arr[i]=arr[i-1]+str;
//     }
//     return arr[9];
// }
// function copy2(str){
//     let arr=[];
//     arr[0]=str;
//     for(let i=1;i<10;i++){
//         arr[i]=arr[i-1]+"-"+str;
//     }
//     return arr[9];
// }
// function Bai_4_2(str){
//     let arr=[];
//     while(arr.length!=10){
//         arr.push(str);
//     }
//     let k="";
//     for(let i=0;i<arr.length;i++){
//         k+=arr[i];
//     }
//     return k;
// }
// function rgb(){
//     return "rgb"+"("+Math.floor(Math.random()*256)+","+Math.floor(Math.random()*256)+","+Math.floor(Math.random()*256)+")";
// }
// console.log(rgb());
// function rgb_2(){
//    let rgb={red:Math.floor(Math.random()*256),green:Math.floor(Math.random()*256),blue:Math.floor(Math.random()*256)};
//    return `rgb(${rgb.red},${rgb.green},${rgb.blue})`;
// }
// console.log(rgb_2());

let products = [
    {
        name: "Iphone 13 Pro Max", // Tên sản phẩm
        price: 3000000, // Giá mỗi sản phẩm
        brand: "Apple", // Thương hiệu
        count: 2, // Số lượng sản phẩm trong giỏ hàng
    },
    {
        name: "Samsung Galaxy Z Fold3",
        price: 41000000,
        brand: "Samsung",
        count: 1,
    },
    {
        name: "IPhone 11",
        price: 15500000,
        brand: "Apple",
        count: 1,
    },
    {
        name: "OPPO Find X3 Pro",
        price: 19500000,
        brand: "OPPO",
        count: 3,
    },
]
function print_1(products) {
    for (let i = 0; i < products.length; i++) {
        console.log(products[i].name + products[i].price + products[i].brand + products[i].count);
    }
}
function sum(products) {
    for (let i = 0; i < products.length; i++) {
        products[i].sum_price = products[i].price * products[i].count;
    }
}
function print_2(products) {
    for (let i = 0; i < products.length; i++) {
        console.log(products[i].name + " " + products[i].price + " " + products[i].brand + " " + products[i].count + " " + products[i].sum_price);
    }
}
function find_according_to_brand(products) {
    for (let i = 0; i < products.length; i++) {
        if (products[i].brand == "Apple") {
            print_1(products[i]);
        }
    }
}
function find_according_to_price(products) {
    for (let i = 0; i < products.length; i++) {
        if (products[i].price > 20000000) {
            console.log(products[i].name + " " + products[i].price + " " + products[i].brand + " " + products[i].count);
        }
    }
}
function find_pro(products) {
    for (let i = 0; i < products.length; i++) {
        if ((products[i].name).includes("Pro")) {
            console.log(products[i].name + " " + products[i].price + " " + products[i].brand + " " + products[i].count);
        }
    }
}
function add(products) {
    let k = {
        name: "f",
        price: 19,
        brand: "f",
        count: 1,
    };
    products.push(k);
}
function remove(products) {
    for (let i = 0; i < products.length; i++) {
        if (products[i].name.includes("Samsung")) {
            products.splice(i, 1);
        }
    }
}
function sort1(products) {
    products.sort((a, b) => b.price - a.price);
}
function sort2(products) {
    products.sort((a, b) => a.price - b.price);
}
function take_random(products) {
    let n = products.length - 1;
    let k = Math.floor(Math.random() * n);
    let q = Math.floor(Math.random() * n);
    return products[k].name + "\n" + products[q].name;
}

