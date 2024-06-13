package com.example.se.model;

import com.example.se.model.dataDTO.PartDTO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "PARTS")
public class parts {

    @Id
    @Column(name = "PartID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partID;

    @Column(name = "PartName")
    private String name;

    @Column(name = "PartPrice")
    private double price;

    @Column(name = "Before")
    private int before;

    @Column(name = "Used")
    private int used;

    public parts() {
    }

    public parts(int partID, String name, double price, int before, int used) {
        this.partID = partID;
        this.name = name;
        this.price = price;
        this.before = before;
        this.used = used;
    }

    public PartDTO toDTO() {
        PartDTO partDTO = new PartDTO();
        partDTO.setPartName(this.name);
        partDTO.setPrice(this.price);

        return partDTO;
    }
}
