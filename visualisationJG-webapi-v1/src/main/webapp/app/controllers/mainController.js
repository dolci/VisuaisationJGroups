
// contrôleur
angular.module("visualjgroups")
    .controller('mainCtrl', ['$scope', '$location','ngDialog','$modal','$http','config',
        function ($scope, $location,ngDialog,$modal,$http,config) {
     
    	 var protocs = {};
         $scope.items = [];
         $scope.protocols = [];
            // modèles des pages
            $scope.graphe = {};
            $scope.operation = {};
            $scope.attribute = {};
            $scope.protocol = {};
            $scope.mbean = {};
            $scope.history = {};
            $scope.contact = {};
            // modèle global
            var main = $scope.main = {};
            main.text = "[Modèle global]";

            // méthodes exposées à la vue
            main.showGraphe = function () {
                $location.path("/graphe");
            };
            main.showOperation = function () {
                $location.path("/operation");
            };
            main.showAttribute = function () {
                $location.path("/attribute");
            };
            main.showHistory = function () {
                $location.path("/history");
            };
            main.showContact = function () {
                $location.path("/contact");
            };

            
            $http.get(config.urlBase+'/getAdr') 
            .then(function(json) {
              $scope.items = json.data.data.listAllAdr; 
              
          return $http.get(config.urlBase+'/getMbeanPro/'+$scope.items[0]+'/');
              
            })
          .then(function(json) {
        	  protocs = json.data.data;         
              for(var i = 0; i < protocs.length; i++) {
      	    	$scope.protocols[i] = protocs[i].label;
      	    	
      	    }
           
            });     
            
            main.addProtocol  =  function() {
                var modalInstance = $modal.open({
                    templateUrl: 'views/addProtocol.html',
                    controller: ModalInstanceAddCtrl,
                    windowClass: 'app-modal-window',
                    resolve: {
                        items: function () {return $scope.items;
                        },
                        protocols: function() {return $scope.protocols; }
                    }
                });

                modalInstance.result.then(function (result) {
                var  postAddProtocol = {addr:result.addr.item,position:result.position.position,
                		 protocolName:result.protocolName.proName,protocolTransport:result.protocolTransport.protocol};
                    console.log("result **************************** ",postAddProtocol);
                    $http.post(config.urlBase+'/addProt',postAddProtocol).success(function (data) {
                    	console.log("rep  "+data.data);
                    	if(data.data == "ok")
                    		alert("The protocol " + result.protocolName.proName+" added");
                    }).error(function (data) {
                        //   
                    });
                   
                }, function () {
                    $log.info('Modal dismissed at: ' + new Date());
                });
            };
            main.deleteProtocol  =  function() {
                var modalInstance = $modal.open({
                    templateUrl: 'views/deleteProtocol.html',
                    controller: ModalInstanceDeleteCtrl,
                    windowClass: 'app-modal-window',
                    resolve: {
                        items: function () {
                            return $scope.items;
                        }
                      

                    }
                });

                modalInstance.result.then(function (result) {
                  //  $scope.selected = selectedItem;

                  //  console.log("iteme selected **************************** ",$scope.selectedItem);
                	var postDeletePro = {nameProtocol:result.protocol.proName,addr:result.addr.item};

                    console.log("result **************************** ",postDeletePro);
                    $http.post(config.urlBase+'/removeProt',postDeletePro).success(function (data) {
                    	console.log("rep0  "+data.data);
                    	if(data.data == "ok")
                    		alert("Protocol :" + result.protocol.proName+" removed");
                    }).error(function (data) {
                        //   
                    });
                }, function () {
                    $log.info('Modal dismissed at: ' + new Date());
                });
            };
           
        }]);
var ModalInstanceAddCtrl = function ($scope, $modalInstance, $timeout, items,$log ,protocols) {

	   // alert("The key is " + key)
	  $scope.addProt = { proName : "BARRIER"};
	  $scope.transport = { position : "above"};
	  $scope.protocol = { listP :[]};
	  $scope.protocol.listP = protocols;
	  $scope.data = {items:[] };
	    $scope.data.items = items;
	    $scope.selected = {
	        item: $scope.data.items[0]
	    };
	    $scope.selectedP = {
		    	protocol : $scope.protocol.listP[0]
		    }
	    $timeout(function(){
         $('.selectpicker').selectpicker('refresh');
           });
	   
	
	    
	var result ={protocolName:$scope.addProt,position:$scope.transport,protocolTransport:$scope.selectedP,addr:$scope.selected};
	    $scope.ok = function () {
	        $modalInstance.close(result);
	    };


	    $scope.cancel = function () {
	        $modalInstance.dismiss('cancel');
	    };
	};
	var ModalInstanceDeleteCtrl = function ($scope, $modalInstance,$timeout,$log, items) {

		   // alert("The key is " + key)
		    
		    $scope.data = {
		    		items:[]
		    };
		    $scope.data.items = items;
		    $scope.selected = {
		        item: $scope.data.items[0]
		    };
		    
		    $timeout(function(){
                $('.selectpicker').selectpicker('refresh');
                  });
    	   
		    $scope.deleteProt={proName:"BARRIER"}
		    console.log("items",$scope.data.items);
		    
		   
    	   
		    
		var result ={addr:$scope.selected , protocol:$scope.deleteProt};
		    $scope.ok = function () {
		    	console.log("result",result);
		        $modalInstance.close(result);
		    };


		    $scope.cancel = function () {
		        $modalInstance.dismiss('cancel');
		    };
		};