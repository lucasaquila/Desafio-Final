<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>Desafio EITS - Autenticação</title>
	<link rel="stylesheet" href="webjars/angular-material/1.1.0-rc1/angular-material.css">
	<link rel="stylesheet" href="webjars/angular-material-icons/0.6.0/angular-material-icons.css">
	<link rel="stylesheet" href="assets/css/style.css" type="text/css" />
</head>
<body class="background-login">
	<div ng-app="loginApp" ng-controller="loginController" layout="row" layout-fill ng-cloak>
		<section layout="column" flex layout-align="center center" class="login">
			<md-card class="md-whiteframe-4dp"> 
				<md-toolbar>
					<div layout="column" layout-padding layout-align="center center">
						<div>
							<img src="<c:url value="/assets/img/logo.png" />" alt="logo"/>
						</div>
					</div>
				</md-toolbar>
				<md-card-content>
					<div layout="row" layout-align="center center">
						<div class="md-headline md-padding" >Autenticação</div>
					</div>
					<form name='loginForm' action="<c:url value='j_spring_security_check' />" method='POST'>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<md-input-container class="md-block"> 
							<label for="username">Email</label> 
							<input type="email" name="username"	ng-model="credentials.email" required />
						</md-input-container>
						<md-input-container class="md-block"> 
							<label for="password">Senha</label> 
							<input type="password" name="password" ng-model="credentials.password" required /> 
						</md-input-container>
						<c:if test="${not empty error}">
							<div layout="row" layout-align="center center">
								<div class="error">${error}</div>
							</div>
						</c:if>
						<c:if test="${not empty msg}">
							<div layout="row" layout-align="center center">
								<div class="msg">${msg}</div>
							</div>
						</c:if>
						<md-input-container class="md-block">
							<div layout="row" layout-align="center center">
								<md-button type="submit" class="md-raised md-accent" flex="50">Acessar</md-button>
							</div>
						</md-input-container>
					</form> 
				</md-card-content> 
			</md-card>
		</section>
	</div>
	<script src="webjars/angularjs/1.5.0/angular.min.js"></script>
	<script src="webjars/angular-route/1.5.0/angular-route.min.js"></script>
	<script src="webjars/angular-animate/1.5.0/angular-animate.min.js"></script>
	<script src="webjars/angular-aria/1.5.0/angular-aria.min.js"></script>
	<script src="webjars/angular-messages/1.5.0/angular-messages.min.js"></script>
	<script src="webjars/angular-material/1.1.0-rc1/angular-material.min.js"></script> 
	<script src="assets/js/login.js"></script>
</body>
</html>
