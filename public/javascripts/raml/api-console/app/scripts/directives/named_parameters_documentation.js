(function() {
  'use strict';

  RAML.Directives.namedParametersDocumentation = function() {
    return {
      restrict: 'E',
      controller: RAML.Controllers.NamedParametersDocumentation,
      templateUrl: 'views/named_parameters_documentation.tmpl.html',
      scope: {
        heading: '@',
        parameters: '='
      }
    };
  };
})();
