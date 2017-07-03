angular.module(
		'appModule',
		[ 'ngRoute', 'navbar', 'static']).config(
		function($routeProvider) {
			$routeProvider.when('/', {
				template : '<home></home>'
			}).otherwise({
				template : '<error></error>'
			})
		});