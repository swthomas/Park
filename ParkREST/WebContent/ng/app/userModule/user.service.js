angular.module('userModule')
  .factory('userService', function($http, $cookies, $location, authService) {
    var service = {};

	var BASE_URL = 'rest/user/';
	
	var checkLogin = function() {
		if (!authService.getToken().id) {
			$location.path('/');
		}
	}
	
	
	service.show = function() {
		checkLogin();
		return $http({
			method : 'GET',
			url : BASE_URL + authService.getToken().id
		}).then(function(res) {
			return res;
		})
	}
	
	service.updateProfile = function(user) {
		checkLogin();
		return $http({
			method : 'PUT',
			url : BASE_URL + authService.getToken().id,
			headers : {
				'Content-Type' : 'application/json'
			},
			data : user
		}).then(function(res) {
			return res;
		})
	}
	
	

    return service;
  })