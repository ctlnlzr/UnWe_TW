//header Menu
let navPanel = document.getElementById("headerMobileMenu");
let title = document.getElementById("title");
let buttons = document.getElementById("buttonsMain");


//header phone menu
let phoneMenu = document.getElementById("phone__menu");
let menuBtn = document.getElementById("phone_meniu_btn");
let menuTitle = document.getElementById("menu_title");
menuBtn.addEventListener("click", showMenu)
document.addEventListener("click", exitMenu);
function showMenu(event){
    phoneMenu.classList.add("phone_meniu_shown");
    menuTitle.classList.add("menu_title_shown");
    event.stopPropagation();
}

function exitMenu(event){
    if(!phoneMenu.contains(event.target)) 
    {phoneMenu.classList.remove("phone_meniu_shown");
    menuTitle.classList.remove("menu_title_shown");}
}


//language

let language = document.querySelector(".languageChange");
let link = document.querySelectorAll(".languageButton");
let titleDoc = document.querySelector(".headerTitle");
let view = document.querySelector(".viewButton");
let compare = document.querySelector(".compareButton");
let about = document.querySelector(".aboutButton");
let resource = document.querySelector(".resource");
let language2 = document.querySelector(".lang");
let aboutFooter = document.querySelector(".aboutFooter");
let titleMenu = document.querySelector(".menu_title");
let viewMenu = document.querySelector(".viewMenu");
let compareMenu = document.querySelector(".compareMenu");
let aboutMenu = document.querySelector(".aboutMenu");

var data = {
    "english": {
        "headerTitle": "ROMANIAN UNEMPLOYMENT DATA",
        "viewButton": "View",
        "compareButton": "Comparison",
        "aboutButton": "About",
        "resource": "Resources:",
        "language": "Language:",
        "aboutFooter": "About Unemployment data Romania",
        "menu": "Menu",
        "menu_title": "Romanian unemployment data",
        "viewMenu": "View",
        "compareMenu": "Comparison",
        "aboutMenu": "About",
    },
    "romanian": {
        "headerTitle": "DATE SOMAJ ROMANIA",
        "viewButton": "Vizualizare",
        "compareButton": "Comparare",
        "aboutButton": "Despre",
        "resource": "Resurse:",
        "language": "Limba:",
        "aboutFooter": "Despre Date somaj Romania",
        "menu": "Meniu",
        "menu_title": "Date somaj Romania",
        "viewMenu": "Vizualizare",
        "compareMenu": "Comparare",
        "aboutMenu": "Despre",
    }
}

link.forEach(e1 => {
    e1.addEventListener("click", () => {

        let a = e1.getAttribute("language");

        titleDoc.textContent = data[a].headerTitle;
        view.textContent = data[a].viewButton;
        compare.textContent = data[a].compareButton;
        about.textContent = data[a].aboutButton;
        resource.textContent = data[a].resource;
        language2.textContent = data[a].language;
        aboutFooter.textContent = data[a].aboutFooter;
        menuBtn.textContent = data[a].menu;
        titleMenu.textContent = data[a].menu_title;
        viewMenu.textContent = data[a].viewMenu;
        compareMenu.textContent = data[a].compareMenu;
        aboutMenu.textContent = data[a].aboutMenu;
    });

})

