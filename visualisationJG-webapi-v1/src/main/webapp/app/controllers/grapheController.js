
// contrôleur

angular.module("visualjgroups")
  .controller('grapheCtrl', ['$scope','$rootScope','$route','$timeout', '$location','$http','$q','items','config',
    function ($scope,$rootScope,$route,$timeout, $location,$http,$q,items,config) {

      // modèle de la page
      var graphe = $scope.graphe;
        /*$scope.messages = [];
        $scope.message = "";
        $scope.max = 140;*/
        var nodess = {};
        var logical ;
        var addr ;
        var listMember,menuItem;
        
        
        
        
        $scope.getData = function(){
            member = $http.get(config.urlBase+'/getGraph',{timeout: 1000});
            menu = $http.get(config.urlBase+'/getMenu');

            $q.all({a: member, b: menu})
                .then(function(results) {
                  
                    
                    listMember = results.a.data; 
                    menuItem = results.b.data;
                   
                   $scope.logical = listMember.data.listNode[0].logical_name;
                   $scope.addr = listMember.data.listNode[0].bind_addr;
                   
                }
                , function(errorMsg) {
                    // if any of the previous promises gets rejected
                    // the success callback will never be executed
                    // the error callback will be called...
                    console.log('An error occurred: ', errorMsg);
                });
                
        };
             
        $scope.returnMbean = function(){
        	 console.log("adresse ---------- :",$scope.logical);
         items.getJson(config.urlBase+'/getMbean/'+$scope.logical+'/'+$scope.addr+'/').then(function(result) {
               $scope.displayTree = result;
                 console.log("*************json******** ",result)
             }, function(result) {
               //  alert("Error: No data returned", result);
              });
       
        };
       
        $scope.selectedNode = "";
        $scope.getData();
        $scope.returnMbean();   
    


        //
       $scope.intervalFunction = function() {
            var currentCtrl = $route.current.$$route.controller;
            var currentlyRunning = $rootScope.myAppMainCtrlRefreshRunning;
            //do not run if the MainCtrl is not in scope
            //do not run if we've already got another timeout underway (in case someone jumps back and forth between
            //controllers without waiting 1 second between)
           // console.log("************* ",currentCtrl, currentlyRunning);
            if (currentCtrl === "grapheCtrl" ) {
               $timeout(function() {
                	var update = 0;
                    $rootScope.myAppMainCtrlRefreshRunning = true;
                    $scope.getData(); 
                   if(JSON.stringify($scope.member) !== JSON.stringify(listMember)){
                	   $scope.member = listMember;
                       $scope.menuList = menuItem ;
                   }
                   
                   $scope.$apply();
                    $scope.intervalFunction();
                    $rootScope.myAppMainCtrlRefreshRunning = false;
                }, 8000);
           };
        };
        // Kick off the interval
        $scope.$apply();
        $scope.intervalFunction();

      

      /*graphe.text = "[Modèle local dans page 1]";
       

         //  messageNot.send("hello");

        messageNot.receive().then(null, null, function(message) {
            $scope.messages.push(message);
            console.log(message);
        });
        var socket = new SockJS('/jgroups');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/jgrousp', function(greeting){

                Console(greeting.body);
            });
        });*/
    }]);
