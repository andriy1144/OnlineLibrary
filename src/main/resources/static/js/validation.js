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
    let smt = el.inputToValidate.value;

    if(smt === ""){
        alert("--Введіть щось у поле добавлення/пошуку спершу ніж відправляти--")
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

// Validation of Authentication section
function validateLoginForm(el){
    const email = el.email.value;
    const pass = el.password.value;

    if(email === "" || pass === ""){
        alert("--Не всі поля вводу заповнені--")
        return false;
    }
    return true;
}

function validateRegistrationForm(el){
    const userName = el.name.value;
    const email = el.email.value;
    const phoneNumber = el.phoneNumber.value;

    const password = el.password.value;
    const passwordReapeat = el.passwordReapeat.value;

    if(userName === ""
        || email === ""
        || phoneNumber === ""
        || password === ""
        || passwordReapeat === ""){
        alert("-- Будь-ласка заповніть УСІ поля-- ")
        return false;
    }
    const checkOnSpaces = password.split(" ");

    const checlOnSymbols = password.split("!");

    const checlOnSymbols2 = password.split("^");

    if(password.trim().length < 8 || checlOnSymbols.length > 1 || checlOnSymbols2.length > 1){
        alert("-- Пароль повинен містити 8 і більше символів \n" +
            "А також не повинен містити таких знаків: !,^ \n" +
            "Та пробілів");
        return false;
    }

    if(password !== passwordReapeat){
        alert("-- Паролі не співпадають --");
        return false;
    }

    return true;
}