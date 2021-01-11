
window.onload = function(){
    getTicket();
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
            data = xhttp.responseText;
            DOMManipulation(data);
        }
    }

    let logoutURL = "http://localhost:8080/ExpReimburse/expr/api/getTicket";
    // let logoutURL = endPoints[getLogout];

    xhttp.open("GET", logoutURL);
    xhttp.send();
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