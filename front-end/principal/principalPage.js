let link2 = document.querySelectorAll(".languageButton");
let title1 = document.querySelector(".title");
let view1 = document.querySelector(".viewButtonMain");
let compare1 = document.querySelector(".compareButtonMain");



link2.forEach(e1 => {
    e1.addEventListener("click", () => {

        let a = e1.getAttribute("lang");

        title1.textContent = data2[a].title1;
        view1.textContent = data2[a].view1;
        compare1.textContent = data2[a].compare1;


    });

})

var data2 = {
    "en": {
        "title1": "ROMANIAN UNEMPLOYMENT DATA",
        "view1": "View",
        "compare1": "Comparison",

    },
    "ro": {
        "title1": "DATE SOMAJ ROMANIA",
        "view1": "Vizualizare",
        "compare1": "Comparare",
    }
}