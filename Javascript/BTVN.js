function bua_keo_bao(p){
    let arr=["bua","keo","bao"];
    let k=Math.floor(Math.random()*3);
    if(p==arr[k]) return "tie";
    if(p==bao){
        if(arr[k]=="keo") return "human lose";
        else if(arr[k]=="bua") return "human win";
        else return "tie";
    } 
    else if(p=="bua"){
        if(arr[k]=="bao") return "human lose";
        else if(arr[k]=="keo") return "human win";
        else return "tie";
    }
    else if(p=="keo"){
        if(arr[k]=="bua") return "human lose";
        else if(arr[k]=="bao") return "human win";
        else return "tie";
    }
}
function bai1_factorial(n){
    if(n==1) return 1;
    return n*bai1_factorial(n-1);
}
function bai2_reverse(str){
    if(str.length==0) return "";
    return str.charAt(str.length-1)+bai2_reverse(str.substring(0,str.length-1));
}
function bai3_message(str){
    switch(str){
        case "VN":console.log("Xin Chào");break;
        case "EN":console.log("Hello");break;
        case "FR":console.log("Bonjour");break;
        default:console.log("Nhập lại");
    }
}
function bai4_change(str){
    if(str.length<15){
        "phải chuyền vào chuỗi 15 kí tự";
    }
    return str.substring(0,10)+"...";
}
