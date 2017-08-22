angular.module('appModule',	[ 'ngRoute', 'nav', 'static', 'ngAnimate', 'duScroll', 'authModule', 'userModule', 'listerModule', 'profile']).value('duScrollDuration', 1900).config(
		function($routeProvider) {
			$routeProvider.when('/', {
				template : '<home></home>'
			}).when('/userModule/userMain', {
				template : '<user-main></user-main>'
			}).when('/listerModule/listerMain', {
				template : '<lister-main></lister-main>'
			}).when('/user/profileUpdate', {
				template : '<profile-create></profile-create>'
			}).otherwise({
	            template: '<error></error>'
	        });
		})