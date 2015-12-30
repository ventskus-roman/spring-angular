function NavigationController($rootScope, $scope, $http, $location) {
    var authenticate = function (credentials, callback) {

        var headers = credentials ? {
            authorization: "Basic "
            + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get('user', {headers: headers}).success(function (data) {
            if (data.name) {
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback();
        }).error(function () {
            $rootScope.authenticated = false;
            callback && callback();
        });

    };

    authenticate();
    $scope.credentials = {};

    $scope.login = function() {
        console.log('login');
        authenticate($scope.credentials, function () {
            if ($rootScope.authenticated) {
                $location.path("/");
                $scope.error = false;
            } else {
                $location.path("/login");
                $scope.error = true;
            }
        });
    };

    $scope.logout = function() {
        $http.post('logout', {}).success(function() {
            $rootScope.authenticated = false;
            $location.path("/");
        }).error(function(data) {
            $rootScope.authenticated = false;
        });
    }
};

function HomeController($rootScope, $scope, $http, $location) {
    $scope.greeting = {id: '123', content: 'asdssdf'};
    $scope.totalItems = 10;
    $scope.currentPage = 1;
    $scope.offset = 0;
    var limit = 30;

    function update() {
        console.log('update');
        $http.get('flat/find', {params: {priceStart: $scope.priceStart, priceEnd: $scope.priceEnd, onlyNearMetro: $scope.onlyNearMetro, offset: $scope.offset, limit: limit}}).success(function (data) {
            if (data.length > 0) {
                $scope.flats = data;
            } else if ($scope.offset > 0) {
                $scope.offset = $scope.offset - limit;
            }
        });
    }

    $scope.$watch('priceStart + priceEnd + onlyNearMetro', function() {
        $scope.offset = 0;
        update();
    });
    update();

    $scope.next = function() {
        $scope.offset = $scope.offset + limit;
        update();
    }

    $scope.prev = function() {
        $scope.offset = $scope.offset - limit;
        update();
    }
};

function PostController($scope, $http) {

};

function FlatController($scope, $http, $routeParams) {
    var id = $routeParams.id;

    $http.get('flat/' + id).success(function (data) {
        $scope.flat = data;
    });

};

angular.module('app-controllers', [])
    .controller('navigation', NavigationController)
    .controller('HomeController', HomeController)
    .controller('PostController', PostController)
    .controller('FlatController', FlatController);