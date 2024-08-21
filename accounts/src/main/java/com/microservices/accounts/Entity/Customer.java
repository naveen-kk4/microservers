package com.microservices.accounts.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   //here in both the cases the @Column annotation is not mandatory as the filed name in the DB is matching with the column name , but still
    //putting here for future reference (norm is _ should be replaced by caps eg->mobile_number in DB = mobileNumber in the class)
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    private String name;

    private String email;
    @Column(name = "mobile_number")
    private String mobileNumber;
}
