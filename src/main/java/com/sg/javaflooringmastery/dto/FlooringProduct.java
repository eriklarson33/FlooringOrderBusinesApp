/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.javaflooringmastery.dto;

import com.sg.javaflooringmastery.dao.FlooringProductsDaoImpl;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class FlooringProduct {
    
    FlooringProductsDaoImpl products;
//    private Map<String, FlooringProduct> flooringProducts;
//    
    public FlooringProduct (FlooringProductsDaoImpl products) {
    this.products = products;
    }
    
    public FlooringProduct () {
       
    }
    
    private String productType;
    private BigDecimal costPerSqFt;
    private BigDecimal laborCostPerSqFt;

    public FlooringProduct(String productType, BigDecimal costPerSqFt, BigDecimal laborCostPerSqFt) {
        // add all 3 parameters to the constructor.
        this.productType = productType;
        this.costPerSqFt = costPerSqFt;
        this.laborCostPerSqFt = laborCostPerSqFt;
    }
    
    
    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }
    
    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public void setLaborCostPerSqFt(BigDecimal laborCostPerSqFt) {
        this.laborCostPerSqFt = laborCostPerSqFt;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
        FlooringProduct product = products.getProductsMap(productType);
        this.costPerSqFt = product.getCostPerSqFt();
        this.laborCostPerSqFt = product.getLaborCostPerSqFt();
        // set variables for laborcost/sqft & set cost/sqft
    }

    public BigDecimal getCostPerSqFt(String productType) {
        
        return costPerSqFt;
    }
    
    public BigDecimal getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }
    
}
