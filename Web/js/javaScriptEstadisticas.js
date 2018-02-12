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


// column chart - > diagrama de barras

google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawMultSeries);

function drawMultSeries() {

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
         ['Villaverde', parseInt(villaverde), 'teal'], // CSS-style declaration
      ]);

      var options = {
        title: 'Según la categoría : ' + categoria   ,
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
        document.getElementById('chart_div'));

      chart.draw(data, options);
  }


  


