package org.learn.jpa_playground.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name="member")
public class MemberDomain {

    @Id
    private String id;
    private String password;
    private String name;
    private int age;
    private String email;
    private String phone;

    /*Java의 날짜 타입*/
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Builder
    public MemberDomain(String id, String password, String name, int age, String email, String phone, Date createdDate) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.createdDate = createdDate;
    }

}
