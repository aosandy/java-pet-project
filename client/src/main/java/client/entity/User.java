package client.entity;

import java.util.List;

public class User {
    public static class Role {
        private Integer id;
        private String name;

        public Role(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Role() {
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private Integer id;
    private String username;
    private String password;
    private List<Role> roles;
    private String token;

    public User(Integer id, String username, String password, List<Role> roles, String token) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.token = token;
    }

    public User() {
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
