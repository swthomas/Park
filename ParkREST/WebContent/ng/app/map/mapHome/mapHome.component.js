angular.module('map').component('mapHome', {
	templateUrl : 'ng/app/map/mapHome/mapHome.component.html',
	controller : function(mapService, $location, NgMap) {

		var vm = this;
  
		NgMap.getMap().then(function(map) {
			    console.log('map', map);
			    vm.map = map;
			  });
		  
		vm.googleMapsUrl="https://maps.googleapis.com/maps/api/js?key=AIzaSyB-eCSz4m2r6WczpOcJANrtbF8xps8EDuU&libraries=places";
		
		vm.parkingSpots = [];
		vm.markers = [];
		
		vm.selectedParkingSpot = null;
		vm.showMap = true;
		
		vm.mapCenter = "Colorado Springs, CO"

		
//		Get array of parkingSpots and address info
		mapService.listParkingSpots().then(function(res){
			
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