angular.module('hello').config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'templates/home.html',
        controller: 'HomeController'
    }).when('/login', {
        templateUrl: 'templates/login.html',
        controller: 'navigation'
    }).otherwise('/');
});
