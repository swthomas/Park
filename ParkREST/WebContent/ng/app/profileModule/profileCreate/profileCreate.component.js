angular.module('profile').component('profileCreate', {
	templateUrl : 'ng/app/profileModule/profileCreate/profileCreate.component.html',
	controller : function($rootScope, $scope, $location, staticService) {
		var vm = this;
		vm.regU = {};
		vm.regU.isLister = false;
		

		
//		Update profile of new user and route to userMain
		vm.updateProfile = function(newUser) {
			console.log(newUser);
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