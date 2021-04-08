let link1 = document.querySelectorAll(".languageButton");
let start = document.querySelector(".start");
let criteria = document.querySelector(".criteriaText");
let view1 = document.querySelector(".viewText");



link1.forEach(e1 => {
    e1.addEventListener("click", () => {

        let a = e1.getAttribute("lang");

        start.textContent = data1[a].start;
        criteria.textContent = data1[a].criteria;
        view1.textContent = data1[a].view;


    });

})

var data1 = {
    "en": {
        "start": "This web page helps to view and compare multi-criteria public data on unemployment in Romania over the last 12 months. The views are in 3 ways, and the statistics can be exported in CSV, SVG and PDF format.",
        "criteria": "Below are the criteria according to which the statistics can be selected.",
        "view": "For the viewing mode we have provided a map where you can select the city for which you want to view the data:",

    },
    "ro": {
        "start": " Aceasta pagina Web ajuta la vizualizarea si compararea multi-criteria a datelor publice referitoare la somajul din Romania pe ultimele 12 luni. Vizualizarile sunt in 3 maniere, iar statisticile vor putea fi exportate in formare CSV, SVG si PDF.",
        "criteria": "Mai jos sunt criteriile dupa care pot fi selectate statisticile.",
        "view": " Pentru modul de vizualizare am pus la dispozitie o harta in care puteti selecta orasul pentru care vreti sa vizualizati datele:",
    }
}