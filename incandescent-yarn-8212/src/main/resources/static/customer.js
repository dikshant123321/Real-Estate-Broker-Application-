let tabs= document.getElementsByClassName("tabcontent");
for(let i=0;i<tabs.length;i++){
    tabs[i].style.display= "none";
}

function openTab(evt, tabName) {
	for(let i=0;i<tabs.length;i++){
    	tabs[i].style.display= "none";
  	}
  	document.getElementById(tabName).style.display = "block";  
    var i, tabcontent, tablinks;
    // Hide all tab content
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
      tabcontent[i].style.display = "none";
    }
    // Deactivate all tab buttons
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
      tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    // Show the selected tab content and activate the corresponding tab button
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
  }
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const uid = urlParams.get('userid');
const ukey = urlParams.get('key');   

function tab1(event){
    event.preventDefault();
	document.getElementById("tab1").style.display = "block";
    const formData = new FormData(event.target);
    alert(formData.get('num')+formData.get('txt')+key+id);

    fetch('broker/update/', {
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
			  	BrokerName: name
					})
		  	 })
		     .then(response => response.json())
		     .then(data => {
				if(data.status==undefined){
					alert("Scucessfull");
				}
				else{
					alert(data.message);
				}
		
		     })
		     .catch(error => {
		       alert(error);
		     });
}
function tab2(event){
    event.preventDefault();



//	let dataToSend = {key:ukey};
//   let queryParams = new URLSearchParams(dataToSend).toString();
//	document.getElementById("tab2").style.display = "block";
	
	console.log(uid,ukey);
    fetch('http://localhost:8088/customers/'+uid+'?Key='+ukey)
		     .then(response => response.json())
		     .then(data => {
				if(data.status==undefined){
					console.log(data);
					document.getElementById("demo").innerHTML = `<pre>${JSON.stringify(data,null,2)}</pre>`;
				}
				else{
					document.getElementById("demo").innerHTML = JSON.parse(data);
				}
		     })
		     .catch(error => {
		       alert(error+"\n"+"error");
		     });
}
function tab3(event){
    event.preventDefault();
//	document.getElementById("tab3").style.display = "block";
    const formData = new FormData(event.target);
	alert(formdata.get("username"));
    fetch('http://localhost:8088/customers/edit?Key='+ukey, {
		     method: 'POST',
		     headers: {
		       'Content-Type': 'application/json'
		     },
		     body: JSON.stringify({
		    	  username:formData.get('username') ,
		    	  email: formData.get('email'),
		    	  mobile: formData.get('mobile'),
		    	  role: "Customer",
		    	  city: formData.get('city'),
		    	  password: formData.get('password'),
		    	  customerName: formData.get('fullname')
					})
		  	 })
		     .then(response => response.json())
		     .then(data => {
				if(data.status==undefined){
					alert("Scucessfull updated");
					document.getElementById("demo").innerHTML = `<pre>${JSON.stringify(data)}</pre>`;
				}
				else{
					alert(data.message);
				}
		
		     })
		     .catch(error => {
		       alert(error);
		     });
}
function tab4(event){
    event.preventDefault();
//	document.getElementById("tab4").style.display = "block";
    fetch('http://localhost:8088/customers/delete/'+id+'?Key='+key, {
		     method: 'POST'
		  	 })
		     .then(response => response.json())
		     .then(data => {
				if(data.status==undefined){
					alert("Scucessfull deleted");
				}
				else{
					alert(data.message);
				}
		
		     })
		     .catch(error => {
		       alert(error);
		     });
}
function tab5(event){
    event.preventDefault();
//	document.getElementById("tab5").style.display = "block";
    		fetch('http://localhost:8088/customers/properties/'+id+'?Key='+key)
		     .then(response => response.json())
		     .then(data => {
				if(data.status==undefined){
					document.getElementById("demo").innerHTML = `<pre>${JSON.stringify(data)}</pre>`;
				}
				else{
					alert(data.message);
				}
		
		     })
		     .catch(error => {
		       alert(error);
		     });
}
function tab6(event){
    event.preventDefault();
//	document.getElementById("tab6").style.display = "block";
    fetch('http://localhost:8088/customers/deals/'+id+'?Key='+key)
		     .then(response => response.json())
		     .then(data => {
				if(data.status==undefined){
					document.getElementById("demo").innerHTML = `<pre>${JSON.stringify(data)}</pre>`;
				}
				else{
					alert(data.message);
				}
		
		     })
		     .catch(error => {
		       alert(error);
		     });
}
function tab7(event){
    event.preventDefault();
    const formData = new FormData(event.target);
//    document.getElementById("tab7").style.display = "block";
    fetch('http://localhost:8088/customers/property/'+id+"/"+formData.get('pid')+'?Key='+key)
		     .then(response => response.json())
		     .then(data => {
				if(data.status==undefined){
					document.getElementById("demo").innerHTML = `<pre>${JSON.stringify(data)}</pre>`;
				}
				else{
					alert(data.message);
				}
		
		     })
		     .catch(error => {
		       alert(error);
		     });
}
function tab8(event){
    event.preventDefault();
    const formData = new FormData(event.target);
 //   document.getElementById("tab8").style.display = "block";
    fetch('http://localhost:8088/customer/'+id+'/notifications?Key='+key+'&notificationId='+formData.get("nid"))
		     .then(response => response.json())
		     .then(data => {
				if(data.status==undefined){
					document.getElementById("demo").innerHTML = `<pre>${JSON.stringify(data)}</pre>`;
				}
				else{
					alert(data.message);
				}
		
		     })
		     .catch(error => {
		       alert(error);
		     });
}
function tab9(event){
    event.preventDefault();
//    document.getElementById("tab9").style.display = "block";
    const formData = new FormData(event.target);
    
    fetch('http://localhost:8088/customers/property/'+formData.get("pid"))
		     .then(response => response.json())
		     .then(data => {
				if(data.status==undefined){
					document.getElementById("demo").innerHTML = `<pre>${JSON.stringify(data,null,2)}</pre>`; 
				}
				else{
					alert(data.message);
				}
		
		     })
		     .catch(error => {
		       alert(error);
		     });
}
function tab10(event){
    event.preventDefault();
//	document.getElementById("tab10").style.display = "block";
    fetch('http://localhost:8088/customers/properties')
		     .then(response => response.json())
		     .then(data => {
				if(data.status==undefined){
					document.getElementById("demo").innerHTML = `<pre>${JSON.stringify(data,null,2)}</pre>`; 
				}
				else{
					alert(data.message);
				}
		
		     })
		     .catch(error => {
		       alert(error);
		     });
}
function tab11(event){
    event.preventDefault();
//	document.getElementById("tab11").style.display = "block";
    const formData = new FormData(event.target);
    
    fetch('http://localhost:8088/customers/addDeal', {
	     method: 'POST',
	     headers: {
	       'Content-Type': 'application/json'
	     },
	     body: JSON.stringify({
	    	  dealCost:formData.get("deadcost"),
	    	  dealType: formData.get("deadtype"),
	    	  customerId: uid,
	    	  propertyId: formData.get("propertyid"),
	    	  brokerId: formData.get("brokerid"),
	    	  startPeriod: formData.get("startperiod"),
	    	  endPeriod: formData.get("endperiod")
				})
	  	 })
		     .then(response => response.json())
		     .then(data => {
				if(data.status==undefined){
					document.getElementById("demo").innerHTML = `<pre>${JSON.stringify(data)}</pre>`; 
				}
				else{
					alert(data.message);
				}
		
		     })
		     .catch(error => {
		       alert(error);
		     });
}
function tab12(event){
    event.preventDefault();
//	document.getElementById("tab12").style.display = "block";
    const formData = new FormData(event.target);
    
    fetch('http://localhost:8088/customers/editDeal/'+formData.get("dealid")+'?Key='+key,{
	     method: 'POST',
	     headers: {
	       'Content-Type': 'application/json'
	     },
	     body: JSON.stringify({
	    	    dealCost: formData.get("dealCost"),
	    	    dealType: formData.get("dealType"),
	    	    customerId: uid,
	    	    propertyId: formData.get("propertyId"),
	    	    brokerId: formData.get("brokerId"),
	    	    startPeriod: formData.get("startPeriod"),//"yyyy-mm-mm"
	    	    endPeriod: formData.get("endPeriod")
				})
	  	 })
	  	 .then(response => response.json())
		     .then(data => {
				if(data.status==undefined){
					document.getElementById("demo").innerHTML = `<pre>${JSON.stringify(data)}</pre>`; 
				}
				else{
					alert(data.message);
				}
		
		     })
		     .catch(error => {
		       alert(error);
		     });
}
function tab13(event){
    event.preventDefault();
//	document.getElementById("tab13").style.display = "block";
    const formData = new FormData(event.target);
    
    fetch('http://localhost:8088/customers/acceptDeal/'+formData.get("dealid")+'/'+uid)
		     .then(response => response.json())
		     .then(data => {
				if(data.status==undefined){
					document.getElementById("demo").innerHTML = `<pre>${JSON.stringify(data)}</pre>`; 
				}
				else{
					alert(data.message);
				}
		
		     })
		     .catch(error => {
		       alert(error);
		     });
}
function tab14(event){
    event.preventDefault();
//	document.getElementById("tab14").style.display = "block";
    const formData = new FormData(event.target);
    
    fetch('http://localhost:8088/customers/rejectDeal/'+formData.get("dealid")+'/'+uid)
		     .then(response => response.json())
		     .then(data => {
				if(data.status==undefined){
					document.getElementById("demo").innerHTML = `<pre>${JSON.stringify(data)}</pre>`; 
				}
				else{
					alert(data.message);
				}
		
		     })
		     .catch(error => {
		       alert(error);
		     });
}
function tab15(event){
    event.preventDefault();
	
    const formData = new FormData(event.target);
    
    fetch('/customers/payBill?Key='+key, {
	     method: 'POST',
	     headers: {
	       'Content-Type': 'application/json'
	     },
	     body: JSON.stringify({
	    	    dealId: formData.get("dealid"),
	    	    customerId: uid,
	    	    cardNo: formData.get("cardno"),
	    	    cvv: formData.get("cvv"),
	    	    cardHolderName:formData.get("cardholdername"),
	    	    paymentAmount: formData.get("paymentamount"),
	    	    expiryDate: formData.get("expiredate")//yyyy-mm-dd
				})
	  	 })
		     .catch(error => {
		       alert(error);
		     });
}




//   function update(){

//     let username = document.getElementById("val5").value
//     let email = document.getElementById("val6").value
//     let mobile = document.getElementById("val7").value
//     let city = document.getElementById("val8").value
//     let brokerName= document.getElementById("val9").value
//     let newBroker ={
//         username,
//         email,
//         mobile,
//         city,
//         brokerName
//     };
//     console.log(newBroker);
//     updateBroker(0,newBroker);
// }

// async function updateBroker(id, brokerObj){
//     let response = await fetch("http://localhost:8088/brokers/"+id,{
//         method: "PUT",
//         "Content-Type" : "application/json",
//         body : JSON.stringify(brokerObj)
//     });
//     console.log(resonse);
//     let data = await response.json();
//     let div = document.createElement("div");
//     console.log(data)
//     let h1 = document.createElement("h1");
//     h1.style.textAlign= "center";
//     h1.style.backgroundColor = "red";
//     h1.style.color = "white";
//     h1.innerHTML = "Your data has been successfully updated in our system";
//     div.append(h1);
//     let mydiv = document.getElementById("showdiv");
//     mydiv.append(div);
// }
