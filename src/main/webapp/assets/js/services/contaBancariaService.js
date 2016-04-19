angular.module("desafioApp").factory("contaBancariaService", ['$http', function($http) {
	
	var _getContasBancariasByRole = function(){
		return $http.get('/desafio/contaBancaria/getContasBancariasByRole');
	};
	
	var _getContasBancarias = function(){
		return $http.get('/desafio/contaBancaria/getContasBancarias');
	};
	
	var _getContaBancaria = function(id){
		return $http.get('/desafio/contaBancaria/' + id);
	};
	
	var _getBancos = function(){
		return $http.get('/desafio/contaBancaria/getBancos');
	};
	
	var _saveContaBancaria = function(contaBancaria){
		return $http.post('/desafio/contaBancaria/save', contaBancaria);
	};
	
	var _inserirSaldoInicial = function(id, contaBancaria){
		return $http.put('/desafio/contaBancaria/saldoInicial/'+id, contaBancaria);
	};
	
	var _editarContaBancaria = function(id, contaBancaria){
		return $http.put('/desafio/contaBancaria/editar/'+id, contaBancaria);
	};	
	
	var _excluirContaBancaria = function(id){
		return $http.delete('/desafio/contaBancaria/excluir/'+id);
	}
	
	return {
		getContasBancariasByRole: _getContasBancariasByRole,
		getContasBancarias: _getContasBancarias,
		getContaBancaria: _getContaBancaria,
		getBancos: _getBancos,
		saveContaBancaria: _saveContaBancaria,
		inserirSaldoInicial: _inserirSaldoInicial,
		editarContaBancaria: _editarContaBancaria,
		excluirContaBancaria: _excluirContaBancaria
	};
}]);