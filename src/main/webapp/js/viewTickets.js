/**
 * 
 */
let tickets = {}
let filter = {
  approved: true,
  rejected: true,
  ticket_type: "all"
    
}
let usertype = null;
let ticket_table = document.querySelector('#ticketList');
window.onload = function(){
    getTickets();
    getUserType();
}

function DOMManipulation(data) {
  ticket_table.innerHTML = "";
  for(let i = 0; i < data.length; i++){
    let approval = "";
    if (data[i].approved) {
      approval = "Approved";
    } else {
      approval = "Rejected";
    }
    let tr = document.createElement("tr"); 
    tr.innerHTML =`
        <td>${data[i].name}</td>
        <td>${data[i].title}</th>
        <td>${data[i].ticketType}</td>
        <td id="approval">${approval}</td>
        <td>$${data[i].amount}</td>
        <td>${data[i].timeStamp}</td>`;
    if(usertype === "Manager") {
      tr.innerHTML = tr.innerHTML + `<td><img src="/ExpReimburse/images/check.jpg" height="50px" width="50px" onclick="approveTicket(${data[i].id})"/><img src="/ExpReimburse/images/x.png" height="50px" width="50px" onclick="rejectTicket(${data[i].id})"/></td>`;
      }
    // tr.onclick = viewTicket(data[i].ticketid);
    ticket_table.appendChild(tr); 
    // document.location.reload();
}
}

function DomManipulation2(data) {
  usertype = data.usertype;
  if(usertype === "Employee") {
    document.getElementById("approvesection").style.display="none";
  }
}

function viewTicket(id) {
  console.log("Login");
  let xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
      if(xhttp.readyState == 4 && xhttp.status == 200){
          console.log("Success");
          window.location = "http://localhost:8080/ExpReimburse/expr/ViewTicket";
      } else {
          console.log("error");
      }
  }
  let viewTicketURL = "http://localhost:8080/ExpReimburse/expr/ViewTicket";
  //let loginURL = endPoints[postLogin];
  xhttp.open("GET", viewTicketURL + "?ticketid=" + id);
  xhttp.send();
}

function approveTicket(id) {
  let xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if(xhttp.readyState == 4 && xhttp.status == 200){
        // document.location.reload();
        console.log("Success");
        alert("Successfully Approved.");
        document.location.reload();
    } else {
        console.log("error");
    }
  }
  let approveURL = "http://localhost:8080/ExpReimburse/expr/api/accept";
  // let logoutURL = endPoints[getLogout];

  xhttp.open("PUT", approveURL + "?ticketid=" + id);
  xhttp.send();
}

function getUserType() {
  fetch("http://localhost:8080/ExpReimburse/expr/api/getUserType")
   .then(resp => resp.json()).then(data => DomManipulation2(data)).catch(alert)
}

function rejectTicket(id) {
  let xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if(xhttp.readyState == 4 && xhttp.status == 200){
        // document.location.reload();
        console.log("Success");
        alert("Successfully Rejected.");
        document.location.reload();
    } else {
        console.log("error");
    }
  }
  let approveURL = "http://localhost:8080/ExpReimburse/expr/api/reject";
  // let logoutURL = endPoints[getLogout];

  xhttp.open("PUT", approveURL + "?ticketid=" + id);
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
    filter.approved = !filter.approved;
    filterList();
}

function rejectedTickets() {
  filter.rejected = !filter.rejected;
  filterList();
}


function filterList() {
  let data = []
  let data2 = []
  switch(filter.ticket_type) {
    case("Lodging"):
    for(let i = 0; i < tickets.length; i++) {
      if(tickets[i].ticketType === "LODGING") {
        data2.push(tickets[i]);
      }
    }
      break;
      case("Travel"):
    for(let i = 0; i < tickets.length; i++) {
      if(tickets[i].ticketType === "TRAVEL") {
        data2.push(tickets[i]);
      }
    }
      break;
      case("Food"):
    for(let i = 0; i < tickets.length; i++) {
      if(tickets[i].ticketType === "FOOD") {
        data2.push(tickets[i]);
      }
    }
      break;
      case("Other"):
    for(let i = 0; i < tickets.length; i++) {
      if(tickets[i].ticketType === "OTHER") {
        data2.push(tickets[i]);
      }
    }
      break;
    default:
     data2 = tickets;
  }
  if(filter.approved && filter.rejected) {
    data = data2;
  } else if(filter.approved) {
    for(let i = 0; i < data2.length; i++) {
      if(data2[i].approved == true) {
        data.push(tickets[i]);
      }
    }
  } else if(filter.rejected) {
    for(let i = 0; i < data2.length; i++) {
      if(data2[i].approved == false) {
        data.push(tickets[i]);
      }
   }
  } 
  DOMManipulation(data);
}

function changetickettype() {
  filter.ticket_type = document.getElementById("ticket_type").value;
  filterList();
}

function getTickets() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(xhttp.status == 200 && xhttp.readyState == 4) {
            let data = JSON.parse(xhttp.responseText);
            tickets = data;
            filterList();
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