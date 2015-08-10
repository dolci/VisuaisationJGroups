
// contrôleur

angular.module("visualjgroups")
  .controller('grapheCtrl', ['$scope','$rootScope','$route','$timeout', '$location','$http','$q','items',
    function ($scope,$rootScope,$route,$timeout, $location,$http,$q,items) {

      // modèle de la page
      var graphe = $scope.graphe;

        /*$scope.messages = [];
        $scope.message = "";
        $scope.max = 140;*/
        var nodess = {};
        var logical = $scope.logical;
        var addr = $scope.addr;
        $scope.getData = function(){
            member = $http.get('http://localhost:8080//visualisationjg-webapi/getGraph');
            menu = $http.get('http://localhost:8080//visualisationjg-webapi/getMenu');

            $q.all({a: member, b: menu})
                .then(function(results) {
                    console.log(results.a.data, results.b.data);
                    $scope.menuList = results.b.data;
                    $scope.member = results.a.data;
                     nodess = $scope.member.data.listNode;
                   logical = nodess[0].logical_name;
                   addr = nodess[0].bind_addr;
                    
                }
                , function(errorMsg) {
                    // if any of the previous promises gets rejected
                    // the success callback will never be executed
                    // the error callback will be called...
                    console.log('An error occurred: ', errorMsg);
                });
           
        //    console.log("logical ------",'http://localhost:8080//visualisationjg-webapi/getMbean/'+logical+'/'+addr+'/');
         /*  $http.get('http://localhost:8080//visualisationjg-webapi/getMbean/'+ $scope.logical+'/'+ $scope.addr+'/').success(function (data) {

                var mbeans  = data;
                $scope.mbean = mbeans.data;
               
            }).error(function (data) {
                //
                $scope.mbean = 0;
            });*/

 
        };
             
        $scope.returnMbean = function(){
        	 console.log("drresse ---------- :"+addr);
         items.getJson('http://localhost:8080//visualisationjg-webapi/getMbean/'+logical+'/'+addr+'/').then(function(result) {
               $scope.displayTree = result;
                 console.log("*************json******** ",result)
             }, function(result) {
                 alert("Error: No data returned", result);
              });
       
        };
        $scope.selectedNode = "";
        $scope.returnMbean();
        $scope.getData();
       



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
                    $rootScope.myAppMainCtrlRefreshRunning = true;
                    $scope.getData();
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
        graphes = $http.get('http://localhost:8080/pfe/getGraph');
        menu = $http.get('http://localhost:8080/pfe/getMenu');
        $q.all({a: graphes, b: menu})
            .then(function(results) {
                console.log(results.a.data, results.b.data);
                $scope.menuList = results.b.data;

            }
        , function(errorMsg) {
            // if any of the previous promises gets rejected
            // the success callback will never be executed
            // the error callback will be called...
            console.log('An error occurred: ', errorMsg);
        });

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
