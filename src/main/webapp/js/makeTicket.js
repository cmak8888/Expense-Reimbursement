window.onload = function() {
    // document.getElementById("logOutSubmit").addEventListener('click', logout);
    document.getElementById("submitTicket").addEventListener('click', submitTicket);
    document.getElementById("home").addEventListener('click', goHome);
    document.getElemendById("logOutSubmit").addEventListener('click', logout)

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


function logout() {
    console.log("Logout");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        document.location.reload();

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