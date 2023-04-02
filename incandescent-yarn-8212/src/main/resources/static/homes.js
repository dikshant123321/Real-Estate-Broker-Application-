$(window).on("hashchange", function () {
	if (location.hash.slice(1) == "signup") {
		$(".page").addClass("extend");
		$("#login").removeClass("active");
		$("#signup").addClass("active");
	} else {
		$(".page").removeClass("extend");
		$("#login").addClass("active");
		$("#signup").removeClass("active");
	}
});
$(window).trigger("hashchange");

function validateLoginForm(event) {
	event.preventDefault();
	
	var name = document.getElementById("logName").value;
	var password = document.getElementById("logPassword").value;

	if (name == "" || password == "") {
		document.getElementById("errorMsg").innerHTML = "Please fill the required fields"
		return false;
	}

	else if (password.length < 3) {
		document.getElementById("errorMsg").innerHTML = "Your password must include atleast 3 characters"
		return false;
	}
	else {
		

 	fetch('http://localhost:8088/EstateExplorer/Login', {
     method: 'POST',
     headers: {
       'Content-Type': 'application/json'
     },
     body: JSON.stringify({username: name,password:password})
  	 })
     .then(response => response.json())
    .then(data => {
		if(data.uniquekey!=undefined){
					alert("Successfully logged in");
			 		let dataToSend = { userid:data.userId,key:data.uniquekey };
       				let queryParams = new URLSearchParams(dataToSend).toString(); // convert object to URL string
					if(data.role=="Broker") window.open("http://localhost:8088/swagger-ui/index.html?" + queryParams); // navigate to destination page
			       	else window.open("http://localhost:8088/swagger-ui/index.html?" + queryParams); // navigate to destination page
			       	
		}
		else{
			alert(data.message);
		}

     })
     .catch(error => {
       console.error(error);
     });
     

 		


	}
}
function validateSignupForm(event) {
	event.preventDefault();
	var username = document.getElementById("signUsername").value;
	var mail = document.getElementById("signEmail").value;
	var name = document.getElementById("signName").value;
	var mobile = document.getElementById("signMobile").value;
	var city = document.getElementById("signcity").value;
	var password = document.getElementById("signPassword").value;
	var role = document.getElementById("roletype").value;

	if (mail == "" || name == "" || password == ""||username==""||mobile==""||city=="") {
		document.getElementById("errorMsg").innerHTML = "Please fill the required fields"
		return false;
	}

	else if (password.length < 3) {
		document.getElementById("errorMsg").innerHTML = "Your password must include atleast 3 characters"
		return false;
	}
	else {
		if(role=="Broker"){
			fetch('http://localhost:8088/brokers/signup', {
		     method: 'POST',
		     headers: {
		       'Content-Type': 'application/json'
		     },
		     body: JSON.stringify({
			  	username: username,
			 	email: mail,
			  	mobile: mobile,
			 	role: role,
			  	city: city,
			  	password: password,
			  	brokerName: name
					})
		  	 })
		     .then(response => response.json())
		    .then(data => {
				if(data.status==undefined){
					alert("hello "+data.username+" userId="+data.userId+"  Wellcome to EstateExplorer "+"\n"+"Sigup Scucessfull");
				}
				else{
					alert(data.message);
				}
		
		     })
		     .catch(error => {
		       console.error(error);
		     });
		}
		else{
			fetch('http://localhost:8088/customers/signup', {
		     method: 'POST',
		     headers: {
		       'Content-Type': 'application/json'
		     },
		     body: JSON.stringify({
			  	username: username,
			 	email: mail,
			  	mobile: mobile,
			 	role: role,
			  	city: city,
			  	password: password,
			  	customerName: name
					})
		  	 })
		     .then(response => response.json())
		    .then(data => {
				if(data.status==undefined){
					alert("hello "+data.username+" userId="+data.userId+"  Wellcome to EstateExplorer "+"\n"+"Sigup Scucessfull");
				}
				else{
					alert(data.message);
				}
		
		     })
		     .catch(error => {
		       alert(error);
		     });
			
		}
		
		return true;
	}
}
//async function postData(url = 'http://localhost:8088/EstateExplorer/Login', data = {username: "string",password: "string"}) {
 // const response = await fetch(url, {
 //   method: 'POST',
 //   headers: {
 //     'Content-Type': 'application/json'
 //   },
 //   body: JSON.stringify(data)
 // });
 // return response.json();
//}