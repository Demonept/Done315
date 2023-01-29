
let table = document.getElementById("table_users");
let edit  = document.getElementById("formEdit");
let post = document.getElementById("formNewUser");
let but = document.getElementById("justabutton");

var xhr = new XMLHttpRequest();

// xhr.open('GET', '/http://localhost:8080/rest/users', true);
// xhr.send();
// console.log(xhr)


function post(){
    fetch('http://localhost:8080/rest/users')
        .then()
}

but.addEventListener('click', function(){
    console.log(
        xhr.open('DELETE', '/http://localhost:8080/rest/users/2', true),
        xhr.send()
    )
    console.log('click')
})


async function loadIntoTable(url, table){
    const tableHead = table.querySelector("thead");
    const tableBody = table.querySelector("tbody");
    const response = await fetch(url);
    const data = await response.json();

    console.log(data)
}



function req() {
    fetch('http://localhost:8080/rest/users')
        .then(response => response.json())
        .then(json => {
            json.forEach(post =>{
                let username = post.username
                let lastname= post.lastName
                create(username, lastname)
            })
        });
}