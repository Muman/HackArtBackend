angular.module('stApp.testAssign', [])
    .service('TestAssignSrv', function ($uibModal) {
        return {
            show: function (group, getAll) {
                return $uibModal.open({
                    animation: true,
                    templateUrl: 'app/components/modal/quizzes/quizAssigning.html',
                    size: 'lg',
                    resolve: {
                        group: function () {
                            return group;
                        },
                        getAll: function () {
                            return getAll;
                        }
                    },
                    controller: function ($scope, $rootScope, TestSrv, $uibModalInstance, UserGroupSrv, alert, group, getAll) {
                        $scope.group = group;

                        $scope.testsToAssign = [];

                        if (angular.isUndefined($scope.group.tests)) {
                            $scope.group.tests = [];
                        } else {
                            angular.copy($scope.group.tests, $scope.testsToAssign);
                        }

                        $scope.getAllTests = function () {
                            TestSrv.tests()
                                .success(function (result) {
                                    $scope.tests = result;
                                    if ($scope.testsToAssign.length) {
                                        var diff = _.difference(_.pluck($scope.tests, 'id'), _.pluck($scope.testsToAssign, 'id'));
                                        $scope.tests = _.filter($scope.tests, function (obj) {
                                            return diff.indexOf(obj.id) >= 0;
                                        });
                                    }
                                }
                            );
                        }

                        $scope.getAllTests();

                        $scope.addTest = function ($index, test) {
                            $scope.testsToAssign.push(test);
                            $scope.tests.splice($index, 1);
                        }

                        $scope.save = function () {
                            $scope.group.tests = $scope.testsToAssign;

                            UserGroupSrv.userGroupToUpdate($scope.group)
                                .success(function () {
                                    $scope.close();
                                    getAll();
                                }).error(function () {
                                    alert.add("Error", "danger", 6000);
                                })
                        }

                        $scope.removeTest = function ($index, test) {
                            $scope.testsToAssign.splice($index, 1);
                            $scope.tests.push(test);
                        }

                        $scope.close = function () {
                            $uibModalInstance.close();
                        }

                    }
                })
            }

        }
    })