angular.module('stApp.quizSrv', [])
    .service('TestSrv', function($http){
            return {
                tests : function(){
                    return $http.get('../rest/test/tests');
                },
                test : function(criteria){
                    return $http.post('../rest/test/', criteria);
                },
                testToSolve : function(userId){
                    return $http.post('../rest/test/toSolve', userId);
                },
                solvedTest : function(answers){
                    return $http.post('../rest/test/resolved', answers);
                }
            }
        })