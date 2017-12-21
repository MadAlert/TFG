// pie chart - > quesito

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {

    var distrito = document.getElementById("distrito").value;
    var desastres = document.getElementById("desastres").value;
    var eventos = document.getElementById("eventos").value;
    var transporte = document.getElementById("transporte").value;
    var terrorismo = document.getElementById("terrorismo").value;
    var criminalidad = document.getElementById("criminalidad").value;
    var contaminacion = document.getElementById("contaminacion").value;
    var trafico = document.getElementById("trafico").value;

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
      title: 'Distrito: ' + distrito
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart'));

    chart.draw(data, options);
  }
            
google.charts.load('current', {'packages':['corechart']});            
google.charts.setOnLoadCallback(drawChartSeguridad);

function drawChartSeguridad() {

    var distrito = document.getElementById("distrito").value;
    var personas = document.getElementById("personas").value;
   /* var patrimonio = document.getElementById("patrimonio").value;
    var armas = document.getElementById("armas").value;
    var ten_drogas = document.getElementById("ten_drogas").value;
    var con_drogas = document.getElementById("con_drogas").value;*/

    var data = google.visualization.arrayToDataTable([
      ['Distritos', 'Categorias'],
      ['Relacionado con personas',     personas],
      /*['Relacionado con el patrimonio',      patrimonio],
      ['Relacionado con las tenencia de armas',      armas],
      ['Relacionado con las tenencia de drogas',      ten_drogas],
      ['Relacionado con las consumo de drogas',  con_drogas]*/
    ]);

    var options = {
      title: 'Distrito: ' + distrito
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart'));

    chart.draw(data, options);
  }


// column chart - > diagrama de barras

google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawMultSeries);

function drawMultSeries() {

    var categoria = document.getElementById("categoria").value;
    /*var aravaca = document.getElementById("aravaca").value;
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
    var villaverde = document.getElementById("villaverde").value;*/
 

    var data = google.visualization.arrayToDataTable([
         ['Element', 'Density', { role: 'style' }],
         ['Copper', 8.94, '#b87333'],            // RGB value
         ['Silver', 10.49, 'silver'],            // English color name
         ['Gold', 19.30, 'gold'],

       ['Platinum', 21.45, 'color: #e5e4e2' ], // CSS-style declaration
      ]);

    /*var data = google.visualization.arrayToDataTable([
         ['Distrito', 'Num', { role: 'style' }],
         ['hola', 5, '#b87333'],
         ['dsdsdsd', 2, '#b87333'],
         ['casasa', 2, '#b87333'],
         ['categoria', 7, '#b87333'],
         ['adiosss', 8, '#b87333'],
         ['holaaa', 3, '#b87333'],
         ['dssdsdsd', 2, '#b87333'],

        /* [categoria, aravaca, '#b87333'],            // RGB value
         [categoria, barajas, 'silver'],            // English color name
         [categoria, carabanchel, 'gold'],
         [categoria, centro, 'black'],
         [categoria, chamartin, 'pink'],
         [categoria , chamberi, 'red'],*/
       /*  ['Ciudad Lineal', 19.30, 'red'],
         ['Fuencarral', 15, 'green'],
         ['Hortaleza', 30, 'orange'],
         ['Latina', 19.30, 'blue'],
         ['Moncloa-Aravaca', 19, 'brown'],
         ['Moratalaz', 14, 'gold'],
         ['Puente de Vallecas', 19.30, 'gold'],
         ['Retiro', 12, 'gold'],
         ['Salamanca', 5, 'gold'],
         ['San Blas-Canillejas', 11, 'red'],
         ['Tetuan', 18, 'pink'],
         ['Usera', 32, 'blue'],
         ['Vicalvaro', 20, 'red'],
         ['Villa de Vallecas', 19.30, 'gold'],
         ['Villaverde', 19.30, 'green'],*/

       //['Platinum', 21.45, 'color: #e5e4e2' ], // CSS-style declaration
      ]);

      var options = {
        title: 'Según la categoría :  ' + categoria,
        hAxis: {
          title: 'Cat',
         // format: 'number',
          //viewWindow: {
           // min: 0,
           // max: 21,
          //}
        },
        vAxis: {
          //title: 'Escala del 1 al 10'
        }
      };

      var chart = new google.visualization.ColumnChart(
        document.getElementById('chart_div'));

      chart.draw(data, options);
  }

  

     