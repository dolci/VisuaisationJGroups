angular.module("visualjgroups")
  .factory('dao',['$http',
        function($http) {
	  
          var urlBase = 'http://localhost:8080//visualisationjg-webapi/';
          return {
          getGraph : function () {
              return $http.get(urlBase + 'getGraph');
          },

          getMenu : function () {
              return $http.get(urlBase + 'getMenu' );
          }
        };
   }
    ]);