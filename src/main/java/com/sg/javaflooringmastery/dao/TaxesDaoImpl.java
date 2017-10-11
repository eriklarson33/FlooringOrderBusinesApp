/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.javaflooringmastery.dao;

import com.sg.javaflooringmastery.dto.StateTax;
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
public class TaxesDaoImpl {
    
    public static final String INVENTORY_FILE = "data/Taxes.txt";
    public static final String DELIMITER = ":=:";
    
    private StateTax stateTax;
    
    public StateTax getStateTaxMap(String state) {
        return stateTaxes.get(state);
    }
    
    public TaxesDaoImpl (StateTax stateTax) {
        this.stateTax = stateTax;
    }
    
    
    public void loadStateTaxes() {
        try {
            loadRoster();
        } catch (FlooringProgramPersistenceException ex) {
            Logger.getLogger(TaxesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public final Map<String, StateTax> stateTaxes = new HashMap<>();
        
    private void loadRoster() throws FlooringProgramPersistenceException {
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
            
            StateTax stateTax = new StateTax((currentTokens[0]),
            new BigDecimal (currentTokens[1]));
            
            stateTaxes.put(stateTax.getState(), stateTax);
//            Product currentItem = new Product(Integer.parseInt(currentTokens[0]));
//
//            currentItem.setName(currentTokens[1]);
//            currentItem.setBdPrice(new BigDecimal(currentTokens[2]));
//            currentItem.setQuantity(Integer.parseInt(currentTokens[3]));

            // Put currentItem into the map using upcCode as the key
//            stateTaxes.put(currentItem.getUpcCode(), currentItem);
        }
        // close scanner
        scanner.close();
    }
    
//    public BigDecimal getTax(String state) {
//        BigDecimal tax = stateTaxes.get(state);
//        return tax;
//    }
    
    
    
}
