angular.module('listerModule').component('createParkingSpot', {
	templateUrl : 'ng/app/listerModule/createParkingSpot/createParkingSpot.component.html',
	controller : function(listerService, geoService) {
		var vm = this;
		var newGeo={longitude:"nope",latitude:"nope"};
        var geo = {longitude:"",latitude:""};
		
		 vm.createParkingSpot = function() {
             geo = (vm.parkingSpotData.address.street+","+
                      vm.parkingSpotData.address.city +","+
                      vm.parkingSpotData.address.state);
             
             geoService.address(geo).then(function(data){
                 newGeo = data;
                 vm.parkingSpotData.address.latitude = newGeo.lat;
                 vm.parkingSpotData.address.longitude = newGeo.long;
                 vm.parkingSpotData.active = true;
                 
                 listerService.createParkingSpot(vm.parkingSpotData).then(
                         function(res) {                            
                 });
                 
             }); 

            }
	},
	controllerAs : 'vm'
})