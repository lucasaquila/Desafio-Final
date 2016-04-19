var app = angular.module('desafioApp', ['ngMaterial','md.data.table', "ngRoute", "ngMdIcons", "ngMessages" ]);
app.config(['$httpProvider', function($httpProvider) {
$httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';
$httpProvider.interceptors.push(function() {
    return {
        response: function(response) {
            $httpProvider.defaults.headers.common['X-CSRF-TOKEN'] = response.headers('X-CSRF-TOKEN');
            return response;
        }
    }    
});
}])

app.filter('capitalize', function() {
    return function(input) {
      return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1).toLowerCase() : '';
    }
});