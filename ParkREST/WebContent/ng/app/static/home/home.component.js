angular.module('static').component('home', {
	templateUrl : 'ng/app/static/home/home.component.html',
	controller : function(staticService, $scope, $location) {
		var vm = this;
		vm.googleMapsUrl="https://maps.googleapis.com/maps/api/js?key=AIzaSyB-eCSz4m2r6WczpOcJANrtbF8xps8EDuU&libraries=places";
		
		vm.parkingSpots = [];
		vm.markers = [];
		
		vm.selectedParkingSpot = null;
		vm.showMap = true;
		

		
		
//		Get array of parkingSpots and address info
		staticService.listParkingSpots().then(function(res){
			
			console.log(res.data);
			
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
			modifiedStrArray = modifiedStrArray.join("")
			vm.selectedParkingSpot = JSON.parse(modifiedStrArray);
		}

		
		vm.goToStore = function(s) {
			$location.path('store/store/'+ s.id);
		}
      
	 },
	 controllerAs: 'vm'
})