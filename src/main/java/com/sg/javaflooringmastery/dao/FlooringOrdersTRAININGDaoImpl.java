/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.javaflooringmastery.dao;

/**
 *
 * @author apprentice
 */


public class FlooringOrdersTRAININGDaoImpl extends FlooringOrdersDaoImpl{
    
    public FlooringOrdersTRAININGDaoImpl(FlooringProductsDaoImpl productsDao, TaxesDaoImpl taxesDao) {
        super(productsDao, taxesDao);
    }
    
    private void writeRoster() throws FlooringProgramPersistenceException {
        // Do Not Write Anything for Training
    }
    
}
