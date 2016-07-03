/**
 * Created by ROLO on 02.11.2015.
 */

angular.module('stApp.main', ['ui.router'])
    .config(['$stateProvider', function($stateProvider){
        $stateProvider.state('main',{
            url : '/main',
            templateUrl : 'app/partials/main/main.html',
            controller : 'MainCtrl'
        })
    }])
    .controller('MainCtrl', function($scope, $location){
            $scope.main = "IT is main viewui r";

    })
