angular.module('userModule').component('homeMap', {
	templateUrl : 'ng/app/mapModule/homeMap/homeMap.component.html',
	controller : function($scope, mapService) {
		var vm = this;
		
		vm.parkingSpots = [];
		vm.markers = [];
		vm.mapCenter = "Colorado Springs, CO";
		
		vm.selectedParkingSpot = null;
		vm.showMap = true;
		
		vm.lat = null;
		vm.lng = null;
		
//		Get current position
		if (navigator.geolocation) navigator.geolocation.getCurrentPosition(onPositionUpdate);
		 
		function onPositionUpdate(position) {
		    vm.lat = position.coords.latitude;
		    vm.lng = position.coords.longitude;
		    
		    vm.mapCenter = '"vm.lat + ", " + vm.lng';
		    		    
//			vm.initialLoadParkingSpots(vm.lat, vm.lng);
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
		
      
	 },
	 controllerAs: 'vm'
})