package api.JsonModels.adminJsonModels;

public class LoginRequest {
    String password;
    String username;

    public LoginRequest(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public LoginRequest() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

