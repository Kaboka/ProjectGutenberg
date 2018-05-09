$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/ProjectGutenberg/api/api/test"
    }).then(function(data) {
       $('.header').append(data[0].title);
    });
});