angular.module('stApp.test', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider.state('quiz', {
            url : '/quiz',
            templateUrl: 'app/partials/quiz/quiz.html',
            controller: 'TestCtrl'
        })
    }])
    .controller('TestCtrl', ['$scope', '$rootScope', 'TestSrv', 'alert', 'QuestionAnswerSrv',
        'QuestionPreviewSrv', 'TestPreviewSrv', 'QuestionSrv',
        function ($scope, $rootScope, TestSrv, alert, QuestionAnswerSrv, QuestionPreviewSrv, TestPreviewSrv, QuestionSrv) {

            $scope.test = {}

            $scope.correctAnswers = [];

            $scope.questions = [];

            $scope.questionAnswers = [];

            $scope.questionTypes = [
                {type: "MULTIPLE", name: $rootScope.msg['st.multipleType']},
                {type: "ONE_ANSWER", name: $rootScope.msg['st.oneAnswer']},
                {type: "TRUE_FALSE", name: $rootScope.msg['st.trueFalse']},
                {type: "NUMBER", name: $rootScope.msg['st.number']},
                {type: "SHORT_ANSWER", name: $rootScope.msg['st.shortAnswer']}];

            $scope.getAllTests = function () {
                TestSrv.tests()
                    .success(function (result) {
                        $scope.tests = result;
                    }).error(function () {

                    })
            }

            $scope.getAllQuestions = function () {
                QuestionSrv.questions({limit: 10, offset: 1})
                    .success(function (result) {
                        $scope.allQuestions = result;
                    });
            }


            $scope.importQuestions = function () {

            }

            $scope.getAllTests();

            $scope.getAllQuestions();

            $scope.addRadioQ = function () {
                QuestionAnswerSrv.show("ONE_ANSWER", $scope.questionFunction);
            }

            $scope.questionFunction = function (question) {
                $scope.questionAnswers.push(question);
            }

            $scope.addCheckQ = function () {
                QuestionAnswerSrv.show("MULTIPLE", $scope.questionFunction);
            }

            $scope.addTextQ = function () {
                QuestionAnswerSrv.show("SHORT_ANSWER", $scope.questionFunction);
            }

            $scope.addNumberQ = function () {
                QuestionAnswerSrv.show("NUMBER", $scope.questionFunction);
            }

            $scope.removeQuestion = function ($index) {
                $scope.questionAnswers.splice($index, 1);
            }

            $scope.setCorrectAnswer = function (question, answer) {
                $scope.correctAnswer = {
                    question: question,
                    answer: answer
                }

                $scope.correctAnswers.push($scope.correctAnswer);
            }

            $scope.setAnswer = function (question) {
                    QuestionPreviewSrv.show(question, true);
            }

            $scope.saveTest = function () {

                for (var i = 0; i < $scope.questionAnswers.length; i++) {
                    $scope.questionAnswers[i].question.correctAnswer = undefined;
                    $scope.questions.push($scope.questionAnswers[i].question);
                }
                $scope.test.questions = $scope.questions;

                $scope.criteria = {
                    test: $scope.test,
                    answers: $scope.answers
                }
                TestSrv.test($scope.criteria)
                    .success(function () {
                        alert.add("Test was saved", 'success', 4000);
                        $scope.test = {};
                        $scope.questionAnswers = [];
                        $scope.correctAnswers = [];
                        $scope.questions = [];
                        $scope.getAllTests();
                    }).error(function () {
                        alert.add("Error", 'danger', 6000);
                    })
            }

            $scope.showTest = function (test) {
                TestPreviewSrv.show(test);
            }

        }])