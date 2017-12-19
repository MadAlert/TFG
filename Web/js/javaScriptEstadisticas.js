google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {

    var data = google.visualization.arrayToDataTable([
      ['Distritos', 'Categorias'],
      ['Desastres y accidentes',     11],
      ['Eventos',      2],
      ['Transporte publico',      2],
      ['Terrorismo',      2],
      ['Criminalidad',  2],
      ['Contaminacion', 2],
      ['Trafico',    7]
    ]);

    var options = {
      title: 'Distrito: '
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart'));

    chart.draw(data, options);
  }
            

