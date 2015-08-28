var wsUri = "ws://" + document.location.host + document.location.pathname + "journeyendpoint";
var websocket = new WebSocket(wsUri);

websocket.onerror = function(evt) { onError(evt) };

function onError(evt) {
    writeToScreen('<p style="color: red;">WebSocket: Notification Disabled</p>');
}

var output = document.getElementById("output");
websocket.onopen = function(evt) { onOpen(evt) };

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}

function onOpen() {
    writeToScreen("<p>WebSocket: Notification Enabled</p>");
}


