$(function() {

    var marcaRetiro = {lat: 40.4101076, lng:-3.6736514};

    var map = new google.maps.Map({
        el: '#gmaps-simple',
        lat: 40.422163,
        lng: -3.689101,
        zoom: 12,
        panControl: false,
        streetViewControl: false,
        mapTypeControl: false,
        overviewMapControl: false,
    });

    var marker = new google.maps.Marker({
          position: marcaRetiro,
          map: map,
          title: 'Holi!!'
    });
});
