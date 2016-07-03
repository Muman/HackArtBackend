/**
 * Created by ROLO on 03.11.2015.
 */
angular.module('stApp.userSrv', [])
    .service('UserSrv', function($http){
        return {
            users : function(){
                return $http.get('../rest/users/all');
            },
            user : function(user){
                return $http.post('../rest/users/user', user);
            },
            userForUpdate : function(user){
                return $http.post('../rest/users/userToUpdate', user);
            },
            userToDelete : function(user){
                return $http.delete('../rest/users/user/' + user.id, user);
            },
            password : function(user){
                return $http.post('../rest/users/password', user);
            },
            summary : function(userId){
                return $http.post('../rest/users/summary', userId);
            },
            register : function(user){
                return $http.post('../rest/register', user);
            }
        }
    });