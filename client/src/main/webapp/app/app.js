angular.module('hello', ['ngRoute', 'app-controllers'])
    .config(function($routeProvider, $httpProvider) {

        $routeProvider.when('/', {
            templateUrl : 'templates/home.html',
            controller : 'HomeController'
        }).when('/login', {
            templateUrl : 'templates/login.html',
            controller : 'navigation'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    });