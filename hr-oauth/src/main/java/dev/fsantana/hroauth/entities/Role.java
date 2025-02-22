package dev.fsantana.hroauth.entities;

import java.io.Serializable;
import java.util.Objects;


public class Role implements Serializable {

    public static final long serialVersionUID = 1L;


    private Long id;
    private String roleName;

    public Role() {
    }

    public Role(Long id, String role) {
        this.id = id;
        this.roleName = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(roleName, role1.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(roleName);
    }
}
