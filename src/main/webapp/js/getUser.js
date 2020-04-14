console.log("hello");

fetch("http://localhost:8080/project-1/getUser.json")
  .then((response) => {
    return response.json();
  })
  .then((data) => {
    updateDom(data);
  });

function updateDom(data) {
  console.log(data);
  document.getElementById("fullName").innerHTML =
    data.firstName + " " + data.lastName;

  document.getElementById("userId").innerHTML = "Id: " + data.id;
  document.getElementById("username").innerHTML = "Username: " + data.username;
  document.getElementById("userEmail").innerHTML = "Email: " + data.email;

  document.getElementById("userRole").innerHTML =
    data.role == 1 ? "Role: Employee" : "Role: Manager";

  // this is for the hidden field in the form
  document.getElementById("author").setAttribute("value", data.id);
}

fetch("http://localhost:8080/project-1/getTypes.json")
  .then((response) => {
    return response.json();
  })
  .then((data) => {
    console.log(data);

    let optionHandle = document.getElementById("typeOptions");
    console.log(optionHandle);
    for (let option of data) {
      console.log(option);
      let optionNode = document.createElement("option");
      optionNode.innerHTML = option.type;
      optionNode.setAttribute("value", option.id);
      optionHandle.appendChild(optionNode);
    }
  });
