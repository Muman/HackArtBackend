angular.module('stApp.userModal', [])
    .service('UserModalSrv', function($uibModal){
        return {
            show : function(user, callbackFunction, roles){
                return $uibModal.open({
                    animation : true,
                    templateUrl : 'app/components/modal/users/addEditUser.html',
                    size : 'md',
                    windowClass : 'app-modal-window',
                    resolve : {
                        us : function(){
                            return user;
                        },
                        callback : function(){
                            return callbackFunction;
                        },
                        roles : function(){
                            return roles;
                        }
                    },
                    controller : function($scope, us, UserSrv, $filter, alert, $uibModalInstance, $translate, callback,
                        roles){

                        $scope.edit = false;
                        $scope.us = us;

                        $scope.userRoles = roles;

                        if(angular.isUndefined(user)){
                            $scope.user = {role: {}}
                            $scope.user.role.name = _.find($scope.userRoles, function(item){
                                return angular.equals(item.type, 'CLIENT');
                            })
                        } else {
                            $scope.edit = true;
                            angular.copy($scope.us, $scope.user);
                            $scope.user.role.name = _.find($scope.userRoles, function(item){
                                return angular.equals(item.type, $scope.user.role.name);
                            })
                        }

                        $scope.save = function(){
                            $scope.user.role.name = $scope.user.role.name.type;
                            UserSrv.user($scope.user)
                                .success(function(){
                                    alert.success($translate.instant($scope.edit ? 'st.userUpdated' : 'st.userAdded'));
                                    callback();
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