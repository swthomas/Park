angular.module('userModule').component('userProfile', {
	templateUrl : 'ng/app/userModule/userProfile/userProfile.component.html',
	controller : function($scope, userService) {
		var vm = this;
		vm.user = [];
		
		vm.reload = function() {
			userService.show().then(function(res){
				vm.user = res.data;
				console.log(vm.user);
			})
		}
		
//		toggles show password or hide
		$scope.toggleShowPassword = function() {
	        $scope.showPassword = !$scope.showPassword;
	    }
		
//		Update profile loggedin user
		vm.updateUser = function(user) {
			userService.updateProfile(user).then(function(res){
				vm.data = res.data;
				vm.reload();
			})
		}
		
		vm.reload();
	},
	controllerAs : 'vm'
})