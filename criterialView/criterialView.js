//view format
let lineChartBtn = document.getElementById("LineChartBtn");
let barChartBtn = document.getElementById("BarChartBtn");
let pieChartBtn = document.getElementById("PieChartBtn");
let radarChartBtn = document.getElementById("RadarChartBtn");

let chartText = document.getElementById("chart_canva__text");

lineChartBtn.addEventListener("click", lineViewFormat);
barChartBtn.addEventListener("click", barViewFormat);
pieChartBtn.addEventListener("click", pieViewFormat);
radarChartBtn.addEventListener("click", radarViewFormat);

var data = [{
    label: 'Lorem',
    data: [12, 19, 3, 5, 2, 3],
    backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(255, 206, 86, 0.2)',
        'rgba(75, 192, 192, 0.2)',
        'rgba(153, 102, 255, 0.2)',
        'rgba(255, 159, 64, 0.2)'
    ],
    borderColor: [
        'rgba(255, 99, 132, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)',
        'rgba(75, 192, 192, 1)',
        'rgba(153, 102, 255, 1)',
        'rgba(255, 159, 64, 1)'
    ],
    borderWidth: 1
}];
var label = ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'];
var ctx = document.getElementById('statistics_chart').getContext('2d');
var lineChart;
var barChart;
var pieChart;
var radarChart;

function lineViewFormat(event){
    chartText.classList.add("chart_text_view");
    if(barChart != undefined){
        barChart.destroy();
        barChart = undefined;
    }
    if(pieChart != undefined){
        pieChart.destroy();
        pieChart = undefined;
    }
    if(radarChart != undefined){
        radarChart.destroy();
        radarChart = undefined;
    }
    lineChart = new Chart(ctx,{
        type:'line',
        data: {
            labels: label,
            datasets: data,
        },
        options:{
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            } 
        } 
    }); 
}

function barViewFormat(event){
    chartText.classList.add("chart_text_view");
    if(lineChart != undefined){
        lineChart.destroy();
        lineChart = undefined;
    }
    if(pieChart != undefined){
        pieChart.destroy();
        pieChart = undefined;
    }
    if(radarChart != undefined){
        radarChart.destroy();
        radarChart = undefined;
    }
    barChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: label,
            datasets: data,
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
}

function pieViewFormat(event){
    chartText.classList.add("chart_text_view");

    if(lineChart != undefined){
        lineChart.destroy();
        lineChart = undefined;
    }
    if(barChart != undefined){
        barChart.destroy();
        barChart = undefined;
    }
    if(radarChart != undefined){
        radarChart.destroy();
        radarChart = undefined;
    }
    pieChart = new Chart(ctx,{
        type:'pie',
        data: {
            labels: label,
            datasets: data,
        },
        options:{
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }, 
        } 
    });
    
}


function radarViewFormat(event){
    chartText.classList.add("chart_text_view");

    if(lineChart != undefined){
        lineChart.destroy();
        lineChart = undefined;
    }
    if(barChart != undefined){
        barChart.destroy();
        barChart = undefined;
    }
    if(pieChart != undefined){
        pieChart.destroy();
        pieChart = undefined;
    }
    radarChart = new Chart(ctx,{
        type:'radar',
        data: {
            labels: label,
            datasets: data,
        },
        options: {
            plugins: {
              filler: {
                propagate: false
              },
              'samples-filler-analyser': {
                target: 'chart-analyser'
              }
            },
            interaction: {
              intersect: false
            }
          } 
    });
    
}
//download format
let CSVdownloadBtn = document.getElementById("CSVbtn");
let SVGdownloadBtn = document.getElementById("SVGbtn");
let PDFdownloadBtn = document.getElementById("PDFbtn");


//add criterium
let criteriumBtn = document.getElementById("criteria__add__btn");
var counter=0;
var criteriaLimit = document.getElementById("criteria-limit");
var bkgColor = document.getElementById("grey-bkg");

document.addEventListener("click", exitCriteriaLimit);
criteriumBtn.addEventListener("click", addCriterium);


let optionLng = {
    "en": { 0: "Woman", 1: "Man", 2: "No studies", 3: "Primary education", 4: "Secondary education", 5: "Highschool education", 6: "Postsecondary education", 7: "Vocational education", 8: "University education", 9: "Urban", 10: "Rural", 11: "under 25", 12: "25-29", 13: "30-39", 14: "40-49", 15: "50-55",16: "over 50", 17: 'Gender', 18: 'Studies level', 19: 'Environment', 20:'Age range' },
    "ro": { 0: "Femeie", 1: "Bărbat", 2: "Fără studii", 3: "Învățământ primar", 4: "Învățământ gimnazial", 5: "Învățământ liceal", 6: "Învățământ postliceal", 7: "Învățământ profesional/arte și meserii", 8: "Învățământ universitar", 9: "Urban", 10: "Rural", 11: "sub 25", 12: "25-29", 13: "30-39", 14: "40-49", 15: "50-55", 16: "peste 50", 17 : 'Gen', 18: 'Studii', 19 : 'Mediu', 20 : 'Categorii de vârsta' }
}
let lang="ro";
function setLang(string){
    lang = string;
}

