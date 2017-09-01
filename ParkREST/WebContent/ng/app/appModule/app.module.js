angular.module('appModule',	[ 'ngRoute', 'nav', 'map', 'static', 'ngAnimate', 'duScroll', 'authModule', 'userModule', 'listerModule', 'profile']).value('duScrollDuration', 1900).config(
		function($routeProvider) {
			$routeProvider.when('/', {
				template : '<home></home>'
			}).when('/userMain/:id', {
				template : '<user-main></user-main>'
			}).when('/userProfile/', {
				template : '<user-profile></user-profile>'
			}).when('/listerMain/:id', {
				template : '<lister-main></lister-main>'
			}).when('/user/profileUpdate', {
				template : '<profile-create></profile-create>'
			}).otherwise({
	            template: '<error></error>'
	        });
		})