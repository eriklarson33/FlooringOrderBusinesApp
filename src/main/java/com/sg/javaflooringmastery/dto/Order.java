/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.javaflooringmastery.dto;

import java.math.BigDecimal;
import static java.math.BigDecimal.ROUND_HALF_UP;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class Order {
 
    
    public Order () {
      
    }
    
//    private static Map<String, FlooringProduct> flooringProducts = new HashMap<>();
    
    
    // set by user
    private String customerName;
    private String state;
    private String productType;
    private BigDecimal area;
    // auto-updated based on user choices
    private BigDecimal taxRate;
    private BigDecimal costPerSqFt;
    private BigDecimal laborCostPerSqFt;
    // auto-calculated
    private String orderNumber;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;
    
   
    
    
//    private BigDecimal taxRate(state) {
//        //Use state to pull taxRate from array
//        return taxRate;
//    }
    
//    private void calculateProduct() {
//        this.costPerSqFt = flooringProducts.get(productType).getCostPerSqFt();
//        this.laborCostPerSqFt = flooringProducts.get(productType).getLaborCostPerSqFt();
//        
//    }
    public Order (String customerName, String state, String productType, BigDecimal area,
            String orderNumber, BigDecimal taxRate, BigDecimal materialCost, BigDecimal laborCost) {
        this.customerName = customerName;
        this.state = state;
        this.productType = productType;
        this.area = area;
        this.orderNumber = orderNumber;
        this.taxRate = taxRate;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        BigDecimal taxHundredths = (taxRate.multiply(new BigDecimal("100")));
        
        this.tax = (materialCost.add(laborCost)).multiply(taxHundredths);
        this.total = (materialCost.add(laborCost).add(tax));
    }
    
    public void calculate() {
        BigDecimal taxHundredths = (taxRate.divide(new BigDecimal("100")));
        
        this.tax = (materialCost.add(laborCost)).multiply(taxHundredths);
        this.total = (materialCost.add(laborCost).add(tax));
    }

   
    public void setProductType(String productType) {
        this.productType = productType;
       
    }
    

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
    
    public void setState(String state) {
        this.state = state;
    }
    
//    public Order(Order order) {
//        this.order = order;
//    }
    
//    private void materialCost() {
//        BigDecimal costOfMaterials = (this.area).multiply(this.costPerSqFt);
//        this.materialCost = costOfMaterials;
//    }

//    public void setTaxRate(BigDecimal taxRate) {
//        Map<String, BigDecimal> stateTaxes = taxesDao.getStateTaxMap();
//        BigDecimal mapValue = new BigDecimal(stateTaxes.get(this.state).toString());
//        this.taxRate = mapValue;
////        BigDecimal taxHundredths = (mapValue.multiply(new BigDecimal("100")));
//    }
    
    public void setProduct(FlooringProduct p) {
        this.productType = p.getProductType();
        this.costPerSqFt = p.getCostPerSqFt();
        this.laborCostPerSqFt = p.getLaborCostPerSqFt();
        
        // auto-calculate materials
        BigDecimal costOfMaterials = (this.area).multiply(this.costPerSqFt);
        this.materialCost = costOfMaterials;
        BigDecimal costOfLabor = (this.area).multiply(this.laborCostPerSqFt);
        this.laborCost = costOfLabor;
    }
    
    public void setStateTax (StateTax st) {
        this.state = st.getState();
        this.taxRate = st.getTaxRate();
    }

//    public void setCostPerSqFt(BigDecimal costPerSqFt) {
//        this.costPerSqFt = products.getCostPerSqFt();
//    }
//
//    public void setLaborCostPerSqFt(BigDecimal laborCostPerSqFt) {
//        this.laborCostPerSqFt = laborCostPerSqFt;
//    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

//    public void setMaterialCost(BigDecimal materialCost) {
//        this.materialCost = materialCost;
//    }
//
//    public void setLaborCost(BigDecimal laborCost) {
//        this.laborCost = laborCost;
//    }
//
//    public void setTax(BigDecimal tax) {
//        this.tax = tax;
//    }
//
//    public void setTotal(BigDecimal total) {
//        this.total = total;
//    }

    public BigDecimal getTotal() {
        return total;
    }
    
    public BigDecimal getTaxRate() {
        
        return taxRate;
    }

    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    public BigDecimal getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

//    public BigDecimal getTotalCost() {
//        return totalCost;
//    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }


    public String getProductType() {
        return productType;
    }

//    public void setProductType(String productType) {
//        this.productType = productType;
//        this.costPerSqFt = products.getCostPerSqFt();
//        this.laborCostPerSqFt = products.getLaborCostPerSqFt();
//        // auto-calculate materials
//        BigDecimal costOfMaterials = (this.area).multiply(this.costPerSqFt);
//        this.materialCost = costOfMaterials;
//        BigDecimal costOfLabor = (this.area).multiply(this.laborCostPerSqFt);
//        this.laborCost = costOfLabor;
//    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.customerName);
        hash = 17 * hash + Objects.hashCode(this.state);
        hash = 17 * hash + Objects.hashCode(this.productType);
        hash = 17 * hash + Objects.hashCode(this.area);
        hash = 17 * hash + Objects.hashCode(this.orderNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.orderNumber, other.orderNumber)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        return true;
    }
    
    
}
