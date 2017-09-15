angular.module('static').component('error', {
	templateUrl : 'ng/app/staticModule/error/error.component.html',
	controller : function(authService, staticService, $rootScope, $scope, $location) {
		var vm = this;
	},
	controllerAs : 'vm',
})