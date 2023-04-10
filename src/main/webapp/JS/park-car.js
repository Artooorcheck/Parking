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

$(document).ready(function() {
    $('#park_id').change(getPlaces);
    getAllParks();
});