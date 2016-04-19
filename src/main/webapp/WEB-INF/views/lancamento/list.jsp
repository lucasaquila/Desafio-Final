<%-- <%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
    <md-content layout="column" flex ng-init="buscar()">
    	<div layout="row">
    	<h2 style="margin-left:10px">Movimentações</h2>
    	    		<div flex layout="row" layout-align="end end">
				<md-button class="md-raised md-primary" style="color:white;" href="#/lancamento/deposito">Depósito<ng-md-icon icon="arrow_forward"></g-md-icon></md-button>
    			<md-button class="md-raised md-primary" style="color:white;" href="#/lancamento/saque">Saque<ng-md-icon icon="arrow_back"></g-md-icon></md-button>
    			<md-button class="md-raised md-primary" style="color:white;" href="#/lancamento/transferencia">Transferência<ng-md-icon icon="swap_horiz"></md-button>
    		</div>
    	
    	</div>
      <md-card>
        <md-toolbar class="md-table-toolbar md-default table-head">
          <div class="md-toolbar-tools">
       <div layout="row">
    	<md-datepicker style="background-color: #fff; margin-right:20px" ng-model="dataDe" md-placeholder="De"></md-datepicker>
    	<md-datepicker style="background-color: #fff; margin-right:20px" ng-model="dataAte" md-placeholder="Até"></md-datepicker>
		<md-button class="md-raised" ng-click="buscar()">Buscar</md-button>
    	</div>
		    <div flex="40">
    	        <md-input-container class="md-block">

          		</md-input-container>
	    	</div>
          </div>
        </md-toolbar>
        <md-divider></md-divider>
        <md-table-container ng-show="lancamentos.length">
          <table md-table md-disable-select="" md-progress="promise">
            <thead md-head md-order="query.order" md-on-reorder="logOrder">
              <tr md-row>
                <th md-column md-order-by="tipoLancamento"><span>Operação</span></th>
                <th md-column md-order-by="valor"><span>Valor</span></th>
                <th md-column md-order-by="contaBancaria.banco"><span>Banco</span></th>
                <th md-column md-order-by="contaBancaria.numero"><span>Nr. Conta</span></th>
                <th md-column md-order-by="contaBancaria.usuario.nome"><span>Titular</span></th>
                <th md-column md-order-by="data"><span>Data</span></th>
              </tr>
            </thead>
            <tbody md-body>
              <tr md-row ng-click="detalhe(lancamento)" ng-repeat="lancamento in lancamentos | filter: busca | orderBy: '-tipoLancamento' | orderBy: query.order | limitTo: query.limit : (query.page -1) * query.limit">
                <td md-cell>{{lancamento.tipoLancamento}}</td>
                <td md-cell>{{lancamento.valor  | currency}}</td>
                <td md-cell>{{lancamento.contaBancaria.banco}}</td>
                <td md-cell>{{lancamento.contaBancaria.numero}}</td>
                <td md-cell>{{lancamento.contaBancaria.usuario.nome}}</td>
                <td md-cell>{{ lancamento.data | date:'dd/MM/yyyy'}}</td>
              </tr>
            </tbody>
          </table>
        <md-table-pagination md-limit="query.limit" md-page="query.page" md-total="{{lancamentos.length}}" md-page-select="options.pageSelector" md-boundary-links="options.boundaryLinks" md-on-paginate="logPagination"></md-table-pagination>
        </md-table-container>
		<div flex class="table-empty" ng-if="lancamentos.length == 0">
			<span> Não foram encontrados registros de movimentação. </span>
		</div>

</md-card>
    </md-content>
	
