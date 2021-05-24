const newAdmin = document.getElementById("add-new-admin");
newAdmin.addEventListener('click', addNewAdmin);
const newMonth = document.getElementById("add-new-month");
newMonth.addEventListener('click', addNewMonth);
const deleteM = document.getElementById("delete-month");
deleteM.addEventListener('click', deleteMonth);
var counterDrops = 0;

function addNewAdmin(){
    if(counterDrops == 0){
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
            <button type="button" class=" text-admin btn1" id="login"> Adauga </button>
        </form>`);
        counterDrops = 1;
   } else{
    eliminateOption(1);
  }   
}

function addNewMonth(){
    if(counterDrops == 0){
        document.getElementById("new-month-div").classList.add("admin-option_on-click");
        newMonth.insertAdjacentHTML("afterend",`
        <div id="add-data">
        <button type="button" class=" text-admin btn1" id="upload"> Incarca CSV </button>       
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
    deleteM.insertAdjacentHTML("afterend",`
    <form id="delete-month-form">
             <div class="form-input">
                 <label for="admin-username" class="text-admin">Luna: </label>
                 <input type="text" id="month"> 
             </div>
             <div class="form-input">
             <label for="admin-password" class="text-admin">Anul: </label>
             <input type="text" id="year">
             </div>
             <button type="button" class="text-admin btn1" id="login"> Sterge din baza de date </button>
         </form>`);
         counterDrops = 3;
        }
    else{
        eliminateOption(3);
    }
}



function eliminateOption(option){
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