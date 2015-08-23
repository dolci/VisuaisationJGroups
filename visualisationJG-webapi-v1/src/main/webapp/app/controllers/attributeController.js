
angular.module("visualjgroups")
    .controller('attributeCtrl', ['$scope', '$location','$http','$timeout',
        function ($scope, $location,$http,$timeout) {

            // modèle de la page
            var operation = $scope.operation;
            $scope.showTable = true;
          
          
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
              $scope.listAtt = $scope.protocolMb.listAtt;
              $scope.attribute = $scope.protocolMb.listAtt[0];

              $timeout(function(){
                  $('.selectpicker').selectpicker();
                    });
      	   
              
            });	
             $scope.onChange = function(protocolMb){       
            	    $scope.listAtt = protocolMb.listAtt;
            	    $scope.attribute = protocolMb.listAtt[0];
            	   // console.log("opet **************** ", $scope.listOper); 
            	    $timeout(function(){
                        $('.selectpicker').selectpicker('refresh');
                          });
            	   
            	  /*  $timeout(function(){
                        $('.selectpicker').selectpicker();
                          });*/
            	    };
            	$scope.invoke = function(){
            		
            		$http.get('http://localhost:8080//visualisationjg-webapi/invokeMethod/'+$scope.adress+'/'+$scope.protocolMb.label+'/'+$scope.attribute.name).success(function (data) {
                   //   console.log("voir adrrese ",'http://localhost:8080//visualisationjg-webapi/invokeMethod/'+$scope.protocolMb.label+'/'+ $scope.methods.nameOp+'/'+$scope.adress+'/');
            			// $scope.showTable = true;
            			var result  = data;
                        $scope.result = result.data;
                       
                        console.log("------------------------------- ", $scope.result[0]);
                    }).error(function (data) {
                        //
                       
                    });
         
            	};
            	     
        }]);
