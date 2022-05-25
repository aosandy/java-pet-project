package client.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {

    private Integer id;
    private String username;
    private String password;
    private List<String> roles;
    private String token;

    public User(Integer id, String username, String password, List<String> roles, String token) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.token = token;
    }

    public User() {
        roles = new ArrayList<>();
    }

    public boolean hasAnyRoles(String... strings) {
        for (int i = 0; i < roles.size(); i++) {
            if (Arrays.asList(strings).contains(getRoles().get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean isAuthenticated() {
        return !roles.isEmpty();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
