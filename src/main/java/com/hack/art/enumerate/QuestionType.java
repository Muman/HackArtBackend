package com.hack.art.enumerate;

/**
 * Created by ROLO on 09.11.2015.
 */
public enum QuestionType {

    MULTIPLE("MULTIPLE"),
    ONE_ANSWER("ONE_ANSWER"),
    TRUE_FALSE("TRUE_FALSE"),
    NUMBER("NUMBER"),
    SHORT_ANSWER("SHORT_ANSWER");

    private String questionType;

    QuestionType(String questionType){
        this.questionType = questionType;
    }

    public String getQuestionType(){
        return questionType;
    }
}
