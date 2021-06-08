//add the four main buttons the drop downs with functionalities
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
                <label for="admin-username" class="text-admin" id="admin-name">`+ langAdminMode[a].adminName +`</label>
                <input type="text" id="admin-username"> 
            </div>
            <div class="form-input">
            <label for="admin-password" class="text-admin" id="password">`+ langAdminMode[a].password +`</label>
            <input type="password" id="admin-password">
            </div>
            <button type="button" class=" text-admin login btn1" id="add-admin-btn">`+ langAdminMode[a].addAdminBtn+`</button>
        </form>`);
        counterDrops = 1;
   } else{
    eliminateOption(1);
  }   
}

//adding a new month + save the uploaded csv
var CSVfile;
function addNewMonth(){
    if(counterDrops == 0){
        document.getElementById("new-month-div").classList.add("admin-option_on-click");
        optionsDiv.classList.add("admin-options_selected");  
        newMonth.insertAdjacentHTML("afterend",`
        <div id="add-data">
            <div class="criteria__arrow">
                <select class="drop-list" id="type-of-file">
                    <option class="text_option" value=1 selected id="age-option">`+langAdminMode[a].ageOption+`</option>
                    <option class="text_option" value=2 id="education-option">`+langAdminMode[a].educationOption+`</option>
                    <option class="text_option" value=3 id="env-option">`+ langAdminMode[a].envOption +`</option>
                    <option class="text_option" value=4 > Total </option>
                </select>
                <span class="arrow"></span>
            </div>
            <label for="upload" class="text-admin btn1" id="add-csv"> 
            <input type="file" id="upload" style="display: none;"/>
            `+ langAdminMode[a].addCsv +` </label>
            <button type="button" class="text-admin btn1" id="add-db"> `+ langAdminMode[a].addDb +` </button>
        </div> 
        `);
        counterDrops = 2;
        document.getElementById('upload').onchange=function(e){
            CSVfile = e.target.files[0];
        }
    }else{
        eliminateOption(2);
    }
}

function deleteMonth(){
    if(counterDrops == 0){
     document.getElementById("delete-div").classList.add("admin-option_on-click");
     optionsDiv.classList.add("admin-options_selected");  
     
     deleteM.insertAdjacentHTML("afterend",`
            <div id ="delete-month-form"> 
            <div class="criteria__arrow">
            <select class="drop-list" id="drop-perioada">
            </select>
            <span class="arrow"></span>
            </div>
             <button type="button" class="text-admin btn1" id="delete">`+ langAdminMode[a].delete +`</button>
            <div>`);
         counterDrops = 3;
         let months = {
            "ro": {0: "Ianuarie", 1: "Februarie", 2: "Martie", 3:"Aprilie", 4:"Mai", 5:"Iunie", 6:"Iulie", 7:"August", 8:"Septembrie", 9:"Octombrie", 10:"Noiembrie", 11:"Decembrie"},
            "en": {0:"January", 1:"February", 2:"March", 3:"April", 4:"May", 5:"June", 6:"July", 7:"August", 8:"September", 9:"October", 10:"November", 11:"December"}}
        var d = new Date();
        var currentMonth = d.getMonth();
        var currentYear = d.getFullYear()
        
        for(var i=0 ; i <15; i++){
             var option = document.createElement('option');
             option.innerHTML=months["ro"][currentMonth].concat(' ').concat(currentYear.toString());
             var select = document.getElementById("drop-perioada");
             option.classList.add("text_option");
             option.value = i+1;
             select.insertAdjacentElement("beforeend",option);
            currentMonth = currentMonth-1;
            if(currentMonth == -1){
                currentMonth = 11;
                currentYear = currentYear-1;
            }
         }
        }
    else{
        eliminateOption(3);
    }
}

//permit only one option to be extended
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
    case 4: break;
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

//logout -> sent a request to the database to erase the token, remove it from locale storage and send the user to the principal page
document.getElementById('logout').addEventListener('click', logout);
function logout(){
    if(counterDrops == 0){
    const Http = new XMLHttpRequest();
    const url='http://localhost:8090/api/v1/admin?logout';
   
    Http.open("POST", url);
    Http.setRequestHeader('Accept', 'application/json'); 
    Http.setRequestHeader('Authorization', localStorage.getItem("token").toString());
    Http.onload = function() {
         if(Http.readyState = 4 && Http.status==200){
            const ans = JSON.parse(Http.responseText);
            if(ans.response == "You've been logged out!" ){
                window.location.href = "../principal/proiect.html";
                showSnackbar(ans.response);
                localStorage.removeItem("token");   
            }
        } 
      }
    Http.send();
   counterDrops=4;
}  else{
    eliminateOption(4);
}
}



//actions - Http requests controller
document.addEventListener('click', function(e){
    switch(e.target.id){
        case 'login': addAdmin(); break;
        case 'add-db': sendFile(); break;
        case 'delete': deleteData(); break;
    } 
});


//adding a new admin -> send the credentials to the database and return the answer as a toast
function addAdmin(){
    const Http = new XMLHttpRequest();
    const url='http://localhost:8090/api/v1/admin';
   
    const username = document.getElementById("admin-username").value;
    const password = document.getElementById("admin-password").value;
    const data = { username: username, password: password};
    
    Http.open("POST", url, true);
    Http.setRequestHeader('Accept', 'application/json'); 
    Http.setRequestHeader('Authorization', localStorage.getItem("token").toString());
    Http.onload = function() {
         if( Http.readyState == 4 && Http.status==200){
           const ans = JSON.parse(Http.responseText);
           console.log(ans.response);
           showSnackbar(ans.response);
        } 
      }
    Http.send(JSON.stringify(data));
}



//util functions for parsing CSV
function isNumber(char){
    return "0123456789".includes(char);
}

function replaceAt (string, index, replacement) {
    return string.substr(0, index) + replacement + string.substr(index + replacement.length);
}

//sending a file to the database + parsing the csv in order to send a json object
function sendFile(){
    const typeOfFile = document.getElementById("type-of-file");
    var addURL;
    switch (typeOfFile.value){
        case "1": 
        addURL = "http://localhost:8090/api/v1/age";
           break;
        case "2": 
        addURL = "http://localhost:8090/api/v1/education";
        break;
        case "3": 
        addURL = "http://localhost:8090/api/v1/environment";
        break;
        case "4": 
        addURL = "http://localhost:8090/api/v1/total";
        break;
    }  

    const reader = new FileReader();
    reader.addEventListener('load', function(e) {
        let CSVdata = e.target.result;
        var lines=CSVdata.split("\n");
        var result = {};
        var headers=lines[0].split(",");
        var groups = [];
        if(headers[headers.length-1] == "\r"){ 
         var newHeaders = [];
         for(var i=0; i<headers.length-1; i++){
             newHeaders[i]=headers[i];
            }
          headers = [];
          headers = newHeaders;   
        } else 
        if(headers[headers.length-1].includes("\r")){
            word = headers[headers.length-1];
            headers[headers.length-1] = word.substring(0,(word.length-1));
        }
        for(var i = 0; i < headers.length; i++){
            headers[i]= headers[i].replaceAll(" ", "");
            headers[i]= headers[i].replaceAll("-", "");
            headers[i]= headers[i].replaceAll("/", "");
            headers[i]= headers[i].replaceAll("(", "");
            headers[i]= headers[i].replaceAll(")", "");
            headers[i]= headers[i].replaceAll("%", "");
            headers[i]= "var" + headers[i][0].toUpperCase() + headers[i].slice(1).toLowerCase();
        }

        for(var i=1;i<lines.length;i++){
         var obj = {};  
          var newLine = lines[i];
        if(typeOfFile.value == 4 ){
         for( var z=1; z<lines[i].length-1; z++){
             if(lines[i].charAt(z) == ',' && isNumber(lines[i].charAt(z-1)) && isNumber(lines[i].charAt(z+1))){
                 newLine = replaceAt(newLine, z, "#");
             }
         }
        }
        
        newLine = newLine.replaceAll("#","");
        var currentline=newLine.split(",");
        
        if(currentline.length >= headers.length){
        for(var j=0;j<headers.length;j++){
            var word = currentline[j];
            word = word.replaceAll("\"","");
            word = word.trim();
            obj[headers[j]] = word;
        }
        if(obj["varJudet"] != "")
          groups.push(obj);
      }
    }
        result["groups"] = groups;
        data = JSON.stringify(result);   
    
        const Http = new XMLHttpRequest();
        Http.open('POST', addURL, true);
        Http.setRequestHeader('Accept', 'application/json'); 
        Http.setRequestHeader('Authorization', localStorage.getItem("token").toString());
        Http.onload = function() {
            if(Http.readyState = 4 && Http.status==200){
            const ans = JSON.parse(Http.responseText);   
            showSnackbar(ans.response)
            } 
    }
       Http.send(data);
    });
 
    reader.readAsBinaryString(CSVfile);
    
}
 

//delete data from database from a certain month
function deleteData(){
    monthID = document.getElementById('drop-perioada').value;
    counter = 0;
    const urls = ["http://localhost:8090/api/v1/age?monthID=","http://localhost:8090/api/v1/education?monthID=","http://localhost:8090/api/v1/environment?monthID=","http://localhost:8090/api/v1/total?monthID="];
    for(var i=0; i < 4; i++){
    const Http = new XMLHttpRequest();
    Http.open('DELETE', urls[i]+monthID);
    Http.setRequestHeader('Accept', 'application/json'); 
    Http.setRequestHeader('Authorization', localStorage.getItem("token").toString());
    Http.onload = function() {
        if(Http.readyState = 4 && Http.status==200){
        const ans = JSON.parse(Http.responseText);
            if(ans.response == "Data was deleted!" ){
                counter++; 
            }
        } 
    }
   Http.send();
  }   
  if(counter == 4){
      showSnackbar("Data was deleted!");
  }else{
      showSnackbar("A problem occured, try again!");
  }
}


function showSnackbar(text){
var x = document.getElementById("snackbar");
  x.textContent = text;
  x.className = "show";
  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 2000);
}


//Ro - En

function changeMonth(lang){
    var currentMonthCh = d.getMonth();
    var currentYearCh = d.getFullYear();
    let mySelect = document.getElementById("drop-perioada");
    let selectLen = mySelect.length;
    for(var j=0; j < selectLen ; j++){
       mySelect.options[j].innerHTML = months[lang][currentMonthCh].concat(' ').concat(currentYearCh.toString()); 
       currentMonthCh = currentMonthCh-1;
    if(currentMonthCh == -1){
        currentMonthCh = 11;
        currentYearCh = currentYearCh-1;
    }
  }
}

let langAdminMode = {
    "en": {
        "addAdmin": "Add a new admin",
        "addData": "Add new data",
        "deleteData": 'Delete data',
        "logout": 'Logout',
        "adminName": 'Username:',
        "password": 'Password:',
        "addAdminBtn" : "Add",
        "addDb": "Add in database",
        "ageOption": "Age",
        "educationOption": "Education",
        "envOption": "Environment",
        "delete": "Delete from database",
        "addCsv": "Add CSV"
    },
    "ro": { 
        "addAdmin": "Adaugă un admin nou",
        "addData": "Adaugă date",
        "deleteData": 'Șterge date',
        "logout": 'Deconectare',
        "adminName": 'Nume admin:',
        "password": 'Parolă:',
        "addAdminBtn" : "Adaugă",
        "addDb": "Adaugă în baza de date",
        "ageOption": "Vârstă",
        "educationOption": "Educație",
        "envOption": "Mediu",
        "delete": "Șterge din baza de date",
        "addCsv":"Adaugă CSV"
    }
}

let addAdministrator = document.getElementById("add-admin");
let addData = document.getElementById("add-data-button");
let delData = document.getElementById("delete-data");
let logoutBtn = document.getElementById("logout");


var a ="ro";
link.forEach(e1 => {
    e1.addEventListener("click", () => {
        a = e1.getAttribute("lang");
        addAdministrator.textContent = langAdminMode[a].addAdmin; 
        addData.textContent = langAdminMode[a].addData;
        delData.textContent = langAdminMode[a].deleteData;
        logoutBtn.textContent = langAdminMode[a].logout;
        if(counterDrops == 1){
            let adminName = document.getElementById("admin-name");
            let password = document.getElementById("password");
            let addAdminBtn = document.getElementById("add-admin-btn");

            adminName.textContent = langAdminMode[a].adminName;
            password.textContent = langAdminMode[a].password;
            addAdminBtn.textContent = langAdminMode[a].addAdminBtn;}
        if(counterDrops == 2){
            let addDb = document.getElementById("add-db");
            let ageOption = document.getElementById("age-option");
            let educationOption = document.getElementById("education-option");
            let envOption = document.getElementById("env-option");
            let addCsv = document.getElementById("add-csv");

            addDb.textContent = langAdminMode[a].addDb;
            ageOption.textContent = langAdminMode[a].ageOption;
            educationOption.textContent = langAdminMode[a].educationOption;
            envOption.textContent = langAdminMode[a].envOption;
            addCsv.textContent = langAdminMode[a].addCsv;
        }
        if(counterDrops == 3){
            changeMonth(a);
            let del = document.getElementById("delete");

            del.textContent = langAdminMode[a].delete;
        }
    });

})