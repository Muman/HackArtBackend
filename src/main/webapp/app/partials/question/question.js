/**
 * Created by Roman on 20.10.2015.
 */

angular.module('stApp.question', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider.state('question', {
            url : '/question',
            templateUrl: 'app/partials/question/question.html',
            controller: 'QuestionCtrl'
        })
    }])
    .controller('QuestionCtrl', function($scope, $location, $rootScope, QuestionSrv, PaginationSrv, AnswerModalSrv,
                                          QuestionPreviewSrv, $filter) {

        var $translate = $filter('translate');

        $scope.questionTypes = [
            {type: "MULTIPLE", name: $translate('st.multipleType')},
            {type: "ONE_ANSWER", name: $translate('st.oneAnswer')},
           // {type: "MATCHING", name: $translate('st.matching')},
            {type: "TRUE_FALSE", name: $translate('st.trueFalse')},
            {type: "NUMBER", name: $translate('st.number')},
            {type: "SHORT_ANSWER", name: $translate('st.shortAnswer')}];

        $scope.addQuestion = function () {
            $scope.question.type = $scope.question.type.type;
            var promise = QuestionSrv.question($scope.question)
                .success(function () {
                    $scope.getQuestions();
                })
        }

        $scope.addAnswer = function(question){
            AnswerModalSrv.show(question    );
        }

        $scope.editQuestion = function(){

        }

        $scope.criteria = {
            limit: 10,
            offset: 0
        }

        $scope.getQuestions = function () {
            var pomise = QuestionSrv.questions($scope.criteria)
                .success(function (result) {
                    $scope.questions = result;
                })
        }

        $scope.showPreview = function(question){
            QuestionPreviewSrv.show({question: question}, false);
        }

        $scope.getQuestions();

    })
