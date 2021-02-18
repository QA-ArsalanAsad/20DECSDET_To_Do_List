`use strict`

const eType = document.querySelector("#eType");
const ePrice = document.querySelector("#ePrice");
const eId = document.querySelector("#eId");
const fkId = document.querySelector("#newGymId");




const postEquipment = () => {
    const equipmentPrice = ePrice.value;
    const equipmentType = eType.value;
    const equipmentToGym = fkId.value;

    const myEquipment = {

        type : equipmentType,
        price : equipmentPrice,
        gym : {
            id : equipmentToGym
        }

    }

    console.log(myEquipment);

    fetch(`http://localhost:9092/equipment/create`, {
        method : `POST`,
        headers : {
            "Content-Type" : "application/json"
        },

        body : JSON.stringify(myEquipment)
    })
    .then((response) => response.json)
    .then((data) => console.info(`Query verified via json ${data}`))
    .catch((err) => console.error(err));
}


const getAllEquipment = () => {
    fetch(`http://localhost:9092/equipment/read`)
    .then((response) => {
        if(response.status !==200){
            console.log(`An unexpected error has occurred ${response.status}`);
            return;
        }
        response.json()
        .then((data) => console.log(data));
    }) .catch((err) => console.error(err));
}

const getEquipmentById = () => {
    let id = eId.value;

    const params = new URLSearchParams(window.location.search);
    console.log(params);
    for (const param of params){
        console.log(param);
    }
    console.log(`your equipment id is ${id}`);
    fetch(`http://localhost:9092/equipment/read/${id}`)
    .then((response) => {
        if(response.status !==200){
            console.log(`An unexpected error has occurred ${response.status}`);
            return;
        }
        response.json()
        .then((data) => console.log(data));
    }) .catch((err) => console.error(err));
}

const putEquipment = () => {
    const updateEId = newEId.value;
    const updateType = newEType.value;
    const updatePrice = newPrice.value;
   
    console.log(updatePrice);
    console.log(updateType);
  

    const newEquipment = { //Not able to change id of gym because ids should not be visible
        "price" : updatePrice,
        "type" : updateType,
       
    }
    console.log(newEquipment);

    fetch(`http://localhost:9092/equipment/update/${updateEId}`, {
        method : `PUT`,
        headers : {
            "Content-Type" : "application/json"
        },
        body : JSON.stringify(newEquipment)
    })
    .then((response) => response.json)
    .then((data) => console.log(data))
    .catch((err) => console.error(err));

}