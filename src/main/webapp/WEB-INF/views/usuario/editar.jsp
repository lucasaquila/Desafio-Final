<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>		
		<div layout="row">
			  <div flex="20">
			  </div>
			  <div flex=60>
		  <h2>Editar de Usuário</h2>
		  <p>Tipo de Usuário</p>
		  <input type="hidden" ng-model="usuario.id">
		  <input type="hidden" ng-model="usuario.situacao">
    	   <md-radio-group ng-model="usuario.tipoUsuario">
		      <md-radio-button value="ROLE_USUARIO" aria-checked="true" class="md-checked">Usuário</md-radio-button>
		      <md-radio-button value="ROLE_ADMINISTRADOR">Administrador</md-radio-button>
           </md-radio-group>
		   </md-input-container>		  
          <md-input-container class="md-block" flex-gt-sm>
	      	<label>Nome</label>
	      		<input ng-model="usuario.nome">
	      </md-input-container>
	      <div layout="row">
          <md-input-container class="md-block" flex-gt-sm flex="50">
          	<label>E-mail</label>
            	<input ng-model="usuario.email">
          </md-input-container>
          <md-input-container class="md-block" flex-gt-sm>
          	<label>Senha</label>
            	<input ng-model="usuario.passwordEdit" type="password" value="">
          </md-input-container>
          </div>
          <div layout="row">
          <%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
          <md-button class="md-raised" href="#/usuario">Voltar</md-button>
          <div flex></div>
          <md-button class="md-raised md-primary" ng-click="editarUsuario(usuario)">Editar</md-button>
		  </div>
			  <div flex>
			  </div>
		</div>
        </div>