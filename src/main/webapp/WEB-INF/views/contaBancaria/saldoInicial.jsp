<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div layout="row" ng-init="listaBancos()">
	<div flex="20"></div>
	<div flex=60>
		<h2>Inserir Saldo Inicial</h2>
		<form name="formSaldoInicial" ng-submit="atualizarSaldoInicial(contaBancaria)">
			<div layout="row">
				<md-autocomplete md-selected-item="contaBancaria.usuario"
					ng-disabled="true" md-search-text="busca"
					md-items="usuario in usuarios | filter: busca"
					md-item-text="usuario.nome" md-floating-label="Usuário" flex="70">
					<span md-highlight-text="nome">{{usuario.nome + ' - ' +	usuario.email }}</span> 
				</md-autocomplete>
			</div>
			<div layout="row">
				<md-input-container class="md-block" flex> 
					<label>Banco</label>
					<md-select ng-model="contaBancaria.banco" ng-disabled="true">
						<md-option ng-repeat="banco in bancos" value="{{banco}}">
							{{banco}} 
						</md-option> 
					</md-select> 
				</md-input-container>
				<md-input-container class="md-block" flex-gt-sm flex="30">
					<label>Agência</label>
					 <input ng-model="contaBancaria.agencia" ng-disabled="true" placeholder="Insira a agência"> 
				</md-input-container>
				<md-input-container class="md-block" flex-gt-sm flex="30">
					<label>Número da Conta</label> 
					<input ng-model="contaBancaria.numero" placeholder="Insira o número da conta" ng-disabled="true">
				</md-input-container>
			</div>
			<div layout="row">
				<md-input-container class="md-block" flex> 
					<label>Saldo Inicial</label> 
					<input type="number" name="saldo" min="1" max="99999999.99"	ng-model="contaBancaria.saldo" ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" step="0.01" required />
					<div ng-messages="formSaldoInicial.saldo.$error">
						<div ng-message="max">Insira um valor com menos de 8 casas
							decimais antes da vírgula e 2 casas decimais após a vírgula.</div>
						<div ng-message="min">Insira um valor maior que 0 (zero).</div>
						<div ng-message="pattern">Insira um valor no formato
							monetário em real.</div>
						<div ng-message="required">Este campo é obrigatório.</div>
					</div>
				</md-input-container>
			</div>
			<div layout="row">
				<md-button class="md-raised" href="#/contaBancaria">Voltar</md-button>
				<div flex></div>
				<md-button class="md-raised md-primary" type="submit" ng-disabled="formSaldoInicial.$invalid">Inserir</md-button>
			</div>
		</form>
		<div flex></div>
	</div>
</div>