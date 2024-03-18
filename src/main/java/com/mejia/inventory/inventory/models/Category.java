package com.mejia.inventory.inventory.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Data
@Entity
@Table(name = "category")
public class Category implements Serializable
{
private static final  long serializableUID=-45566777890567l;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String name;
private String description;
}
