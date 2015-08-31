var wsUri = "ws://" + document.location.host + document.location.pathname + "journeyendpoint";
var websocket = new WebSocket(wsUri);

websocket.onerror = function(evt) { onError(evt) };

function onError(evt) {
    writeToScreen('<p style="color: red;">WebSocket: Notification Disabled</p>');
}

var output = document.getElementById("output");
websocket.onopen = function(evt) { onOpen(evt) };
websocket.onmessage = function(evt) { onMessage(evt) };

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}

function onOpen() {
    writeToScreen("<p>WebSocket: Notification Enabled</p>");
}

function onMessage(evt) {
	//writeToScreen('<span style="color: blue;">RESPONSE: ' + evt.data + '</span>');

	var data = angular.fromJson(evt.data);

	var appElement = document.querySelector('[ng-controller="MainCtrl"]');
    var $scope = angular.element(appElement).scope();
    $scope.$apply(function() {
    	for (var int = 0; int < $scope.journeys.data.length; int++) {
			var record = $scope.journeys.data[int];
			if (record.Id == data.Id) {
				$scope.journeys.data[int] = data;

			}
		}
    });
}


