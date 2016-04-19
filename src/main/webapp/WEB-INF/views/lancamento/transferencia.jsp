<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div layout="row">
	<div flex="20"></div>
	<div flex=60>
		<h2>Transferência entre Contas</h2>
		<form name="formLancamento" ng-submit="transferirDialog()">
		<div layout="row" ng-init="listContasBancariasByRole()">
			<md-autocomplete md-selected-item="lancamento.contaBancariaOrigem"
				required
				md-search-text="buscaOrigem"
				md-items="conta in contasBancariasOrigem | filter: buscaOrigem"
				md-item-text="'Banco: ' + conta.banco + ' - Nr. Conta: ' + conta.numero + ' - Titular: ' + conta.usuario.nome" 
				md-floating-label="Conta Origem" flex>
        <md-item-template>
          <span class="item-title">
            <strong>Titular:</strong> {{conta.usuario.nome}} 
          </span>
          <span class="item-metadata">
            <span class="item-metastat">
              <strong>Banco:</strong>  {{conta.banco}}
            </span>
            <span class="item-metastat">
              <strong>Nr. Conta:</strong>  {{conta.numero}}
            </span> 
          </span>
        </md-item-template>
    	</md-autocomplete>
		</div>
		<div layout="row" ng-init="listContasBancarias()">
			<md-autocomplete md-selected-item="lancamento.contaBancariaDestino"
				required
				md-search-text="buscaDestino"
				md-items="conta in contasBancariasDestino | filter: buscaDestino"
				md-item-text="'Banco: ' + conta.banco + ' - Nr. Conta: ' + conta.numero + ' - Titular: ' + conta.usuario.nome" 
				md-floating-label="Conta Destino" flex>
        <md-item-template>
          <span class="item-title">
            <strong>Titular:</strong> {{conta.usuario.nome}} 
          </span>
          <span class="item-metadata">
            <span class="item-metastat">
              <strong>Banco:</strong>  {{conta.banco}}
            </span>
            <span class="item-metastat">
              <strong>Nr. Conta:</strong>  {{conta.numero}}
            </span> 
          </span>
        </md-item-template>
    	</md-autocomplete>
		</div>
		<div ng-app="lancamentoController" ng-include="'assets/partials/lancamento.jsp'"></div>
				<div layout="row">
			<md-button class="md-raised" href="#/lancamento">Voltar</md-button>
			<div flex></div>
			<!-- <md-button class="md-raised md-primary" ng-click="transferir()">Transferir</md-button> -->
			<md-button class="md-raised md-primary" type="submit" ng-disabled="formLancamento.$invalid">Transferir</md-button>
		</div>
		</form>
		</div>
		
		<div flex></div>
		

</div>