/**
 * 
 */
/**
 * 
 */
var endPoints = {}

window.onload = function() {
    // getEndPoints();
    // document.getElementById("logOutSubmit").addEventListener('click', logout);
    // document.getElementById("logInSubmit").addEventListener('click', login);
    // document.getElementById().addEventListener('click');

}

function logout() {
    console.log("Logout");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        document.location.reload();

    }

    let logoutURL = "http://localhost:8080/ExpReimburse/expr/api/logout";
    // let logoutURL = endPoints[getLogout];

    xhttp.open("GET", logoutURL);
    xhttp.send();
}

function login() {
    console.log("Login");
    let xhttp = new XMLHttpRequest();
    
    
    xhttp.onreadystatechange = function() {
        if(xhttp.readyState == 4 && xhttp.status == 200){
            data = JSON.parse(xhttp.responseText);
            window.location = data.url;
            console.log("Success");
        } else {
            console.log("error");
        }
    }
    let loginURL = "http://localhost:8080/ExpReimburse/expr/api/login";
    //let loginURL = endPoints[postLogin];
    // xhttp.setRequestHeader("Content-type", "application/json");
    console.log(document.getElementById("username").value);
    console.log(document.getElementById("pass").value);
    let data2 = {
        username: document.getElementById("username").value,
        password: document.getElementById("pass").value
    }
    if(!data2.username || !data2.password) {
        console.log("no input");
        return
    }
    console.log(data2.username);
    xhttp.open("POST", loginURL);
    xhttp.send(JSON.stringify(data2));
}

// function getEndPoints() {
//     console.log("Endpoints");
//     xhttp = new XMLHttpRequest();
//     xhttp.onreadystatechange = function() {
//         if(xhttp.readyState == 4 && xhttp.status == 200) {
//             endPoints = JSON.parse(xhttp.responseText);
//         } else {
//             console.log("Error, could not connect to server");
//         }
//     }

//     let URL = "";
//     xhttp.open("GET", URL);
//     xhttp.send();
// }