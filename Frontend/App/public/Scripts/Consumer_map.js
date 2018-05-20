
function loadMapFromTitle() {
    
    var input = $("#titleInput").val();
    $('.searchForm').keyup(function () {
        $.ajax({
            url: "http://localhost:8080/ProjectGutenberg/api/api/getCitiesByBookTitle/" + input,
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $('.dataTable .tbody').empty();
                $('#errorText').empty().append("Could not find any results");
            }
        }).then(function (data) {
            console.log(data);
            if (data.length === 0) {
                console.log("EMPTY");
                $('#errorText').empty().append("Could not find any results");
                $('.dataTable .tbody').empty();
            }
            else {
                $('#mapid').empty().append(
                    $.map(data, function (item, index) {
                        return    L.marker([data[index].longitude, data[index].latitude]).addTo(mymap);
                    }).join());
                $('#errorText').empty();

            }
        });
    });
};
