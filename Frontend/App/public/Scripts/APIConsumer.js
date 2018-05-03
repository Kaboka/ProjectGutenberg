var request = new XMLHttpRequest();
request.open('GET', 'http://localhost:8080/ProjectGutenberg/api/api/test', true);
request.onload = function () {
    var data = JSON.parse(this.response);
    console.log(data);

    const h2 = document.getElementById('header');
    h2.textContent = data;
}
request.send();