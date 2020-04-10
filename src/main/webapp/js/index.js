console.log("hello");


fetch("http://localhost:8080/project-1/getreimb").then(response =>{
	return response.json();
}).then(data =>{
	let container = document.getElementById("test");
	

	for(let prop in data){
		let p = document.createElement("p");
		var textnode = document.createTextNode(data[prop]);  
		p.appendChild(textnode); 
		container.appendChild(p);
	}
})
