'use strict';
var app = angular.module('spaexample.controllers');

app.controller('HomeCtrl', function($scope, $http) {
	$http.get('services/greeting').success(function(data){
		console.log(data);
		$scope.name = data.name;
	});
});

