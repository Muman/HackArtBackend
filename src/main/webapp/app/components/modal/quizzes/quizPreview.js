angular.module('stApp.testPreview', [])
    .service('TestPreviewSrv', function ($uibModal) {
        return {
            show : function(test){
                return $uibModal.open({
                    animation : true,
                    templateUrl : 'app/components/modal/quizzes/quizPreview.html',
                    size : 'md',
                    resolve : {
                        test : function(){
                            return test;
                        }
                    },
                    controller : function($scope, $rootScope, test, $uibModalInstance){

                        $scope.test = test;
                        $scope.questions = $scope.test.questions;

                        $scope.cancel = function(){
                            $uibModalInstance.close();
                        }

                    }
                })

            }
        }

    })