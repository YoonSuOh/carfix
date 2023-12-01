let map;

$(document).ready(function() {
    initMap();
});

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: { lat: -34.397, lng: 150.644 },
        zoom: 15
    });

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            const pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };

            new google.maps.Marker({
                position: pos,
                map: map,
                title: 'You are here!'
            });

            map.setCenter(pos);

            // Send coordinates to the server automatically on page load
            sendLocation(pos);
        }, function() {
            handleLocationError(true, map.getCenter());
        });
    } else {
        handleLocationError(false, map.getCenter());
    }
}

function handleLocationError(browserHasGeolocation, pos) {
    const infoWindow = new google.maps.InfoWindow();
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
        'Error: The Geolocation service failed.' :
        'Error: Your browser doesn\'t support geolocation.');
    infoWindow.open(map);
}

function sendLocation(pos) {
    // Send coordinates to the server using Ajax
    $.ajax({
        type:"PUT"
        ,url:"/map/distance"
        ,data: JSON.stringify(pos)
        ,contentType: 'application/json'
        , success:function (data){
            if(data.code == 200){
                console.log("위도, 경도 모두 가져오기 성공!");
            } else if (data.code == 500) {
                alert(data.errorMessage);
            }
        }
        , error:function(request, status, error) {
            alert("수정에 실패했습니다.");
        }
    });
}