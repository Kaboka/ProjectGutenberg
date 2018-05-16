$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/ProjectGutenberg/api/api/getBookAuthorByCity"
    }).then(function(data) {
        $('.dataTable').append(
            $.map(data, function (item, index) {
                return '<tr><td>' + data[index].author + '</td><td>' + data[index].title + '</td></tr>';
        }).join());
    });
    
});