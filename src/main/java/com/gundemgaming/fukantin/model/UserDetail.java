package com.gundemgaming.fukantin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String department;

    private String instagram;

    private String biography;

    @OneToOne(mappedBy = "userDetail", fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

}
