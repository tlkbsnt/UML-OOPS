package fr.epita.quiz;

public class Score {

    String student_name;
    int  student_score;

    public Score(String student_name, int student_score) {
        this.student_name = student_name;
        this.student_score = student_score;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public int getStudent_score() {
        return student_score;
    }

    public void setStudent_score(int student_score) {
        this.student_score = student_score;
    }
}
