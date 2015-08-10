
// contrôleur
angular.module("visualjgroups")
    .controller('mainCtrl', ['$scope', '$location',
        function ($scope, $location) {

            // modèles des pages
            $scope.graphe = {};
            $scope.operation = {};
            $scope.attribute = {};
            $scope.protocol = {};
            $scope.mbean = {};
            $scope.history = {};
            $scope.contact = {};
            // modèle global
            var main = $scope.main = {};
            main.text = "[Modèle global]";

            // méthodes exposées à la vue
            main.showGraphe = function () {
                $location.path("/graphe");
            };
            main.showOperation = function () {
                $location.path("/operation");
            };
            main.showAttribute = function () {
                $location.path("/attribute");
            };
            main.showProtocol = function () {
                $location.path("/protocol");
            };
            main.showMBean = function () {
                $location.path("/mbean");
            };
            main.showHistory = function () {
                $location.path("/history");
            };
            main.showContact = function () {
                $location.path("/contact");
            };

        }]);
