angular.module("desafioApp").controller('indexController', ['$mdEditDialog', '$q', '$scope', '$timeout', '$http', 'usuarioService','$location',  function ($mdEditDialog, $q, $scope, $timeout, $http, usuarioService, $location) {
	'use strict';
	$scope.redirecionar = function(link){
		$location.path(link);
	}
}]);


