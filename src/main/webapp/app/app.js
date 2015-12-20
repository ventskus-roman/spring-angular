angular.module('hello', ['ngRoute', 'app-controllers'])
    .config(function($routeProvider, $httpProvider) {
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    });