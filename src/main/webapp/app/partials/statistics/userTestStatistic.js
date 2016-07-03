angular.module('stApp.userTestStatistic', ['ui.router'])
    .config(['$stateProvider', function($stateProvider){
        $stateProvider.state('userTestStatistic', {
            url: '/userTestStatistic/:userId',
            templateUrl: 'app/partials/statistics/userTestStatistic.html',
            controller: 'UserTestStatisticCtrl'
        });
    }])
.controller('UserTestStatisticCtrl', ['$scope', 'UserSrv', '$stateParams', function($scope, UserSrv, $stateParams){

        $scope.successSolved = 0;

        $scope.userId = $stateParams.userId;
        $scope.getUserSummary = function(){
            UserSrv.summary($scope.userId)
                .success(function(result){
                    $scope.summary = result;
                    $scope.testNumber = $scope.summary ? $scope.summary.length : 0;
                    $scope.calculateSuccess();
                });

        }

        $scope.calculateSuccess = function() {
            _.each($scope.summary, function (s) {
                if (null != s.test.scoreToPass) {
                    if (s.degree >= s.test.scoreToPass) {
                        $scope.successSolved += 1;
                    }
                }
            });
        }

        $scope.showTestDescription = function(test){
            console.log(test);
            test.selected = !test.selected;
            _.each($scope.summary, function(s){
                if(test.id != s.test.id){
                    s.test.selected = false;
                }
            })
        }





        $scope.getUserSummary();

    }]);