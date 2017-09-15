angular.module('userModule').component('profileCreate', {
	templateUrl : 'ng/app/userModule/profileCreate/profileCreate.component.html',
	controller : function($rootScope, $scope, $location, staticService) {
		var vm = this;
		vm.regU = {};
		vm.regU.isLister = false;
		

		
//		Update profile of new user and route to userMain
		vm.updateProfile = function(newUser) {
			staticService.updateProfile(newUser).then(function(){
				if (newUser.isLister) {
					$location.path('/listerModule/listerMain')
				} else {
					$location.path('/userModule/userMain');					
				}
			}).catch(function(){
				vm.error = "Something went wrong";
			})
		}
	
	},
	controllerAs : 'vm'
})