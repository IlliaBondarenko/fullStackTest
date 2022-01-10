package com.palitronica.store.data.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotNull
    @Column(name = "customerName", nullable = false)
    private String customerName;

    @NotNull
    @Column(name = "customerStreet", nullable = false)
    private String customerStreet;

    @NotNull
    @Column(name = "customerCity", nullable = false)
    private String customerCity;

    @NotNull
    @Column(name = "customerProvince", nullable = false)
    private String customerProvince;

    @NotNull
    @Column(name = "customerCounty", nullable = false)
    private String customerCounty;

    @NotNull
    @Column(name = "customerZIP", nullable = false)
    private String customerZIP;


    public void setcustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getcustomerId() {
        return customerId;
    }

    public void setcustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getcustomerName() {
        return customerName;
    }

    public void setcustomerStreet(String customerStreet) {
        this.customerStreet = customerStreet;
    }

    public String getcustomerStreet() {
        return customerStreet;
    }

    public void setcustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getcustomerCity() {
        return customerCity;
    }

    public void setcustomerProvince(String customerProvince) {
        this.customerProvince = customerProvince;
    }

    public String getcustomerProvince() {
        return customerProvince;
    }

    public void setcustomerCounty(String customerCounty) {
        this.customerCounty = customerCounty;
    }

    public String getcustomerCounty() {
        return customerCounty;
    }


    public void setcustomerZIP(String customerZIP) {
        this.customerZIP = customerZIP;
    }

    public String getcustomerZIP() {
        return customerZIP;
    }

}
