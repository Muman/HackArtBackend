angular.module('stApp.typeName', [])
    .filter('typeName', [ function(){
        return function(type, listOfTypes){
            for(var i =0; i<listOfTypes.length; i++){
                if(angular.equals(type, listOfTypes[i].type)){
                    return listOfTypes[i].name;
                }
            }
        }
    }]);