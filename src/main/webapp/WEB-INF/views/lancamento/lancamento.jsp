<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div layout="row" ng-init="listContasBancarias()">
	<div flex="20"></div>
	<div flex=60>
		<h2>Depósito</h2>
		<div layout="row">
			<md-autocomplete md-selected-item="lancamento.contaBancaria"
				md-search-text="busca"
				md-items="conta in contasBancarias | filter: busca"
				md-item-text="'Banco: ' + conta.banco + ' - Nr. Conta: ' + conta.numero + ' - Titular: ' + conta.usuario.nome" 
				md-floating-label="Conta Bancária" flex>
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
		<div layout="row">
			<md-content flex>
				<label>Data</label>
				<md-datepicker ng-model="lancamento.data" md-placeholder="Data" disabled></md-datepicker>
			</md-content>
		</div>
		<div layout="row">
		<md-input-container class="md-block" flex>
	    	<label>Valor</label> 
			<input type="number" ng-model="lancamento.valor">
		</md-input-container>
		</div>
		<div layout="row">
        <md-input-container class="md-block" flex>
          <label>Observações</label>
          <textarea ng-model="lancamento.observacao" md-maxlength="255" rows="15" md-select-on-focus></textarea>
        </md-input-container>
		</div>
		<div layout="row">
			<md-button class="md-raised" href="#/lancamento">Voltar</md-button>
			<div flex></div>
			<md-button class="md-raised md-primary" ng-click="adicionarContaBancaria(contaBancaria)">Depositar</md-button>
		</div>
		<div flex></div>
	</div>
</div>