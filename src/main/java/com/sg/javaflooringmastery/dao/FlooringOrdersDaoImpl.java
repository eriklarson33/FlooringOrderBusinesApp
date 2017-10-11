/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.javaflooringmastery.dao;

import com.sg.javaflooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class FlooringOrdersDaoImpl  {

    FlooringProductsDaoImpl productsDao;
    TaxesDaoImpl taxesDao;

    public FlooringOrdersDaoImpl(FlooringProductsDaoImpl productsDao, TaxesDaoImpl taxesDao) {
        this.productsDao = productsDao;
        this.taxesDao = taxesDao;
    }

    ArrayList<Order> currentOrders = new ArrayList<>();
    String inventoryFile;
    String DELIMITER = ":=:";

    
    public int getLastOrderId() {

        int listSize = currentOrders.size();
        if (listSize == 0) {
            return 0;
        } else {
            Order lastOrder = currentOrders.get(listSize - 1);
            String lastId = lastOrder.getOrderNumber();
            return Integer.parseInt(lastId);
        }
    }

    
    public ArrayList<Order> ordersByDate(String dateRequested) { // Used in DisplayOrders
        String filePath = ("data/Orders_" + dateRequested + ".txt");
        File x = new File(filePath);
        if (x.exists()) {
            (this.inventoryFile) = filePath;
            System.out.println(x.getName() + " exists!");
            try {
                loadOrders();
            } catch (FlooringProgramPersistenceException ex) {
                Logger.getLogger(FlooringOrdersDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("The file for the date " + dateRequested + " doesn't exist.");
        }

        return currentOrders;
    }

    
    public ArrayList returnTodaysOrder(String fileDate) {  // Used in Add Object
        String todaysFile = ("data/Orders_" + fileDate + ".txt");
        boolean writeFile = false;
        File cD = new File(todaysFile);
        (this.inventoryFile) = todaysFile;
        try {
            if (cD.createNewFile()) {

                System.out.println("File " + "data/Orders_" + fileDate + ".txt created.");
            } else {
                System.out.println("File " + "data/Orders_" + fileDate + ".txt could not be created.");
            }
        } catch (IOException ex) {
            Logger.getLogger(FlooringOrdersDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            loadOrders();
        } catch (FlooringProgramPersistenceException ex) {
            Logger.getLogger(FlooringOrdersDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return currentOrders;

    }

// Used in Add order
    
    public void writeOrder() throws FlooringProgramPersistenceException {
        writeRoster();
    }

    private void loadOrders() throws FlooringProgramPersistenceException {

        Scanner scanner;
        currentOrders.clear();

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(inventoryFile)));
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

            Order order = new Order();
            order.setOrderNumber(currentTokens[0]);
            order.setCustomerName(currentTokens[1]);
            order.setState(currentTokens[2]);
//            order.setStateAndTaxes(this.taxesDao.getStateTaxMap().get(currentTokens[2]));
            order.setTaxRate(new BigDecimal(currentTokens[3]));
//            order.setProductType(currentTokens[4]);
            order.setArea(new BigDecimal(currentTokens[5]));
//            order.setCostPerSqFt(new BigDecimal(currentTokens[6]));
//            order.setLaborCostPerSqFt(new BigDecimal(currentTokens[7]));
//            order.setMaterialCost(new BigDecimal(currentTokens[8]));
//            order.setLaborCost(new BigDecimal(currentTokens[9]));
//            order.setTax(new BigDecimal(currentTokens[10]));
//            order.setTotal(new BigDecimal(currentTokens[11]));

            // set the Product Object for reference in the Order object(order)
            order.setProduct(this.productsDao.getProductsMap(currentTokens[4]));
            order.calculate();
            // Add order Object to currentOrders ArrayList
            currentOrders.add(order);

        }
        // close scanner
        scanner.close();

//        HashMap <Date, Order> ordersByDate = new HashMap <>();
//        
//        ordersByDate.put(Date, order);
    }

//    /**
//     * Writes all items in the roster out to a INVENTORY_FILE. See loadOrders
//     * for file format.
//     *
//     * @throws DaoException if an error occurs writing to the file
//     */
    private void writeRoster() throws FlooringProgramPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(inventoryFile));
        } catch (IOException e) {
            throw new FlooringProgramPersistenceException(
                    "Could not save product data.", e);
        }

        for (Order order : currentOrders) {
            // write the Student object to the file
            out.println(order.getOrderNumber() + DELIMITER
                    + order.getCustomerName() + DELIMITER
                    + order.getState() + DELIMITER
                    + order.getTaxRate() + DELIMITER
                    + order.getProductType() + DELIMITER
                    + order.getArea() + DELIMITER
                    + order.getCostPerSqFt() + DELIMITER
                    + order.getLaborCostPerSqFt() + DELIMITER
                    + order.getMaterialCost() + DELIMITER
                    + order.getLaborCost() + DELIMITER
                    + order.getTax() + DELIMITER
                    + order.getTotal()
            );

        }
        // force PrintWriter to write line to the file
        out.flush();
        // Clean up
        out.close();
    }
    
    
    public void removeItem(Order order) {
        currentOrders.remove(order);
        
    }
}