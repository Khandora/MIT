package com.khandora.it.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created")
    @JsonIgnore
    private Date created;

    @LastModifiedDate
    @Column(name = "last_updated")
    @JsonIgnore
    private Date lastUpdated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
