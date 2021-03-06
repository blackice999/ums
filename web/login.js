$(document).ready(function () {
    $('#loginForm').on('submit',function (event) {
        event.preventDefault();

        var payload = {
            email: $('#email').val(),
            password: $('#password').val()
        };

        jQuery.ajax({
            method: "POST",
            data: JSON.stringify(payload),
            url: App.constants.basePathJava + 'user/authenticate',
            success: function(resp) {
                console.log(resp);
                window.location = App.constants.basePathWeb + "ums/web/members.html?token=" + resp.token;
            },
            error: function(e) {
                console.log(e)
            }
        })
    })
})