let tickets = {}
let filter = {
  approved: false,
  rejected: false,
  ticket_type: "all"
    
}
let ticket_table = document.querySelector('#ticketList');
window.onload = function(){
    getTickets();
    document.getElementById("approved").addEventListener('click', approvedTickets);
}

function DOMManipulation(data) {
  for(let i = 0; i < data.length; i++){
    let tr = document.createElement("tr"); 
    tr.innerHTML =`
        <td>${data[i].ticketid}</td>
        <td>${data[i].title}</th>
        <td>${data[i].name}</td>
        <td>${data[i].ticketType}</td>
        <td>${data[i].approved}</td>
        <td>$${data[i].amount}</td>
        <td>$${data[i].timestamp}</td>
    `;
    tr.setAttribute(onclick, viewTicket($data[i].ticketid))
    ticket_table.appendChild(tr); 
}
}

function viewTicket(id) {
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
  xhttp.open("GET", viewTicketURL + "?ticketid=" + id);
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

function approvedTickets() {
    
}

function getTickets() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(xhttp.status == 200 && xhttp.readyState == 4) {
            
        }
    }

    let logoutURL = "http://localhost:8080/ExpReimburse/expr/api/viewTickets";
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