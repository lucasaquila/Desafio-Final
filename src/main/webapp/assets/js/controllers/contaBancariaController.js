app.controller('contaBancariaController', function ($scope, $routeParams,$location, contaBancariaService, usuarioService, $mdToast, $mdDialog){

	/*Configurações de paginação no list.jsp*/
	$scope.query = {
			order: 'usuario.nome',
			limit: 5,
			page: 1
	};

	$scope.options = {
			rowSelection: true,
			multiSelect: true,
			autoSelect: true,
			decapitate: false,
			largeEditDialog: false,
			boundaryLinks: false,
			limitSelect: true,
			pageSelect: true
	};

	$scope.toast = function(message,type)
	{
		$mdToast.show({
			template: '<md-toast class="md-toast ' + type + '">' + message + '</md-toast>',
			hideDelay: 6000,
			position: 'bottom right'
		});
	}

	$scope.selected = [];

	$scope.detalhe = function(conta){
		$location.path("/contaBancaria/detalhes/" + conta.id);
	}

	$scope.listaBancos = function(){
		contaBancariaService.getBancos().
		success(function(data){
			$scope.bancos = data;
		})
	}

	var listaUsuarios = function(){
		usuarioService.getUsuarios().
		success(function(data){
			$scope.usuarios = data;
		})
	}

	listaUsuarios();

	$scope.bancos = [];
	$scope.usuarios = [];
	$scope.contaBancaria = {
			banco : "CAIXA",
			saldo : "0.00",
			usuario: null
	};

	if($routeParams.id != null){
		contaBancariaService.getContaBancaria($routeParams.id).
		success(function(conta){
			$scope.contaBancaria = conta;
		});
	}

	$scope.submit = function(contaBancaria){
		if(!contaBancaria.id)
		{
			contaBancariaService.saveContaBancaria($scope.contaBancaria).
			success(function(){
				$location.path("/contaBancaria");
				$scope.toast("Conta Bancária cadastrada com sucesso!", "success")
			})
			.error(function(data,status,headers,config) {
				$scope.toast(data, "error")
			})			  
		}
		else
		{
			contaBancariaService.editarContaBancaria($scope.contaBancaria.id, $scope.contaBancaria).
			success(function(){
				delete $scope.contaBancaria;
				$location.path("/contaBancaria");
				$scope.toast("Conta Bancária alterada com sucesso!", "success")
			})
			.error(function() {
			})
		}
	};


	$scope.contasBancarias = [];

	$scope.listContasBancarias = function () {
		contaBancariaService.getContasBancariasByRole().
		success ( function ( data ){
			$scope.contasBancarias = data;
		})
	};

	$scope.atualizarSaldoInicial = function(conta){
		contaBancariaService.inserirSaldoInicial(conta.id, conta.saldo).
		success ( function ()  {
			$location.path("/contaBancaria");
			$scope.toast("Saldo Inicial inserido com sucesso!", "success")
		})
		.error(function(data,status,headers,config) {
		})
	};

	var excluirContaBancaria = function(id)
	{
		contaBancariaService.excluirContaBancaria(id)
		.success(function(){
			contaBancariaService.getContasBancariasByRole().
			success ( function ( data )  {
				$scope.contasBancarias = data;
				$scope.toast("Conta Bancária excluída com sucesso!","success")
			})
		})
		.error(function(data) {
			$mdDialog.show(
					$mdDialog.alert()
					.clickOutsideToClose(true)
					.title('Operação Negada!')
					.textContent(data.mensagem)
					.ok('OK')
			);
		})
	};

	$scope.excluirContaBancariaDialog = function(conta){
		var confirm = $mdDialog.confirm()
		.title('Excluir Conta Bancária')
		.textContent('Você têm certeza que deseja exlcuir a Conta com o Número: ' + conta.numero + ', Banco: ' +  conta.banco + ', Agência: ' + conta.agencia + ', Usuário: ' + conta.usuario.nome + '?')
		.ariaLabel('Lucky day')
		.ok('Sim')
		.cancel('Não');
		$mdDialog.show(confirm).then(function() {
			excluirContaBancaria(conta.id)
		}, function() {

		});
	};

});