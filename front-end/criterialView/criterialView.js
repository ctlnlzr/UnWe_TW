//charts format
let lineChartBtn = document.getElementById("LineChartBtn");
let barChartBtn = document.getElementById("BarChartBtn");
let pieChartBtn = document.getElementById("PieChartBtn");
let radarChartBtn = document.getElementById("RadarChartBtn");

let chartText = document.getElementById("chart_canva__text");

lineChartBtn.addEventListener("click", lineViewFormat);
barChartBtn.addEventListener("click", barViewFormat);
pieChartBtn.addEventListener("click", pieViewFormat);
radarChartBtn.addEventListener("click", radarViewFormat);

//FORMAT Line chart
var dataLine = [
      {
        label: 'maiMic25',
        data: [4752, 4700, 4720, 4502, 4512],
        borderColor: 'rgba(255, 99, 132, 1)',
        backgroundColor: 'rgba(0, 0, 0, 0)',
      },
      {
        label: 'intre25si29',
        data: [900, 791, 891, 901, 921],
        borderColor: 'rgba(54, 162, 235, 1)',
        backgroundColor: 'rgba(0, 0, 0, 0)',
      },
      {
        label: 'intre30si39',
        data: [218, 208, 208, 208, 208],
        borderColor: 'rgba(255, 206, 86, 1)',
        backgroundColor: 'rgba(0, 0, 0, 0)',
      },
      {
        label: 'intre40si49',
        data: [681, 681, 681, 681, 681],
        borderColor: 'rgba(75, 192, 192, 1)',
    backgroundColor: 'rgba(0, 0, 0, 0)',    
      },
      {
        label: 'intre50si55',
        data: [1138, 1138, 1138, 1138, 1138],
        borderColor: 'rgba(153, 102, 255, 1)',
        backgroundColor: 'rgba(0, 0, 0, 0)',
      },
      {
        label: 'peste55',
        data: [884, 745, 785, 900, 859],
        borderColor: 'rgba(255, 159, 64, 1)',
        backgroundColor: 'rgba(0, 0, 0, 0)',
      }
    ];
    var labelsLine = ["Mai", "Iunie", "Iulie", "August", "Septembrie"];


//FORMAT PIE,BAR,RADIX
var dataPie = [
    {
      label: 'Dataset 1',
      data: [4572, 991, 208, 681, 1138, 884],
      backgroundColor: ['rgba(255, 159, 64, 0.5)', 'rgba(153, 102, 255, 0.5)', 'rgba(75, 192, 192, 0.5)', 'rgba(255, 206, 86, 0.5)','rgba(54, 162, 235, 0.5)', 'rgba(255, 99, 132, 0.5)' ],
    }
  ]
var labelsPie = ['maiMic25', 'intre25si29', 'intre30si39','intre40si49', 'intre50si55','peste55'];


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
            labels: labelsLine,
            datasets: dataLine,
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
            labels: labelsPie,
            datasets: dataPie,
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
            labels: labelsPie,
            datasets: dataPie,
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
            labels: labelsPie,
            datasets: dataPie,
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


let optionLng = {
    "en": { 0: "Woman", 1: "Man", 2: "No studies", 3: "Primary education", 4: "Secondary education", 5: "Highschool education", 6: "Postsecondary education", 7: "Vocational education", 8: "University education", 9: "Urban", 10: "Rural", 11: "under 25", 12: "25-29", 13: "30-39", 14: "40-49", 15: "50-55",16: "over 50", 17: 'Gender', 18: 'Studies level', 19: 'Environment', 20:'Age range' },
    "ro": { 0: "Femeie", 1: "Bărbat", 2: "Fără studii", 3: "Învățământ primar", 4: "Învățământ gimnazial", 5: "Învățământ liceal", 6: "Învățământ postliceal", 7: "Învățământ profesional/arte și meserii", 8: "Învățământ universitar", 9: "Urban", 10: "Rural", 11: "sub 25", 12: "25-29", 13: "30-39", 14: "40-49", 15: "50-55", 16: "peste 50", 17 : 'Gen', 18: 'Studii', 19 : 'Mediu', 20 : 'Categorii de vârsta' }
}
let lang="ro";
function setLang(string){
    lang = string;
}


//criteria values & get data
var timePeriod = document.getElementById("time-period");
var countyForComparison = document.getElementById("county-for-comparison");
var principalCriterion = document.getElementById("principal-criterion");

document.getElementById('criteria_create').addEventListener('click', getChartData);
function getChartData (event){
        const Http = new XMLHttpRequest();
        const url='https://unwetw.herokuapp.com/rest/age';
        var selected = [...countyForComparison.options]
        .filter(option => option.selected)
        .map(option => option.value);

        const newURL = url.concat("/", timePeriod.value, "/", principalCriterion.value, "/", selected); 
        console.log(newURL);
        /* Http.open("GET", url);
        console.log('Aduc date intr o fericire');
        Http.send();
        Http.onreadystatechange = function() {
             parseDataLine(Http.responseText, selected, timePeriod.value);
             parseDataPie(Http.responseText, selected, timePeriod.value);
            } */
}


