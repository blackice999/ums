$(document).ready(function () {
    $('#studentsBtn').on('click',function (event) {
        event.preventDefault();

        var payload = {
            token: getSearchParameters().token,
        };

        jQuery.ajax({
            method: "POST",
            data: JSON.stringify(payload),
            url: App.constants.basePathJava + 'semester/list',
            success: function(resp) {

             traverse(resp);
            },
            error: function(e) {
                console.log(e)
            }
        })
    })
})