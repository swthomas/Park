angular.module('static').component('error', {
	templateUrl : 'ng/app/static/error/error.component.html',
	controller : function(authService, staticService, $rootScope, $scope, $location) {
		var vm = this;
	},
	controllerAs : 'vm',
})