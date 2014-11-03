<%@page import="java.util.List"%>
<%@page import="com.supertaxis.model.Vehicle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles");
%>
<!DOCTYPE html>
<html>
  <head>
    <title>Simple Map</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
    <style>
        html, body, #map-canvas { height: 100%; margin: 0px; padding: 0px }
        h1 { position: absolute; top: 0; left: 100px; font-family: 'Lobster', cursive; color: #ff3366; background-color: #111111; display: inline-block; padding: 10px; margin: 0 auto 0; border-radius: 0 0 10px 10px }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
    <script>
        var map;
        function initialize() {
            var mapOptions = {
                zoom: 12
            };
            map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
        
            // Try HTML5 geolocation
           if (navigator.geolocation) {
               navigator.geolocation.getCurrentPosition(function(position) {
                   var pos = new google.maps.LatLng(position.coords.latitude,
                           position.coords.longitude);

                   var infowindow = new google.maps.InfoWindow({
                       map: map,
                       position: pos,
                       content: 'You are here'
                   });

                   map.setCenter(pos);
                   
                    <% if (vehicles != null) for (Vehicle v : vehicles) { %>
                        new google.maps.Marker({
                            position: new google.maps.LatLng(<%= v.getLastLocationEntry().getLatitude() %>, <%= v.getLastLocationEntry().getLongitude() %>),
                            map: map,
                            title: '<%= v.getId() %>'
                        });
                    <% } else { %>
                        alert('Le service est momentanément indisponible.');
                    <% } %>
                   
               }, function () {
                   handleNoGeolocation(true);
               });
           } else {
               // Browser doesn't support Geolocation
               handleNoGeolocation(false);
           }
        }

        function handleNoGeolocation(errorFlag) {
            if (errorFlag) {
                var content = 'Error: The Geolocation service failed.';
            } else {
                var content = 'Error: Your browser doesn\'t support geolocation.';
            }

            var options = {
                map: map,
                position: new google.maps.LatLng(60, 105),
                content: content
            };

            var infowindow = new google.maps.InfoWindow(options);
            map.setCenter(options.position);
        }


        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
  </head>
  <body>
    <div id="map-canvas"></div>
    <h1>SuperTacos</h1>
  </body>
</html>