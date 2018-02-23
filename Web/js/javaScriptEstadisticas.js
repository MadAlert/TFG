// pie chart - > quesito

function ObtenerMes(mes){
  var meses  = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
  return meses[mes-1];
}
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);
google.charts.setOnLoadCallback(drawChart2);

//Dibujar el mes 1
function drawChart() {

    var desastres = document.getElementById("desastres").value;
    var eventos = document.getElementById("eventos").value;
    var transporte = document.getElementById("transporte").value;
    var terrorismo = document.getElementById("terrorismo").value;
    var criminalidad = document.getElementById("criminalidad").value;
    var contaminacion = document.getElementById("contaminacion").value;
    var trafico = document.getElementById("trafico").value;
    var mes = document.getElementById("mes1").value;

    var m = ObtenerMes(parseInt(mes));

    var data = google.visualization.arrayToDataTable([
      ['Distritos', 'Categorias'],
      ['Desastres y accidentes',     parseInt(desastres)],
      ['Eventos',      parseInt(eventos)],
      ['Transporte publico',      parseInt(transporte)],
      ['Terrorismo',      parseInt(terrorismo)],
      ['Criminalidad',  parseInt(criminalidad)],
      ['Contaminacion', parseInt(contaminacion)],
      ['Trafico',    parseInt(trafico)]
    ]);

    var options = {
      title: 'Mes de ' + m
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart'));

    chart.draw(data, options);
}

//Dibujar el 2 mes
function drawChart2() {

    var desastres = document.getElementById("desastres2").value;
    var eventos = document.getElementById("eventos2").value;
    var transporte = document.getElementById("transporte2").value;
    var terrorismo = document.getElementById("terrorismo2").value;
    var criminalidad = document.getElementById("criminalidad2").value;
    var contaminacion = document.getElementById("contaminacion2").value;
    var trafico = document.getElementById("trafico2").value;
    var mes = document.getElementById("mes2").value;
    var m = ObtenerMes(parseInt(mes));


    var data = google.visualization.arrayToDataTable([
      ['Distritos', 'Categorias'],
      ['Desastres y accidentes',     parseInt(desastres)],
      ['Eventos',      parseInt(eventos)],
      ['Transporte publico',      parseInt(transporte)],
      ['Terrorismo',      parseInt(terrorismo)],
      ['Criminalidad',  parseInt(criminalidad)],
      ['Contaminacion', parseInt(contaminacion)],
      ['Trafico',    parseInt(trafico)]
    ]);

    var options = {
      title: 'Mes de ' + m
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart2'));

    chart.draw(data, options);
}            

google.charts.load('current', {'packages':['corechart']});            
google.charts.setOnLoadCallback(drawChartSeguridad);

function drawChartSeguridad() {

    var distrito = document.getElementById("distrito").value;
    var personas = document.getElementById("personas").value;
    var patrimonio = document.getElementById("patrimonio").value;
    var armas = document.getElementById("armas").value;
    var ten_drogas = document.getElementById("ten_drogas").value;
    var con_drogas = document.getElementById("con_drogas").value;

    var data = google.visualization.arrayToDataTable([
      ['Distritos', 'Tipos'],
      ['Relacionado con personas',     parseInt(personas)],
      ['Relacionado con el patrimonio',      parseInt(patrimonio)],
      ['Relacionado con las tenencia de armas',     parseInt(armas)],
      ['Relacionado con las tenencia de drogas',      parseInt(ten_drogas)],
      ['Relacionado con las consumo de drogas',  parseInt(con_drogas)]
    ]);

    var options = {
     title: 'Distrito: ' + distrito
    };

    var chart = new google.visualization.PieChart(document.getElementById("piechart"));

    chart.draw(data, options);
  }


// SILVIA column chart - > diagrama de barras



//google.charts.load('current', {packages: ['corechart', 'bar']});
//google.charts.setOnLoadCallback(drawMultSeriesMes1);

google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawMultSeriesMes1);
google.charts.setOnLoadCallback(drawMultSeriesMes2);

function drawMultSeriesMes1() {

    var categoria = document.getElementById("categoria").value;
    var arganzuela = document.getElementById("arganzuela").value;
    var barajas = document.getElementById("barajas").value;
    var carabanchel = document.getElementById("carabanchel").value;
    var centro = document.getElementById("centro").value;
    var chamartin = document.getElementById("chamartin").value;
    var chamberi = document.getElementById("chamberi").value;
    var ciudadLineal = document.getElementById("ciudadLineal").value;
    var fuencarral = document.getElementById("fuencarral").value;
    var general = document.getElementById("general").value;
    var hortaleza = document.getElementById("hortaleza").value;
    var latina = document.getElementById("latina").value;
    var moncloa = document.getElementById("moncloa").value;
    var moratalaz = document.getElementById("moratalaz").value;
    var puenteVallecas = document.getElementById("puenteVallecas").value;
    var retiro = document.getElementById("retiro").value;
    var salamanca = document.getElementById("salamanca").value;
    var sanblas = document.getElementById("sanblas").value;
    var tetuan = document.getElementById("tetuan").value;
    var usera = document.getElementById("usera").value;
    var vicalvaro = document.getElementById("vicalvaro").value;
    var villaVallecas = document.getElementById("villaVallecas").value;
    var villaverde = document.getElementById("villaverde").value;
 

    var data = google.visualization.arrayToDataTable([
         ['Distrito', '', { role: 'style' }],
         ['Arganzuela', parseInt(arganzuela), 'green'],            // RGB value
         ['Barajas', parseInt(barajas), 'khaki'],
         ['Carabanchel', parseInt(carabanchel), 'red'],            // English color name
         ['Centro', parseInt(centro), 'pink'],
         ['Chamartin', parseInt(chamartin), 'black'],
         ['Chamberi', parseInt(chamberi), 'orange'],
         ['Ciudad Lineal', parseInt(ciudadLineal), 'royalblue'],
         ['Fuencarral', parseInt(fuencarral), 'hotpink'],
         ['General', parseInt(general), 'gold'],
         ['Hortaleza', parseInt(hortaleza), 'brown'],
         ['Latina', parseInt(latina), 'darkseagreen'],
         ['Moncloa-Aravaca', parseInt(moncloa), 'beige'],
         ['Moratalaz', parseInt(moratalaz), 'plum'],
         ['Puente de Vallecas', parseInt(puenteVallecas), 'palegreen'],
         ['Retiro', parseInt(retiro), 'salmon'],
         ['Salamanca', parseInt(salamanca), 'navy'],
         ['San Blas-Canillejas', parseInt(sanblas), 'red'],
         ['Tetuan', parseInt(tetuan), 'sienna'],
         ['Usera', parseInt(usera), 'aqua'],
         ['Vicalvaro', parseInt(vicalvaro), 'coral'],
         ['Villa de Vallecas', parseInt(villaVallecas), 'grey'],
         ['Villaverde', parseInt(villaverde), 'teal'], // CSS-style declaration*/
      ]);

      var options = {
        legend: 'none',
        height: 500,
        chartArea:{
          height: 200,
          top:100
        },
        hAxis: {
          title: 'DISTRITOS',
          slantedText: true,
          slantedTextAngle: 45,
          minValue: 0,          
          ticks: [0 , 1]          
         // format: 'number',
        },
        vAxis: {
          title: 'ALERTAS',
          format: 'short',

          viewWindow: {
            min: 0,          
          }
        }
      };

      var chart = new google.visualization.ColumnChart(
        document.getElementById('chart_div1'));

      chart.draw(data, options);
  }


// column chart - > diagrama de barras
// MES 2




function drawMultSeriesMes2() {

    var categoria = document.getElementById("categoria").value;
    var arganzuela = document.getElementById("arganzuela2").value;
    var barajas = document.getElementById("barajas2").value;
    var carabanchel = document.getElementById("carabanchel2").value;
    var centro = document.getElementById("centro2").value;
    var chamartin = document.getElementById("chamartin2").value;
    var chamberi = document.getElementById("chamberi2").value;
    var ciudadLineal = document.getElementById("ciudadLineal2").value;
    var fuencarral = document.getElementById("fuencarral2").value;
    var general = document.getElementById("general2").value;
    var hortaleza = document.getElementById("hortaleza2").value;
    var latina = document.getElementById("latina2").value;
    var moncloa = document.getElementById("moncloa2").value;
    var moratalaz = document.getElementById("moratalaz2").value;
    var puenteVallecas = document.getElementById("puenteVallecas2").value;
    var retiro = document.getElementById("retiro2").value;
    var salamanca = document.getElementById("salamanca2").value;
    var sanblas = document.getElementById("sanblas2").value;
    var tetuan = document.getElementById("tetuan2").value;
    var usera = document.getElementById("usera2").value;
    var vicalvaro = document.getElementById("vicalvaro2").value;
    var villaVallecas = document.getElementById("villaVallecas2").value;
    var villaverde = document.getElementById("villaverde2").value;
 

    var data = google.visualization.arrayToDataTable([
         ['Distrito', '', { role: 'style' }],
         ['Arganzuela', parseInt(arganzuela), 'green'],            // RGB value
         ['Barajas', parseInt(barajas), 'khaki'],
         ['Carabanchel', parseInt(carabanchel), 'red'],            // English color name
         ['Centro', parseInt(centro), 'pink'],
         ['Chamartin', parseInt(chamartin), 'black'],
         ['Chamberi', parseInt(chamberi), 'orange'],
         ['Ciudad Lineal', parseInt(ciudadLineal), 'royalblue'],
         ['Fuencarral', parseInt(fuencarral), 'hotpink'],
         ['General', parseInt(general), 'gold'],
         ['Hortaleza', parseInt(hortaleza), 'brown'],
         ['Latina', parseInt(latina), 'darkseagreen'],
         ['Moncloa-Aravaca', parseInt(moncloa), 'beige'],
         ['Moratalaz', parseInt(moratalaz), 'plum'],
         ['Puente de Vallecas', parseInt(puenteVallecas), 'palegreen'],
         ['Retiro', parseInt(retiro), 'salmon'],
         ['Salamanca', parseInt(salamanca), 'navy'],
         ['San Blas-Canillejas', parseInt(sanblas), 'red'],
         ['Tetuan', parseInt(tetuan), 'sienna'],
         ['Usera', parseInt(usera), 'aqua'],
         ['Vicalvaro', parseInt(vicalvaro), 'coral'],
         ['Villa de Vallecas', parseInt(villaVallecas), 'grey'],
         ['Villaverde', parseInt(villaverde), 'teal'], // CSS-style declaration*/
      ]);

      var options = {
       
        legend: 'none',
        height: 500,
        chartArea:{
          height: 200,
          top:100
        },
        hAxis: {
          title: 'DISTRITOS',
          slantedText: true,
          slantedTextAngle: 45
         // format: 'number',
        },
        vAxis: {
          title: 'ALERTAS',
          format: 'short',
          viewWindow: {
            min: 0,
           // max: 21,
          }
        }
      };

      var chart = new google.visualization.ColumnChart(
        document.getElementById('chart_div2'));

      chart.draw(data, options);
  }





// A PARTIR DE AQUI ES LO DE GONZALO


google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawColumns);

