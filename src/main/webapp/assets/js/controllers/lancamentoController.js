app.controller('lancamentoController', function ($scope, $routeParams,$location,usuarioService, lancamentoService, contaBancariaService, $mdDialog, $mdMedia, $q, $mdToast){

	/*Configurações de paginação no list.jsp*/
	$scope.query = {
			order: 'tipoLancamento',
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

	if($routeParams.id != null){
		lancamentoService.getLancamento($routeParams.id).
		success(function(lancamento){
			$scope.lancamento = lancamento;
		});
	}

	$scope.toast = function(message,type)
	{
		$mdToast.show({
			template: '<md-toast class="md-toast ' + type + '">' + message + '</md-toast>',
			hideDelay: 6000,
			position: 'bottom right'
		});
	}


	$scope.selected = [];
	$scope.dataDe = null;
	$scope.dataAte = null;
	$scope.lancamento = {
			data : new Date(),
			tipoLancamento : "ENTRADA"
	}
	$scope.lancamentos = [];


	$scope.buscar = function(){
		lancamentoService.getLancamentosData($scope.dataDe, $scope.dataAte).
		success ( function (data)  {
			$scope.lancamentos = data;
		})

	};

	$scope.detalhe = function(lancamento){
		$location.path("/lancamento/detalhes/" + lancamento.id);
	}

	$scope.listLancamentos = function () {
		lancamentoService.getLancamentos().
		success ( function ( data )  {
			$scope.lancamentos = data;
		})
	};

	$scope.listContasBancarias = function () {
		contaBancariaService.getContasBancarias().
		success ( function ( data )  {
			$scope.contasBancariasDestino = data;
		})
	};

	$scope.listContasBancariasByRole = function () {
		contaBancariaService.getContasBancariasByRole().
		success ( function ( data )  {
			$scope.contasBancariasOrigem = data;
		})
	};

	$scope.depositarDialog = function(lancamento){
		var confirm = $mdDialog.confirm()
		.title('Confirmar Depósito')
		.textContent('Você têm certeza que deseja realizar este depósito?')
		.ariaLabel('Lucky day')
		.ok('Sim')
		.cancel('Não');
		$mdDialog.show(confirm).then(function() {
			depositar();
		}, function() {

		});
	};

	var depositar = function() {
		if ( $scope.lancamento.contaBancaria &&
				$scope.lancamento.contaBancaria.id ) {
			$scope.lancamento.tipoLancamento = "ENTRADA"
				lancamentoService.efetuarDeposito($scope.lancamento).
				success(function(){
					$location.path("/lancamento");
					$scope.toast("Depósito realizado com sucesso.", "success")
				})
				.error(function(data,status,headers,config,response) {
				})
		} else {
			$scope.toast("Selecione uma Conta Bancária.", "error")
		}
	};


	$scope.sacarDialog = function(){
		var confirm = $mdDialog.confirm()
		.title('Confirmar Saque')
		.textContent('Você têm certeza que deseja realizar este saque?')
		.ariaLabel('Lucky day')
		.ok('Sim')
		.cancel('Não');
		$mdDialog.show(confirm).then(function() {
			sacar();
		}, function() {

		});
	};

	var sacar = function() {
		if ( $scope.lancamento.contaBancaria &&
				$scope.lancamento.contaBancaria.id ) {
			$scope.lancamento.tipoLancamento = "SAIDA"
				lancamentoService.efetuarSaque($scope.lancamento).
				success(function(data, status, headers, config){
					$location.path("/lancamento");
					$scope.toast("Saque realizado com sucesso.", "success")
				})
				.error(function(data, status, headers, config, errorMessage) {
				})
		} else {
			$scope.toast("Selecione uma Conta Bancária.", "error")
		}
	};

	$scope.transferirDialog = function(){
		var confirm = $mdDialog.confirm()
		.title('Confirmar Transferência')
		.textContent('Você têm certeza que deseja realizar esta transferência?')
		.ariaLabel('Lucky day')
		.ok('Sim')
		.cancel('Não');
		$mdDialog.show(confirm).then(function() {
			transferir();
		}, function() {

		});
	};

	var transferir = function()
	{ 
		if ( $scope.lancamento.contaBancariaOrigem && $scope.lancamento.contaBancariaOrigem.id &&
				$scope.lancamento.contaBancariaDestino && $scope.lancamento.contaBancariaDestino.id	)
		{
			var entrada = $scope.lancamento;
			var saida = {
					data: $scope.lancamento.data,
					valor: $scope.lancamento.valor,
					observacao: $scope.lancamento.observacao,
					contaBancaria: $scope.lancamento.contaBancariaOrigem,
					tipoLancamento : "SAIDA"
			};

			var entrada = {
					data: $scope.lancamento.data,
					valor: $scope.lancamento.valor,
					observacao: $scope.lancamento.observacao,
					contaBancaria: $scope.lancamento.contaBancariaDestino,
					tipoLancamento : "ENTRADA"
			};

			var transferencia = {
					saida: saida,
					entrada: entrada
			}
			lancamentoService.efetuarTransferencia(transferencia).
			success(function(data, status, headers, config){
				$location.path("/lancamento");
				$scope.toast("Transferência realizada com sucesso!", "success")
			})
			.error(function(data, status, headers, config, errorMessage) {
			})
		}
		else{
			$scope.toast("Selecione uma Conta Bancária de Origem e Destino.", "error")
		}
	}



});