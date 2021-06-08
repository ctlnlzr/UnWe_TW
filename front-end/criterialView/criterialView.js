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
var selectedChart = 0;
//dummy data
var dataLine = [{
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


//Format pie, radix, bar, dummy data
var dataPie = [{
    label: 'Dataset 1',
    data: [4572, 991, 208, 681, 1138, 884],
    backgroundColor: ['rgba(255, 159, 64, 0.5)', 'rgba(153, 102, 255, 0.5)', 'rgba(75, 192, 192, 0.5)', 'rgba(255, 206, 86, 0.5)', 'rgba(54, 162, 235, 0.5)', 'rgba(255, 99, 132, 0.5)'],
}]
var labelsPie = ['maiMic25', 'intre25si29', 'intre30si39', 'intre40si49', 'intre50si55', 'peste55'];


var ctx = document.getElementById('statistics_chart').getContext('2d');
var lineChart;
var barChart;
var pieChart;
var radarChart;

function lineViewFormat(event) {
    selectedChart = 1;
    chartText.classList.add("chart_text_view");
    if (barChart != undefined) {
        barChart.destroy();
        barChart = undefined;
    }
    if (pieChart != undefined) {
        pieChart.destroy();
        pieChart = undefined;
    }
    if (radarChart != undefined) {
        radarChart.destroy();
        radarChart = undefined;
    }
    lineChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labelsLine,
            datasets: dataLine,
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

function barViewFormat(event) {
    selectedChart = 2;
    chartText.classList.add("chart_text_view");
    if (lineChart != undefined) {
        lineChart.destroy();
        lineChart = undefined;
    }
    if (pieChart != undefined) {
        pieChart.destroy();
        pieChart = undefined;
    }
    if (radarChart != undefined) {
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

function pieViewFormat(event) {
    selectedChart = 3;

    chartText.classList.add("chart_text_view");

    if (lineChart != undefined) {
        lineChart.destroy();
        lineChart = undefined;
    }
    if (barChart != undefined) {
        barChart.destroy();
        barChart = undefined;
    }
    if (radarChart != undefined) {
        radarChart.destroy();
        radarChart = undefined;
    }
    pieChart = new Chart(ctx, {
        type: 'pie',
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
            },
        }
    });

}


function radarViewFormat(event) {
    chartText.classList.add("chart_text_view");
    selectedChart = 4;

    if (lineChart != undefined) {
        lineChart.destroy();
        lineChart = undefined;
    }
    if (barChart != undefined) {
        barChart.destroy();
        barChart = undefined;
    }
    if (pieChart != undefined) {
        pieChart.destroy();
        pieChart = undefined;
    }

    radarChart = new Chart(ctx, {
        type: 'radar',
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




let optionLng = {
    "en": { 0: "Woman", 1: "Man", 2: "No studies", 3: "Primary education", 4: "Secondary education", 5: "Highschool education", 6: "Postsecondary education", 7: "Vocational education", 8: "University education", 9: "Urban", 10: "Rural", 11: "under 25", 12: "25-29", 13: "30-39", 14: "40-49", 15: "50-55", 16: "over 50", 17: 'Gender', 18: 'Studies level', 19: 'Environment', 20: 'Age range' },
    "ro": { 0: "Femeie", 1: "Bărbat", 2: "Fără studii", 3: "Învățământ primar", 4: "Învățământ gimnazial", 5: "Învățământ liceal", 6: "Învățământ postliceal", 7: "Învățământ profesional/arte și meserii", 8: "Învățământ universitar", 9: "Urban", 10: "Rural", 11: "sub 25", 12: "25-29", 13: "30-39", 14: "40-49", 15: "50-55", 16: "peste 50", 17: 'Gen', 18: 'Studii', 19: 'Mediu', 20: 'Categorii de vârsta' }
}
let lang = "ro";

function setLang(string) {
    lang = string;
}


//criteria values & get data
var timePeriod = document.getElementById("time-period");
var countyForComparison = document.getElementById("county-for-comparison");
var principalCriterion = document.getElementById("principal-criterion");
var dataInfo;
document.getElementById('criteria_create').addEventListener('click', getChartData);

function getChartData(event) {
    const Http = new XMLHttpRequest();
    const url = 'http://localhost:8090/api/v1/criterion';
    var counties = "";
    var selected = [...countyForComparison.options]
        .filter(option => option.selected)
        .map(option => option.value);
    selected.map(option => counties += "county=" + option + "&");
    counties = counties.slice(0, -1);
    const newURL = url.concat("?monthID=", timePeriod.value, "&", principalCriterion.value, "&", counties);
    console.log(newURL);
    Http.open("GET", newURL);
    Http.setRequestHeader('Accept', 'application/json');
    Http.onreadystatechange = function() {
        if (Http.readyState == 4) {
            dataInfo = Http.responseText;
            parseDataLine(Http.responseText, selected, timePeriod.value);
            parseDataPie(Http.responseText, selected, timePeriod.value);
        }
    }
    Http.send();
}


const borderColors = ['rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)']
    //16 ultimele 2, 17 ultimele 6 luni, 18 ultimul an
function parseDataLine(text, counties, monthsID) {
    var data = JSON.parse(text);
    /*
    luna - judet ->titlu
    mai multe luni -> mai multe elemente in data [] -> labelsLine cu lunile
    mai multe judete -> label din {} va fi numele judetului -> luna - criteriu -> titlu
    mai multe luni + mai multe judete : label {nume judet}, labels lunile, titlu - criteriu 
    */
    labelsLine = [];
    dataLine = [];
    var d = new Date();
    var currentMonth = d.getMonth() + 1;
    if (monthsID >= 16) {
        var noOfMonths;
        console.log(monthsID);
        switch (monthsID) {
            case "16":
                { noOfMonths = 2; break; }
            case "17":
                { noOfMonths = 6; break; }
            case "18":
                { noOfMonths = 12; break; }
            default:
                noOfMonths = 1;
        }

        if (counties.length > 1) { // mai multe luni, mai multe judete

            var dataMtx = new Array(counties.length);
            for (i = 0; i < dataMtx.length; i++) {
                dataMtx[i] = new Array(noOfMonths);
            }

            var j = 0;
            for (i = 0; i < data.groups.length; i++) {
                if (j == counties.length) j = 0;
                const obj = data.groups[i];
                dataMtx[j][obj["month"] - 1] = obj["number"];
                j++;
            }

            for (var i = 0; i < counties.length; i++) {
                dataLine.push({
                    label: counties[i],
                    data: dataMtx[i],
                    borderColor: borderColors[i],
                    backgroundColor: 'rgba(0, 0, 0, 0)'
                });
            }
            for (var i = 0; i < noOfMonths; i++) {
                if (currentMonth - i == -1) currentMonth = 12 + i - 1;
                labelsLine.push(months["ro"][currentMonth - i]);
            }
        } else { // mai multe luni, un judet + labelsLine
            const keys = Object.keys(data.groups[0]);
            var dataMtx = new Array(keys.length - 2);
            for (i = 0; i < dataMtx.length; i++) {
                dataMtx[i] = new Array(data.groups.length);
            }
            for (var i = 0; i < data.groups.length; i++) {
                const obj = data.groups[i];
                for (var j = 2; j < keys.length; j++) {
                    dataMtx[j - 2][i] = obj[keys[j]];
                }
            }

            for (j = 2; j < keys.length; j++) {
                dataLine.push({
                    label: keys[j],
                    data: dataMtx[j - 2],
                    borderColor: borderColors[j - 2],
                    backgroundColor: 'rgba(0, 0, 0, 0)'
                });
            }
            for (var i = 0; i < noOfMonths; i++) {
                if (currentMonth - i == -1) currentMonth = 12 + i - 1;
                labelsLine.push(months["ro"][currentMonth - i]);
            }
        }
    } else {
        if (counties.length > 1) { //o luna, mai multe judete
            for (var i = 0; i < data.groups.length; i++) {
                const keys = Object.keys(data.groups[i]);
                const obj = data.groups[i];
                dataLine.push({
                    label: obj["county"],
                    data: [obj["number"], obj["number"], obj["number"], obj["number"], obj["number"]],
                    borderColor: borderColors[i],
                    backgroundColor: 'rgba(0, 0, 0, 0)'
                });

            }
        } else { // o luna, un judet
            const keys = Object.keys(data.groups[0]);
            const obj = data.groups[0];
            for (var j = 2; j < keys.length; j++) {
                dataLine.push({
                    label: keys[j],
                    data: [obj[keys[j]], obj[keys[j]], obj[keys[j]], obj[keys[j]], obj[keys[j]], obj[keys[j]]],
                    borderColor: borderColors[j - 2],
                    backgroundColor: 'rgba(0, 0, 0, 0)'
                });
            }
        }

    }
    switch (selectedChart) {
        case 1:
            lineViewFormat();
            break;
        case 2:
            barViewFormat();
            break;
        case 3:
            pieViewFormat();
            break;
        case 4:
            radarViewFormat();
            break;
    }
}

const backgroundColors = ['rgba(255, 99, 132, 0.5)', 'rgba(54, 162, 235, 0.5)', 'rgba(255, 206, 86, 0.5)', 'rgba(75, 192, 192, 0.5)', 'rgba(153, 102, 255, 0.5)', 'rgba(255, 159, 64, 0.5)'];

function parseDataPie(text, counties, monthsID) {
    var data = JSON.parse(text);
    dataPie = [];
    labelsPie = [];
    if (counties.length > 1 && monthsID >= 16) { // trigger default can not be used
        labelsPie.push("Choose another chart type");
    } else {
        var values = [];
        var bkgColors = [];
        if (counties.length > 1) { //o luna, mai multe judete
            for (var i = 0; i < data.groups.length; i++) {
                const obj = data.groups[i];
                values.push(obj["number"]);
                labelsPie.push(obj["county"]);
                bkgColors.push(backgroundColors[i]);
            }
            dataPie.push({ label: data.luna, data: values, backgroundColor: bkgColors });
        } else
        if (monthsID >= 16) { // mai multe luni, un judet
            labelsPie.push("Choose another chart type");
        } else { // o luna un judet
            const obj = data.groups[0];
            const keys = Object.keys(obj);
            var values = [];
            var bkgColors = [];
            for (var i = 2; i < keys.length; i++) {
                values.push(obj[keys[i]]);
                console.log(obj[keys[i]]);
                labelsPie.push(keys[i]);
                bkgColors.push(backgroundColors[i - 2]);
            }
            dataPie.push({ label: data.luna, data: values, backgroundColor: bkgColors });
        }
    }
    switch (selectedChart) {
        case 1:
            lineViewFormat();
            break;
        case 2:
            barViewFormat();
            break;
        case 3:
            pieViewFormat();
            break;
        case 4:
            radarViewFormat();
            break;
    }
    console.log(dataPie);
}

//modify principal criterion depending on multiselect
function modifyPrincipalCriterion(select) {
    var selected = [...select.options]
        .filter(option => option.selected)
        .map(option => option.value);

    if (selected.length > 1) {
        document.getElementById("principal-criterion").innerHTML = ` <optgroup label="` + optionLng[lang][17] + `" name="genderOpt">
        <option class="text text_option" id="woman" value="female">` + optionLng[lang][0] + `</option>
        <option class="text text_option" id="man" value="male">` + optionLng[lang][1] + `</option>
   </optgroup>
   <optgroup label="` + optionLng[lang][18] + `" name="studiesOpt">
       <option class="text text_option" id="noStudies" value="noStudies">` + optionLng[lang][2] + `</option>
       <option class="text text_option" id="primarySchool" value="primarySchool">` + optionLng[lang][3] + `</option>
       <option class="text text_option" id="secondarySchool" value="secondarySchool">` + optionLng[lang][4] + `</option>
       <option class="text text_option" id="highschool" value="highschool">` + optionLng[lang][5] + `</option>
       <option class="text text_option" id="postsecodarySchool" value="postsecodarySchool">` + optionLng[lang][6] + `</option>
       <option class="text text_option" id="vocationalEducation" value="vocationalEducation">` + optionLng[lang][7] + `</option>
       <option class="text text_option" id="universityEducation" value="universityEducation">` + optionLng[lang][8] + `</option>
   </optgroup>
   <optgroup label="` + optionLng[lang][19] + `" name="envOpt">
       <option class="text text_option" value="urban">` + optionLng[lang][9] + `</option>
       <option class="text text_option" value="rural">` + optionLng[lang][10] + `</option>
   </optgroup>
   <optgroup label="` + optionLng[lang][20] + `" name="ageOpt">
       <option class="text text_option" id="under" value="under25" >` + optionLng[lang][11] + `</option>
           <option class="text text_option" value="between25and29">` + optionLng[lang][12] + `</option>
           <option class="text text_option" value="between30and39">` + optionLng[lang][13] + `</option>
           <option class="text text_option" value="between40and49">` + optionLng[lang][14] + `</option>
           <option class="text text_option" value="between50and55">` + optionLng[lang][15] + `</option>
           <option class="text text_option" id="over" value="over55">` + optionLng[lang][16] + `</option>
   </optgroup>`;
    } else {
        document.getElementById("principal-criterion").innerHTML = ` <option class="text text_option" value="gender" id="gender">Gen</option>
        <option class="text text_option" value="environment" id="environment">Mediu</option>
        <option class="text text_option" value="education" id="studies">Studii</option>
        <option class="text text_option" value="age" id="age">Categorii de vârstă</option>`;
    }
}
/*Last 15 months*/
let months = {
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
    option.value = i + 1;
    var select = document.getElementById("time-period");
    select.insertAdjacentElement("beforeend", option);
    currentMonth = currentMonth - 1;
    if (currentMonth == -1) {
        currentMonth = 11;
        currentYear = currentYear - 1;
    }
}


//download format
document.getElementById("CSVbtn").addEventListener('click', transfromToCSV);
document.getElementById("SVGbtn").addEventListener('click', transfromToSVG);
document.getElementById("PDFbtn").addEventListener('click', transfromToPDF);

function transfromToCSV() {
    if (dataInfo == undefined) {
        console.log("can't do that yet");
    } else {
        const objJson = JSON.parse(dataInfo);
        var json = objJson.groups;
        var fields = Object.keys(json[0]);
        var replacer = function(key, value) { return value === null ? '' : value }
        var csv = json.map(function(row) {
            return fields.map(function(fieldName) {
                return JSON.stringify(row[fieldName], replacer);
            }).join(',');
        })
        csv.unshift(fields.join(',')); // add header column
        csv = csv.join('\r\n');
        exportFile(csv, 'cvs');
    }

}

function exportFile(file, type) {
    var exportedFilenmae = 'export.' + type;
    var blob = new Blob([file], { type: 'text/' + type + ';charset=utf-8;' });
    if (navigator.msSaveBlob) { // IE 10+
        navigator.msSaveBlob(blob, exportedFilenmae);
    } else {
        var link = document.createElement("a");
        if (link.download !== undefined) { // feature detection
            // Browsers that support HTML5 download attribute
            var url = URL.createObjectURL(blob);
            link.setAttribute("href", url);
            link.setAttribute("download", exportedFilenmae);
            link.style.visibility = 'hidden';
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        }
    }
}

function tweakLib() {
    C2S.prototype.getContext = function(contextId) {
        if (contextId == "2d" || contextId == "2D") {
            return this;
        }
        return null;
    }

    C2S.prototype.style = function() {
        return this.__canvas.style
    }

    C2S.prototype.getAttribute = function(name) {
        return this[name];
    }

    C2S.prototype.addEventListener = function(type, listener, eventListenerOptions) {
        console.log("canvas2svg.addEventListener() not implemented.")
    }
}

function transfromToSVG() {
    if (dataInfo == undefined) {
        console.log("can't do that yet");
    } else {



        var configuration = {
            type: 'line',
            data: {
                labels: ["test1", "test2", "test3"],
            },
            options: {
                animation: false,
                responsive: false,
                maintainAspectRatio: false,
                animation: false,
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }

            }
        };
        if (lineChart) {
            lineChart.options.responsive = false;
            lineChart.options.animation = false;
            lineChart.width = 1000;
            lineChart.height = 800;
            configuration.type = 'line';
            configuration.data = lineChart.data;
            configuration.data.labels = lineChart.labels;
            configuration.data.datasets = lineChart.data.datasets;
            configuration.data = lineChart.data;
        }
        if (barChart) {
            barChart.options.responsive = false;
            barChart.options.animation = false;
            barChart.width = 1200;
            barChart.height = 1100;
            configuration.type = 'bar';
            configuration.data = barChart.data;
            configuration.data.labels = barChart.labels;
            configuration.data.datasets = barChart.data.datasets;
            configuration.data = barChart.data;
        }
        if (pieChart) {
            pieChart.options.responsive = false;
            pieChart.options.animation = false;
            pieChart.width = 1000;
            pieChart.height = 800;
            configuration.type = 'pie';
            configuration.data = pieChart.data;
            configuration.data.labels = pieChart.labels;
            configuration.data.datasets = pieChart.data.datasets;
            configuration.data = pieChart.data;
        }
        if (radarChart) {
            radarChart.options.responsive = false;
            radarChart.options.animation = false;
            radarChart.width = 1000;
            radarChart.height = 800;
            configuration.type = 'radar';
            configuration.data = radarChart.data;
            configuration.data.labels = radarChart.labels;
            configuration.data.datasets = radarChart.data.datasets;
            configuration.data = radarChart.data;
        }

        var svgContext = C2S(10000, 10000);
        svgContext.width = 1000;
        svgContext.height = 800;
        var mySvg = new Chart(svgContext, configuration);
        mySvg.width = 1000;
        mySvg.height = 800;

        var svgText = svgContext.getSerializedSvg(true);
        if (lineChart) {
            svgText.height = 10000;
            svgText.width = 10000;
        }

        var downloadSVG = document.createElement('a');
        downloadSVG.setAttribute('href', 'data:text/plain;utf8,' + encodeURIComponent(svgText));
        downloadSVG.setAttribute('download', 'Chart.svg');

        downloadSVG.style.display = 'none';
        document.body.appendChild(downloadSVG);

        downloadSVG.click();

        document.body.removeChild(downloadSVG);

    }
}

function transfromToPDF() {
    if (dataInfo == undefined) {
        console.log("can't do that yet");
    } else {
        var table = document.createElement("TABLE");
        table.border = "1";
        table.id = "statistics";
        const objJson = JSON.parse(dataInfo);
        var json = objJson.groups;
        var fields = Object.keys(json[0]);
        var row = table.insertRow(-1);
        for (var i = 0; i < fields.length; i++) {
            var headerCell = document.createElement("TH");
            headerCell.innerHTML = fields[i];
            row.appendChild(headerCell);
        }
        for (var j = 0; j < json.length; j++) {
            row = table.insertRow(-1);
            const values = Object.values(json[j]);
            for (var i = 0; i < values.length; i++) {
                var cell = row.insertCell(-1);
                console.log(values[i]);
                cell.innerHTML = values[i];
            }
        }
        var dvTable = document.getElementById("downloadButton");
        dvTable.innerHTML = "";
        dvTable.appendChild(table);

        //Convert Table to PDF.
        html2canvas(document.getElementById('downloadButton'), {
            onrendered: function(canvas) {
                var data = canvas.toDataURL();
                var docDefinition = {
                    content: [{
                        image: data,
                        width: 400
                    }]
                };
                pdfMake.createPdf(docDefinition).download("statistics.pdf");
                //Remove the Table.
                dvTable.innerHTML = "";
            }
        });

    }
}

function changeMonth() {
    var currentMonthCh = d.getMonth();
    var currentYearCh = d.getFullYear();
    let mySelect = document.getElementById("time-period");
    let selectLen = mySelect.length;
    for (var j = 0; j < selectLen; j++) {
        mySelect.options[j].innerHTML = months[lang][currentMonthCh].concat(' ').concat(currentYearCh.toString());
        currentMonthCh = currentMonthCh - 1;
        if (currentMonthCh == -1) {
            currentMonthCh = 11;
            currentYearCh = currentYearCh - 1;
        }
    }
}
/*Droplist county*/
let countyList = ["Toată țara", "Alba", "Arad", "Arges", "Bacau", "Bihor", "Bistrita", "Botosani", "Braila", "Brasov", "Buzau", "Calarasi", "Caras-Severin", "Cluj", "Constanta", "Covasna", "Dambovita", "Dolj", "Galati", "Giurgiu", "Gorj", "Harghita", "Hunedoara", "Ialomita", "Iasi", "Ilfov", "Maramures", "Mehedinti", "Bucuresti", "Mures", "Neamt", "Olt", "Prahova", "Salaj", "Satu Mare", "Sibiu", "Suceava", "Teleorman", "Timis", "Tulcea", "Valcea", "Vaslui", "Vrancea"];
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
        "criterion2": "County",
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
                    mySelect[z].options[j].innerHTML = optionLng[lang][j];
                }
                genderOpt[z].setAttribute('label', optionLng[lang][17]);
                studiesOpt[z].setAttribute('label', optionLng[lang][18]);
                envOpt[z].setAttribute('label', optionLng[lang][19]);
                ageOpt[z].setAttribute('label', optionLng[lang][20]);
            }
        }
    }
}

function changeCounty() {
    let countySelect = document.getElementById("county-for-comparison");
    countySelect.options[0].innerHTML = langCrtView[lang].country;
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