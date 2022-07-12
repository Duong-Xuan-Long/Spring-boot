const grades = [
    {name: 'John', grade: 8, sex: 'M'},
    {name: 'Sarah', grade: 12, sex: 'F'},
    {name: 'Bob', grade: 16, sex: 'M'},
    {name: 'Johnny', grade: 2, sex: 'M'},
    {name: 'Ethan', grade: 4, sex: 'M'},
    {name: 'Paula', grade: 18, sex: 'F'},
    {name: 'Donald', grade: 5, sex: 'M'},
    {name: 'Jennifer', grade: 13, sex: 'F'},
    {name: 'Courtney', grade: 15, sex: 'F'},
    {name: 'Jane', grade: 9, sex: 'F'}
   ]
   function average_grade(grades){
    let sum=0;
        let n=grades.length;
        grades.forEach(grade =>{
            sum+=grades.grade;
        });
        return Math.round(sum/n);
   }
   function average_male_grade(grades){
    let sum=0;
    let count=0;
        for(let i=0;i<grades.length;i++){
            if(grades[i].sex=='M'){
                sum+=grades[i].grade;
                count++;
            }
        }
        return Math.round(sum/count);
   }
//    console.log(B_2(grades));
function average_female_grade(grades){
    let sum=0;
    let count=0;
        for(let i=0;i<grades.length;i++){
            if(grades[i].sex=='F'){
                sum+=grades[i].grade;
                count++;
            }
        }
        return Math.round(sum/count);
   }
function top_grade_male(grades){
    let max=0;
    let top_male=null;
    for(let i=0;i<grades.length;i++){
        if(grades[i].sex=='M'&&grades[i].grade>max){
            max=grades[i].grade;
            top_male=grades[i];
        }
    }
    return top_male;
}
// console.log(top_grade_male(grades));
function top_grade_female(grades){
    let max=0;
    let top_female=null;
    for(let i=0;i<grades.length;i++){
        if(grades[i].sex=='F'&&grades[i].grade>max){
            max=grades[i].grade;
            top_female=grades[i];
        }
    }
    return top_female;
}
function bottom_grade_male(grades){
    grades.sort((a,b)=>a.grade-b.grade);
    for(let i=0;i<grades.length;i++){
        if(grades[i].sex=='M') return grades[i];
    }
}
// console.log(bottom_grade_male(grades));
function bottom_grade_female(grades){
    grades.sort((a,b)=>a.grade-b.grade);
    for(let i=0;i<grades.length;i++){
        if(grades[i].sex=='F') return grades[i];
    }
}
function top_of_class(grades){
    grades.sort((a,b)=>b.grade-a.grade);
    return grades[0];
}
function bottom_of_class(grades){
    grades.sort((a,b)=>b.grade-a.grade);
    return grades[grades.length-1];
}
function print_list_of_females(grades){
    for(let i=0;i<grades.length;i++){
        if(grades[i].sex=='F'){
            console.log(grades[i]);
        }
    }
}
function sort_according_to_name(grades){
    grades.sort((a,b)=>a.name.localeCompare(b.name));
    return grades;
 }
// console.log(sort_according_to_name(grades));
function sort_according_to_grade(grades){
    grades.sort((a,b)=>b.grade-a.grade);
    return grades;
}
function find_female_with_starting_character(grades){
    for(let i=0;i<grades.length;i++){
        if(grades[i].sex='F'&&grades[i].name.charAt(0)=='J'){
            console.log(grades[i]);
        }
    }
}
// find_female_with_starting_character(grades);
function top_five_of_class(grades){
    grades.sort((a,b)=>b.grade-a.grade);
    let count=0;
    for(let i=0;i<grades.length;i++){
        if(count==5) break;
        console.log(grades[i]);
        count++;
    }
}
top_five_of_class(grades);