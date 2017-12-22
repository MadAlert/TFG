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
    var nombre = document.getElementById("nombre").value;

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

    var chart = new google.visualization.PieChart(document.getElementById(nombre));

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
         ['Arganzuela', parseInt(arganzuela), 'red'],            // RGB value
         ['Barajas', parseInt(barajas), 'yellow'],
         ['Carabanchel', parseInt(carabanchel), 'gold'],            // English color name
         ['Centro', parseInt(centro), 'gold'],
         ['Chamartin', parseInt(chamartin), 'gold'],
         ['Chamberi', parseInt(chamberi), 'gold'],
         ['Ciudad Lineal', parseInt(ciudadLineal), 'gold'],
         ['Fuencarral', parseInt(fuencarral), 'green'],
         ['General', parseInt(general), 'green'],
         ['Hortaleza', parseInt(hortaleza), 'orange'],
         ['Latina', parseInt(latina), 'blue'],
         ['Moncloa-Aravaca', parseInt(moncloa), 'brown'],
         ['Moratalaz', parseInt(moratalaz), 'gold'],
         ['Puente de Vallecas', parseInt(puenteVallecas), 'gold'],
         ['Retiro', parseInt(retiro), 'gold'],
         ['Salamanca', parseInt(salamanca), 'gold'],
         ['San Blas-Canillejas', parseInt(sanblas), 'red'],
         ['Tetuan', parseInt(tetuan), 'pink'],
         ['Usera', parseInt(usera), 'blue'],
         ['Vicalvaro', parseInt(vicalvaro), 'red'],
         ['Villa de Vallecas', parseInt(villaVallecas), 'gold'],
         ['Villaverde', parseInt(villaverde), 'green'], // CSS-style declaration
      ]);

      var options = {
        title: 'Según la categoría : ' + categoria   ,
        hAxis: {
          title: 'DISTRITOS',
         // format: 'number',
          //viewWindow: {
           // min: 0,
           // max: 21,
          //}
        },
        vAxis: {
          title: 'ALERTAS',
        }
      };

      var chart = new google.visualization.ColumnChart(
        document.getElementById('chart_div'));

      chart.draw(data, options);
  }

  


