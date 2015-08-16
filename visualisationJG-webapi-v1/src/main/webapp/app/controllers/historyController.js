
// contrôleur
angular.module("visualjgroups")
  .controller('historyCtrl', ['$scope','$location','$http',
    function ($scope, $location,$http) {
	  'use strict';
	  $scope.dtFrom = new Date();
	  $scope.dtFromOpened= false;
	  $scope.dtToOpened = false;
	  var today = new Date();
	  $scope.maxDate = new Date(today.getFullYear(),today.getMonth() , today.getDate());
	   
       $scope.showWeeks = true;
       $scope.toggleWeeks = function() {
           return $scope.showWeeks = !$scope.showWeeks;
       };
       $scope.timeFrom = new Date();
       $scope.timeTo = new Date();
       $scope.hstep = 1;
       $scope.mstep = 15;
      
       $scope.options = {
         hstep: [1, 2, 3],
         mstep: [1, 5, 10, 15, 25, 30]
       };
       $scope.clear = function() {
           return $scope.dt = null;
       };
       $scope.disabled = function(date, mode) {
           return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
       };
       $scope.toggleMin = function() {
           var _ref;
           return $scope.minDate = (_ref = $scope.minDate) != null ? _ref : {
               "null": new Date()
           };
       };
       $scope.toggleMin();
       $scope.opendtFromOpened = function ($event) {
    	    $event.preventDefault();
    	    $event.stopPropagation();
    	    $scope.dtFromOpened = !$scope.dtFromOpened;
    	    };

    	    $scope.opendtToOpened = function ($event) {
    	    $event.preventDefault();
    	    $event.stopPropagation();
    	    $scope.dtToOpened = !$scope.dtToOpened;
    	    };
     
      
   	  $scope.graphShow = function(){
   		/*$http.get('http://localhost:8080//visualisationjg-webapi/invokeMethod/'+$scope.protocolMb.label+'/'+ $scope.methods.nameOp+'/'+$scope.adress+'/').success(function (data) {
            //   console.log("voir adrrese ",'http://localhost:8080//visualisationjg-webapi/invokeMethod/'+$scope.protocolMb.label+'/'+ $scope.methods.nameOp+'/'+$scope.adress+'/');
     			// $scope.showTable = true;
     			var result  = data;
                 $scope.result = result.data;
                
                 console.log("------------------------------- ", $scope.result[0]);
             }).error(function (data) {
                 //
                
             });*/
    console.log(" date from  to ",$scope.dtFrom,$scope.dtTo,$scope.timeFrom,$scope.timeTo);
   	  };
   	
    }]);
