angular.module('navbar').component('navbar', {
	templateUrl : 'ng/app/nav/navbar/navbar.component.html',
	controller : function($location) {
		var vm = this;
		
		vm.isLister = false;
		vm.isLoggedIn = false;
		
		vm.signIn = function() {
			vm.showSignInForm = true;
		}


	},
	controllerAs : 'vm'
})