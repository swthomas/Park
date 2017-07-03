angular.module('authModule').component('registerUser', {
	templateUrl : 'ng/app/authModule/registerUser/reguser.component.html',
	controller : function(authService){
		var vm = this;
		
		vm.initialCountry = 'United States of America';

		vm.registerUser = function(user) {
			return authService.register(user);
		}

	},
	controllerAs : 'vm'
		
})