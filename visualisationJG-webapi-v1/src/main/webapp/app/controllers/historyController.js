
// contrôleur
angular.module("visualjgroups")
  .controller('historyCtrl', ['$scope', '$location',
    function ($scope, $location) {

      // modèle de la page
      var history = $scope.history;
      history.text = "[Modèle local dans page 1]";


    }]);
