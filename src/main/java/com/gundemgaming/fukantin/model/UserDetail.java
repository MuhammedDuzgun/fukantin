package com.gundemgaming.fukantin.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_details")
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department")
    private String department;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "biography")
    private String biography;

    @OneToOne(mappedBy = "userDetail", fetch = FetchType.LAZY)
    private User user;

}
