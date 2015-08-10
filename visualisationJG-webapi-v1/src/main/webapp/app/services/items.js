angular.module("visualjgroups")
  .factory('items',['$http',
        function($http) {

            return {
                getJson: function(url) {
                    var ItemsJson = $http.get(url).then(function(response) {
                       
                        return response.data;
                    });
                    return ItemsJson;
                }
            
            }
        }
    ]);