const borderColors = ['rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)' ]
    //16 ultimele 2, 17 ultimele 6 luni, 18 ultimul an
    function parseDataLine(text, counties, monthsID){
       var data = JSON.parse(text);
       /*
       luna - judet ->titlu
       mai multe luni -> mai multe elemente in data [] -> labelsLine cu lunile
       mai multe judete -> label din {} va fi numele judetului -> luna - criteriu -> titlu
       mai multe luni + mai multe judete : label {nume judet}, labels lunile, titlu - criteriu 
       */
       labelsLine.clear();
       dataLine.clear();
       var d = new Date();
       var currentMonth = d.getMonth() + 1;
       if (monthsID >= 16 ){
            var noOfMonths = 1;
            switch (monthsID){
                case 16: noOfMonths = 2; break;
                case 17: noOfMonths = 6; break;
                case 18: noOfMonths = 12; break;
                default: noOfMonths = 1;
            }
            for (var i = 0; i < noOfMonths ; i++ ){
                labelsLine.push( months["ro"][currentMonth + i]);
               }   
            
               if(counties.length() > 1){ // mai multe luni, mai multe judete
                for (var i = 0; i < counties.length(); i++){
                   var values = [];
                   for (var j = 0; j< data.length(); j++){
                        for (var k=0; k < data[i].judete.length(); k++){
                            if(data[i].judete[k].nume == counties[i]){
                                values.push(data[i].judete[k].numar);
                            }
                        }
                   }
                   dataLine.push({label : counties[i], 
                    data: values, 
                    borderColor: borderColors[i], 
                    backgroundColor: 'rgba(0, 0, 0, 0)'});
                }
            }
            else { // mai multe luni, un judet
                for( var i = 0; i < data.criterii.length(); i++){
                    dataLine.push({label : data.criterii[i].nume, 
                        data: data.criterii[i].numar, 
                        borderColor: borderColors[i], 
                        backgroundColor: 'rgba(0, 0, 0, 0)'});
                }
            }
       }
       else {   
        if( counties.length() > 1){//o luna, mai multe judete
                   for (var j = 0; j< data.judete.length(); j++){
                    dataLine.push({label : data.judete[j].nume, 
                        data: data.judete[j].numar, 
                        borderColor: borderColors[i], 
                        backgroundColor: 'rgba(0, 0, 0, 0)'});     
                   }
                  
        } else {// o luna, un judet
            for (var j = 0; j< data.criterii.length(); j++){
                dataLine.push({label : data.criterii[j].nume, 
                    data: data.criterii[j].numar, 
                    borderColor: borderColors[i], 
                    backgroundColor: 'rgba(0, 0, 0, 0)'});     
               }
        }

       }

    }

function chooseAnotherChartType(){
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
    if(lineChart != undefined){
        lineChart.destroy();
        lineChart = undefined;
    }

    document.getElementById("chart_canva__text").innerText="Mod de reprezentare incompatibil cu criteriile alese. Alegeti un alt mod de reprezentare.";
}
const backgroundColors = ['rgba(255, 99, 132, 0.5)', 'rgba(54, 162, 235, 0.5)', 'rgba(255, 206, 86, 0.5)', 'rgba(75, 192, 192, 0.5)', 'rgba(153, 102, 255, 0.5)', 'rgba(255, 159, 64, 0.5)' ];
function parseDataPie(text, counties, monthsID){
    var data = JSON.parse(text);
    dataPie.clear();
    labelsPie.clear();
    if(counties.length() > 1 && monthsID >= 16) {// trigger default can not be used
        chooseAnotherChartType();
    }else{
        var values = [];   
        var bkgColors = [];
        if (counties.length() > 1) { //o luna, mai multe judete
        for (var i = 0; i < data.judete.length(); i++){
           values.push(data.judete[i].numar);
           labelsPie.push(data.judete[i].nume);
           bkgColors.push(backgroundColors[i]);
        }
        dataPie.push({label : data.luna, data: values, backgroundColor: bkgColors });
       } else
          if (monthsID >= 16){ // mai multe luni, un judet
            chooseAnotherChartType();
          } else { // o luna un judet
            for (var i = 0; i < data.criterii.length(); i++){
                values.push(data.criterii[i].numar);
                labelsPie.push(data.criterii[i].nume);
                bkgColors.push(backgroundColors[i]);
             }
          } 
    }
}

