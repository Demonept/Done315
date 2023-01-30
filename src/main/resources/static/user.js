let authUserInfo = document.getElementById('authUserInfo')
let headerLeft = document.getElementById('header-left')

let promise1 = new Promise(function (resolve, reject) {
    fetch("http://localhost:8080/rest/users/authentication")
        .then((response) => response.json())
        .then((json) => {
            if (json) {
                let data = json
                resolve(data, console.log('data prishla')
                ,console.log(data))
            } else {
                reject(console.log('data ne prishla'))
            }
        })
})
promise1.then((user)=>{
    let userName = document.createElement('h5')
    userName.textContent = user.username + " with roles: " + user.stringRoles
    headerLeft.appendChild(userName);
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