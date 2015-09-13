
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
   	 var graph;
     function myGraph(elem) {

         // Add and remove elements on the graph object
         this.addNode = function (id) {
             nodes.push({"id": id});
             update();
         };

         this.removeNode = function (id) {
             var i = 0;
             var n = findNode(id);
             while (i < links.length) {
                 if ((links[i]['source'] == n) || (links[i]['target'] == n)) {
                     links.splice(i, 1);
                 }
                 else i++;
             }
             nodes.splice(findNodeIndex(id), 1);
             update();
         };

         this.removeLink = function (source, target) {
             for (var i = 0; i < links.length; i++) {
                 if (links[i].source.id == source && links[i].target.id == target) {
                     links.splice(i, 1);
                     break;
                 }
             }
             update();
         };

         this.removeallLinks = function () {
             links.splice(0, links.length);
             update();
         };

         this.removeAllNodes = function () {
             nodes.splice(0, links.length);
             update();
         };

         this.addLink = function (source, target, value) {
             links.push({"source": findNode(source), "target": findNode(target), "value": value});
             update();
         };

         var findNode = function (id) {
             for (var i in nodes) {
                 if (nodes[i]["id"] === id) return nodes[i];
             }
             ;
         };

         var findNodeIndex = function (id) {
             for (var i = 0; i < nodes.length; i++) {
                 if (nodes[i].id == id) {
                     return i;
                 }
             }
             ;
         };

         // set up the D3 visualisation in the specified element
         var w = 660,
             h = 450;

         var color = d3.scale.category10();
        

         // Create the SVG container for the visualization and
        // define its dimensions.

   
         var vis = d3.select(elem)
                 .append("svg:svg")
                 .attr("width", w)
                 .attr("height", h)
                 .attr("id", "svg")
                 .attr("pointer-events", "all")
                 .attr("viewBox", "0 0 " + w + " " + h)
                 .attr("perserveAspectRatio", "xMinYMid")
                 .append('svg:g');

         var force = d3.layout.force();

         var nodes = force.nodes(),
                 links = force.links();

         var update = function () {
             var link = vis.selectAll("line")
                     .data(links, function (d) {
                         return d.source.id + "-" + d.target.id;
                     });

             link.enter().append("line")
                     .attr("id", function (d) {
                         return d.source.id + "-" + d.target.id;
                     })
                     .attr("stroke-width", function (d) {
                         return d.value / 10;
                     })
                     .attr("class", "link");
             link.append("title")
                     .text(function (d) {
                         return d.value;
                     });
             link.exit().remove();

             var node = vis.selectAll("g.node")
                     .data(nodes, function (d) {
                         return d.id;
                     });

             var nodeEnter = node.enter().append("g")
                     .attr("class", "node")
                     .call(force.drag);

             nodeEnter.append("svg:circle")
                     .attr("r", 12)
                     .attr("id", function (d) {
                         return "Node;" + d.id;
                     })
                     .attr("class", "nodeStrokeClass")
                     .attr("fill", function(d) { return color(d.id); });

             nodeEnter.append("svg:text")
                     .attr("class", "textClass")
                     .attr("x", 14)
                     .attr("y", ".31em")
                     .text(function (d) {
                         return d.id;
                     });

             node.exit().remove();

             force.on("tick", function () {

                 node.attr("transform", function (d) {
                     return "translate(" + d.x + "," + d.y + ")";
                 });

                 link.attr("x1", function (d) {
                     return d.source.x;
                 })
                         .attr("y1", function (d) {
                             return d.source.y;
                         })
                         .attr("x2", function (d) {
                             return d.target.x;
                         })
                         .attr("y2", function (d) {
                             return d.target.y;
                         });
             });

             // Restart the force layout.
             force
                     .gravity(.01)
                     .charge(-80000)
                     .friction(0)
                     .linkDistance( function(d) { return d.value * 10 } )
                     .size([w, h])
                     .start();
         };


         // Make it all go
         update();
     }

     function drawGraph() {

         graph = new myGraph("#historyGraph");


         graph.addNode('sfo1');
         graph.addNode('sfo2');
         graph.addNode('sfo3');
         graph.addNode('lon1');
         graph.addNode('lon2');
         graph.addNode('lon3');
         graph.addNode('nyc1');
         graph.addNode('nyc2');
       
         graph.addLink('sfo1', 'sfo2', '20');
         graph.addLink('sfo1', 'sfo3', '20');
         graph.addLink('sfo1', 'lon1', '20');
         graph.addLink('sfo1', 'nyc1', '30');
         graph.addLink('nyc1', 'nyc2', '20');
         graph.addLink('lon1', 'lon2', '10');
         graph.addLink('lon1', 'lon3', '10');
         graph.addLink('lon1', 'nyc1', '10');
         
       
         keepNodesOnTop();

         // callback for the changes in the network
         var step = -1;
         function nextval()
         {
             step++;
             return 4000 + (1500*step); // initial time, wait time
         }

         setTimeout(function() {
        	 graph.addNode('nyc3');
             graph.addLink('nyc1', 'nyc3', '20');
             keepNodesOnTop();
         }, nextval());

         setTimeout(function() {
        	graph.removeNode('sfo2');
             keepNodesOnTop();
         }, nextval());

         setTimeout(function() {
         	graph.removeNode('sfo3');
              keepNodesOnTop();
          }, nextval());
         setTimeout(function() {
          	graph.removeNode('lon2');
               keepNodesOnTop();
           }, nextval());


       /*  setTimeout(function() {
             graph.addLink('Daniel', 'Alex', '20');
             keepNodesOnTop();
         }, nextval());

         setTimeout(function() {
             graph.addLink('Suzie', 'Daniel', '30');
             keepNodesOnTop();
         }, nextval());

         setTimeout(function() {
             graph.removeLink('Dylan', 'Mason');
             graph.addLink('Dylan', 'Mason', '8');
             keepNodesOnTop();
         }, nextval());

         setTimeout(function() {
             graph.removeLink('Dylan', 'Emma');
             graph.addLink('Dylan', 'Emma', '8');
             keepNodesOnTop();
         }, nextval());
*/
     }

     drawGraph();

     // because of the way the network is created, nodes are created first, and links second,
     // so the lines were on top of the nodes, this just reorders the DOM to put the svg:g on top
     function keepNodesOnTop() {
         $(".nodeStrokeClass").each(function( index ) {
             var gnode = this.parentNode;
             gnode.parentNode.appendChild(gnode);
         });
     }
     $scope.addNodes = function() {
         d3.select("svg")
                 .remove();
          drawGraph();
     }
    }]);
