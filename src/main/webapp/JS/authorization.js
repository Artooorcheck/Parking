
let errorText;

function signIn() {
    $.ajax({
        method: 'post',
        url: 'authorization-servlet',
        data: {
            login: $('#login').val(),
            password: $('#password').val()
        },
        success: function (data) {
            if (data == null || data === "") {
                window.location = '/Parking_war_exploded/route-servlet';
                return;
            }
            errorText.html(data);
        }
    });
};


function signUp() {
    $.ajax({
        method: 'post',
        url: 'registration-servlet',
        data: {
            login: $('#login').val(),
            password: $('#password').val(),
            userName: $('#userName').val(),
            cardNumber: $('#cardNumber').val()
        },
        success: function (data) {
            if (data == null || data === "") {
                window.location = '/Parking_war_exploded/route-servlet';
                return;
            }
            errorText.html(data);
        }
    });
};

function resetError() {
    errorText.html('');
}

$(document).ready(function () {
    errorText = $('#error-text');
    $('#login').change(resetError);
    $('#password').change(resetError);
    $('#userName').change(resetError);
    $('#cardNumber').change(resetError);
});