<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>		
		<div layout="row">
			<md-content flex>
				<label>Data</label>
				<md-datepicker ng-model="lancamento.data" md-placeholder="Data" disabled></md-datepicker>
			</md-content>
		</div>
		<div layout="row">
		<md-input-container class="md-block" flex>
	    	<label>Valor</label> 
			<!-- <input type="number" ng-model="lancamento.valor" min="0" > -->
			<input type="number" name="valor" min="1" max="99999999.99" ng-model="lancamento.valor" ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" step="0.01" required />
			<div ng-messages="formLancamento.valor.$error">
				<div ng-message="max">Insira um valor com menos de 8 casas decimais antes da vírgula e 2 casas decimais após a vírgula.</div>
				<div ng-message="min">Insira um valor maior que 0 (zero).</div>
				<div ng-message="pattern">Insira um valor no formato monetário em real.</div>
				<div ng-message="required">Este campo é obrigatório.</div>
			</div>
		</md-input-container>
		</div>
		<div layout="row">
        <md-input-container class="md-block" flex>
          <label>Observações</label>
          <textarea ng-model="lancamento.observacao" md-maxlength="255" maxlength="255" rows="15" md-select-on-focus></textarea>
        </md-input-container>
		</div>
