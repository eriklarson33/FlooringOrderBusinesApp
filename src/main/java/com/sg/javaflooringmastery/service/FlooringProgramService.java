/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.javaflooringmastery.service;

import com.sg.javaflooringmastery.controller.FlooringProgramController;
import com.sg.javaflooringmastery.dao.FlooringOrdersDaoImpl;
import com.sg.javaflooringmastery.dao.FlooringProductsDaoImpl;
import com.sg.javaflooringmastery.dao.FlooringProgramPersistenceException;
import com.sg.javaflooringmastery.dao.TaxesDaoImpl;
import com.sg.javaflooringmastery.dto.FlooringProduct;
import com.sg.javaflooringmastery.dto.Order;
import com.sg.javaflooringmastery.dto.StateTax;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class FlooringProgramService {

    FlooringOrdersDaoImpl ordersDao;
    TaxesDaoImpl taxesDao;
    FlooringProductsDaoImpl productsDao;
    Order order;
    FlooringProduct floorProduct;
    FlooringProgramController controller;

    public FlooringProgramService(FlooringOrdersDaoImpl ordersDao,
            TaxesDaoImpl taxesDao,
            FlooringProductsDaoImpl productsDao,
            Order order, FlooringProduct floorProduct /*, FlooringProgramController controller*/) {
        this.ordersDao = ordersDao;
        this.taxesDao = taxesDao;
        this.productsDao = productsDao;
        this.order = order;
        this.floorProduct = floorProduct;
//        this.controller = controller;
    }

    public ArrayList<Order> getOrdersByDate(String dateRequested) {
        ArrayList<Order> dateOrders = ordersDao.ordersByDate(dateRequested);

        return dateOrders;
    }

    public String createTodaysDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatOut = DateTimeFormatter.ofPattern("MMddyyyy");
        return today.format(formatOut);
    }

    public void retrieveTodaysOrders() {
        String todaysDate = createTodaysDate();
        ordersDao.returnTodaysOrder(todaysDate);
    }

    public void initializeProductAndStateDao() {
        productsDao.loadProductSelection();
        taxesDao.loadStateTaxes();
    }

//    public void fixProductObject(String productType) {
//        productsDao.setProductObject(productType);
//    }
//    public Order extractOrderObject(ArrayList) {
//        Order editThis = dateOrders.get(orderId-1);
//        return editThis;
//    }
    public void populateOrder(Order newOrder, String date, String state, String productType) { // Used in Add Order

        try {
            String orderId = generateOrderNumber(date);
            newOrder.setOrderNumber(orderId);
        } catch (FlooringProgramPersistenceException ex) {
            Logger.getLogger(FlooringProgramService.class.getName()).log(Level.SEVERE, null, ex);
        }

//        Map<String, BigDecimal> stateTaxes = taxesDao.getStateTaxMap();
//        BigDecimal mapValue = new BigDecimal(stateTaxes.get(newOrder.getState()).toString());
//        newOrder.setTaxRate(mapValue);
        StateTax stateTax = taxesDao.getStateTaxMap(state);
        newOrder.setStateTax(stateTax);
        FlooringProduct product = productsDao.getProductsMap(productType);
        newOrder.setProduct(product);
        newOrder.calculate();
    }

    public void updateEditOrder(Order orderEdit, String productType, String state) { // Used in Add Order

//        Map<String, BigDecimal> stateTaxes = taxesDao.getStateTaxMap();
//        BigDecimal mapValue = new BigDecimal(stateTaxes.get(orderEdit.getState()).toString());
//        orderEdit.setTaxRate(mapValue);
//        if (state == "") {
//            state = orderEdit.getState();
//        } else if (productType == "") {
//            productType = orderEdit.getProductType();
//        }
        StateTax stateTax = taxesDao.getStateTaxMap(state);
        orderEdit.setStateTax(stateTax);
        FlooringProduct product = productsDao.getProductsMap(productType);
        orderEdit.setProduct(product);
        orderEdit.calculate();
    }

    public void addOrder(Order addOrder, String dateRequested) throws FlooringProgramPersistenceException {  //Used in Add Order
        ArrayList<Order> currentOrder = ordersDao.ordersByDate(dateRequested);
        currentOrder.add(addOrder);
    }

    public void writeOrderPassThru() {
        try {
            ordersDao.writeOrder();
        } catch (FlooringProgramPersistenceException ex) {
            Logger.getLogger(FlooringProgramService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String generateOrderNumber(String todaysDate) throws FlooringProgramPersistenceException {
        ordersDao.ordersByDate(todaysDate);
        int counter = ordersDao.getLastOrderId();

        int orderId = ++counter;
        String orderNum = Integer.toString(orderId);
        return orderNum;
    }

    public void deleteOrder(ArrayList<Order> dateOrders, int orderId) {
//        Order removeThis = dateOrders.remove(orderId - 1);
//        try {
//            ordersDao.writeOrder();
//        } catch (FlooringProgramPersistenceException ex) {
//            Logger.getLogger(FlooringProgramService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
        int zero = 0;
        try {
            String orderNum = Integer.toString(orderId);
            for (Order o : dateOrders) {
                if (o.getOrderNumber().equalsIgnoreCase(orderNum)) {
                    break;
                }
                zero++;
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
            System.out.println("Please provide a valide Order ID");
        }
        ordersDao.removeItem(dateOrders.get(zero));
    }

    public Order findOrderFromArrayList(ArrayList<Order> dateOrders, int orderId)
            throws FlooringProgramPersistenceException, IndexOutOfBoundsException {

        int zero = 0;
        try {
            String orderNum = Integer.toString(orderId);
            for (Order o : dateOrders) {
                if (o.getOrderNumber().equalsIgnoreCase(orderNum)) {
                    break;
                }
                zero++;
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
            System.out.println("Please provide a valide Order ID");
//            try {
//                controller.runMenu();
//            } catch (FlooringProgramPersistenceException ex) {
//                Logger.getLogger(FlooringProgramService.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        return dateOrders.get(zero);
    }

    public FlooringProduct getProductsMap(String productType) {
        FlooringProduct p = productsDao.getProductsMap(productType);
        return p;
    }

}
