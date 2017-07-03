angular.module('authModule').component('logout', {
	templateUrl : 'ng/app/authModule/logout/logout.component.html',
	controller : function(authService, $location){
		var vm = this;
		
		vm.logout = function() {
			return authService.logout().then(function(){
				$location.path('/');
			}).catch(function(){
				vm.error = "Logout failed";
			})
		}
	},
	controllerAs : 'vm'
})