//view format
let lineChartBtn = document.getElementById("LineChartBtn");
let barChartBtn = document.getElementById("BarChartBtn");
let pieChartBtn = document.getElementById("PieChartBtn");
let chartText = document.getElementById("chart_canva__text");

lineChartBtn.addEventListener("click", lineViewFormat);
barChartBtn.addEventListener("click", barViewFormat);
pieChartBtn.addEventListener("click", pieViewFormat);

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

function addCriterium (event){
    if(counter >= 3){
       criteriaLimit.classList.add("criteria_limit_shown");
       bkgColor.classList.add("criteria-limit_grey-bkg_shown");
       event.stopPropagation();
    }
    else{
    counter = counter+1;
    document.getElementById("criteria__line").insertAdjacentHTML("afterend",`
  <div> 
         <div class="criteria__arrow"> 
         <select class=\"drop-list\">
         <optgroup label=\"Gen\"><option class=\"text text_option\">Femeie</option>
                 <option class=\"text text_option\">Barbat</option>
            </optgroup>
            <optgroup label=\"Studii\">
                <option class=\"text text_option\">Fara studii</option>
                <option class=\"text text_option\">Invatamant primar</option>
                <option class=\"text text_option\">Invatamant gimnazial</option>
                <option class=\"text text_option\">Invatamant liceal</option>
                <option class=\"text text_option\">Invatamant postliceal</option>
                <option class=\"text text_option\">Invatamant profesional/arte si meserii</option>
                <option class=\"text text_option\">Invatamant univesitar</option>
            </optgroup>
            <optgroup label=\"Mediu\">
                <option class=\"text text_option\">Urban</option>
                <option class=\"text text_option\">Rural</option>
            </optgroup>
            <optgroup label=\"Categorii de vârstă\">
                <option class=\"text text_option\">Sub 25</option>
                    <option class=\"text text_option\">25-29</option>
                    <option class=\"text text_option\">30-39</option>
                    <option class=\"text text_option\">40-49</option>
                    <option class=\"text text_option\">50-55</option>
                    <option class=\"text text_option\">Peste 50</option>
            </optgroup>
            </select>
            <span class=\"arrow arrow-add\"></span>
            </div>
            </div>`);}

    }

function exitCriteriaLimit(event){
    if(!criteriaLimit.contains(event.target)) 
    {bkgColor.classList.remove("criteria-limit_grey-bkg_shown");
    criteriaLimit.classList.remove("criteria_limit_shown");}
}
//criteria options
var criterium0Value = document.getElementById("criterium0").nodeValue;
var criterium1Value = document.getElementById("criterium1").nodeValue;


//create 
let createStatistics = document.getElementById("criteria_create");

/*Last 12 months*/
let months = ["Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie", "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie"];
var d = new Date();
var currentMonth = d.getMonth();
var currentYear = d.getFullYear()

for(var i=0 ; i <12; i++){
     var option = document.createElement('option');
     option.innerHTML=months[currentMonth].concat(' ').concat(currentYear.toString());
     option.classList.add("text");
     option.classList.add("text_option");
     var select = document.getElementById("drop-perioada");
     select.insertAdjacentElement("beforeend",option);
    currentMonth = currentMonth-1;
    if(currentMonth == -1){
        currentMonth = 11;
        currentYear = currentYear-1;
    }

}