const validMessageBlock = document.getElementById("validMessage");

function validateForm(el) {
    let title = el.title.value;
    let desc = el.description.value;

    let fail = ""

    console.log(title + " " + desc);

    if(title === "" && desc === ""){
        fail = "--Заповніть усі поля--";
    }else if(title ===""){
        fail = "--Заповніть поле із назвою 'Закоголовок'--";
    }else if(desc === ""){
        fail = "--Заповніть поле із назвою 'Опис'--"
    }

    if(fail !== ""){
        validMessageBlock.innerHTML= "<p style='font-size: 25px;color: red'>" + fail + "</p>"
        return false;
    }else{
        let isContinue = confirm("--Дані заповненні! Ви хочете добавити цю книгу?--");
        if(isContinue){
            return true;
        }else{
            return false;
        }
    }
}

function validateInput(el){
    let smt = el.genre.value;

    if(smt === ""){
        alert("--Введіть щось у поле добавлення жанру спершу ніж відправляти--")
        return false;
    }else{
        return true;
    }
}

function validateAddBookForm(el){
    const name = el.name.value;
    const author = el.author.value;
    const descr = el.description.value;
    const feature = el.features.value;

    const genreChekbox = el.genresList;

    let isCheckedSomething = false;
    for(let i = 0; i < genreChekbox.length; i++){
        if(genreChekbox[i].checked === true){
            isCheckedSomething = true
        }
    }

    if(name === "" || author === "" || descr === "" || feature === "" || !isCheckedSomething){
        alert("--Усі поля крім Картинок повині бути заповнені--")
        return false;
    }
    return true;
}