/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.javaflooringmastery.dao;

import com.sg.javaflooringmastery.dto.FlooringProduct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class FlooringProductsDaoImpl {
    
    public FlooringProductsDaoImpl (FlooringProduct products) {
        this.product = products;
    }
    
    public static final String INVENTORY_FILE = "data/Products.txt";
    public static final String DELIMITER = ":=:";
    
    
    private FlooringProduct product;
    
//    public FlooringProductsDaoImpl (FlooringProduct products) {
//        this.products = products;
//    }
    
    private Map<String, FlooringProduct> flooringProducts = new HashMap<>();
    
    // Returns FlooringProduct object of Key: productType.
    public FlooringProduct getProductsMap(String productType) {
        try {
            loadRoster();
        } catch (Exception ex) {
            Logger.getLogger(FlooringProductsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flooringProducts.get(productType);
    }
    
    public void loadProductSelection() {
        try {
            loadRoster();
        } catch (Exception ex) {
            Logger.getLogger(FlooringProductsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setProductObject(String productType) {
        FlooringProduct product = flooringProducts.get(productType);
    }
    
    
    
   
        
    private void loadRoster() throws Exception {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringProgramPersistenceException(
                    "-_- Could not load inventory data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            
//            flooringProducts.put(currentTokens[0], new BigDecimal (currentTokens[1]));
            FlooringProduct currentItem = new FlooringProduct((currentTokens[0]),
            new BigDecimal (currentTokens[1]), new BigDecimal(currentTokens[2]));
//            currentItem.setProductType(currentTokens[0]);
//            currentItem.setCostPerSqFt(new BigDecimal (currentTokens[1]));
//            currentItem.setLaborCostPerSqFt(new BigDecimal(currentTokens[2]));
            
//            Put currentItem into the map using upcCode as the key
            flooringProducts.put(currentItem.getProductType(), currentItem);
        }
        // close scanner
        scanner.close();
    }
    
    
    
}