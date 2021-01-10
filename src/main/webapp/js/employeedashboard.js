/**
 * 
 */
/**
 * 
 */
var endPoints = {}

window.onload = function() {
    getUser();
    // document.getElementById("logOutSubmit").addEventListener('click', logout);
    document.getElementById("submitTicket").addEventListener('click', createTicket);
    document.getElementById("viewTickets").addEventListener('click', viewTickets);
    document.getElementById("logOutSubmit").addEventListener('click', logout);
    document.getElementById("home").addEventListener('click', goHome)
    // document.addEventListener("DOMContentLoaded", getUser)
    // document.getElementById().addEventListener('click');

}

function DomManipulation(data) {
    let user = data.name;
    console.log(data);
    document.getElementById("name").innerHTML = user;
}

function logout() {
    console.log("Logout");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        // document.location.reload();
        xhttp.onreadystatechange = function() {
            if(xhttp.readyState == 4 && xhttp.status == 200){
                // document.location.reload();
                console.log("Success");
                window.location = "http://localhost:8080/ExpReimburse/expr/logout";
            } else {
                console.log("error");
            }
        }
    }

    let logoutURL = "http://localhost:8080/ExpReimburse/expr/api/logout";
    // let logoutURL = endPoints[getLogout];

    xhttp.open("GET", logoutURL);
    xhttp.send();
}

function getUser() {
   fetch("http://localhost:8080/ExpReimburse/expr/api/getUser")
   .then(resp => resp.json()).then(data => DomManipulation(data)).catch(alert);
    // let logoutURL = endPoints[getLogout];
}

function createTicket() {
    console.log("createTicket");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(xhttp.readyState == 4 && xhttp.status == 200){
            window.location="http://localhost:8080/ExpReimburse/expr/CreateTicket";
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

function goHome() {
    console.log("Login");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(xhttp.readyState == 4 && xhttp.status == 200){
            console.log("Success");
            window.location = "http://localhost:8080/ExpReimburse/expr/home";
        } else {
            console.log("error");
        }
    }
    let homeURL = "http://localhost:8080/ExpReimburse/expr/home";
    //let loginURL = endPoints[postLogin];
    xhttp.open("GET", homeURL);
    xhttp.send();
}