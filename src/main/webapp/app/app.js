angular.module('stApp', ['ui.router',
    'ui.bootstrap',
    'pascalprecht.translate',
    'stApp.partials',
    'stApp.services',
    'stApp.modals',
    'stApp.filter',
    'stApp.common',
    'stApp.directive'])

    .config(['$stateProvider', '$urlRouterProvider', '$translateProvider',
        function ($stateProvider, $urlRouterProvider, $translateProvider) {
            $urlRouterProvider.otherwise('/main');
            $stateProvider.state('login', {
                url: '/login',
                templateUrl: '/login.html'
            });
            //$translateProvider.translations('en', {})
            //$translateProvider.preferredLanguage('en');
            $translateProvider.useUrlLoader('../rest/messages');
            $translateProvider.preferredLanguage('en');
            //$locationProvider.html5Mode(false);

        }])

    .controller('IndexCtrl', function ($scope, $http, $window, alert) {

        $scope.alertList = alert.alerts;

        $scope.closeAlert = function (index) {
            alert.closeAlert(index);
        };

        $scope.testData = "data";

        $scope.logout = function () {
            $http.get("/logout").success(function () {
                $window.open("../rest/login", "_self");
            });
        }
    })

    .run(['$rootScope', '$http', function ($rootScope, $http) {

        //var messages = $http.get('../rest/messages/en.json');
        $rootScope.msg = "";//messages;

        var user = $http.post('../rest/user');
        user.success(function(result){
            $rootScope.user = result;
        });

    }])