var express = require('express');
var path = require('path');

var app = express();
var index = "./Views/index.html";

app.get('/', function (req, res) {
    res.sendFile(path.join(__dirname + '/Views/index.html'));
  })

app.listen(7777, function () {
    console.log("Server started, listening on: " + 7777);
});