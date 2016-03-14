'use strict';
var app = angular.module('spaexample.services');
/**
 * Factory to interact with the REST Backend
 */
app.factory('CarsFactory', function($resource){
	return $resource('services/cars/:id', 
			{ id: '@id' },
			{ update: { method: 'PUT' }}
	);
});