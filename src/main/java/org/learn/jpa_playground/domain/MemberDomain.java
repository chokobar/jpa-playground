package org.learn.jpa_playground.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class MemberDomain {

    private String id;

    private String name;

    private int age;

    private String email;

    private String phone;

    /*Java의 날짜 타입*/
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
}
