package com.prac.ecom_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(name = "first_name", length = 50)
    private String firstname;

    @Column(name = "last_name", length = 50)
    private String lastname;

    @Column(length = 50)
    private String role;

    //  Constructors
    public User() {}

    public User(Long id, String username, String email, String password,
                String firstname, String lastname, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
    }

    //  Getters
    public Long getId()          { return id; }
    public String getUsername()  { return username; }
    public String getEmail()     { return email; }
    public String getPassword()  { return password; }
    public String getFirstname() { return firstname; }
    public String getLastname()  { return lastname; }
    public String getRole()      { return role; }

    // Setters
    public void setId(Long id)              { this.id = id; }
    public void setUsername(String username){ this.username = username; }
    public void setEmail(String email)      { this.email = email; }
    public void setPassword(String password){ this.password = password; }
    public void setFirstname(String firstname){ this.firstname = firstname; }
    public void setLastname(String lastname){ this.lastname = lastname; }
    public void setRole(String role)        { this.role = role; }
}