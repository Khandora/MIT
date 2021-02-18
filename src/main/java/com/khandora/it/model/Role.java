package com.khandora.it.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> userList;

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", userList=" + userList +
                '}';
    }
}
