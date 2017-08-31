angular.module('userModule')
  .factory('mapService', function($http, $cookies, $location) {
    var service = {};

	var BASE_URL = 'rest/';
	
	service.listParkingSpots = function(lat, lng) {
		checkLogin();
		return $http({
			method : 'GET',
			url : BASE_URL + 'parkingspot/initialLoad/lat/' + lat + '/lng/' + lng + '/',
		}).then(function(res) {
			return res;
		})
	}

    return service;
  })