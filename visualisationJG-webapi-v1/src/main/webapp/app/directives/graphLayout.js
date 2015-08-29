
angular.module("visualjgroups")
    .directive('grapheLayout', [function() {
    return {
        restrict: 'A',
        link: function (scope, element) {
            var width = 450;
            var height = 400;
            var color = d3.scale.category20();
            
            // On récupère les données présentent dans scope.grapheDatas
            // Le $watch a pour but de mettre à jour le graphe dès que les
            // données présentent dans $scope.grapheDatas changent.
            // Ex : suppression ou ajout de noeuds
            scope.$watch('members', function (members) {
                var force = d3.layout.force()
                .charge(-120)
                .linkDistance(30)
                .size([width, height])
                .nodes(members.nodes)
                .links(members.links)
                .start();
                
                var svg = d3.select("#nodeGraph").append("svg")
                .attr("width", width)
                .attr("height", height);
                
                var link = svg.selectAll(".link")
                .data(members.links)
                .enter().append("line")
                .attr("class", "link")
                .style("stroke-width", function(d) { return Math.sqrt(d.value); });
                
                var node = svg.selectAll(".node")
                .data(members.nodes)
                .enter().append("circle")
                .attr("class", "node")
                .attr("r", 5)
                .style("fill", function(d) { return color(d.group); })
                .call(force.drag);
                
                node.append("title")
                .text(function(d) { return d.name; });
                
                force.on("tick", function() {
                    link.attr("x1", function(d) { return d.source.x; })
                    .attr("y1", function(d) { return d.source.y; })
                    .attr("x2", function(d) { return d.target.x; })
                    .attr("y2", function(d) { return d.target.y; });
                    
                    node.attr("cx", function(d) { return d.x; })
                    .attr("cy", function(d) { return d.y; });
                });
            });
        }
    }
}])