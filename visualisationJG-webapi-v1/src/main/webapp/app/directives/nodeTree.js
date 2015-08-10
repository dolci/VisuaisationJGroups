
angular.module("visualjgroups")
  .directive('nodeTree', [function() {
    return {
        template: '<node ng-repeat="node in tree"></node>',
        replace: true,
        transclude: true,
        restrict: 'E',
        scope: {
            tree: '=ngModel'
        }
    };
}]);
