let userTableLink = document.getElementById("nav-home-tab");
let formNewuser = document.getElementById("formNewUser");
let role = document.getElementById("newroles");
let xhr = new XMLHttpRequest();
let newUserBtn = document.getElementById("newUserButton")
let userNavLink = document.getElementById('user-nav-link')
let authUserInfo = document.getElementById('authUserInfo')
let headerLeft = document.getElementById('header-left')

let promise1 = new Promise(function (resolve, reject) {
    fetch("http://localhost:8080/rest/users/authentication")
        .then((response) => response.json())
        .then((json) => {
            if (json) {
                let data = json
                resolve(data, console.log('data prishla'))
            } else {
                reject(console.log('data ne prishla'))
            }
        })
})
promise1.then((user)=>{
    let userName = document.createElement('h5')
    userName.textContent = user.username + " with roles: " + user.stringRoles
    headerLeft.appendChild(userName);
})

userNavLink.addEventListener('click', function () {

    promise1.then((user) => {
        authUserInfo.firstChild.remove()
        let userInfo = document.createElement('tr')
        let userid = document.createElement('td')
        let username = document.createElement('td')
        let userlast = document.createElement('td')
        let useremail = document.createElement('td')
        let userroles = document.createElement('td')
        userid.textContent = user.id
        username.textContent = user.username
        userlast.textContent = user.lastName
        useremail.textContent = user.email
        userroles.textContent = user.stringRoles
        userInfo.appendChild(userid)
        userInfo.appendChild(username)
        userInfo.appendChild(userlast)
        userInfo.appendChild(useremail)
        userInfo.appendChild(userroles)

        authUserInfo.appendChild(userInfo)
    })

})


