window.onload = function() {
    getUser();
    // document.getElementById("logOutSubmit").addEventListener('click', logout);
    // document.getElementById("newUser").addEventListener('click', createUser);
    document.getElementById("viewTickets").addEventListener('click', viewTicket)
    document.getElementById("home").addEventListener('click', goHome)
    document.getElementById("logOutSubmit").addEventListener('click', logout)
}

function DomManipulation(data) {
    let user = data.name;
    console.log(data);
    document.getElementById("name").innerHTML = user;
}

function getUser() {
    fetch("http://localhost:8080/ExpReimburse/expr/api/getUser")
    .then(resp => resp.json()).then(data => DomManipulation(data)).catch(alert);
     // let logoutURL = endPoints[getLogout];
 }


 function logout() {
    console.log("Logout");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(xhttp.readyState == 4 && xhttp.status == 200){
            // document.location.reload();
            console.log("Success");
            window.location = "http://localhost:8080/ExpReimburse/expr/logout";
        } else {
            console.log("error");
        }
    }

    let logoutURL = "http://localhost:8080/ExpReimburse/expr/api/logout";
    // let logoutURL = endPoints[getLogout];

    xhttp.open("GET", logoutURL);
    xhttp.send();
}

function createUser() {
    console.log("Logout");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {

    }

    let newTicketURL = "http://localhost:8080/ExpReimburse/expr/CreateUser";
    // let logoutURL = endPoints[getLogout];

    xhttp.open("GET", newTicketURL);
    xhttp.send();
}

function viewTicket() {
    console.log("Login");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(xhttp.readyState == 4 && xhttp.status == 200){
            console.log("Success");
            window.location = "http://localhost:8080/ExpReimburse/expr/ViewTickets";
        } else {
            console.log("error");
        }
    }
    let viewTicketURL = "http://localhost:8080/ExpReimburse/expr/ViewTickets";
    //let loginURL = endPoints[postLogin];
    xhttp.open("GET", viewTicketURL);
    xhttp.send();
}

function approveTicket(event) {

}

function rejectTicket(event) {
    
}

function goHome() {
    console.log("Login");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(xhttp.readyState == 4 && xhttp.status == 200){
            console.log("Success");
        } else {
            console.log("error");
        }
    }
    let homeURL = "http://localhost:8080/ExpReimburse/expr/";
    //let loginURL = endPoints[postLogin];
    xhttp.open("GET", homeURL);
    xhttp.send();
}