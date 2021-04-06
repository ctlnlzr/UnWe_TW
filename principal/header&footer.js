//header Menu

let menuBtn = document.getElementById("buttonMenuOpen");
let navPanel = document.getElementById("headerMobileMenu");
let title = document.getElementById("title");
let buttons = document.getElementById("buttonsMain");
let buttonClose = document.getElementById("buttonMenuClose");


buttonClose.addEventListener("click", onBtnCloseClick);

menuBtn.addEventListener("click", onBtnClick);

function onBtnClick(event) {
    navPanel.classList.add("isOpen");
    title.classList.add("isOpen");
    buttons.classList.add("isOpen");
}

function onBtnCloseClick(e) {
    navPanel.classList.remove("isOpen");
    title.classList.remove("isOpen");
    buttons.classList.remove("isOpen");
}


//language

let language = document.querySelector(".languageChange");
let link = document.querySelectorAll(".languageButton");
let titleDoc = document.querySelector(".title");
let view = document.querySelector(".viewButton");
let compare = document.querySelector(".compareButton");
let about = document.querySelector(".aboutButton");
let view2 = document.querySelector(".viewButtonMain");
let compare2 = document.querySelector(".compareButtonMain");
let resource = document.querySelector(".resource");
let language2 = document.querySelector(".lang");
let aboutFooter = document.querySelector(".about");
let titleMenu = document.querySelector(".titleMobile");
let viewMenu = document.querySelector(".viewMenu");
let compareMenu = document.querySelector(".compareMenu");
let aboutMenu = document.querySelector(".aboutMenu");


link.forEach(e1 => {
    e1.addEventListener("click", () => {

        let a = e1.getAttribute("language");

        titleDoc.textContent = data[a].title;
        view.textContent = data[a].viewButton;
        compare.textContent = data[a].compareButton;
        about.textContent = data[a].aboutButton;
        view2.textContent = data[a].viewButtonMain;
        compare2.textContent = data[a].compareButtonMain;
        resource.textContent = data[a].resource;
        language2.textContent = data[a].language;
        aboutFooter.textContent = data[a].aboutFooter;
        menuBtn.textContent = data[a].menu;
        titleMenu.textContent = data[a].titleMenu;
        viewMenu.textContent = data[a].viewMenu;
        compareMenu.textContent = data[a].compareMenu;
        aboutMenu.textContent = data[a].aboutMenu;
    });

})

var data = {
    "english": {
        "title": "ROMANIAN UNEMPLOYMENT DATA",
        "viewButton": "View",
        "compareButton": "Comparison",
        "aboutButton": "About",
        "compareButtonMain": "Comparison",
        "viewButtonMain": "View",
        "resource": "Resources:",
        "language": "Language:",
        "aboutFooter": "About Unemployment data Romania",
        "menu": "Menu",
        "titleMenu": "Romanian unemployment data",
        "viewMenu": "View",
        "compareMenu": "Comparison",
        "aboutMenu": "About",
    },
    "romenian": {
        "title": "DATE SOMAJ ROMANIA",
        "viewButton": "Vizualizare",
        "compareButton": "Comparare",
        "aboutButton": "Despre",
        "compareButtonMain": "Comparare",
        "viewButtonMain": "Vizualizare",
        "resource": "Resurse:",
        "language": "Limba:",
        "aboutFooter": "Despre Date somaj Romania",
        "menu": "Meniu",
        "titleMenu": "Date somaj Romania",
        "viewMenu": "Vizualizare",
        "compareMenu": "Comparare",
        "aboutMenu": "Despre",
    }
}