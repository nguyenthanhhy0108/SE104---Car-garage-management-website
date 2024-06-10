package com.example.se.model;

import com.example.se.model.dataDTO.BrandDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "BRANDS")
public class brands {

    @Id
    @Column(name = "BrandID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brandID;

    @Column(name = "BrandName")
    private String brandName;

    @OneToMany(mappedBy = "brands", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private List<cars> carsList;

    /**
     * Constructor
     * @param brandID: int
     * @param brandName: String
     */
    public brands(int brandID, String brandName) {
        this.brandID = brandID;
        this.brandName = brandName;
    }

    /**
     * Constructor
     */
    public brands() {
    }

    public BrandDTO toDTO() {
        BrandDTO dto = new BrandDTO();
        dto.setBrandName(this.getBrandName());
        return dto;
    }
}
