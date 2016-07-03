angular.module('stApp.questionAnswer', [])
    .service('QuestionAnswerSrv', function ($uibModal) {
        return {
            show: function (questionType, questionFunction) {
                return $uibModal.open({
                        animation: true,
                        templateUrl: 'app/components/modal/questionAnswerModal.html',
                        size: 'md',
                        resolve: {
                            questionType: function () {
                                return questionType;
                            },
                            questionFunction : function(){
                                return questionFunction;
                            }
                        },
                        controller: function ($scope, AnswerSrv, $rootScope, $uibModalInstance, questionType, questionFunction, alert) {

                            $scope.questionFunction = questionFunction;
                            $scope.questionType = questionType;

                            $scope.question = {
                                type: $scope.questionType
                            }

                            $scope.close = function () {
                                $uibModalInstance.close();
                            }

                            $scope.answers = [
                                {name: ""},
                                {name: ""},
                                {name: ""}
                            ];

                            $scope.removeAnswer = function ($index) {
                                $scope.answers.splice($index, 1);
                            }

                            $scope.addAnswer = function (question) {
                                $scope.answers.push({
                                    name: '',
                                    question: question
                                })
                            }

                            $scope.save = function () {

                                $scope.question.name = $scope.questionName;

                                $scope.criteria = {
                                    question: $scope.question,
                                    answers: $scope.answers
                                }

                                AnswerSrv.questionAnswer($scope.criteria)
                                    .success(function (result) {
                                        $scope.question = result;
                                        $scope.questionFunction($scope.question);
                                        alert.add($rootScope.msg['st.questionSaved'], "success", 6000);
                                    }).finally(function(){
                                        $scope.close();
                                    })
                            }


                        }
                    }
                )
            }
        }
    })