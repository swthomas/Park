angular.module('static').component('home', {
	templateUrl : 'ng/app/staticModule/home/home.component.html',
	controller : function(authService, staticService, $rootScope, $scope) {
		// $location, NgMap
		var vm = this;
        $scope.userMenuOpened = $rootScope.userMenuOpened;
		$rootScope.isLoggedIn = false;
  
//		NgMap.getMap().then(function(map) {
//			    console.log('map', map);
//			    vm.map = map;
//			  });
//		  
//		vm.googleMapsUrl="https://maps.googleapis.com/maps/api/js?key=AIzaSyB-eCSz4m2r6WczpOcJANrtbF8xps8EDuU&libraries=places";
//		
//		vm.parkingSpots = [];
//		vm.markers = [];
//		
//		vm.selectedParkingSpot = null;
//		vm.showMap = true;
//		
		
//		Create generic user
		vm.register = function(newUser) {
			authService.register(newUser).then(function() {
				$location.path('/user/profileUpdate');
			}).catch(function(){
				vm.error = "Something went wrong";
			})
		}
		
//		toggles show password or hide
		$scope.toggleShowPassword = function() {
	        $scope.showPassword = !$scope.showPassword;
	    }
			
//		Get array of parkingSpots and address info
//		staticService.listParkingSpots().then(function(res){
//			
//			vm.parkingSpots = res.data;
//			for (var i = 0; i < vm.parkingSpots.length; i++) {
//				vm.markers[i] = '[' + vm.parkingSpots[i].parkingSpotAddress.latitude + ',' + 
//					vm.parkingSpots[i].parkingSpotAddress.longitude + ','+ JSON.stringify(vm.parkingSpots[i]) + ']';
//			}
//			return vm.markers;
//		})
				
//		// first argument is metadata from the map 
//		// second argument it marker data provided by 'staticService.listParkingSpots()' above
//		vm.showParkingSpot = function(mk, data) {
//			var modifiedStrArray = data.split(",").slice(2).join(",").split("");
//			modifiedStrArray.pop();
//			modifiedStrArray = modifiedStrArray.join("");
//			
//			vm.selectedParkingSpot = JSON.parse(modifiedStrArray);
//		    vm.map.showInfoWindow('foo-iw', this);
//		}


      
	 },
	 controllerAs: 'vm'
})