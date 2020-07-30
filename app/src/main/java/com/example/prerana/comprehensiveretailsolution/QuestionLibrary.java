package com.example.prerana.comprehensiveretailsolution;

public class QuestionLibrary {

    private String mQuestions [] = {
            "2 + 8",
            "5 + 10",
            "15 + 20",
        "30 + 10",
        "20 + 2",
        "8 + 1",
        "5 + 5",
        "9 + 1",
        "6 + 12"
    };

    private String mChoices [] [] = {
            {"12","15","10"},
            {"5","15","20"},
            {"35","28","78"},
            {"45","40","42"},
            {"22","20","2"},
            {"15","9","22"},
            {"10","15","8"},
            {"15","5","10"},
            {"6","12","18"}
    };

    private String mCorrectAnswers[] = {"10","15","35","40","22","9","10","10","18"};

    public String getQuestion(int a){
        String question = mQuestions[a];
        return question;
    }

    public String getChoice1(int a){
        String choice0 = mChoices[a][0];
        return  choice0;
    }

    public String getChoice2(int a){
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }
}
