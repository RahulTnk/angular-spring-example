'use strict';
var app = angular.module('spaexample.controllers');

app.controller('CarsListCtrl', function($scope, $rootScope, $location, CarsFactory,  UtilsFactory){
	
	$scope.listAll = function(){
		$rootScope.showProcessing();  
		CarsFactory.query(function(response){
				$rootScope.closeProcessing();
				$scope.cars = response ? response : [];
		  	},
			function(error){
				$rootScope.addError(error);
			}
		);
	}
	$scope.listAll();
  
	$scope.editCar = function(carId){
		$location.path('/cars-edit/'+carId);
	}
  
	$scope.insertCar = function(){
		$location.path('/cars-insert');
	}
    $scope.deleteCar = function(car){
    	$rootScope.showProcessing();
    	CarsFactory.delete({id:car.id}, 
    			function(){
    				$rootScope.closeProcessing();
    				UtilsFactory.addAlert('success','Car deleted!');
    				$scope.listAll();
    			},
    			function(error){
    				$rootScope.addError(error);
    			}
		);
    }
});

