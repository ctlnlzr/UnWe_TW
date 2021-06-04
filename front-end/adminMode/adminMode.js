const newAdmin = document.getElementById("add-new-admin");
newAdmin.addEventListener('click', addNewAdmin);
const newMonth = document.getElementById("add-new-month");
newMonth.addEventListener('click', addNewMonth);
const deleteM = document.getElementById("delete-month");
deleteM.addEventListener('click', deleteMonth);
var counterDrops = 0;
var optionsDiv = document.getElementById("options");
function addNewAdmin(){
    if(counterDrops == 0){
      optionsDiv.classList.add("admin-options_selected");  
     document.getElementById("new-admin-div").classList.add("admin-option_on-click");
     newAdmin.insertAdjacentHTML("afterend",`
        <form id="add-admin-form">
            <div class="form-input">
                <label for="admin-username" class="text-admin"> Username: </label>
                <input type="text" id="admin-username"> 
            </div>
            <div class="form-input">
            <label for="admin-password" class="text-admin">Parola:</label>
            <input type="password" id="admin-password">
            </div>
            <button type="submit" class=" text-admin login btn1" id="login"> Adauga </button>
        </form>`);
        counterDrops = 1;
   } else{
    eliminateOption(1);
  }   
}

function addNewMonth(){
    if(counterDrops == 0){
        document.getElementById("new-month-div").classList.add("admin-option_on-click");
        optionsDiv.classList.add("admin-options_selected");  
        newMonth.insertAdjacentHTML("afterend",`
        <div id="add-data">
        <select id="type-of-file">
        <option value=1 > Varsta <option>
        <option value=2 > Educatie <option>
        <option value=3 > Mediu <option>
        <option value=4 > Total <option>
        </select>
        <label for="upload" class="text-admin"> Incarca CSV </label>
        <input type="file" id="upload">
        <button type="button" class=" text-admin btn1" id="add-db"> Adauga in baza de date </button>
        </div> 
        `);
        counterDrops = 2;

    }else{
        eliminateOption(2);
    }
}

function deleteMonth(){
    if(counterDrops == 0){
     document.getElementById("delete-div").classList.add("admin-option_on-click");
     optionsDiv.classList.add("admin-options_selected");  
     
     deleteM.insertAdjacentHTML("afterend",`
    <form id="delete-month-form">
             <div class="form-input">
                 <label for="month" class="text-admin">Luna: </label>
                 <input type="text" id="month"> 
             </div>
             <div class="form-input">
             <label for="year" class="text-admin">Anul: </label>
             <input type="text" id="year">
             </div>
             <button type="button" class="text-admin btn1" id="delete"> Sterge din baza de date </button>
         </form>`);
         counterDrops = 3;
        }
    else{
        eliminateOption(3);
    }
}

function eliminateOption(option){
    optionsDiv.classList.remove("admin-options_selected");  
    switch(counterDrops){
    case 1: 
    document.getElementById("new-admin-div").classList.remove("admin-option_on-click");
        document.getElementById("add-admin-form").remove();
        break;
    case 2:
        document.getElementById("new-month-div").classList.remove("admin-option_on-click");
        document.getElementById("add-data").remove();  
        break;
    case 3:
        document.getElementById("delete-div").classList.remove("admin-option_on-click");
        document.getElementById("delete-month-form").remove();
        break;
  }
  if(option != counterDrops)
  {counterDrops= 0;
   switch (option){
    case 1: addNewAdmin();
    break;
    case 2: addNewMonth();
    break;
    case 3: deleteMonth();
   } }
    else counterDrops= 0;


}

//Http requests
document.addEventListener('click', function(e){
    switch(e.target.id){
        case 'login': addAdmin(); break;
        case 'upload': uploadFile(); break;
        case 'add-db': sendFile(); break;
        case 'delete': deleteData(); break;
    } 
});


function addAdmin(){
    console.log("admin");
    const Http = new XMLHttpRequest();
    const url='http://localhost:8090/api/v1/admin';
   
    const username = document.getElementById("admin-username").value;
    const password = document.getElementById("admin-password").value;
    const data = { username: username, password: password};
    
    Http.open("POST", url, true);
    Http.setRequestHeader('Accept', 'application/json'); 
    Http.setRequestHeader('Authorization', localStorage.getItem("token").toString());
    Http.onload = function() {
         if(Http.readyState = 4 && Http.status==200){
            const ans = JSON.parse(Http.responseText);
            if(ans.response == "You added a new admin!" ){
            //create u added admin info, snackbar
            }else{
             console.log(ans.response);
              //create already exists
            }
        } 
      }
    Http.send(JSON.stringify(data));
}

function uploadFile(){
  const typeOfFile = document.getElementById("type-of-file");
  var URL;
  switch (typeOfFile.value){
      case 1: 
         URL = "http://localhost:8090/api/v1/age";
         break;
      case 2: 
        URL = "http://localhost:8090/api/v1/education";
      break;
      case 3: 
      URL = "http://localhost:8090/api/v1/environment";
      break;
      case 4: 
      URL = "http://localhost:8090/api/v1/total";
      break;
  } 
  var data = "schimba csv ul in json";
  Http.open("Post", "http://localhost:8090/api/v1/age", true);
  Http.setRequestHeader('Accept', 'application/json'); 
  Http.setRequestHeader('Authorization', localStorage.getItem("token").toString());
  Http.onload = function() {
     if(Http.readyState = 4 && Http.status==200){
        const ans = JSON.parse(Http.responseText);
        if(ans.response == "Data wad added" ){
        //create u add data 
        }else{
         console.log(ans.response);
          //create already exists
        }
    } 
  }
  Http.send(JSON.stringify(data)); 
}

function sendFile(){
    console.log("sendFile");
}

function deleteData(){
    
}