function addCriterium (event){
    if(counter >= 3){
       criteriaLimit.classList.add("criteria_limit_shown");
       bkgColor.classList.add("criteria-limit_grey-bkg_shown");
       event.stopPropagation();
    }
    else{
    counter = counter+1;
    document.getElementById("criteria__line").insertAdjacentHTML("afterend",`
    <div class="new_drop-list" id="dropList`+ counter +`"> 
    <div class="criteria__arrow"> 
    <select class="drop-list" name="addSelect">
    <optgroup label="` + optionLng[lang][17] +`" name="genderOpt"><option class="text text_option" id="woman">`+ optionLng[lang][0] +`</option>
            <option class="text text_option" id="man">`+optionLng[lang][1]+`</option>
       </optgroup>
       <optgroup label="`+optionLng[lang][18]+`" name="studiesOpt">
           <option class="text text_option" id="noStudies">`+ optionLng[lang][2] +`</option>
           <option class="text text_option" id="primarySchool">`+ optionLng[lang][3] +`</option>
           <option class="text text_option" id="secondarySchool">`+ optionLng[lang][4] +`</option>
           <option class="text text_option" id="highschool">`+ optionLng[lang][5] +`</option>
           <option class="text text_option" id="postsecodarySchool">`+ optionLng[lang][6] +`</option>
           <option class="text text_option" id="vocationalEducation">`+ optionLng[lang][7] +`</option>
           <option class="text text_option" id="universityEducation">`+ optionLng[lang][8] +`</option>
       </optgroup>
       <optgroup label="`+ optionLng[lang][19] +`" name="envOpt">
           <option class="text text_option">`+ optionLng[lang][9] +`</option>
           <option class="text text_option">`+ optionLng[lang][10] +`</option>
       </optgroup>
       <optgroup label="`+ optionLng[lang][20] +`" name="ageOpt">
           <option class="text text_option" id="under" >`+ optionLng[lang][11] +`</option>
               <option class="text text_option">`+ optionLng[lang][12] +`</option>
               <option class="text text_option">`+ optionLng[lang][13] +`</option>
               <option class="text text_option">`+ optionLng[lang][14] +`</option>
               <option class="text text_option">`+ optionLng[lang][15] +`</option>
               <option class="text text_option" id="over">`+ optionLng[lang][16] +`</option>
       </optgroup>
       </select>
       <span class="arrow arrow-add"></span>
       </div>
       <button type="button" value=`+ counter +` class ="criteria_form_btn deleteBtn" id="delete_btn">-</button>
       </div>`);
    }

}

function exitCriteriaLimit(event){
    if(!criteriaLimit.contains(event.target)) 
    {bkgColor.classList.remove("criteria-limit_grey-bkg_shown");
    criteriaLimit.classList.remove("criteria_limit_shown");
  }
}

//delete criterium
document.addEventListener('click', deleteCriterium)
function deleteCriterium(event){
    if(event.target && event.target.id=='delete_btn'){
        let deleteCrtBtn = document.getElementById("delete_btn");
        let number = deleteCrtBtn.getAttribute('value');
        let criteriumDel = "dropList"+number.toString();
        document.getElementById(criteriumDel).remove();
        counter = counter -1;
    }
}

//criteria options
var criterium0Value = document.getElementById("criterium0").nodeValue;
var criterium1Value = document.getElementById("criterium1").nodeValue;


//create 
let createStatistics = document.getElementById("criteria_create");

/*Last 12 months*/
let months ={ 
    "ro": { 0: "Ianuarie", 1: "Februarie", 2: "Martie", 3: "Aprilie", 4: "Mai", 5: "Iunie", 6: "Iulie", 7: "August", 8: "Septembrie", 9: "Octombrie", 10: "Noiembrie", 11: "Decembrie" },
    "en": { 0: "January", 1: "February", 2: "March", 3: "April", 4: "May", 5: "June", 6: "July", 7: "August", 8: "September", 9: "October", 10: "November", 11: "December" }
}
var d = new Date();
var currentMonth = d.getMonth() + 1;
var currentYear = d.getFullYear()

for (var i = 0; i < 15; i++) {
    var option = document.createElement('option');
    option.innerHTML = months["ro"][currentMonth].concat(' ').concat(currentYear.toString());
    option.classList.add("text");
    option.classList.add("text_option");
    var select = document.getElementById("drop-perioada");
    select.insertAdjacentElement("beforeend", option);
    currentMonth = currentMonth - 1;
    if (currentMonth == -1) {
        currentMonth = 11;
        currentYear = currentYear - 1;
    }
}



