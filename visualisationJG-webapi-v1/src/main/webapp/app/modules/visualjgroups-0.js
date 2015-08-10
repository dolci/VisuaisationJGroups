
// --------------------- module Angular

angular.module("visualjgroups", ['ngRoute','ui.bootstrap']);
angular.module("visualjgroups").config(["$routeProvider", function ($routeProvider) {
// ------------------------ routage
  $routeProvider.when("/operation",
    {
      templateUrl: "views/operation.html",
      controller: 'operationCtrl'
    });
  $routeProvider.when("/graphe",
    {
      templateUrl: "views/graphe.html",
      controller: 'grapheCtrl'
    });
  $routeProvider.when("/protocol",
    {
      templateUrl: "views/protocol.html",
      controller: 'protocolCtrl'
    });
    $routeProvider.when("/attribute",
        {
            templateUrl: "views/attribute.html",
            controller: 'attributeCtrl'
        });
    $routeProvider.when("/mbean",
        {
            templateUrl: "views/mbean.html",
            controller: 'mbeanCtrl'
        });
    $routeProvider.when("/history",
        {
            templateUrl: "views/history.html",
            controller: 'historyCtrl'
        });
    $routeProvider.when("/contact",
        {
            templateUrl: "views/contact.html",
            controller: 'contactCtrl'
        });
  $routeProvider.otherwise(
    {
      redirectTo: "/graphe"
    });
}]);
