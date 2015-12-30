angular.module('hello').config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'templates/home.html',
        controller: 'HomeController'
    }).when('/login', {
        templateUrl: 'templates/login.html',
        controller: 'navigation'
    }).when('/post', {
        templateUrl: 'templates/flat.html',
        controller: 'HomeController'
    }).when('/flat/:id', {
        templateUrl: 'templates/flat.html',
        controller: 'FlatController'
    }).otherwise('/');
});
