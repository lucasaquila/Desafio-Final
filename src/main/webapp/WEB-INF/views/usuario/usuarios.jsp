<%@page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html ng-app="todoApp">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="../webjars/angularjs/1.5.0/angular.min.js"></script>
		<script src="../assets/js/index.js"></script>
	</head>
	<body>
 		<div ng-controller="Usuarios" ng-init="listUsers()">
			Número de Usuários: {{usuarios.length}}
			<ul class="usuarios-container">
				<li ng-repeat="usuario in usuarios">
				 	{{usuario.nome}}
				</li>
			</ul>
			<button ng-click="listUsers()"> att list </button>
		</div>
	</body>
</html>