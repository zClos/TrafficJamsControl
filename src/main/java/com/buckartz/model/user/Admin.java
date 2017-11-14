package com.buckartz.model.user;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "admin")
public class Admin extends User {

    @Override
    public String toString() {
        return "Admin{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
