angular.module("desafioApp").factory("usuarioService", ['$http', function($http) {
	
	var _getUsuarios = function(){
		return $http.get('/desafio/usuario/listagem');
	};
	
	var _getUsuario = function(id){
		return $http.get('/desafio/usuario/' + id);
	};
	
	var _saveUsuario = function(contato){
		return $http.post('/desafio/usuario/save', contato);
	};
	
	var _alterarSituacao = function(id, usuario){
		return $http.put('/desafio/usuario/alteraSituacao/'+id, usuario);
	};
	
	var _editarUsuario = function(id, usuario){
		return $http.put('/desafio/usuario/editar/'+id, usuario);
	};	
	
	var _excluirUsuario = function(id){
		return $http.delete('/desafio/usuario/excluirUsuario/'+id);
	}
	
	return {
		getUsuarios: _getUsuarios,
		getUsuario: _getUsuario,
		saveUsuario: _saveUsuario,
		alterarSituacao: _alterarSituacao,
		excluirUsuario: _excluirUsuario,
		editarUsuario: _editarUsuario
	};
}]);