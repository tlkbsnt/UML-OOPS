package fr.epita.quiz;

public class Admin{
    public int adminNo;
    public String fullName;
    public String emailId;
    public String password;

    public Admin(int adminNo, String fullName, String emailId, String password) {
        this.adminNo = adminNo;
        this.fullName = fullName;
        this.emailId = emailId;
        this.password = password;
    }
    public int getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(int adminNo) {
        this.adminNo = adminNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}