angular.module('authModule').component('registerLister', {
	templateUrl : 'ng/app/authModule/registerLister/reglister.component.html',
	controller : function(authService){
		var vm = this;
		
		vm.initialCountry = 'United States of America';

		vm.registerLister = function(lister) {
			return authService.registerLister(lister);
		}

	},
	controllerAs : 'vm'
		
})