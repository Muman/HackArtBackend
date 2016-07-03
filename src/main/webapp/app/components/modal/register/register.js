angular.module('register.registrationModal', [])
    .service('RegistrationModalSrv', function($uibModal){
        return {
            show : function(){
                return $uibModal.open({
                    animation : true,
                    templateUrl : 'app/components/modal/register/register.html',
                    size : 'md',
                    windowClass : 'app-modal-window',
                    resolve : {
                    },
                    controller : function($scope, UserSrv, $filter, alert, $uibModalInstance, $translate){

                        $scope.save = function(){
                            UserSrv.register($scope.user)
                                .success(function(){
                                    alert.success($translate.instant('st.userRegistered'));
                                    $scope.close();
                                })
                                .error(function(){
                                    alert.error($translate.instant('st.common.error'))
                                });
                        }

                        $scope.close = function(){
                            $uibModalInstance.close();
                        }
                    }
                });
            }
        };
    });