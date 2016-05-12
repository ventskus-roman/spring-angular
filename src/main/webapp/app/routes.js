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
    }).when('/about', {
        templateUrl: 'templates/about.html',
        controller: 'AboutController'
    }).when('/contacts', {
        templateUrl: 'templates/contacts.html',
        controller: 'ContactsController'
    }).when('/subscribe', {
        templateUrl: 'templates/subscribe.html',
        controller: 'SubscribeController'
    }).otherwise('/');
});
