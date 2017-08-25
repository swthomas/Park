angular.module('nav').component('navbar', {
	templateUrl : 'ng/app/nav/navbar/navbar.component.html',
	controller : function($rootScope, $scope, $location, authService) {
		var vm = this;
		vm.isLoggedIn = false;
		vm.loginFormVisible = false;
		
//		toggles Login window
		vm.toggleLoginForm = function() {
			vm.loginFormVisible = !vm.loginFormVisible;
		}
		
//		toggles show password or hide
		$scope.toggleShowPassword = function() {
	        $scope.showPassword = !$scope.showPassword;
	    }	
		
//		Sign in
		vm.login = function(user) {
			authService.login(user).then(function(){
				vm.isLoggedIn = true;
				vm.loginFormVisible = false;
				$location.path('/userMain/');
			}).catch(function(){
				vm.error = "Something went wrong";
			})
		}
		
//		Sign out
		vm.signOut = function() {
			authService.logout().then(function(){
				$rootScope.isLoggedIn = false;
				$location.path('/');
			}).catch(function(){
				vm.error = "Something went wrong";
			})
		}
		
//		isLoggedIn navbar switcher
		$scope.$watch(function() {
			return $rootScope.isLoggedIn;
        }, function() {
            $scope.isLoggedIn = $rootScope.isLoggedIn;
        }, false);
				
//		scroll change effect
		var mainbottom = $('#main').offset().top + $('#main').height();
		// on scroll, 
		$(window).on('scroll',function(){
		    // we round here to reduce a little workload
		    var stop = Math.round($(window).scrollTop());
		    if (stop > mainbottom) {
		        $('.navbar').addClass('past-main');
		    } else {
		        $('.navbar').removeClass('past-main');
		    }
		})
		
	
	},
	controllerAs : 'vm'
})