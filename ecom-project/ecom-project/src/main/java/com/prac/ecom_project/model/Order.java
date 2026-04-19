package com.prac.ecom_project.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity

public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    private Double totalAmount;

    private String status;


    public Order(){

    }
    public Order(User user,Double totalAmount, String status){
        this.user=user;
        this.totalAmount=totalAmount;
        this.status=status;

    }
    public Long getId(){return id;}
    public User getUserId(){
        return user;
    }
    public double getTotalAmount(){
        return totalAmount;
    }
    public String getStatus(){
        return status;
    }
    public void setUserId(User userId){
        this.user=user;
    }
    public void setStatus(String status){

        this.status=status;
    }

    public void setTotalAmount(Double totalAmount){
        this.totalAmount=totalAmount;
    }
}

