angular.module('st.answerModal', [])
    .service('AnswerModalSrv', function($uibModal){
        return {
            show : function(question){
                return $uibModal.open({
                    animation : true,
                    templateUrl : 'app/components/modal/answerModal.html',
                    size : 'md',
                    resolve : {
                        question : function(){
                            return question;
                        }
                    },
                    controller : function($uibModalInstance, $scope, $rootScope, question, AnswerSrv, alert){
                        $scope.question = question;

                        $scope.save = function(answer){
                            answer.question = $scope.question;
                            AnswerSrv.answer(answer)
                                .success(function(){
                                    alert.add($rootScope.msg['st.answerAdded'], 'success', 4000)
                                }).error(function(){

                                });
                        }

                        $scope.cancel = function(){
                            $uibModalInstance.close();
                        }
                    }
                })

            }
        }

    })