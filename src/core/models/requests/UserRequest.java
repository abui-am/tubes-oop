/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.requests;

/**
 *
 * @author USER
 */
public class UserRequest {

    public UserRequest(String name, String email, int roleId, String password) {
        this.name = name;
        this.email = email;
        this.roleId = roleId;
        this.password = password;
    }
    
    public UserRequest(String name, String email, int roleId) {
        this.name = name;
        this.email = email;
        this.roleId = roleId;
    }

    private String name;
    private String email;
    private int roleId;
    private String password;

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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
