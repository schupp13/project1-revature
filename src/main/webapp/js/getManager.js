let pendingAmount = 0;
let deniedAmount = 0;
let approvedAmmount = 0;

// getting user information
fetch("/project-1/getUser.json")
  .then((response) => {
    return response.json();
  })
  .then((data) => {
    createUser(data);
  });

// NEED TO CHANGE/ADD

// getting all reimbursments for the user
fetch("/project-1/getAllReimb.json?")
  .then((response) => {
    return response.json();
  })
  .then((data) => {
    buildTable(data);
  });

document.getElementById("logout").addEventListener("click", (e) => {
  fetch("/project-1/logout.view")
    .then((response) => {
      return response.json();
    })
    .then((data) => {
      console.log(data);
    });
});

let createUser = (data) => {
  document.getElementById("fullName").innerHTML =
    data.firstName + " " + data.lastName;
  document.getElementById("userId").innerHTML = "Id: " + data.id;
  document.getElementById("username").innerHTML = "Username: " + data.username;
  document.getElementById("userEmail").innerHTML = "Email: " + data.email;
  document.getElementById("userRole").innerHTML =
    data.role == 1 ? "Role: Employee" : "Role: Manager";
  // this is for the hidden field in the form
  document.getElementById("author").setAttribute("value", data.id);
};

let buildFormPeople = (data) => {
  let optionHandle = document.getElementById("userOptions");
  for (let option of data) {
    let optionNode = document.createElement("option");
    optionNode.innerHTML =
      option.firstName + " " + option.lastName + " (" + option.id + ")";
    optionNode.setAttribute("value", option.id);
    optionHandle.appendChild(optionNode);
  }
};

let buildTable = (data) => {
  let tableHandle = document.getElementById("userTableBody");
  for (let row of data) {
    console.log(row);
    let rowHandle = document.createElement("tr");
    row.status == "Pending" &&
      rowHandle.setAttribute("class", "table-dark searhEmp");
    row.status == "Denied" &&
      rowHandle.setAttribute("class", "table-danger searhEmp");
    row.status == "Approved" &&
      rowHandle.setAttribute("class", "table-success searhEmp");

    rowHandle.setAttribute("data-id", row.authorId);
    tableHandle.appendChild(rowHandle);

    let idNode = document.createElement("td");
    idNode.innerHTML = row.id;
    rowHandle.appendChild(idNode);

    let authorNode = document.createElement("td");
    authorNode.innerHTML = row.author;
    rowHandle.appendChild(authorNode);

    let statusNode = document.createElement("td");
    statusNode.innerHTML = row.status;
    rowHandle.appendChild(statusNode);

    let amountNode = document.createElement("td");
    amountNode.innerHTML = row.amount;
    rowHandle.appendChild(amountNode);

    let typeNode = document.createElement("td");
    typeNode.innerHTML = row.type;
    rowHandle.appendChild(typeNode);

    let submittedNode = document.createElement("td");
    submittedNode.innerHTML =
      row.submitted != null ? row.submitted.slice(0, 10) : "N/A";
    rowHandle.appendChild(submittedNode);

    let resolvedNode = document.createElement("td");
    resolvedNode.innerHTML =
      row.resolved != null ? row.resolved.slice(0, 10) : "N/A";
    rowHandle.appendChild(resolvedNode);

    let descriptionNode = document.createElement("td");
    descriptionNode.innerHTML = row.description;
    rowHandle.appendChild(descriptionNode);

    let resolverNode = document.createElement("td");
    resolverNode.innerHTML = row.resolver != null ? row.resolver : "N/A";
    rowHandle.appendChild(resolverNode);

    let approveNode = document.createElement("td");
    approveNode.innerHTML = `<form action="/project-1/approveById.json" method="Post"><input type="hidden" name="id" value=${row.id}></input><input type="hidden" name="status" value='3'><button ty                    pe="submit" class="btn">
    <i class='fas fas fa-check-square  text-success fa-2x' ></i>
</button></form>`;
    rowHandle.appendChild(approveNode);

    let removeNode = document.createElement("td");
    removeNode.innerHTML = `<form action="/project-1/denyById.json" method="Post"><input type="hidden" name="id" value=${row.id}></input><input type="hidden" name="status" value='2'><button type="submit" class="btn">
    <i class='fas fa-window-close  text-danger fa-2x' ></i>
</button></form>`;
    rowHandle.appendChild(removeNode);
    if (row.status == "Pending") {
      pendingAmount += row.amount;
    } else if (row.status == "Approved") {
      approvedAmmount += row.amount;
    } else {
      deniedAmount += row.amount;
    }
  }
  new Chart(document.getElementById("myData"), {
    type: "doughnut",
    data: {
      labels: ["Pending", "Approved", "Denied"],
      datasets: [
        {
          label: "Population (millions)",
          backgroundColor: ["#3e95cd", "#28A745", "#DC3545"],
          data: [pendingAmount, approvedAmmount, deniedAmount],
        },
      ],
    },
    options: {
      title: {
        display: true,
        text: "Amount by status",
        fontColor: "#FFFFFF",
      },
      legend: {
        labels: {
          fontColor: "#FFFFFF",
        },
      },
    },
  });
};

// getting types to fill form options
fetch("/project-1/getUsersList.json")
  .then((response) => {
    return response.json();
  })
  .then((data) => {
    console.log("test");
    console.log(data);
    buildFormPeople(data);
  });

document.getElementById("userOptions").addEventListener("change", (e) => {
  let value = e.target.value;

  let list = document.getElementsByClassName("searhEmp");

  for (let i = 0; i < list.length; i++) {
    list[i].style.display = "table-row";
    if (value != "all") {
      console.log(list[i].attributes["data-id"].value);
      if (list[i].attributes["data-id"].value != value) {
        list[i].style.display = "none";
      }
    }
  }
});
