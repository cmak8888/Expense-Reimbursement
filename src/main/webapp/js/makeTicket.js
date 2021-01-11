window.onload = function() {
    document.getElementById("postTicket").addEventListener('click', submitTicket);
    document.getElementById("logOutSubmit").addEventListener('click', logout);
    document.getElementById("home").addEventListener('click', goHome);
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
    let viewTicketURL = "http://localhost:8080/ExpReimburse/expr/ViewTicket";
    //let loginURL = endPoints[postLogin];
    xhttp.open("GET", viewTicketURL);
    xhttp.send();
}

function submitTicket() {
    console.log("Submit Ticket");
    let newTicket = {
        title: document.getElementById('title').value,
        description: document.getElementById('description').value,
        amount: document.getElementById('amount').value
    }
    if(amount < 0) {
        return;
    }
    let ticket_type = document.getElementById('ticket_type').value;
    // console.log( document.getElementById('ticket_type').value);
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(xhttp.readyState == 4 && xhttp.status == 200){
            console.log("Success");
            window.location = "http://localhost:8080/ExpReimburse/expr/TicketComplete";
        } else {
            console.log("error");
        }
    }
    let submitTicketURL = "http://localhost:8080/ExpReimburse/expr/api/postTicket";
    xhttp.open("POST", submitTicketURL + "?" + "ticket_type=" + ticket_type);
    xhttp.send(JSON.stringify(newTicket));
}
function goHome() {
    console.log("Login");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(xhttp.readyState == 4 && xhttp.status == 200){
            console.log("Success");
            let homeURL = "http://localhost:8080/ExpReimburse/expr/";
        } else {
            console.log("error");
        }
    }
    let homeURL = "http://localhost:8080/ExpReimburse/expr/";
    //let loginURL = endPoints[postLogin];
    xhttp.open("GET", homeURL);
    xhttp.send();
}