package com.prac.ecom_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length=50)
    private String username;

    @Column(unique = true,nullable = false,length=100)
    private String email;

    @Column( nullable= false, length = 50)
    private String password;

    @Column( name= "first_name", length = 50)
    private String firstNamme;

    @Column( name= "last_name", length = 50)
    private String lastName;

    @Column  (length =50)
    private String role;
}
