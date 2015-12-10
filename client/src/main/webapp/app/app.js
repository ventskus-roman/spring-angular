angular.module('hello', [ 'ngRoute' ])
    .config(function($routeProvider, $httpProvider) {

        $routeProvider.when('/', {
            templateUrl : 'templates/home.html',
            controller : 'home'
        }).when('/login', {
            templateUrl : 'templates/login.html',
            controller : 'navigation'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    })
    .controller('home', function($scope, $http) {
        $http.get('/resource/').success(function(data) {
            $scope.greeting = data;
        })
    })
    .controller('navigation', function() {});