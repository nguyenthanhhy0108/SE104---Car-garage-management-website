package com.example.se.model;

import com.example.se.model.dataDTO.ServiceDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "SERVICES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class services {

    @Id
    @Column(name = "ServiceID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int servicesID;

    @Column(name = "ServiceName")
    private String serviceName;

    @Column(name = "ServiceCost")
    private double serviceCost;

    /**
     * Convert to DTO
     * @return
     * DTO object
     */
    public ServiceDTO toDTO() {
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setServiceCost(this.serviceCost);
        serviceDTO.setServiceName(this.serviceName);

        return serviceDTO;
    }
}
