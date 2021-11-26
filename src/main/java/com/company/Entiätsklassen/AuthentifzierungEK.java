package com.company.Entiätsklassen;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "AUTHENTIFZIERUNG", schema = "PUBLIC", catalog = "TEST")
public class AuthentifzierungEK {
    private String username;
    private String password;
    private String role;
    private int id;

    @Basic
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "ROLE")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthentifzierungEK that = (AuthentifzierungEK) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(role, that.role) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role, id);
    }
}
