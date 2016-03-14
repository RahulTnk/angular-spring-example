'use strict';
var app = angular.module('spaexample.services');
/**
 * Utility Factory
 */
app.factory('UtilsFactory', function($rootScope){
	var factory = {};
	
	$rootScope.alerts = [];
	
	factory.addAlert = function(type, msg){
		$rootScope.alerts.push({"type":type, "msg":msg});
	}
	
	factory.closeAlert = function(index){
		$rootScope.alerts.splice(index,1);
	}
	
	$rootScope.backendException = null;
	
	factory.addBackendException = function(error){
		$rootScope.backendException = error;
	}
	
	factory.clearBackendException = function(){
		$rootScope.backendException = null;
	}
	
	return factory;
});