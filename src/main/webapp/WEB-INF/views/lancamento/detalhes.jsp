<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<div layout="row" ng-init="listContasBancarias()" id="fromHTMLtestdiv">
	<div flex="20"></div>
	<div flex=60>
		<h2>Detalhes da Movimentação</h2>
			<div layout="row">
				<md-input-container class="md-block" flex-gt-sm flex="50">
				<div layout="row">
					<span class="detail-label">Titular</span>
				</div>
				<div layout="row">
					<span ng-bind="lancamento.contaBancaria.usuario.nome"  class="detail"></span>
				</div>
				</md-input-container>
			</div>
 			<div layout="row">
				<md-input-container class="md-block" flex-gt-sm flex="40">
				<div layout="row">
					<span class="detail-label">Nr. Conta</span>
				</div>
				<div layout="row">
					<span ng-bind="lancamento.contaBancaria.numero"  class="detail"></span>
				</div>
				</md-input-container>
				<md-input-container class="md-block" flex-gt-sm flex="40">
				<div layout="row">
					<span class="detail-label">Banco</span>
				</div>
				<div layout="row">
					<span ng-bind="lancamento.contaBancaria.banco"  class="detail"></span>
				</div>
				</md-input-container>
				<md-input-container class="md-block" flex-gt-sm flex>
				<div layout="row">
					<span class="detail-label">Agência</span>
				</div>
				<div layout="row">
					<span ng-bind="lancamento.contaBancaria.agencia"  class="detail"></span>
				</div>
				</md-input-container>
			</div>
			<div layout="row">
				<md-input-container class="md-block" flex-gt-sm flex="40">
				<div layout="row">
					<span class="detail-label">Valor</span>
				</div>
				<div layout="row">
					<span ng-bind="(lancamento.valor | currency)"  class="detail"></span>
				</div>
				</md-input-container>
				<md-input-container class="md-block" flex-gt-sm flex="40">
				<div layout="row">
					<span class="detail-label">Data</span>
				</div>
				<div layout="row">
					<span ng-bind="(lancamento.data | date:'dd/MM/yyyy')" class="detail"></span>
				</div>
				</md-input-container>
				<md-input-container class="md-block" flex-gt-sm flex>
				<div layout="row">
					<span class="detail-label">Operação</span>
				</div>
				<div layout="row">
					<span ng-bind="lancamento.tipoLancamento | capitalize"  class="detail"></span>
				</div>
				</md-input-container>
			</div>
			<md-input-container class="md-block" flex>
			<div layout="row">
				<span class="detail-label">Observações</span>
			</div>
			<div layout="row">
				<span ng-bind="lancamento.observacao" class="detail"></span>
			</div>
			</md-input-container>


			<div layout="row">
			<md-button class="md-raised" href="#/lancamento">Voltar</md-button>
			<div flex></div>
		</div>
		</div>
		<div flex></div>
</div>