angular.module('stApp.confirmModal', [])
    .service('ConfirmModalSrv', function($uibModal){
        return {
            show : function(message, callbackFunction){
                return $uibModal.open({
                    animation : true,
                    size : 'md',
                    templateUrl : 'app/components/modal/common/confirm.html',
                    resolve : {
                        message : function(){
                            return message;
                        },
                        callbackFunction : function(){
                            return callbackFunction;
                        }
                    },
                    controller : function($scope, message, callbackFunction, $uibModalInstance){

                        $scope.callback = callbackFunction;

                        $scope.message = message;

                        $scope.close = function(){
                            $uibModalInstance.close();
                        }

                        $scope.confirm = function(){
                            $scope.callback();
                            $scope.close();
                        }
                    }
                })

            }
        }
    })
