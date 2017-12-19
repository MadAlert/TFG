// pie chart - > quesito

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {

    var distrito = document.getElementById('distrito').value;
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
            

// column chart - > diagrama de barras

google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawMultSeries);

function drawMultSeries() {
    var data = google.visualization.arrayToDataTable([
         ['Distrito', 'Categoria', { role: 'style' }],
         ['Arganzuela', 8, '#b87333'],            // RGB value
         ['Barajas', 10, 'silver'],            // English color name
         ['Carabanchel', 40, 'gold'],
         ['Centro', 25, 'black'],
         ['Chamartin', 15, 'pink'],
         ['Chamberi', 19.30, 'yellow'],
         ['Ciudad Lineal', 19.30, 'red'],
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
         ['Villaverde', 19.30, 'green'],

       ['Platinum', 21.45, 'color: #e5e4e2' ], // CSS-style declaration
      ]);

      var options = {
        title: 'Según la categoría : Criminalidad ',
        hAxis: {
          title: 'Distritos',
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

  

     