function drawColumns() {

    var arganzuela = document.getElementById("arganzuela").value;
    var barajas = document.getElementById("barajas").value;
    var carabanchel = document.getElementById("carabanchel").value;
    var centro = document.getElementById("centro").value;
    var chamartin = document.getElementById("chamartin").value;
    var chamberi = document.getElementById("chamberi").value;
    var ciudadLineal = document.getElementById("ciudadLineal").value;
    var fuencarral = document.getElementById("fuencarral").value;
    var hortaleza = document.getElementById("hortaleza").value;
    var latina = document.getElementById("latina").value;
    var moncloa = document.getElementById("moncloa").value;
    var moratalaz = document.getElementById("moratalaz").value;
    var puenteVallecas = document.getElementById("puenteVallecas").value;
    var retiro = document.getElementById("retiro").value;
    var salamanca = document.getElementById("salamanca").value;
    var sanblas = document.getElementById("sanblas").value;
    var tetuan = document.getElementById("tetuan").value;
    var usera = document.getElementById("usera").value;
    var vicalvaro = document.getElementById("vicalvaro").value;
    var villaVallecas = document.getElementById("villaVallecas").value;
    var villaverde = document.getElementById("villaverde").value;
 

    var data = google.visualization.arrayToDataTable([
         ['Distrito', 'Detenidos'],
         ['Arganzuela', parseInt(arganzuela)],            // RGB value
         ['Barajas', parseInt(barajas)],
         ['Carabanchel', parseInt(carabanchel)],            // English color name
         ['Centro', parseInt(centro)],
         ['Chamartin', parseInt(chamartin)],
         ['Chamberi', parseInt(chamberi)],
         ['Ciudad Lineal', parseInt(ciudadLineal)],
         ['Fuencarral', parseInt(fuencarral)],
         /*['General', parseInt(general), 'gold'],*/
         ['Hortaleza', parseInt(hortaleza)],
         ['Latina', parseInt(latina)],
         ['Moncloa-Aravaca', parseInt(moncloa)],
         ['Moratalaz', parseInt(moratalaz)],
         ['Puente de Vallecas', parseInt(puenteVallecas)],
         ['Retiro', parseInt(retiro)],
         ['Salamanca', parseInt(salamanca)],
         ['San Blas-Canillejas', parseInt(sanblas)],
         ['Tetuan', parseInt(tetuan)],
         ['Usera', parseInt(usera)],
         ['Vicalvaro', parseInt(vicalvaro)],
         ['Villa de Vallecas', parseInt(villaVallecas)],
         ['Villaverde', parseInt(villaverde)],
      ]);

    var options = {
      title: "Según el distrito",
      width: "90%",
      height: 400,
      bar: {groupWidth: "75%"},
      legend: {position: "none"},
      chartArea:{
          height: 200,
          top:100
        },
      hAxis: {
          
          slantedText: true,
          slantedTextAngle: 45
         // format: 'number',
        },
    };

    var chart = new google.visualization.ColumnChart(document.getElementById('column_id'));
    chart.draw(data, options);

}

