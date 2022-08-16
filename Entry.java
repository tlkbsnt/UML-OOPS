package fr.epita.quiz;

public class Entry{
    public String student_name;
    public String emailID;

    public Entry(String student_name, String emailID) {
        this.student_name = student_name;
        this.emailID = emailID;
    }



    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }
}