
// contrôleur
angular.module("rdvmedecins")
  .controller('mbeanCtrl', ['$scope', '$location',
    function ($scope, $location) {


      // modèle de la page
      var mbean = $scope.mbean;
      mbean.text = "[Modèle local dans page 3]";
    }]);
