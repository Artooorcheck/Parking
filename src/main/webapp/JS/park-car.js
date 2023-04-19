function getAllParks() {
    $.ajax({
        method : 'get',
        url : 'updateField-servlet',
        data : {
            fieldName: 'getParks',
            parkId: $('#park_id').val()
        },
        success : function(responseText) {
            let resParks = JSON.parse(responseText);
            let parkSelector = $('#park_id');
            parkSelector.children().remove();
            for(let i =0; i<resParks.length; i++) {
                let opt = document.createElement('option');
                opt.value = resParks[i].parkId;
                opt.innerHTML = resParks[i].parkName;
                parkSelector.append(opt);
            }
            getPlaces()
        }
    });
}

function getPlaces() {
    $.ajax({
        method : 'get',
        url : 'updateField-servlet',
        data : {
            fieldName: 'getPlaces',
            parkId: $('#park_id').val()
        },
        success : function(responseText) {
            let resParks = JSON.parse(responseText);
            let placeSelector = $('#park_place');
            placeSelector.children().remove();
            for(let i =0; i<resParks.length; i++) {
                let opt = document.createElement('option');
                opt.value = resParks[i].placeId;
                opt.innerHTML = resParks[i].placeNumber;
                placeSelector.append(opt);
            }
        }
    });
}

function leaveCar() {
    $.ajax({
        method : 'post',
        url : 'userPlaces-servlet',
        data : {
            type: 'add',
            placeId: $('#park_place').val(),
            carId: $('#car_id').val()
        },
        success: getCarsUser
    });
}

function removeCar(placeId) {
    $.ajax({
        method : 'post',
        url : 'userPlaces-servlet',
        data : {
            type: 'delete',
            placeId: placeId
        },
        success: getCarsUser
    });
}

function getHeader() {
    return '<tr>' +
                '<th>Address</th>' +
                '<th>Place</th>' +
                '<th>Car Identifier</th>' +
            '</tr>'
}

function createRow(parkAddress, placeNumber, carId, placeId) {
    return '<tr name="row">' +
                '<td>'+ parkAddress +'</td>'+
                '<td>'+ placeNumber +'</td>' +
                '<td>'+ carId +'</td>' +
                '<td> <button type="submit" id="' + placeId + '" onclick="removeCar(' + placeId + ')">Remove</button> </td>' +
            '</tr>';
}

function getFooter() {
    return '<tr>' +
                '<td><select id="park_id" onchange="getPlaces()"></select></td>' +
                '<td><select id="park_place"></select></td>' +
                '<td><input id="car_id" type="text" placeholder="А111AA111"></td>' +
                '<td><button type="submit" id="set_button" onclick="leaveCar()">Add</button></td>' +
            '</tr>'
}

function getCarsUser() {
    $.ajax({
        method : 'get',
        url : 'userPlaces-servlet',
        data : {
        },
        success : function(responseText) {
            let resParks = JSON.parse(responseText);
            let table = $('#car_list');
            //table.children().remove();
            let innerHTML = getHeader();
            for(let i =0; i<resParks.length; i++) {
                innerHTML += createRow(resParks[i].parkAddress, resParks[i].placeNumber, resParks[i].carId, resParks[i].placeId)
            }
            innerHTML += getFooter();
            table.html(innerHTML);
            getAllParks();
        }
    });
}

function deleteUser() {
    $.ajax({
        method: 'delete',
        url : 'authorization-servlet',
        data: {},
        success: function(data){
            window.location = '/Parking_war_exploded/route-servlet';
        }
    })
}

$(document).ready(function() {
    getCarsUser();
});