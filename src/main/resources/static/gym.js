`use strict`

const cName = document.querySelector("#gName");
const cType = document.querySelector("#gType");
const cId = document.querySelector("#id");
const cNewName = document.querySelector("#newName");
const cNewType = document.querySelector("#newType");
const updateId = document.querySelector("#gymId");
const deleteId = document.querySelector("#deleteId");
const success = document.querySelector(".gymTitle");

const createSuccess = () => {
   const popUp = document.createElement("p");
   const txt = document.createTextNode("successful");
   popUp.appendChild(txt);
    success.appendChild(popUp);

}




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
	.then(createSuccess())
    .catch((err) => console.error(err));
}



const getAllGyms = () => {
    fetch(`http://localhost:9092/gym/read`)
    .then((response) => {
        if(response.status !==200){
            console.log(`Unexpected error has occurred ${response.status}`);
            return;
        }
        response.json()
        .then((data) => console.log(data));
    }) .catch((err) => console.error(err));
}

const getGymId = () => {

    let id = cId.value;

    const params = new URLSearchParams(window.location.search);
    console.log(params);
    for (const param of params){
        console.log(param);
    }
    // let id = params.get("id");
    console.log(`your gym id is ${id}`);
    fetch(`http://localhost:9092/gym/read/${id}`)
    .then((response) => {
        if(response.status !==200){
            console.log(`Unexpected error has occured ${response.status}`);
            return;
        }
        response.json()
        .then((data) => console.log(data));
    }) .catch((err) => console.error(err));


}

const putGym = () => {
    const newId = updateId.value;
    const newName = cNewName.value;
    const newType = cNewType.value;

    const newGym = {

        "name" : this.newName.value,
        "type" : this.newType.value
    }
    console.log(newId);


    fetch(`http://localhost:9092/gym/update/${newId}`,{
        method : `PUT`,
        headers : {
            "Content-Type" : "application/json"
        },
        body : JSON.stringify(newGym)
    })
    .then((response) => response.json())
    .then((data) => console.log(data))
    .catch((err) => console.error(err));
}

const deleteGym = () => {
    const removeId = deleteId.value;

    fetch(`http://localhost:9092/gym/delete/${removeId}`, {
        method : `DELETE`
    })
    .then((data) => console.log(`Query verified via json ${data}`))
    .catch((err) => console.log(err));
}