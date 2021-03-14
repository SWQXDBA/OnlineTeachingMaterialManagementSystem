package model;

public class User {
    private String userName;
    private String passWord;
    private int userId;
    private boolean isAdmin;

    public User() {
    }

    public User(String userName, String passWord, int userId, boolean isAdmin) {
        this.userName = userName;
        this.passWord = passWord;
        this.userId = userId;
        this.isAdmin = isAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", userId=" + userId +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
