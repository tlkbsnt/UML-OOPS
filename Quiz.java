package fr.epita.quiz;

public class Quiz {
    private int question_number;
    private String question;
    private String answer;
    private String answer_Option1;
    private String answer_Option2;
    private String answer_Option3;

    public Quiz(int question_number, String question, String answer, String answer_Option1, String answer_Option2, String answer_Option3) {
        this.question_number = question_number;
        this.question = question;
        this.answer = answer;
        this.answer_Option1 = answer_Option1;
        this.answer_Option2 = answer_Option2;
        this.answer_Option3 = answer_Option3;
    }

    public int getQuestion_number() {
        return question_number;
    }

    public void setQuestion_number(int question_number) {
        this.question_number = question_number;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer_Option1() {
        return answer_Option1;
    }

    public void setAnswer_Option1(String answer_Option1) {
        this.answer_Option1 = answer_Option1;
    }

    public String getAnswer_Option2() {
        return answer_Option2;
    }

    public void setAnswer_Option2(String answer_Option2) {
        this.answer_Option2 = answer_Option2;
    }

    public String getAnswer_Option3() {
        return answer_Option3;
    }

    public void setAnswer_Option3(String answer_Option3) {
        this.answer_Option3 = answer_Option3;
    }
}
