function initializeMap(file){
//insert Map
var map = new ol.Map({
    target: 'map',
    layers: [
      new ol.layer.Tile({
        source: new ol.source.OSM()
      })
    ],view: new ol.View({
        center: ol.proj.fromLonLat([24.75,46.14]),
        zoom: 6
      })
});

//Arrow on county
const fillStyle = new ol.style.Fill({
    color: [245,49,49,0.8],
})
const strokeStyle = new ol.style.Stroke({
    color: [46, 45, 45, 0.3],
    width: 1.2
}) 
const arrowStyle = new ol.style.Style({
    fill: fillStyle,
    stroke: strokeStyle,
    image: new ol.style.RegularShape({
        fill: fillStyle,
        stroke: strokeStyle,
        points: 3,
        radius: 13,
        rotation: Math.PI / 3,
        angle: 0,
        displacement:[-11,7],
      })
})

const countyList = new ol.layer.VectorImage({
    source: new ol.source.Vector({
        url:file,
        format: new ol.format.GeoJSON()
    }),
    visible:true,
    title: 'Rata șomajului la nivelul fiecărui județ',
    style: arrowStyle,
})

map.addLayer(countyList)


//Show Info

const overlayContainer = document.querySelector('.overlay-container');
const overlayInfo = new ol.Overlay({
    element: overlayContainer
})
map.addOverlay(overlayInfo);

const overlayCounty = document.getElementById('overlay-county-name');
const overlayCountyInfo = document.getElementById('overlay-county-info');

map.on('click', showInfo)
function showInfo(event){
    overlayInfo.setPosition(undefined);
    map.forEachFeatureAtPixel(event.pixel, function(feature, layer){
       let coordinate = event.coordinate;
       let countyName = feature.get('Name');
       let countyInfo = "Număr șomeri: ".concat(feature.get('Value')); 
       overlayInfo.setPosition(coordinate);
       overlayCounty.innerHTML= countyName;
       overlayCountyInfo.innerHTML = countyInfo;
    })
}
}