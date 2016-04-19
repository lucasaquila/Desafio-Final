<%-- <%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
    <md-content layout="column" flex ng-init="listUsers()">
    <div layout="row">
    	<h2 style="margin-left:10px">Lista de Usuários</h2>
		<security:authorize access="hasAnyRole('ADMINISTRADOR')">
		<div flex layout="row" layout-align="end end">
			<md-button class="md-raised md-primary" style="color:white; background-color:#4EBD4E" href="#/usuario/form">Novo</md-button>
		</div>
		</security:authorize>
	</div>
      <md-card>
        <md-toolbar class="md-table-toolbar md-default table-head" ng-hide="options.rowSelection && selected.length">
          <div class="md-toolbar-tools">
		    <div flex="40">
    	        <md-input-container class="md-block">
        	    <label>Pesquisar</label>
            	<input ng-model="busca">
          		</md-input-container>
	    	</div>
          </div>
        </md-toolbar>
        <md-divider></md-divider>
        <md-table-container>
          <table md-table md-disable-select="" md-progress="promise">
            <thead md-head md-order="query.order" md-on-reorder="logOrder">
              <tr md-row>
                <th md-column md-order-by="nome"><span>Nome</span></th>
                <th md-column md-order-by="email"><span>E-mail</span></th>
                <th md-column md-order-by="tipo"><span>Tipo</span></th>
                <th md-column md-order-by="situacao"><span>Situação</span></th>
                <security:authorize access="hasAnyRole('ADMINISTRADOR')">
                <th md-column><span>Ações</span></th>
                </security:authorize>
              </tr>
            </thead>
            <tbody md-body>
              <tr md-row md-auto-select="" ng-repeat="usuario in usuarios | filter: busca | orderBy: '-nome'  | orderBy: query.order | limitTo: query.limit : (query.page -1) * query.limit" >
                <td md-cell>{{usuario.nome}}</td>
                <td md-cell>{{usuario.email}}</td>
                <td md-cell>{{usuario.tipoUsuario == "ROLE_USUARIO" ? "Usuário" : "Administrador" }}</td>
                <security:authorize access="hasAnyRole('ADMINISTRADOR')">
                <td md-cell>
                    <md-switch ng-model="usuario.situacao" aria-label="Switch 2" class="md-primary" ng-change="alterarSituacaoDialog(usuario)">
    				{{ usuario.situacao == true ? "Ativado" : "Desativado" }} 
    				</md-switch>
                </td>
                </security:authorize>
                <security:authorize access="hasAnyRole('USUARIO')">
                <td md-cell>
                    <md-switch ng-model="usuario.situacao" aria-label="Disabled active switch" ng-disabled="true">
    				{{ usuario.situacao == true ? "Ativado" : "Desativado" }} 
    				</md-switch>
                </td>
                </security:authorize>
                <security:authorize access="hasAnyRole('ADMINISTRADOR')">
                <td md-cell>
        			<md-button class="md-icon-button md-primary md-button md-ink-ripple" size="20" aria-label="edit" href="#/usuario/editar/{{usuario.id}}">
        			<ng-md-icon icon="edit" size="20"> </g-md-icon>
					</md-button>
				</td>
				</security:authorize>
              </tr>
            </tbody>
          </table>
        </md-table-container>

        <md-table-pagination md-limit="query.limit" md-page="query.page" md-total="{{usuarios.length}}" md-page-select="options.pageSelector" md-boundary-links="options.boundaryLinks" md-on-paginate="logPagination"></md-table-pagination>
      </md-card>
    </md-content>
	
