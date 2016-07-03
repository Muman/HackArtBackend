angular.module('stApp.userGroup', ['ui.router'])
    .config(function ($stateProvider) {
        $stateProvider.state('userGroup', {
            url : '/userGroup',
            templateUrl: 'app/partials/userGroup/userGroup.html',
            controller: 'UserGroupCtrl'
        })
    })
    .controller('UserGroupCtrl', ['$scope', 'UserGroupSrv', '$rootScope', 'alert', 'UserGroupEditSrv', 'TestAssignSrv',
        '$filter', 'TestResultsSrv',
        function ($scope, UserGroupSrv, $rootScope, alert, UserGroupEditSrv, TestAssignSrv, $filter, TestResultsSrv) {

            $scope.selectedGroup = undefined;

            $scope.getAll = function () {
                UserGroupSrv.userGroups()
                    .success(function (result) {
                        $scope.groups = result;
                    }).error(function () {
                        alert.add("Error", "danger", 6000);
                    })
            }
            $scope.getAll();

            $scope.addUserGroup = function () {
                UserGroupEditSrv.show($scope.getAll, undefined);
            }

            $scope.editGroup = function (group) {
                UserGroupEditSrv.show($scope.getAll, group);
            }

            $scope.assignTests = function (group) {
                TestAssignSrv.show(group, $scope.getAll);
            }

            $scope.removeGroup = function (group) {
                UserGroupSrv.groupToRemove(group)
                    .success(function () {
                        $scope.getAll();
                    }).error(function () {
                        alert.add("Error", "danger", 6000);
                    });
            }

            $scope.showResults = function (group) {
                $scope.selectedGroup = group;
                $scope.tests = group.tests;
                $scope.header = $filter('orderBy')(group.tests, 'id');
                $scope.tests = $filter('orderBy')($scope.tests, 'id');

                UserGroupSrv.summary(group.id)
                    .success(function (result) {
                        $scope.summary = result;
                        $scope.transformSummary($scope.tests, $scope.summary, $scope.selectedGroup);
                    });
            }

            $scope.transformSummary = function (tests, summary, selectedGroup) {
                $scope.results = [];
                $scope.students = $filter('orderBy')(selectedGroup.users, 'id');
                $scope.tt = tests;

                for (var i = 0; i < $scope.students.length; i++) {
                    $scope.ssummary = [];
                    for (var s = 0; s < summary.length; s++) {

                        if (angular.equals(summary[s].student.id, $scope.students[i].id)) {
                            $scope.ssummary.push(summary[s]);
                        }
                    }

                    $scope.ssummary = $filter('orderBy')($scope.ssummary, 'test');

                    $scope.result = {
                        user: $scope.students[i],
                        degree: []
                    }

                    var tmpTests = $scope.tt;
                    if (angular.isDefined($scope.ssummary) && null != $scope.summary) {

                        for (var t = 0; t < $scope.ssummary.length; t++) {
                            if(t < tmpTests.length){
                                if (!angular.equals($scope.ssummary[t].test, tmpTests[t].id)) {
                                    $scope.ssummary.splice(t, 0, {degree: '', test: tmpTests[t].id});
                                }
                            }
                        }

                        for (var s = 0; s < $scope.header.length; s++) {
                            $scope.result.degree.push(
                                {
                                    test: s < $scope.ssummary.length ? $scope.ssummary[s].test : '',
                                    degree: s < $scope.ssummary.length ? $scope.ssummary[s].degree : ''
                                }
                            );
                        }

                        $scope.results.push($scope.result);
                    }
                }
                TestResultsSrv.show($scope.header, $scope.results);
            }
        }
    ]);