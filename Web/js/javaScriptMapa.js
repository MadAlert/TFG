function initMap() {
    var marcadores = [
        ['Arganzuela', 40.400861, -3.699350, document.getElementById("arganzuela").value], //Arganzuela
        ['Barajas', 40.4839402, -3.5701402, document.getElementById("barajas").value], //Barajas
        ['Carabanchel', 40.381607, -3.735203, document.getElementById("carabanchel").value], //Carabanchel
        ['Centro', 40.4169416, -3.7083759, document.getElementById("centro").value], //Centro
        ['Chamartín', 40.460367, -3.676567, document.getElementById("chamartin").value], //Chamartín
        ['Chamberí', 40.438656, -3.704180, document.getElementById("chamberi").value], //Chamberí
        ['Ciudad Lineal', 40.455531, -3.656119, document.getElementById("ciudadlineal").value], //Ciudad Lineal
        ['Fuencarral-El Pardo', 40.494289, -3.693477, document.getElementById("fuencarral").value], //Fuencarral-El Pardo
        ['Hortaleza', 40.485152, -3.634796, document.getElementById("hortaleza").value], //Hortaleza
        ['Latina', 40.387812, -3.773530, document.getElementById("latina").value], //Latina
        ['Moncloa-Aravaca', 40.443568,  -3.742829, document.getElementById("moncloa").value], //Moncloa-Aravaca
        ['Moratalaz', 40.407016, -3.644330, document.getElementById("moratalaz").value], //Moratalaz
        ['Puente de Vallecas', 40.386887, -3.658476, document.getElementById("puentevallecas").value], //Puente de Vallecas
        ['Retiro', 40.4101076, -3.6736514, document.getElementById("retiro").value], //Retiro
        ['Salamanca', 40.429807, -3.673778, document.getElementById("salamanca").value], //Salamanca
        ['San Blas-Canillejas', 40.436229, -3.599431, document.getElementById("sanblas").value], //San Blas-Canillejas
        ['Tetuán', 40.460158, -3.698835, document.getElementById("tetuan").value], //Tetuán
        ['Usera', 40.377026, -3.701982, document.getElementById("usera").value], //Usera
        ['Vicálvaro', 40.393974, -3.581134, document.getElementById("vicalvaro").value], //Vicálvaro
        ['Villa de Vallecas', 40.355089, -3.621192, document.getElementById("villavallecas").value], //Villa de Vallecas
        ['Villaverde', 40.345987, -3.693332, document.getElementById("villaverde").value] //Villaverde
    ];

    var map = new google.maps.Map(document.getElementById('mapa'),{
        zoom: 12,
        //zoomControl: false,
        streetViewControl: false,
        scrollwheel: false,
        center: new google.maps.LatLng(40.422163, -3.689101),
    });

    var infowindow = new google.maps.InfoWindow(); //Abre ventana del marcador

    var marker, i;
    var distritoM;
    for (i = 0; i < marcadores.length; i++) {
        if(marcadores[i][3] != 0) {  //Solo muestra los distritos que tienen alertas
            marker = new google.maps.Marker({
                position: new google.maps.LatLng(marcadores[i][1], marcadores[i][2]),
                map: map
            });
        }
    
        google.maps.event.addListener(marker, 'click', (function(marker, i) {
            return function() {
                if(marcadores[i][3] != 0) {
                    var contentString = '<form id="formulario_marcador" method="POST" action="alertas.php">' +
                    '<h2>' + marcadores[i][0] + '</h2>'+
                    '<input type="hidden" name="distritoM" value="'+ marcadores[i][0] +'" id="distritoM"/>' +
                    '<p> Se han encontrado <b>'+ marcadores[i][3] + ' alertas</b>. </p>' +
                    '<div class="form-center" style=" margin-left:25px; margin-top:20px">' +
                        '<button class="btn btn-info" type="submit">Ver alertas</button>' +
                    '</div>' +
                    '</form>';
                    infowindow.setContent(contentString);
                    infowindow.open(map, marker);
                }
            }
        })(marker, i));
    }

    google.maps.event.addDomListener(window, 'load', initMap);

}

                                        
                                    
                                    