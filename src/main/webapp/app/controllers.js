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
        $http.get('flat/find', {params: {priceStart: $scope.priceStart, priceEnd: $scope.priceEnd, onlyNearMetro: $scope.onlyNearMetro, offset: $scope.offset, limit: limit, metro: onlySelectedStations()}}).success(function (data) {
            if (data.length > 0) {
                $scope.flats = data;
            } else if ($scope.offset > 0) {
                $scope.offset = $scope.offset - limit;
            }
        });
    }

    $scope.moscovskaya = [
        {name: "Уручье", selected: false},
        {name: "Борисовский тракт", selected: false},
        {name: "Восход", selected: false},
        {name: "Московская", selected: false},
        {name: "Парк Челюскинцев", selected: false},
        {name: "Академия наук", selected: false},
        {name: "Площадь Якуба Коласа", selected: false},
        {name: "Площадь Победы", selected: false},
        {name: "Октябрьская", selected: false},
        {name: "Площадь Ленина", selected: false},
        {name: "Институт Культуры", selected: false},
        {name: "Грушевка", selected: false},
        {name: "Михалово", selected: false},
        {name: "Петровщина", selected: false},
        {name: "Малиновка", selected: false}
    ];

    $scope.autozavodskaya = [
        {name: "Могилёвская", selected: false},
        {name: "Автозаводская", selected: false},
        {name: "Партизанская", selected: false},
        {name: "Тракторный завод", selected: false},
        {name: "Пролетарская", selected: false},
        {name: "Первомайская", selected: false},
        {name: "Купаловская", selected: false},
        {name: "Немига", selected: false},
        {name: "Фрунзенская", selected: false},
        {name: "Молодежная", selected: false},
        {name: "Пушкинская", selected: false},
        {name: "Спортивная", selected: false},
        {name: "Кунцевщина", selected: false},
        {name: "Каменная Горка", selected: false}
    ];

    var onlySelectedStations = function() {
        var result = [];
        for (var i = 0; i < $scope.moscovskaya.length; i++) {
            var station = $scope.moscovskaya[i];
            if (station.selected) {
                result.push(station.name);
            }
        }
        for (var i = 0; i < $scope.autozavodskaya.length; i++) {
            var station = $scope.moscovskaya[i];
            if (station.selected) {
                result.push(station.name);
            }
        }
        return result;
    }

    $scope.selectAll = function(target) {
        angular.forEach(target, function(item, i) {
           item.selected = !item.selected;
        });
    };

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

function AboutController($scope) {

};

function ContactsController($scope) {

};

function SubscribeController($scope, $http) {
    $scope.subscribe = function() {
        $http.get('subscription/create', {params: {email: $scope.email, priceStart: $scope.priceStart, priceEnd: $scope.priceEnd, onlyNearMetro: $scope.onlyNearMetro}}).success(function (data) {
            $scope.message = 'Все ок, проверьте почту!';
        }).error(function() {
            $scope.message = 'Ошибка! Проверьте введенные данные!';
        });
    }
}

angular.module('app-controllers', [])
    .controller('navigation', NavigationController)
    .controller('HomeController', HomeController)
    .controller('PostController', PostController)
    .controller('FlatController', FlatController)
    .controller('AboutController', AboutController)
    .controller('ContactsController', ContactsController)
    .controller('SubscribeController', SubscribeController);