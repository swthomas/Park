angular.module('navbar').component('navbar', {
	templateUrl : 'ng/app/nav/navbar/navbar.component.html',
	controller : function($rootScope, $scope, $location) {
		var vm = this;
		$rootScope.userMenuOpened = false;
		$rootScope.listerMenuOpened = false;
		vm.isLister = false;
		vm.isLoggedIn = false;
		
				
//		changes login form state
		vm.signInU = function(event) {
			$rootScope.userMenuOpened = !($rootScope.userMenuOpened);
			event.stopPropagation();
		};

		vm.signInL = function(event) {
			$rootScope.listerMenuOpened = !($rootScope.listerMenuOpened);
			event.stopPropagation();
		};
		
				
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