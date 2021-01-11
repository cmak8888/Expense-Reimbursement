window.onload() = function() {
    document.getElementById("logoutButton").addEventListener('click', function() {
        window.location="http://localhost:8080/ExpReimburse/expr/home";
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            // document.location.reload();
            if(xhttp.readyState == 4 && xhttp.status == 200){
                // document.location.reload();
                console.log("Success");
                window.location = "http://localhost:8080/ExpReimburse/expr/home";
            } else {
                console.log("error");
            }
        }
    
        let logoutURL = "http://localhost:8080/ExpReimburse/expr/home";
        // let logoutURL = endPoints[getLogout];
    
        xhttp.open("GET", logoutURL);
        xhttp.send();
    });

};