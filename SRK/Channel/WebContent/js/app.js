var app = angular.module('app', ['ui.grid', 'ui.grid.edit', 'ui.grid.rowEdit', 'ui.grid.cellNav']);

app.controller('MainCtrl', ['$scope', '$http', '$q', '$interval','uiGridConstants', function ($scope, $http, $q, $interval,uiGridConstants) {

	$scope.journeys = {}
	$scope.journeys.enableSorting = false;

	$scope.journeys.columnDefs = [ {
		name : 'Z',
		enableCellEdit : true,
		width: 50
	}, {
		name : 'A',
		enableCellEdit : true,
		width : 50
	}, {
		name : 'S',
		enableCellEdit : true,
		width : 50
	}, {
		name : 'I',
		enableCellEdit : true,
		width : 50
	},{
		name : 'L',
		enableCellEdit : true,
		width : 50
	},
	{
		name : 'NaamSchip',
		enableCellEdit : false,
	},
	{
		name : 'CallSign',
		enableCellEdit : false,
	}
	];

	$http.get('api/journeys').success(function(data) {
		$scope.journeys.data = data;
	});

			$scope.journeys.onRegisterApi = function(gridApi) {
				// set gridApi on scope
				$scope.gridApi = gridApi;
				gridApi.rowEdit.on.saveRow($scope, $scope.saveRow);
				gridApi.edit.on.afterCellEdit($scope, function(rowEntity, colDef, newValue, oldValue) {
					$scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.ALL);
					$scope.$apply();
				});
			};

	$scope.saveRow = function( rowEntity ) {
	    var promise = $http.put('api/journeys',angular.toJson(rowEntity));
	    $scope.gridApi.rowEdit.setSavePromise( rowEntity, promise );
	};

} ]);
