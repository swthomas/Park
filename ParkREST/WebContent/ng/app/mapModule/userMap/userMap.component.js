angular.module('userModule').component('userMap', {
	templateUrl : 'ng/app/mapModule/userMap/userMap.component.html',
	controller : function($scope, mapService) {
		var vm = this;
		
		vm.parkingSpots = [];
		vm.markers = [];
		vm.mapCenter = "current-position";
		
		vm.selectedParkingSpot = null;
		vm.showMap = true;
		
		vm.lat = null;
		vm.lng = null;
		
		vm.locationError = false;
		
//		Get current position
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(onPositionUpdate);
		}
		else {
			vm.locationError = true;
		}
		 
		function onPositionUpdate(position) {
		    vm.lat = position.coords.latitude;
		    vm.lng = position.coords.longitude;
		    
		    vm.mapCenter = '"vm.lat + ", " + vm.lng';
		    		    
			vm.initialLoadParkingSpots(vm.lat, vm.lng);
		}
		
//		Get array of parkingSpots and address info
		vm.initialLoadParkingSpots = function(lat, lng) {
			userService.listParkingSpots(lat, lng).then(function(res){
				vm.parkingSpots = res.data;
				for (var i = 0; i < vm.parkingSpots.length; i++) {
					vm.markers[i] = '[' + vm.parkingSpots[i].parkingSpotAddress.latitude + ',' + 
						vm.parkingSpots[i].parkingSpotAddress.longitude + ','+ JSON.stringify(vm.parkingSpots[i]) + ']';
				}
				return vm.markers;
			})
		}
		
//		First argument is metadata from the map 
//		Second argument it marker data provided by 'staticService.listParkingSpots()' above
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