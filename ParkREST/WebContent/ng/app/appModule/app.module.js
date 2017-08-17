angular.module('appModule',	[ 'ngRoute', 'navbar', 'static', 'ngAnimate', 'duScroll', 'authModule', 'userModule']).value('duScrollDuration', 1900).config(
		function($routeProvider) {
			$routeProvider.when('/', {
				template : '<home></home>'
			}).when('/user/userMain/', {
				template : '<userMain></userMain>'
			}).otherwise({
	            template: '<error></error>'
	        });
		})