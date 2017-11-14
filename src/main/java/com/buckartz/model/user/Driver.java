package com.buckartz.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "driver")
public class Driver extends User {

    @Override
    public String toString() {
        return "Driver{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
