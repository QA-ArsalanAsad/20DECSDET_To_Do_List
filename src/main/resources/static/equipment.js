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