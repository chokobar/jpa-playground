package org.learn.jpa_playground.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "`user`")
public class MemberDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uniqueKey")
    private int uniqueKey;

    @Column(name = "userId")
    private String userId;

    @Column(name = "userPassword")
    private String userPassword;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "userPhone")
    private String userPhone;

    @Column(name = "userRole")
    private String userRole;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "edit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editdDate;

    @Builder
    public MemberDomain(int uniqueKey, String userId, String userPassword, String userName, String userEmail,
                        String userPhone, String userRole, Date createdDate, Date editdDate) {
        this.uniqueKey = uniqueKey;
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userRole = userRole;
        this.createdDate = createdDate;
        this.editdDate = editdDate;
    }
}
