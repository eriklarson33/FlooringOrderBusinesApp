/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.javaflooringmastery.controller;

import com.sg.javaflooringmastery.dao.FlooringProgramPersistenceException;
import com.sg.javaflooringmastery.dto.Order;
import com.sg.javaflooringmastery.service.FlooringProgramService;
import com.sg.javaflooringmastery.ui.FlooringProgramView;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class FlooringProgramController {

    FlooringProgramView view;
    FlooringProgramService service;

    public FlooringProgramController(FlooringProgramService service, FlooringProgramView view) {
        this.service = service;
        this.view = view;
    }

    public void run() throws FlooringProgramPersistenceException {
        service.initializeProductAndStateDao();
        runMenu();
    }

    public void runMenu() throws FlooringProgramPersistenceException {
        boolean keepGoing = true;
        int menuSelection = 0;

        while (keepGoing) {

            menuSelection = view.printMenuAndGetSelection();

            switch (menuSelection) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    saveCurrentWork();
                    break;
                case 6:
                    keepGoing = false;
                    return;
                default:
                    unknownCommand();
            }
        }
        exitMessage();
    }

    private void displayOrders() {
        String dateRequested = "";
        try {
        dateRequested = view.askForDate();
        } catch (DateTimeParseException e) {
            System.out.println("Please enter a valid date in the format provided.");
            displayOrders();
        }
        ArrayList<Order> dateOrders = service.getOrdersByDate(dateRequested);
        view.displayAllOrders(dateOrders);
    }

    private void addOrder() {
        service.retrieveTodaysOrders();
        String date = service.createTodaysDate();
        Order addOrder = view.createAnOrder();
        String state = view.addStateName(addOrder);
        String productType = view.addProductType(addOrder);
        service.populateOrder(addOrder, date, state, productType);
        
        try {
            service.addOrder(addOrder, date);
            
//        try {
//            service.writeOrder(addOrder, date);
//        } catch (FlooringProgramPersistenceException ex) {
//            Logger.getLogger(FlooringProgramController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        } catch (FlooringProgramPersistenceException ex) {
            Logger.getLogger(FlooringProgramController.class.getName()).log(Level.SEVERE, null, ex);
        }
        view.saveWorkReminder();
    }

    private void editOrder() throws FlooringProgramPersistenceException {
        String date = "";
        try {
        date = view.askForDate();
        } catch (DateTimeParseException e) {
            System.out.println("Please enter a valid date in the format provided.");
            editOrder();
        }
        int orderId = view.askForOrderId();
        ArrayList<Order> dateOrders = service.getOrdersByDate(date);
        Order editThis = service.findOrderFromArrayList(dateOrders, orderId);
        view.editOrderUI(editThis);
        view.editCustomerName(editThis);
        String state = view.editStateName(editThis);
        String productType = view.editProductType(editThis);
        view.editArea(editThis);
        service.updateEditOrder(editThis, productType, state);
//        service.writeOrderPassThru();
        view.saveWorkReminder();
        
    }
    
    public void removeOrder() {
        String date = view.askForDate();
        int orderId = view.askForOrderId();
        ArrayList<Order> dateOrders = service.getOrdersByDate(date);
        service.deleteOrder(dateOrders, orderId);
        view.saveWorkReminder();
    }
    
    public void saveCurrentWork() {
        service.writeOrderPassThru();
    }

    private int getMenuAndSelection() {
        return view.printMenuAndGetSelection();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommand();
    }

}