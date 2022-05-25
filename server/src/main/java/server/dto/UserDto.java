package server.dto;

import java.util.List;

public class UserDto {
    private Integer id;
    private String username;
    private List<String> roles;
    private String token;

    public UserDto(Integer id, String username, List<String> roles, String token) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.token = token;
    }

    public UserDto() {
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