newUserBtn.addEventListener('click',async function (event) {
    event.preventDefault()
    let roles = []
    let selected = Array.from(role.options)
        .filter(option => option.selected)
        .map(option => option.value);
    for (let i = 0; i < selected.length; i++) {
        if (selected[i] === "1") {
            roles.push({
                id: "1",
                name: "ROLE_ADMIN",
                authority: "ROLE_ADMIN"
            })
        }
        if (selected[i] === "2") {
            roles.push({
                id: "2",
                name: "ROLE_USER",
                authority: "ROLE_USER"
            })
        }
    }
    let json = {
        username: formNewuser.username.value,
        lastName: formNewuser.lastname.value,
        email: formNewuser.email.value,
        password: formNewuser.password.value,
        roles: roles
    }
    fetch("http://localhost:8080/rest/users",
        {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: JSON.stringify(json)
        }).then(
        response => response.text().then(html => console.log(html))
    )
    console.log(formNewuser.username.value, formNewuser.lastname.value,
        formNewuser.email.value,
        formNewuser.password.value,
        formNewuser.roles)
    console.log(json)
    console.log(roles)
    console.log(JSON.stringify(json))
})
let table = async function () {
    let promise = new Promise(function (resolve, reject) {
            fetch("http://localhost:8080/rest/users/")
                .then((response) => response.json())
                .then((json) => {
                        if (json) {
                            let data = json
                            resolve(data, console.log('data prishla'))
                        } else {
                            reject(console.log('data ne prishla'))
                        }
                    }
                )
        }
    )
    if (userTableLink.className === "nav-link active") {
        promise.then((user) => {
            user.forEach(user => {
                let usersBody = document.getElementById('users-body')
                let userInfo = document.createElement('tr')
                let userid = document.createElement('td')
                let username = document.createElement('td')
                let userlast = document.createElement('td')
                let useremail = document.createElement('td')
                let userroles = document.createElement('td')
                let userbtnI = document.createElement('td')
                let userbtnD = document.createElement('td')
                let buttonInfo = document.createElement('button')
                buttonInfo.className = "btn btn-info"
                buttonInfo.setAttribute("data-bs-toggle", "modal")
                buttonInfo.setAttribute("data-bs-target", "#modalEdit")
                buttonInfo.setAttribute("id", "idEditUserBut")
                buttonInfo.setAttribute("value", user.id)
                buttonInfo.textContent = "Edit"
                userbtnI.appendChild(buttonInfo)
                let buttonDelite = document.createElement('button')
                buttonDelite.className = "btn btn-danger"
                buttonDelite.setAttribute("data-bs-toggle", "modal")
                buttonDelite.setAttribute("data-bs-target", "#modalDelete")
                buttonDelite.setAttribute("id", "idDeleteUserBut")
                buttonDelite.setAttribute("value", user.id)
                buttonDelite.textContent = "Delete"
                userbtnD.appendChild(buttonDelite)
                userid.textContent = user.id
                username.textContent = user.username
                userlast.textContent = user.lastName
                useremail.textContent = user.email
                userroles.textContent = user.stringRoles
                userInfo.appendChild(userid)
                userInfo.appendChild(username)
                userInfo.appendChild(userlast)
                userInfo.appendChild(useremail)
                userInfo.appendChild(userroles)
                userInfo.appendChild(userbtnI)
                userInfo.appendChild(userbtnD)

                usersBody.appendChild(userInfo)


                buttonInfo.addEventListener('click', function () {
                    console.log('click edit but')
                    new Promise(function (resolve, reject) {
                        fetch("http://localhost:8080/rest/users/" + buttonInfo.value)
                            .then((response) => response.json())
                            .then((json) => {
                                    if (json) {
                                        resolve(json, console.log('data prishla'))
                                        let form = document.getElementById("formEditUser");
                                        form.id.value = json.id;
                                        form.username.value = json.username;
                                        form.lastName.value = json.lastName;
                                        form.email.value = json.email;
                                        form.password.value = json.password;
                                        form.addEventListener('submit', function (event) {
                                            event.preventDefault()

                                            let roles = []
                                            let selected = Array.from(form.roles.options)
                                                .filter(option => option.selected)
                                                .map(option => option.value);
                                            for (let i = 0; i < selected.length; i++) {
                                                if (selected[i] === "1") {
                                                    roles.push({
                                                        id: "1",
                                                        name: "ROLE_ADMIN",
                                                        authority: "ROLE_ADMIN"
                                                    })
                                                }
                                                if (selected[i] === "2") {
                                                    roles.push({
                                                        id: "2",
                                                        name: "ROLE_USER",
                                                        authority: "ROLE_USER"
                                                    })
                                                }
                                            }

                                            let edited = {
                                                id: json.id,
                                                username: form.username.value,
                                                lastName: form.lastName.value,
                                                email: form.email.value,
                                                password: form.password.value,
                                                roles: roles
                                            }
                                            userid.textContent = user.id
                                            username.textContent = form.username.value
                                            userlast.textContent = form.lastName.value
                                            useremail.textContent = form.email.value
                                            userroles.textContent = user.stringRoles
                                            console.log(edited)
                                            fetch("http://localhost:8080/rest/users/", {
                                                method: "PUT",
                                                headers: {
                                                    'Content-Type': 'application/json'
                                                    // 'Content-Type': 'application/x-www-form-urlencoded',
                                                },
                                                body: JSON.stringify(edited)
                                            })
                                        })
                                    } else {
                                        reject(console.log('data ne prishla'))
                                    }
                                }
                            )
                    })
                })

                buttonDelite.addEventListener('click', function () {
                    console.log('click delete but')
                    new Promise(function (resolve, reject) {
                        fetch("http://localhost:8080/rest/users/" + buttonDelite.value)
                            .then((response) => response.json())
                            .then((json) => {
                                    if (json) {
                                        resolve(json, console.log('data prishla'))
                                        let formDe = document.getElementById("formDeleteUser");

                                        formDe.id.value = json.id;
                                        formDe.username.value = json.username;
                                        formDe.lastname.value = json.lastName;
                                        formDe.email.value = json.email;

                                        formDe.addEventListener('submit',
                                            function(event){
                                                fetch("http://localhost:8080/rest/users/" + json.id,{
                                                    method: "DELETE"
                                                })
                                                userInfo.remove()
                                                event.preventDefault()
                                            }
                                        )

                                    } else {
                                        reject(console.log('data ne prishla'))
                                    }
                                }
                            )
                    })
                })
            })
        })
    }
}

if (userTableLink.className === "nav-link active") {
    document.getElementById("users-body").remove()
    let tableus = document.getElementById('users_table')
    let newTable = document.createElement('tbody')
    tableus.appendChild(newTable)
    newTable.setAttribute("id", "users-body")
    table()
}
userTableLink.addEventListener('click', function () {
    document.getElementById("users-body").remove()
    let tableus = document.getElementById('users_table')
    let newTable = document.createElement('tbody')
    tableus.appendChild(newTable)
    newTable.setAttribute("id", "users-body")
    table()
    console.log('была вызвана таблица юзеров')
})


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