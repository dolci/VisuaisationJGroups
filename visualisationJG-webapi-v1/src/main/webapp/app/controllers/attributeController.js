
angular.module("visualjgroups")
    .controller('attributeCtrl', ['$scope', '$location','$http','$timeout',
        function ($scope, $location,$http,$timeout) {

            // modèle de la page
            var operation = $scope.operation;
            $scope.showTable = true;
           var attributes = {} ;
             $scope.listAtt = [];
             $http.get('http://localhost:8080//visualisationjg-webapi/getAdr') 
            .then(function(json) {
              $scope.adr = json.data.data.rep; 
              $scope.adress = $scope.adr[0];
            //  console.log("list adr **** ", $scope.adr);
              
          return $http.get('http://localhost:8080//visualisationjg-webapi/getMbeanPro/'+$scope.adr[0]+'/');
              
            })
          .then(function(json) {
      
              
              $scope.protocols = json.data.data;
              console.log("list protocl **** ", $scope.protocols[0]);
              $scope.protocolMb = $scope.protocols[0]; 
              attributes = $scope.protocolMb.listAtt;
              //$scope.attribute = $scope.protocolMb.listAtt[0];
              for(var i = 0; i < attributes.length; i++) {
      	    	$scope.listAtt[i] = attributes[i].name;
      	    }
              $scope.attribute = $scope.listAtt[0];
              $timeout(function(){
                  $('.selectpicker').selectpicker();
                    });
      	   
              
            });	
             $scope.onChange = function(protocolMb){       
            	    attributes = protocolMb.listAtt;
            	   //var  attributes = protocolMb.listAtt[0];
            	   // console.log("opet **************** ", $scope.listOper); 
            	    for(var i = 0; i < attributes.length; i++) {
            	    	$scope.listAtt[i] = attributes[i].name;
            	    }
            	    $scope.attribute = $scope.listAtt[0];
            	    //console.log( "list name 1",$scope.listAtt);   
            	    $timeout(function(){
                        $('.selectpicker').selectpicker('refresh');
                          });
            	   
            	  /*  $timeout(function(){
                        $('.selectpicker').selectpicker();
                          });*/
            	    };
            	$scope.invoke = function(){
            		
            		$http.get('http://localhost:8080//visualisationjg-webapi/getAtt/'+$scope.adress+'/'+$scope.protocolMb.label+'/'+$scope.attribute).success(function (data) {
                   //   console.log("voir adrrese ",'http://localhost:8080//visualisationjg-webapi/invokeMethod/'+$scope.protocolMb.label+'/'+ $scope.methods.nameOp+'/'+$scope.adress+'/');
            			// $scope.showTable = true;
            			var result  = data;
                        $scope.result = result.data;
                       
                        console.log("------------------------------- ", $scope.result);
                    }).error(function (data) {
                        //
                       
                    });
         
            	};
            	     
        }]);
