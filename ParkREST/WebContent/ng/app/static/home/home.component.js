angular.module('static').component('home', {
	templateUrl : 'ng/app/static/home/home.component.html',
	controller : function(authService, staticService, $rootScope, $scope, $location, NgMap) {
		var vm = this;
        $scope.userMenuOpened = $rootScope.userMenuOpened;
        $scope.listerMenuOpened = $rootScope.listerMenuOpened;
		  
		NgMap.getMap().then(function(map) {
			    console.log('map', map);
			    vm.map = map;
			  });
		  
		vm.googleMapsUrl="https://maps.googleapis.com/maps/api/js?key=AIzaSyB-eCSz4m2r6WczpOcJANrtbF8xps8EDuU&libraries=places";
		
		vm.parkingSpots = [];
		vm.markers = [];
		
		vm.selectedParkingSpot = null;
		vm.showMap = true;
		
//		Watches for sign in form state change from navbar and returns it in home	
		$scope.$watch(function() {
			return $rootScope.userMenuOpened;
        }, function() {
            $scope.userMenuOpened = $rootScope.userMenuOpened;
        }, true);
		
		$scope.$watch(function() {
			return $rootScope.listerMenuOpened;
        }, function() {
            $scope.listerMenuOpened = $rootScope.listerMenuOpened;
        }, true);
		
		
//		Login User
		vm.loginU = function(user) {
			authService.login(user).then(function(){
				$location.path('/user/userMain/');
			}).catch(function(){
				vm.error = "Something went wrong";
			})
		}
		
//		Create generic user
		vm.register = function(newUser) {
			authService.register(newUser).then(function() {
				$location.path('/user/profileUpdate');
			}).catch(function(){
				vm.error = "Something went wrong";
			})
		}
		

			
//		Get array of parkingSpots and address info
		staticService.listParkingSpots().then(function(res){
			
			vm.parkingSpots = res.data;
			for (var i = 0; i < vm.parkingSpots.length; i++) {
				vm.markers[i] = '[' + vm.parkingSpots[i].parkingSpotAddress.latitude + ',' + 
					vm.parkingSpots[i].parkingSpotAddress.longitude + ','+ JSON.stringify(vm.parkingSpots[i]) + ']';
			}
			return vm.markers;
		})
				
		// first argument is metadata from the map 
		// second argument it marker data provided by 'staticService.listParkingSpots()' above
		vm.showParkingSpot = function(mk, data) {
			var modifiedStrArray = data.split(",").slice(2).join(",").split("");
			modifiedStrArray.pop();
			modifiedStrArray = modifiedStrArray.join("");
			
			vm.selectedParkingSpot = JSON.parse(modifiedStrArray);
		    vm.map.showInfoWindow('foo-iw', this);
		}


      
	 },
	 controllerAs: 'vm'
})