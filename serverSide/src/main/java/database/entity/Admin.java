package database.entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name = "Admin.find", query = "SELECT g FROM Admin g WHERE g.username = :username AND g.password=:password")
@NamedQuery(name = "Admin.deleteToken", query = "UPDATE Admin g SET g.token=null WHERE g.username=:username AND g.password=:password")
@NamedQuery(name = "Admin.findToken", query = "SELECT g FROM Admin g WHERE g.token = :token")
@Table(name = "admin")
public class Admin implements Serializable{
    @Id
    String username;
    @Id
    String password;
    @Basic
    String token;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }
}
