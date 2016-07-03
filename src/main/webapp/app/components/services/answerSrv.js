angular.module('stApp.answerSrv', [])
    .service('AnswerSrv', function($http){
        return {
            answer : function(answer){
                return $http.post('../rest/answer/', answer);
            },
            answersByQuestionId : function(questionId){
                return $http.post('../rest/answer/question', questionId);
            },
            questionAnswer : function(questionAnswer){
                return $http.post('../rest/answer/questionAnswer', questionAnswer);
            }
        }
    })