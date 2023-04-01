let dataToSend={};

async function getData(id, key){
    let response = await fetch("http://localhost:8088/brokers/"+id);
    let data = await response.json(); 

    let div = document.createElement("div");
    
    let h1 = document.createElement("h1");
    h1.innerHTML = "Broker Details";
    h1.style.textAlign = "center";
    h1.style.backgroundColor = "orange";

    let h31 = document.createElement("h3");
    h31.innerHTML = data.username;

    let h32 = document.createElement("h3");
    h32.innerHTML = data.mobile;

    let h33 = document.createElement("h3");
    h33.innerHTML = data.email;

    let h34 = document.createElement("h3");
    h34.innerHTML = data.city;

    let h35 = document.createElement("h3");
    h35.innerHTML = data.brokerName;

    div.append(h1,h31,h35,h32,h33,h34);

    let mydiv = document.getElementById("showdiv");
    mydiv.append(div);
};


let btn1 = document.getElementById("btn1");
btn1.addEventListener("click", getData);



async function deleteData(id){
    let response = await fetch("http://localhost:8088/brokers/"+id,{
        method: "DELETE",
        "Content-Type" : "application/json"
    });
    let data = await response.json();

    let div = document.createElement("div");

    let h1 = document.createElement("h1");
    h1.style.textAlign= "center";
    h1.style.backgroundColor = "red";
    h1.style.color = "white";
    h1.innerHTML = "Your data has been successfully deleted fro our system";

    div.append(h1);
    let mydiv = document.getElementById("showdiv");
    mydiv.append(div);
}

let btn2 = document.getElementById("btn2");
btn2.addEventListener("click",deleteData);

async function updateBroker(id, brokerObj){
    let response = await fetch("http://localhost:8088/brokers/"+id,{
        method: "PUT",
        "Content-Type" : "application/json",
        body : JSON.stringify(brokerObj)
    });
    let data = await response.json();
    let div = document.createElement("div");

    let h1 = document.createElement("h1");
    h1.style.textAlign= "center";
    h1.style.backgroundColor = "red";
    h1.style.color = "white";
    h1.innerHTML = "Your data has been successfully updated in our system";

    div.append(h1);
    let mydiv = document.getElementById("showdiv");
    mydiv.append(div);

}

let btn3 = document.getElementById("btn3");
btn3.addEventListener("click",updateBroker);


async function addProperty(id, key , propertyObj){
    let response = await fetch("http://localhost:8088/brokers/"+id);
    let data = await response.json(); 
    data.listOFProperties.add(propertyObj);

    let res = await fetch("http://localhost:8088/brokers/"+id,{
        method: "PUT",
        "Content-Type" : "application/json",
        body : JSON.stringify(data)
    });
    let dddata = await res.json();
    getData(id);
}

let btn4 = document.getElementById("btn4");
btn4.addEventListener("click",addProperty);

async function getProperties(id){
    let response = await fetch("http://localhost:8088/brokers/"+id);
    let data = await response.json(); 

    let mydiv = document.getElementById("showdiv");
    data.listOFProperties.forEach((ele)=>{
        let div = document.createElement("div");
        let h31 = document.createElement("h3");
        h31.innerHTML = ele.address;
        let h32 = document.createElement("h3");
        h32.innerHTML = ele.city;
        let h33 = document.createElement("h3");
        h33.innerHTML = ele.street;
        let h34 = document.createElement("h3");
        h34.innerHTML = ele.areaSqrt;
        let h35 = document.createElement("h3");
        h35.innerHTML = ele.offerCost;

        div.append(h31,h32,h33,h34,h35);
        mydiv.append(div);
    })
}

let btn5 = document.getElementById("btn5");
btn5.addEventListener("click",getProperties);