function changeMonth() {
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


let langCrtView = {
    "en": {
        "dataRepr": "Choose chart type:",
        "chartCanvaText": "Choose a chart type",
        "download": "Download format:",
        "criteriaTitle": "Comparing data criteria",
        "criterium0": "Choose period",
        "lastMonth": "Last month",
        "twoMonths": "Last two months",
        "sixMonths": "Last six months",
        "oneYear": "Last year",
        "criterium1": "Principal criterium",
        "gender": "Gender",
        "environment": "Environment",
        "studies": "Studies level",
        "age": "Age range",
        "newCriteria": "Add new criterium",
        "criteriaCreate": "Show statistics",
        "criteriaLimitText": "You can choose maximum three secondary criteria"
    },
    "ro": {
        "dataRepr": "Alegeti formatul pentru reprezentarea datelor:",
        "chartCanvaText": "Alegeți un mod de afișare",
        "download": "Format descărcare:",
        "criteriaTitle": "Criterii pentru compararea datelor",
        "criterium0": "Perioada pentru comparare",
        "lastMonth": "Ultima luna",
        "twoMonths": "Ultimele 2 luni",
        "sixMonths": "Ultimele 6 luni",
        "oneYear": "Ultimul an",
        "criterium1": "Criteriu principal",
        "gender": "Gen",
        "environment": "Mediu",
        "studies": "Studii",
        "age": "Categorii de vârstă",
        "newCriteria": "Adauga un criteriu nou",
        "criteriaCreate": "Prezintă statistici",
        "criteriaLimitText": "Pot fi alese maxim 3 criterii secundare" 
    }
}

function changeSelect() {
    let mySelect = document.getElementsByName("addSelect");
    if (mySelect != null) {
        let genderOpt = document.getElementsByName("genderOpt");
        let studiesOpt = document.getElementsByName("studiesOpt");
        let envOpt = document.getElementsByName("envOpt");
        let ageOpt = document.getElementsByName("ageOpt");

        for (var z = 0; z < 3; z++) {
            if (mySelect[z] != null) {
                let selectLen = mySelect[z].length;
                for (var j = 0; j < selectLen; j++) {
                    mySelect[z].options[j].innerHTML=optionLng[lang][j];
                }
            genderOpt[z].setAttribute('label',optionLng[lang][17]);
            studiesOpt[z].setAttribute('label', optionLng[lang][18]);
            envOpt[z].setAttribute('label', optionLng[lang][19]);
            ageOpt[z].setAttribute('label', optionLng[lang][20]);
        }
        }
    }
}
let dataRepr = document.getElementById("data-repr");
let chartCanvaText = document.getElementById("chart_canva__text");
let download = document.getElementById("download");
let criteriaTitle = document.getElementById("criteria-title");
let criterium0 = document.getElementById("criterium0");
let lastMonth = document.getElementById("lastMonth");
let twoMonths = document.getElementById("2Months");
let sixMonths = document.getElementById("6months");
let oneYear = document.getElementById("oneYear");
let criterium1 = document.getElementById("criteriumOne");
let gender = document.getElementById("gender");
let environment = document.getElementById("environment");
let studies = document.getElementById("studies");
let age = document.getElementById("age");
let newCriteria = document.getElementById("newCriteria");
let criteriaCreate = document.getElementById("criteria_create");
let criteriaLimitText = document.getElementById("criteria-limit-text");

link.forEach(e1 => {

    e1.addEventListener("click", () => {
        let a = e1.getAttribute("lang");
        dataRepr.textContent = langCrtView[a].dataRepr;
        chartCanvaText.textContent = langCrtView[a].chartCanvaText;
        download.textContent = langCrtView[a].download;
        criteriaTitle.textContent = langCrtView[a].criteriaTitle;
        criterium0.textContent = langCrtView[a].criterium0;
        lastMonth.textContent = langCrtView[a].lastMonth;
        twoMonths.textContent = langCrtView[a].twoMonths;
        sixMonths.textContent = langCrtView[a].sixMonths;
        viewMenu.oneYear = langCrtView[a].oneYear;
        criterium1.textContent = langCrtView[a].criterium1;
        gender.textContent = langCrtView[a].gender;
        environment.textContent = langCrtView[a].environment;
        studies.textContent = langCrtView[a].studies;
        age.textContent = langCrtView[a].age;
        newCriteria.textContent = langCrtView[a].newCriteria;
        criteriaCreate.textContent = langCrtView[a].criteriaCreate;
        criteriaLimitText.textContent = langCrtView[a].criteriaLimitText;
        setLang(a);
        changeMonth();
        changeSelect();

    });

})
