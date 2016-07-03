angular.module('stApp.testResults', [])
    .service('TestResultsSrv', function($uibModal){
        return {
            show : function(header, results){
                return $uibModal.open({
                    animation : true,
                    templateUrl : 'app/components/modal/quizzes/quizResults.html',
                    size : 'lg',
                    resolve : {
                        header : function(){
                            return header;
                        },
                        results : function(){
                            return results;
                        }
                    },
                    controller : function($scope, $rootScope, results, header, $uibModalInstance){

                        $scope.results = results;
                        $scope.header = header;

                        $scope.close = function(){
                            $uibModalInstance.close();
                        }

                    }
                })
            }

        }
    })