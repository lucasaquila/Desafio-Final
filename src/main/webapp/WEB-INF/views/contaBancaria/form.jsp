<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div layout="row" ng-init="listaBancos()">
	<div flex="20"></div>
	<div flex=60>
		<h2>Cadastro de Conta Bancária</h2>
		<form name="formContaBancaria" ng-submit="submit(contaBancaria)">
			<div layout="row">
				<md-autocomplete flex="70"
						required
						md-input-name="autocomplete" 
						md-selected-item="contaBancaria.usuario"
						md-search-text="busca"
						md-items="usuario in usuarios | filter: busca"
						md-item-text="usuario.nome" 
						md-floating-label="Usuário">
					<span md-highlight-text="nome">{{usuario.nome + ' - ' +	usuario.email }}</span>
				    <div ng-messages="formContaBancaria.autocomplete.$error">
				      <div ng-message="required">This field is required</div>
				    </div>
					<md-not-found>Usuário não encontrado.</md-not-found>
				</md-autocomplete>
			</div>
		<div layout="row">
   		<md-input-container class="md-block" flex> 
			<label>Banco</label>
			<md-select ng-model="contaBancaria.banco">
			<md-option ng-repeat="banco in bancos" value="{{banco}}">
			{{banco}} </md-option> 
			</md-select> 
		</md-input-container>
		<md-input-container class="md-block" flex-gt-sm flex="30">
			<label>Agência</label> 
			<input type="text" name="agencia" ng-model="contaBancaria.agencia" ng-pattern="/^(0|[0-9]*)$/" md-maxlength="4" maxlength="4" required />
			<div ng-messages="formContaBancaria.agencia.$error">
				<div ng-message="pattern">Insira apenas números entre 0 a 9.</div>
				<div ng-message="required">Este campo é obrigatório.</div>
			</div>
		</md-input-container>
		<md-input-container class="md-block" flex-gt-sm flex="30">
			<label>Número da Conta</label> 
			<!-- <input type="text" ng-pattern="/^\d+$/" ng-model="contaBancaria.numero" name="numero" required> -->
			<input type="text" name="numero" ng-model="contaBancaria.numero" ng-pattern="/^(0|[0-9]*)$/" md-maxlength="10" maxlength="10" required />
				<div ng-messages="formContaBancaria.numero.$error">
				<div ng-message="required">Este campo é obrigatório.</div>
				<div ng-message="pattern">Insira apenas número inteiros.</div>
			</div>
			</md-input-container>
		</div>
		<div layout="row">
			<md-button class="md-raised" href="#/contaBancaria">Voltar</md-button>
			<div flex></div>
				<md-button class="md-raised md-primary" type="submit" ng-disabled="formContaBancaria.$invalid">Salvar</md-button>
		</div>
		<div flex></d
		
		iv>
		</form>
	</div>
</div>