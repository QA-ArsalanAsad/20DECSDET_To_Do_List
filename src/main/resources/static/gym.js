`use strict`

const cName = document.querySelector("#gName");
const cType = document.querySelector("#gType");

const postToGym = () => {

    const gymName = cName.value;
    const gymType = cType.value;

    let myGym = {

        name : gymName,
        type : gymType
        
    }
    console.log(myGym);

    fetch(`http://localhost:9092/gym/create`, {
        method : `POST`,
        headers : {
            "Content-Type" : "application/json"
        },

        body : JSON.stringify(myGym)
    })
    .then((response) => response.json())
    .then((data) => console.info(`Query verified via json ${data}`))
    .catch((err) => console.error(err));
}