//modify principal criterion depending on multiselect
function modifyPrincipalCriterion(select){
    var selected = [...select.options]
        .filter(option => option.selected)
        .map(option => option.value);
   
     if((selected.length > 1) || (select.value > 15) ){
        document.getElementById("principal-criterion").innerHTML= ` <optgroup label="` + optionLng[lang][17] +`" name="genderOpt"><option class="text text_option" id="woman">`+ optionLng[lang][0] +`</option>
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
   </optgroup>`;
     } else {
        document.getElementById("principal-criterion").innerHTML=` <option class="text text_option" value="gen" id="gender">Gen</option>
        <option class="text text_option" value="mediu" id="environment">Mediu</option>
        <option class="text text_option" value="educatie" id="studies">Studii</option>
        <option class="text text_option" value="varsta" id="age">Categorii de vârstă</option>`;}
}
/*Last 15 months*/
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
    option.value = i+1;
    var select = document.getElementById("time-period");
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
    let mySelect = document.getElementById("time-period");
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
/*Droplist county*/
let countyList= ["Toată țara","Alba","Arad","Arges","Bacau","Bihor","Bistrita","Botosani","Braila","Brasov","Buzau","Calarasi","Caras-Severin","Cluj","Constanta","Covasna","Dambovita","Dolj","Galati","Giurgiu","Gorj","Harghita","Hunedoara","Ialomita","Iasi", "Ilfov","Maramures","Mehedinti","Bucuresti","Mures","Neamt","Olt","Prahova","Salaj","Satu Mare","Sibiu","Suceava","Teleorman","Timis","Tulcea","Valcea","Vaslui","Vrancea"];
for (var it = 0; it < countyList.length; it++) {
    var optionCounty = document.createElement('option');
    optionCounty.innerHTML = countyList[it];
    optionCounty.classList.add("text");
    optionCounty.classList.add("text_option");
    optionCounty.setAttribute('value', countyList[it].toUpperCase());
    var selectCounty = document.getElementById("county-for-comparison");
    selectCounty.insertAdjacentElement("beforeend", optionCounty);
}




let langCrtView = {
    "en": {
        "dataRepr": "Choose chart type:",
        "chartCanvaText": "Choose a chart type",
        "download": "Download format:",
        "criteriaTitle": "Comparing data criteria",
        "criterion0": "Choose period",
        "criterion2":"County",
        "country": "Entire country",
        "lastMonth": "Last month",
        "twoMonths": "Last two months",
        "sixMonths": "Last six months",
        "oneYear": "Last year",
        "criterion1": "Principal criterion",
        "gender": "Gender",
        "environment": "Environment",
        "studies": "Studies level",
        "age": "Age range",
        "newCriteria": "Add new criterion",
        "criteriaCreate": "Show statistics",
        "criteriaLimitText": "You can choose maximum three secondary criteria"
    },
    "ro": {
        "dataRepr": "Alegeti formatul pentru reprezentarea datelor:",
        "chartCanvaText": "Alegeți un mod de afișare",
        "download": "Format descărcare:",
        "criteriaTitle": "Criterii pentru compararea datelor",
        "criterion0": "Perioada pentru comparare",
        "criterion2": "Județ",
        "country": "Toată țara",
        "lastMonth": "Ultima luna",
        "twoMonths": "Ultimele 2 luni",
        "sixMonths": "Ultimele 6 luni",
        "oneYear": "Ultimul an",
        "criterion1": "Criteriu principal",
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

function changeCounty(){
     let countySelect = document.getElementById("county-for-comparison");
     countySelect.options[0].innerHTML=langCrtView[lang].country;
}
let dataRepr = document.getElementById("data-repr");
let chartCanvaText = document.getElementById("chart_canva__text");
let download = document.getElementById("download");
let criteriaTitle = document.getElementById("criteria-title");
let criterion0 = document.getElementById("criterion0");
let lastMonth = document.getElementById("lastMonth");
let twoMonths = document.getElementById("2Months");
let sixMonths = document.getElementById("6months");
let oneYear = document.getElementById("oneYear");
let criterion1 = document.getElementById("criterionOne");
let gender = document.getElementById("gender");
let environment = document.getElementById("environment");
let studies = document.getElementById("studies");
let age = document.getElementById("age");
let newCriteria = document.getElementById("newCriteria");
let criteriaCreate = document.getElementById("criteria_create");
let criteriaLimitText = document.getElementById("criteria-limit-text");
let criterion2 = document.getElementById("criterion2");

link.forEach(e1 => {

    e1.addEventListener("click", () => {
        let a = e1.getAttribute("lang");
        dataRepr.textContent = langCrtView[a].dataRepr;
        chartCanvaText.textContent = langCrtView[a].chartCanvaText;
        download.textContent = langCrtView[a].download;
        criteriaTitle.textContent = langCrtView[a].criteriaTitle;
        criterion0.textContent = langCrtView[a].criterion0;
        criterion2.textContent = langCrtView[a].criterion2;
        lastMonth.textContent = langCrtView[a].lastMonth;
        twoMonths.textContent = langCrtView[a].twoMonths;
        sixMonths.textContent = langCrtView[a].sixMonths;
        viewMenu.oneYear = langCrtView[a].oneYear;
        criterion1.textContent = langCrtView[a].criterion1;
        gender.textContent = langCrtView[a].gender;
        environment.textContent = langCrtView[a].environment;
        studies.textContent = langCrtView[a].studies;
        age.textContent = langCrtView[a].age;
        newCriteria.textContent = langCrtView[a].newCriteria;
        criteriaCreate.textContent = langCrtView[a].criteriaCreate;
        criteriaLimitText.textContent = langCrtView[a].criteriaLimitText;
        setLang(a);
        changeCounty();
        changeMonth();
        changeSelect();

    });

})

