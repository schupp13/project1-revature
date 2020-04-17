// getting user information
fetch("/project-1/getUser.json")
  .then((response) => {
    return response.json();
  })
  .then((data) => {
    createUser(data);
  });

// getting types to fill form options
fetch("/project-1/getTypes.json")
  .then((response) => {
    return response.json();
  })
  .then((data) => {
    console.log(data);
    buildFormTypes(data);
  });

// getting all reimbursments for the user
fetch("/project-1/getUserReimb.json")
  .then((response) => {
    return response.json();
  })
  .then((data) => {
    buildTable(data);
  });

document.getElementById("logout").addEventListener("click", (e) => {
  fetch("/project-1/logout.view", { method: "Post" })
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

let buildFormTypes = (data) => {
  let optionHandle = document.getElementById("typeOptions");
  for (let option of data) {
    let optionNode = document.createElement("option");
    optionNode.innerHTML = option.type;
    optionNode.setAttribute("value", option.id);
    optionHandle.appendChild(optionNode);
  }
};

let buildTable = (data) => {
  let tableHandle = document.getElementById("userTableBody");
  for (let row of data) {
    console.log(row);
    let rowHandle = document.createElement("tr");
    tableHandle.appendChild(rowHandle);
    row.status == "Pending" && rowHandle.setAttribute("class", "table-dark");
    row.status == "Denied" && rowHandle.setAttribute("class", "table-danger");
    row.status == "Approved" &&
      rowHandle.setAttribute("class", "table-success");

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
  }
};
