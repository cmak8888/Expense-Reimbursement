window.onload = function() {
    // document.getElementById("logOutSubmit").addEventListener('click', logout);
    document.getElementById("newUser").addEventListener('click', createUser);
    document.getElementById("viewTickets").addEventListener('click', viewTicket)
    document.getElementById("home").addEventListener('click', goHome)

}

function createUser() {
    console.log("Logout");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {

    }

    let newTicketURL = "http://localhost:8080/ExpReimburse/expr/CreateTicket";
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
    let homeURL = "http://localhost:8080/ExpReimburse/expr/";
    //let loginURL = endPoints[postLogin];
    xhttp.open("GET", homeURL);
    xhttp.send();
}