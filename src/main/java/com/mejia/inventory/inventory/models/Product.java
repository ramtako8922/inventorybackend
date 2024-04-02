package com.mejia.inventory.inventory.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;

import java.io.Serializable;
@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final Long SerialVersionUID= -43676776578905L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int price;
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;
    @Column(name = "picture",length = 1000)
    private byte[] picture;
}
