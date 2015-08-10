
// contrôleur
angular.module("visualjgroups")
  .controller('attributeCtrl', ['$scope', '$location',
    function ($scope, $location) {

      // modèle de la page
      var attribute = $scope.attribute;
      attribute.text = "[Modèle local dans page 1]";
    }]);
