package com.buckartz.model.user;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

    protected Integer userId;
    protected String name;
    protected String login;
    protected String pass;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "login", nullable = false, unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "pass", nullable = false)
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getUserId() != null ? !getUserId().equals(user.getUserId()) : user.getUserId() != null) return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) return false;
        return getPass() != null ? getPass().equals(user.getPass()) : user.getPass() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getPass() != null ? getPass().hashCode() : 0);
        return result;
    }
}
//@Entity
