package fr.epita.quiz;

public class Admin{
    public int an;
    public String name;
    public String email;
    public String password;

    public Admin(int an, String name, String email, String password) {
        this.an = an;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Admin(String an, String name, String email, String password) {
        this.an = Integer.parseInt(an);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}