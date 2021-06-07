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
let dataLang = {
    "en": {
        "title": "ROMANIAN UNEMPLOYMENT DATA",
        "resource": "Resources:",
        "language": "Language:",
        "menu": "Menu",
        "titleMenu": "Romanian unemployment data",
        "viewMenu": "View",
        "compareMenu": "Comparison",
        "aboutWe": "Admin Mode"
    },
    "ro": {
        "title": "DATE SOMAJ ROMANIA",
        "resource": "Resurse:",
        "language": "Limba:",
        "menu": "Meniu",
        "titleMenu": "Date somaj Romania",
        "viewMenu": "Vizualizare",
        "compareMenu": "Comparare",
        "aboutWe": "Mod adminstrare"
    }
}

let language = document.querySelector(".lang");
let link = document.querySelectorAll(".languageButton");
let titleDoc = document.getElementById("headerTitle");
let view = document.getElementById("viewButton");
let compare = document.getElementById("compareButton");
let about = document.getElementById("aboutButton");
let resource = document.querySelector(".resource");
let titleMenu = document.getElementById("menu_title");
let viewMenu = document.getElementById("viewMenu");
let compareMenu = document.getElementById("compareMenu");
let aboutMenu = document.getElementById("aboutMenu");
let language2 = document.querySelector(".lang");
let aboutWe = document.getElementById("aboutWe");

link.forEach(e1 => {
    e1.addEventListener("click", () => {
        let a = e1.getAttribute("lang");
        titleDoc.textContent = dataLang[a].title;
        view.textContent = dataLang[a].viewMenu;
        compare.textContent = dataLang[a].compareMenu;
        about.textContent = dataLang[a].aboutFtr;
        resource.textContent = dataLang[a].resource;
        language2.textContent = dataLang[a].language;
        about.textContent = dataLang[a].aboutFtr;
        titleMenu.textContent = dataLang[a].titleMenu;
        viewMenu.textContent = dataLang[a].viewMenu;
        compareMenu.textContent = dataLang[a].compareMenu;
        aboutMenu.textContent = dataLang[a].aboutMenu;
        aboutWe.textContent = dataLang[a].aboutWe
    });

})
