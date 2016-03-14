'use strict';

angular.module('spaexample', 
		[ 'ngRoute', 
		  'ui.bootstrap',
		  'spaexample.services', 
		  'spaexample.controllers' ])
  .config(function($routeProvider, $httpProvider){
	 $routeProvider
	 .when('/', { templateUrl : 'views/home.html', controller: 'HomeCtrl' })
	 .when('/cars-list', { templateUrl: 'views/cars-list.html', controller: 'CarsListCtrl' })
	 .when('/cars-insert', { templateUrl: 'views/cars-insert.html', controller: 'CarsInsertCtrl' })
	 .when('/cars-edit/:id', { templateUrl: 'views/cars-edit.html', controller: 'CarsEditCtrl' })
	 .when('/error', { templateUrl: 'views/error.html'} )
	 .otherwise('/');
	 
	 
  });

