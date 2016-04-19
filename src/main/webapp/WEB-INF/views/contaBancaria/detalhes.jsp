<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div layout="row" ng-init="listContasBancarias()" id="fromHTMLtestdiv">
	<div flex="20"></div>
	<div flex=60>
		<h2>Detalhes da Conta Bancária</h2>
		<div layout="row">
			<md-input-container class="md-block" flex-gt-sm flex="50">
				<div layout="row">
					<span class="detail-label">Titular</span>
				</div>
				<div layout="row">
					<span ng-bind="contaBancaria.usuario.nome"  class="detail"></span>
				</div>
			</md-input-container>
		</div>
 		<div layout="row">
			<md-input-container class="md-block" flex-gt-sm flex="40">
				<div layout="row">
					<span class="detail-label">Nr. Conta</span>
				</div>
				<div layout="row">
					<span ng-bind="contaBancaria.numero"  class="detail"></span>
				</div>
			</md-input-container>
			<md-input-container class="md-block" flex-gt-sm flex="40">
				<div layout="row">
					<span class="detail-label">Banco</span>
				</div>
				<div layout="row">
					<span ng-bind="contaBancaria.banco"  class="detail"></span>
				</div>
			</md-input-container>
			<md-input-container class="md-block" flex-gt-sm flex>
				<div layout="row">
					<span class="detail-label">Agência</span>
				</div>
				<div layout="row">
					<span ng-bind="contaBancaria.agencia"  class="detail"></span>
				</div>
			</md-input-container>
		</div>
		<div layout="row">
			<md-input-container class="md-block" flex-gt-sm flex="50">
				<div layout="row">
					<span class="detail-label">Saldo</span>
				</div>
				<div layout="row">
					<span ng-bind="(contaBancaria.saldo | currency)"  class="detail"></span>
				</div>
			</md-input-container>
		</div>
		<div layout="row">
			<md-button class="md-raised" href="#/contaBancaria">Voltar</md-button>
			<div flex></div>
		</div>
	</div>
	<div flex></div>
</div>