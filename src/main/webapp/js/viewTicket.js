window.onload = function(){
    getTicket();
    getUserType();
}

function DOMManipulation(data) {
  document.getElementById("title").innerHTML = data.title;
  switch(data.ticket_type) {
    case(0):
      document.getElementById("tickettype").innerHTML = "Lodging";
      break;
    case(1):
      document.getElementById("tickettype").innerHTML = "Travel"
      break;
    case(2):
      document.getElementById("tickettype").innerHTML = "Food"
      break;
    case(3):
      document.getElementById("tickettype").innerHTML = "Other"
      break;
  }
  document.getElementById("desc").innerHTML = data.description;
  document.getElementById("timestamp").innerHTML = data.timestamp;
  document.getElementById("amount").innerHTML = data.amount;
}

function DomManipulation2(data){
  if(data.usertype === "Manager") {
    let approveButton = document.createElement("input");
    approveButton.setAttribute("type", "text");
    approveButton.onclick(approveTicket());
    let rejectButton = document.createElement("input");
    rejectButton.setAttribute("type", "text");
    rejectButton.onclick(rejectTicket());
    document.getElementById("buttons").appendChild(approveButton);
    document.getElementById("buttons").appendChild(rejectButton);
  }
}

function approveTicket() {
  let xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if(xhttp.readyState == 4 && xhttp.status == 200){
        // document.location.reload();
        console.log("Success");
        document.getElementById.innerHTML="Successfully Approved.";
        document.location.reload();
    } else {
        console.log("error");
    }
  }
  let approveURL = "http://localhost:8080/ExpReimburse/expr/api/accept";
  // let logoutURL = endPoints[getLogout];

  xhttp.open("PUT", approveURL);
  xhttp.send();
}


function rejectTicket() {
  let xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if(xhttp.readyState == 4 && xhttp.status == 200){
        // document.location.reload();
        console.log("Success");
        document.getElementById.innerHTML="Successfully Rejected.";
        document.location.reload();
    } else {
        console.log("error");
    }
  }
  let approveURL = "http://localhost:8080/ExpReimburse/expr/api/reject";
  // let logoutURL = endPoints[getLogout];

  xhttp.open("PUT", approveURL);
  xhttp.send();
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


function getTicket() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(xhttp.status == 200 && xhttp.readyState == 4) {
            data = JSON.parse(xhttp.responseText);
            DOMManipulation(data);
        }
    }

    let logoutURL = "http://localhost:8080/ExpReimburse/expr/api/getTicket";
    // let logoutURL = endPoints[getLogout];

    xhttp.open("GET", logoutURL);
    xhttp.send();
}

function getUserType() {
  fetch("http://localhost:8080/ExpReimburse/expr/api/getUserType")
   .then(resp => resp.json()).then(data => DomManipulation2(data)).catch(alert)
}

function cellHeaders(tableId) {
    try {
      let thArray = [];
      const table = document.getElementById(tableId);
      const headers = table.getElementsByTagName('th');
      for (let i = 0; i < headers.length; i++) {
        const headingText = headers[i].innerHTML;
        thArray.push(headingText);
      }
      const styleElm = document.createElement('style');
      let styleSheet;
      document.head.appendChild(styleElm);
      styleSheet = styleElm.sheet;
      for (let i = 0; i < thArray.length; i++) {
        styleSheet.insertRule(
          '#' +
            tableId +
            ' td:nth-child(' +
            (i + 1) +
            ')::before {content:"' +
            thArray[i] +
            ': ";}',
          styleSheet.cssRules.length
        );
      }
    } catch (err) {
      console.log('cellHeaders(): ' + err);
    }
  }

  cellHeaders('respTable');