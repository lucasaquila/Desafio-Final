angular.module('desafioApp').config(function($routeProvider, $httpProvider) {
	$httpProvider.interceptors.push('responseObserver');
	
	/*LOGIN*/
	$routeProvider.when("/logout", {
		templateUrl: "logout",
		controller: "usuarioController"
	});
	
	$routeProvider.when("/login", {
		templateUrl: "login",
		controller: "usuarioController"
	});
	
	$routeProvider.when("/denied", {
		templateUrl: "denied",
		controller: "usuarioController"
	});
	
	/*USUÁRIOS*/
	$routeProvider.when("/usuario", {
		templateUrl: "usuario",
		controller: "usuarioController"
	});
	
	$routeProvider.when("/usuario/form", {
		templateUrl: "usuario/form",
		controller: "usuarioController"
	});
	
	$routeProvider.when("/usuario/editar/:id", {
		templateUrl: "usuario/form",
		controller: "usuarioController"
	});
	
	/*CONTAS BANCÁRIAS*/
	$routeProvider.when("/contaBancaria", {
		templateUrl: "contaBancaria",
		controller: "contaBancariaController"
	});
	
	$routeProvider.when("/contaBancaria/form", {
		templateUrl: "contaBancaria/form",
		controller: "contaBancariaController",
	});
	
	$routeProvider.when("/contaBancaria/editar/:id", {
		templateUrl: "contaBancaria/form",
		controller: "contaBancariaController",
	});
	
	$routeProvider.when("/contaBancaria/saldoInicial/:id", {
		templateUrl: "contaBancaria/saldoInicial",
		controller: "contaBancariaController",
	});
	
	$routeProvider.when("/contaBancaria/detalhes/:id", {
		templateUrl: "contaBancaria/detalhes",
		controller: "contaBancariaController",
	});
	
	/*LANÇAMENTOS*/
	$routeProvider.when("/lancamento", {
		templateUrl: "lancamento",
		controller: "lancamentoController"
	});
	
	$routeProvider.when("/lancamento/deposito", {
		templateUrl: "lancamento/deposito",
		controller: "lancamentoController",
	});
	
	$routeProvider.when("/lancamento/saque", {
		templateUrl: "lancamento/saque",
		controller: "lancamentoController",
	});
	
	$routeProvider.when("/lancamento/transferencia", {
		templateUrl: "lancamento/transferencia",
		controller: "lancamentoController",
	});
	
	$routeProvider.when("/lancamento/detalhes/:id", {
		templateUrl: "lancamento/detalhes",
		controller: "lancamentoController",
	});
})
.factory('responseObserver', function responseObserver($q, $window) {
    return {
        'responseError': function(errorResponse) {
            switch (errorResponse.status) {
            case 403:
                $window.location = '#/denied';
                break;
            }
            return $q.reject(errorResponse);
        }
    };
});