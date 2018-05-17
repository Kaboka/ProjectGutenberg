
function fromCity() {
    var input = $("#cityInput").val();
    $('.searchForm').keyup(function () {
        $.ajax({
            url: "http://localhost:8080/ProjectGutenberg/api/api/getBookAuthorByCity/" + input,
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $('.dataTable .tbody').empty();
                $('#errorText').empty().append("Could not find any results");
                console.log(XMLHttpRequest.error);
            }
        }).then(function (data) {
            console.log(data);
            if (data.length === 0) {
                console.log("EMPTY");
                $('#errorText').empty().append("Could not find any results");
                $('.dataTable .tbody').empty();
            }
            else {
                $('.dataTable .tbody').empty().append(
                    $.map(data, function (item, index) {
                        return '<tr><td>' + data[index].author_name + '</td><td>' + data[index].book_title + '</td></tr>';
                    }).join());
                $('#errorText').empty();

            }
        });
    });
};
