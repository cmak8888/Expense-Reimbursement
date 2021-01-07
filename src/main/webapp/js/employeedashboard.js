/**
 * 
 */
/**
 * 
 */
var endPoints = {}

window.onload = function() {
    // document.getElementById("logOutSubmit").addEventListener('click', logout);
    document.getElementById("submitTicket").addEventListener('click', createTicket);
    document.getElementById("viewTickets").addEventListener('click', viewTickets);
    document.getElementById("logOutSubmit").addEventListener('click', logout);
    document.getElementById("home").addEventListener('click', goHome)
    // document.addEventListener("DOMContentLoaded", getUser)
    // document.getElementById().addEventListener('click');

}

function logout() {
    console.log("Logout");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        // document.location.reload();

    }

    let logoutURL = "http://localhost:8080/ExpReimburse/expr/api/logout";
    // let logoutURL = endPoints[getLogout];

    xhttp.open("GET", logoutURL);
    xhttp.send();
}

function getUser() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(xhttp.readyState == 4 && xhttp.status == 200){
            console.log("Success");
            let data = JSON.parse(xhttp.responseText);
            document.getElementById("name").innerHTML=data.get("username");
        } else {
            console.log("error");
        }
    }

    let newUserURL = "http://localhost:8080/ExpReimburse/expr/api/getUser";
    // let logoutURL = endPoints[getLogout];

    xhttp.open("GET", newUserURL);
    xhttp.send();
}

function createTicket() {
    console.log("createTicket");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(xhttp.readyState == 4 && xhttp.status == 200){
            // document.location.reload();
            console.log("Success");
        } else {
            console.log("error");
        }
    }

    let newTicketURL = "http://localhost:8080/ExpReimburse/expr/CreateTicket";
    // let logoutURL = endPoints[getLogout];

    xhttp.open("GET", newTicketURL);
    xhttp.send();
}

function viewTickets() {
    console.log("Login");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(xhttp.readyState == 4 && xhttp.status == 200){
            console.log("Success");
        } else {
            console.log("error");
        }
    }
    let viewTicketURL = "http://localhost:8080/ExpReimburse/expr/ViewTickets";
    //let loginURL = endPoints[postLogin];
    xhttp.open("GET", viewTicketURL);
    xhttp.send();
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
    let homeURL = "http://localhost:8080/ExpReimburse/expr/home";
    //let loginURL = endPoints[postLogin];
    xhttp.open("GET", homeURL);
    xhttp.send();
}