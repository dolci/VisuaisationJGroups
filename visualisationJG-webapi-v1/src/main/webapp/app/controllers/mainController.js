
// contrôleur
angular.module("visualjgroups")
    .controller('mainCtrl', ['$scope', '$location','ngDialog','$modal',
        function ($scope, $location,ngDialog,$modal) {

    	 var key = 1000;
         $scope.items = ['item1', 'item2', 'item3'];
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

            main.addProtocol  =  function() {
                var modalInstance = $modal.open({
                    templateUrl: 'views/addProtocol.html',
                    controller: ModalInstanceAddCtrl,
                    windowClass: 'app-modal-window',
                    resolve: {
                        items: function () {
                            return $scope.items;
                        },
                        key: function() {return key; }


                    }
                });

                modalInstance.result.then(function (result) {
                  //  $scope.selected = selectedItem;

                  //  console.log("iteme selected **************************** ",$scope.selectedItem);

                    console.log("result **************************** ",result);
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
                        },
                        key: function() {return key; }


                    }
                });

                modalInstance.result.then(function (result) {
                  //  $scope.selected = selectedItem;

                  //  console.log("iteme selected **************************** ",$scope.selectedItem);

                    console.log("result **************************** ",result);
                }, function () {
                    $log.info('Modal dismissed at: ' + new Date());
                });
            };
           
        }]);
var ModalInstanceAddCtrl = function ($scope, $modalInstance, items ,key) {

	   // alert("The key is " + key)
	    $scope.data={proName:""}
	    $scope.items = items;
	    $scope.selected = {
	        item: $scope.items[0]
	    };
	    $scope.transport={selectedProtocolTran:""};
	var result ={item:$scope.selected,protocol:$scope.data,transport:$scope.transport};
	    $scope.ok = function () {
	        $modalInstance.close(result);
	    };


	    $scope.cancel = function () {
	        $modalInstance.dismiss('cancel');
	    };
	};
	var ModalInstanceDeleteCtrl = function ($scope, $modalInstance, items ,key) {

		   // alert("The key is " + key)
		    $scope.data={proName:""}
		    $scope.items = items;
		    $scope.selected = {
		        item: $scope.items[0]
		    };
		    $scope.transport={selectedProtocolTran:""};
		var result ={item:$scope.selected,protocol:$scope.data,transport:$scope.transport};
		    $scope.ok = function () {
		        $modalInstance.close(result);
		    };


		    $scope.cancel = function () {
		        $modalInstance.dismiss('cancel');
		    };
		};