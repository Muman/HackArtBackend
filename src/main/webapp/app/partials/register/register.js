angular.module("register", ['ui.router',
                            'register.registrationModal',
                            'pascalprecht.translate',
                            'ui.bootstrap',
                            'stApp.alert',
    'stApp.userSrv'])
    .config(['$stateProvider', '$urlRouterProvider', '$translateProvider',
        function ($stateProvider, $urlRouterProvider, $translateProvider) {
        $stateProvider.state('users', {
            url: '/login',
            templateUrl: 'login.html',
            controller: 'RegisterCtrl'
        })
        $translateProvider.useUrlLoader('../rest/messages');
        $translateProvider.preferredLanguage('en');
    }])
.controller("RegisterCtrl", ['RegistrationModalSrv', '$scope', '$translate', 'alert',  '$location',
    function(RegistrationModalSrv, $scope, $translate, alert, $location){


        $scope.register = function(){
            RegistrationModalSrv.show();         
        }


    }])