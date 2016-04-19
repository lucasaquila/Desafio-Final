<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<div layout="row" ng-init="listContasBancarias()">
	<div flex="20"></div>
	<div flex=60>
		<h2>Depósito</h2>
		<form name="formLancamento" ng-submit="depositarDialog()">
		<div layout="row">
			<md-autocomplete required 
				md-selected-item="lancamento.contaBancaria"
				md-search-text="busca"
				md-items="conta in contasBancariasDestino | filter: busca"
				md-item-text="'Banco: ' + conta.banco + ' - Nr. Conta: ' + conta.numero + ' - Titular: ' + conta.usuario.nome" 
				md-floating-label="Conta Bancária" 
				md--search-text-change="selectedItemChange(lancamento.contaBancaria)"
				flex>
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
			<md-button class="md-raised md-primary" type="submit" ng-disabled="formLancamento.$invalid">Depositar</md-button>
		</div>
		</form>
		</div>
		<div flex></div>
</div>