<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div layout="row">
	<div flex="20"></div>
	<div flex=60>
		<h2>Cadastro de Usuários</h2>
		<p>Tipo de Usuário</p>
		<form ng-submit="adicionarUsuario(usuario)" name="formulario">
			<md-input-container class="md-block" flex-gt-sm>
			<md-radio-group ng-model="usuario.tipoUsuario"> <md-radio-button
				value="ROLE_USUARIO" aria-checked="true" class="md-checked">Usuário</md-radio-button>
			<md-radio-button value="ROLE_ADMINISTRADOR">Administrador</md-radio-button>
			</md-radio-group> </md-input-container>
			
			<md-input-container class="md-block" flex-gt-sm>
			<label>Nome</label> 
			<input name="nome" ng-model="usuario.nome" required md-maxlength="200" maxlength="200">
 			<div ng-messages="formulario.nome.$error" >
					<div ng-message="required">Este campo é obrigatório.</div>
				</div>
			</md-input-container>
			<div layout="row">
				<md-input-container class="md-block" flex-gt-sm flex="50">
				<label>E-mail</label>
					<input type="email" name="email" ng-model="usuario.email" required md-maxlength="200" maxlength="200">
					<div ng-messages="formulario.email.$error">
						<div ng-message="required">Este campo é obrigatório.</div>
						<div ng-message="email">Este campo deve ser preenchido no formato de e-mail.</div>
					</div>
				</md-input-container>
				<md-input-container class="md-block" flex-gt-sm ng-if="!usuario.id">
				<label>Senha</label> 
				<input ng-model="usuario.password" type="password" required md-maxlength="255" maxlength="255" name="password"> 
				<div ng-messages="formulario.password.$error">
					<div ng-message="required">Este campo é obrigatório.</div>
				</div>
				</md-input-container>
				<md-input-container class="md-block" flex-gt-sm ng-if="usuario.id">
				<label>Senha</label> <input ng-model="usuario.passwordEdit"	type="password" value="" md-maxlength="255"> </md-input-container>
			</div>
			<div layout="row">
				<md-button class="md-raised" href="#/usuario">Voltar</md-button>
				<div flex></div>
				<!-- <md-button class="md-raised md-primary" ng-click="adicionarUsuario(usuario)" ng-disabled="formulario.$invalid">Salvar</md-button> -->
				<md-button class="md-raised md-primary" type="submit"
					ng-disabled="formulario.$invalid">Salvar</md-button>
 		</form>
	</div>
	<div flex></div>
</div>
</div>