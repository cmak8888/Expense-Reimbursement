window.onload = function() {
    document.getElementById("submitTicket").addEventListener('click', submitTicket);
    document.getElementById("logOutSubmit").addEventListener('click', logout);
    document.getElementById("home").addEventListener('click', goHome);
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

function submitTicket() {
    console.log("Submit Ticket");
    let newTicket = {
        title: document.getElementById(title).value,
        description: document.getElementById(description).value,
        tickettype: document.getElementById(tickettype).value,
        amount: document.getElementById(amount).value
    }
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(xhttp.readyState == 4 && xhttp.status == 200){
            console.log("Success");
            window.location = "http://localhost:8080/ExpReimburse/expr/ticketComplete.html";
        } else {
            console.log("error");
        }
    }
    let submitTicketURL = "http://localhost:8080/ExpReimburse/expr/api/postTicket";
    xhttp.open("POST", submitTicketURL);
    xhttp.send(JSON.stringify(newTicket));
}

function logout() {
    console.log("Logout");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(xhttp.readyState == 4 && xhttp.status == 200) {
            window.location = "login.html";
        }

    }

    let logoutURL = "http://localhost:8080/ExpReimburse/expr/api/logout";
    // let logoutURL = endPoints[getLogout];

    xhttp.open("POST", logoutURL);
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