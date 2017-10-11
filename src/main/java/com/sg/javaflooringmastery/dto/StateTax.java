/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.javaflooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class StateTax {
    
    private String state;
    private BigDecimal taxRate;
    
    public StateTax (String state, BigDecimal taxRate) {
        this.state = state;
        this.taxRate = taxRate;
        
    }

    public StateTax() {
        
    }
    
    

    public String getState() {
        return state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }
    
    
    
}
