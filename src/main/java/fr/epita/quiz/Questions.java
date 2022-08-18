package fr.epita.quiz;

public class Questions{

    private int qn;
    private String question;
    private String answer;
    private String answeroption1;
    private String answeroption2;
    private String answeroption3;

    public Questions(String qn, String question, String answer, String answeroption1, String answeroption2, String answeroption3) {
        this.qn = Integer.parseInt(qn);
        this.question = question;
        this.answer = answer;
        this.answeroption1 = answeroption1;
        this.answeroption2 = answeroption2;
        this.answeroption3 = answeroption3;
    }

    public Questions(int qn, String question) {
        this.qn = qn;
        this.question = question;
    }



    public int getQn() {
        return qn;
    }

    public void setQn(int qn) {
        this.qn = qn;
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

    public String getAnsweroption1() {
        return answeroption1;
    }

    public void setAnsweroption1(String answeroption1) {
        this.answeroption1 = answeroption1;
    }

    public String getAnsweroption2() {
        return answeroption2;
    }

    public void setAnsweroption2(String answeroption2) {
        this.answeroption2 = answeroption2;
    }

    public String getAnsweroption3() {
        return answeroption3;
    }

    public void setAnsweroption3(String answeroption3) {
        this.answeroption3 = answeroption3;
    }
}
