angular.module('map').factory(
		'mapService',
		function($http, $cookies, $location) {
			var service = {};

			var BASE_URL = 'rest/';
			
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