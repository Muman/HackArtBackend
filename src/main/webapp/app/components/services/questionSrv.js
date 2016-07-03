/**
 * Created by ROLO on 03.11.2015.
 */
angular.module('stApp.questionSrv', [])
    .service('QuestionSrv', function($http){
        return {
            question : function(question){
                return $http.post('../rest/question/new', question);
            },
            questions : function(questionCriteria){
                return $http.get('../rest/question/questions', questionCriteria);
            },
            byId : function(id){
                return $http.post('../rest/question/byId', id);
            },
            correctAnswers : function(answers){
                return $http.post('../rest/question/correctAnswer', answers);
            }
        }
    })
