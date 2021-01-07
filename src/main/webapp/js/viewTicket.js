


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