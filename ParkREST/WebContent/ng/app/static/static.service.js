angular.module('static').factory(
		'staticService',
		function($http, $cookies, $location, authService) {
			var service = {};

			var BASE_URL = 'rest/';
			
			var checkLogin = function() {
				if (!authService.getToken().id) {
					$location.path('/');
				}
			}
			
			service.updateProfile = function(user) {
				checkLogin();
				return $http({
					method : 'PUT',
					url : BASE_URL + '/newUser/' + authService.getToken().id,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : user
				}).then(function(res) {
					return res;
				})
			}
			
			service.listParkingSpots = function() {
				return $http({
					method : 'GET',
					url : BASE_URL + 'parkingspot/lat/38.832697/lng/-104.824003/',
				}).then(function(res) {
					return res;
				})
			}
			return service;
		})