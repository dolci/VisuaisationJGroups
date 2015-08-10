
// contrôleur
angular.module("visualjgroups")
  .controller('contactCtrl', ['$scope', '$location',
    function ($scope, $location) {

      // modèle de la page
      var contact = $scope.contact;
      contact.text = "[Modèle local dans page 1]";
    }]);