google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawColumnsDouble);

function drawColumnsDouble() {

    var arganzuelaC = document.getElementById("arganzuelaC").value;
    var arganzuelaS = document.getElementById("arganzuelaS").value;
    var barajasC = document.getElementById("barajasC").value;
    var barajasS = document.getElementById("barajasS").value;
    var carabanchelC = document.getElementById("carabanchelC").value;
    var carabanchelS = document.getElementById("carabanchelS").value;
    var centroC = document.getElementById("centroC").value;
    var centroS = document.getElementById("centroS").value;
    var chamartinC = document.getElementById("chamartinC").value;
    var chamartinS = document.getElementById("chamartinS").value;
    var chamberiC = document.getElementById("chamberiC").value;
    var chamberiS = document.getElementById("chamberiS").value;
    var ciudadLinealC = document.getElementById("ciudadLinealC").value;
    var ciudadLinealS = document.getElementById("ciudadLinealS").value;
    var fuencarralC = document.getElementById("fuencarralC").value;
    var fuencarralS = document.getElementById("fuencarralS").value;
    var hortalezaC = document.getElementById("hortalezaC").value;
    var hortalezaS = document.getElementById("hortalezaS").value;
    var latinaC = document.getElementById("latinaC").value;
    var latinaS = document.getElementById("latinaS").value;
    var moncloaC = document.getElementById("moncloaC").value;
    var moncloaS = document.getElementById("moncloaS").value;
    var moratalazC = document.getElementById("moratalazC").value;
    var moratalazS = document.getElementById("moratalazS").value;
    var puenteVallecasC = document.getElementById("puenteVallecasC").value;
    var puenteVallecasS = document.getElementById("puenteVallecasS").value;
    var retiroC = document.getElementById("retiroC").value;
    var retiroS = document.getElementById("retiroS").value;
    var salamancaC = document.getElementById("salamancaC").value;
    var salamancaS = document.getElementById("salamancaS").value;
    var sanblasC = document.getElementById("sanblasC").value;
    var sanblasS = document.getElementById("sanblasS").value;
    var tetuanC = document.getElementById("tetuanC").value;
    var tetuanS = document.getElementById("tetuanS").value;
    var useraC = document.getElementById("useraC").value;
    var useraS = document.getElementById("useraS").value;
    var vicalvaroC = document.getElementById("vicalvaroC").value;
    var vicalvaroS = document.getElementById("vicalvaroS").value;
    var villaVallecasC = document.getElementById("villaVallecasC").value;
    var villaVallecasS = document.getElementById("villaVallecasS").value;
    var villaverdeC = document.getElementById("villaverdeC").value;
    var villaverdeS = document.getElementById("villaverdeS").value;
 

    var data = google.visualization.arrayToDataTable([
         ['Distrito', 'Con Heridos', 'Sin Heridos'],
         ['Arganzuela', parseInt(arganzuelaC), parseInt(arganzuelaS)],           
         ['Barajas', parseInt(barajasC), parseInt(barajasS)],
         ['Carabanchel', parseInt(carabanchelC), parseInt(carabanchelS)],            
         ['Centro', parseInt(centroC), parseInt(centroS)],
         ['Chamartin', parseInt(chamartinC), parseInt(chamartinS)],
         ['Chamberi', parseInt(chamberiC), parseInt(chamberiS)],
         ['Ciudad Lineal', parseInt(ciudadLinealC), parseInt(ciudadLinealS)],
         ['Fuencarral', parseInt(fuencarralC), parseInt(fuencarralS)],
         /*['General', parseInt(general), 'gold'],*/
         ['Hortaleza', parseInt(hortalezaC), parseInt(hortalezaS)],
         ['Latina', parseInt(latinaC), parseInt(latinaS)],
         ['Moncloa-Aravaca', parseInt(moncloaC), parseInt(moncloaS)],
         ['Moratalaz', parseInt(moratalazC), parseInt(moratalazS)],
         ['Puente de Vallecas', parseInt(puenteVallecasC), parseInt(puenteVallecasS)],
         ['Retiro', parseInt(retiroC), parseInt(retiroS)],
         ['Salamanca', parseInt(salamancaC), parseInt(salamancaS)],
         ['San Blas-Canillejas', parseInt(sanblasC), parseInt(sanblasS)],
         ['Tetuan', parseInt(tetuanC), parseInt(tetuanS)],
         ['Usera', parseInt(useraC), parseInt(useraS)],
         ['Vicalvaro', parseInt(vicalvaroC), parseInt(vicalvaroS)],
         ['Villa de Vallecas', parseInt(villaVallecasC), parseInt(villaVallecasS)],
         ['Villaverde', parseInt(villaverdeC), parseInt(villaverdeS)],
      ]);

    var options = {
      title: "Según el distrito",
      width: "100%",
      height: 400,
      bar: {groupWidth: "75%"},
      legend: {position: "none"},
    };

    var chart = new google.charts.Bar(document.getElementById('doubleColumn_id'));
    chart.draw(data, google.charts.Bar.convertOptions(options));

}
  


