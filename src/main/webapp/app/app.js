angular.module('hello', ['ngRoute', 'ui.bootstrap', 'ui.bootstrap.tpls', 'app-controllers'])
    .config(function($routeProvider, $httpProvider) {
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    });