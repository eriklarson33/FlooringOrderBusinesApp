/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.javaflooringmastery.ui;

import com.sg.javaflooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class FlooringProgramView {

    UserIO io;

    public FlooringProgramView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("\n === MENU ===");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Save Current Work");
        io.print("6. Quit Program");

        return io.readInt("Please select from the above choices, numbers 1-6.", 1, 6);
    }

    public String askForDate() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Please provide the date of the the orders you would like to view in the following formate, 'MMddyyyy'.");
        String requestedDate = scan.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate fileDate = LocalDate.parse(requestedDate, formatter);
        DateTimeFormatter formatOut = DateTimeFormatter.ofPattern("MMddyyyy");
        return fileDate.format(formatOut);
    }

    public Order createAnOrder() {
        Order newOrder;
        newOrder = new Order();
//        String clientName = io.readString("Please provide the client's name.");
//        newOrder.setCustomerName(clientName);
        addCustomerName(newOrder);

//        String state = io.readString("Please name one of the following states, \"OH, PA, MI, IN\"");
//        newOrder.setState(state);
//        String state = addStateName(newOrder);
//        String product = io.readString("Please name one of the following products for this order: Carpet, Laminate, Tile, Wood.");
//        newOrder.setProductType(product);
//        String productType = addProductType(newOrder);
        int area = io.readInt("Please provide the area for the order, rounded up to the nearest Sq.Ft.");
        String a = Integer.toString(area);
        while (a == "") {
            System.out.println("Please provide the area for the order, rounded up to the nearest Sq.Ft.");
        }
        BigDecimal orderArea = new BigDecimal(a);
        newOrder.setArea(orderArea);

        return newOrder;
    }

    public void addCustomerName(Order newOrder) {
//        String clientName = io.readString("Please provide the client's name.");
//        while (clientName.matches("")) {
//            System.out.println("Please provide the client's name.");
//        }
        String clientName = "";

        boolean pass = true;
        while (pass = true) {
            clientName = io.readString("Please provide the client's name.");
            if (!clientName.matches("")) {
                newOrder.setCustomerName(clientName);
                pass = false;
                break;
            }
//            } else {
//                System.out.println("Please provide the client's name.");
//            }
        }
    }

    public String addStateName(Order newOrder) {
        String state = "";

        boolean pass = true;
        while (pass = true) {
            state = io.readString("Please name one of the following states, \"OH, PA, MI, IN\"");
            if (state.matches("")) {
//                System.out.println("\"Please name one of the following states, \"OH, PA, MI, IN\"");
                pass = true;

            } else if (!state.matches("")) {

                if (state.equalsIgnoreCase("OH")
                        || state.equalsIgnoreCase("MI")
                        || state.equalsIgnoreCase("PA")
                        || state.equalsIgnoreCase("IN")) {
                    pass = false;
                    break;
                } else {
                    pass = true;
                    System.out.println("Please Enter OH, MI, PA, IN.");
                }
            }

        }
        return state;
    }

    public String addProductType(Order newOrder) {
        String product = "";

        boolean pass = true;
        while (pass = true) {
            product = io.readString("Please name one of the following products for this order: Carpet, Laminate, Tile, Wood.");
            if (product.matches("")) {
                System.out.println("Please type one of the following: \n"
                        + "Laminate, Tile, Wood, or Carpet");
                pass = true;
//                break;
            }
            /*else*/ if (!product.matches("")) {

                if (product.equalsIgnoreCase("Laminate")
                        || product.equalsIgnoreCase("Tile")
                        || product.equalsIgnoreCase("Wood")
                        || product.equalsIgnoreCase("Carpet")) {
                    pass = false;
                    break;

                } else {
                    pass = true;
                    /*System.out.println("Please type one of the following: \n"
                            + "Laminate, Tile, Wood, or Carpet");
                }*/
                }
            }
        }
        return product;
    }

    public String addArea(Order newOrder) throws NumberFormatException {

        String temp = "";

        boolean pass = true;
        while (pass = true) {
            temp = io.readString("Enter the Area, rounded to nearet Sq. Ft. ");

            if (temp.matches("")) {
                pass = true;
                break;
            }

            if (!temp.matches("")) {

                try {
                    Integer.parseInt(temp);
                    pass = false;
                    break;
                } catch (NumberFormatException e) {
                    pass = true;
                    System.out.println("Please provide a whole number.");
                }
            }
        }
        return temp;
    }

    public void displayAllOrders(ArrayList<Order> dateOrders) {

        for (int i = 0; i < dateOrders.size(); i++) {
            Order x = dateOrders.get(i);
            System.out.print(dateOrders.get(i).getOrderNumber() + " "
                    + dateOrders.get(i).getCustomerName() + " "
                    + x.getState() + " "
                    + x.getTaxRate() + " "
                    + x.getProductType() + " "
                    + x.getArea() + " "
                    + x.getCostPerSqFt() + " "
                    + x.getLaborCostPerSqFt() + " "
                    + x.getMaterialCost() + " "
                    + x.getLaborCost() + " "
                    + x.getTax() + " "
                    + x.getTotal() + "\n");
        }
    }

    public int askForOrderId() {
        int orderNumber = io.readInt("Please provide the Order # for the order you would like to retrieve.");
        return orderNumber;
    }

    public void editOrderUI(Order editOrder) {
        Order o = editOrder;
        System.out.print("Your order is: \nOrder #: " + o.getOrderNumber()
                + " Customer: " + o.getCustomerName()
                + " State: " + o.getState()
                + " TaxRate: " + o.getTaxRate()
                + " Product: " + o.getProductType()
                + " Area: " + o.getArea() + " Sq.Ft."
                + " Cost/SqFt.: " + o.getCostPerSqFt()
                + " Labor Cost/SqFt.: " + o.getLaborCostPerSqFt()
                + " Material Cost: " + o.getMaterialCost()
                + " Labor Cost " + o.getLaborCost()
                + " Tax: " + o.getTax()
                + " Total: " + o.getTotal()
        );

        System.out.println("Please type the edit you would like to see for the order.  Hit enter to not change anything.");
    }

    public void editCustomerName(Order editOrder) {
        String temp;
        temp = io.readString("Enter Customer Name (" + editOrder.getCustomerName() + "):");
        if (!temp.matches("")) {
            editOrder.setCustomerName(temp);
        }

    }

    public String editStateName(Order editOrder) {
        String temp = "";

        boolean pass = true;
        while (pass = true) {
            temp = io.readString("Enter State: OH, MI, PA, or IN(" + editOrder.getState() + "):");
            if (temp.matches("")) {
                temp = editOrder.getState();
                pass = false;
                break;
            }

            if (!temp.matches("")) {

                if (temp.equalsIgnoreCase("OH")
                        || temp.equalsIgnoreCase("MI")
                        || temp.equalsIgnoreCase("PA")
                        || temp.equalsIgnoreCase("IN")) {
                    pass = false;
                    break;
                } else {
                    pass = true;
                    System.out.println("Please Enter OH, MI, PA, IN.");
                }
            }

        }
        return temp;
    }

    public String editProductType(Order editOrder) {
        String temp = "";

        boolean pass = true;
        while (pass = true) {
            temp = io.readString("Enter Product Type (" + editOrder.getProductType() + "):");
            if (temp.matches("")) {
                temp = editOrder.getProductType();
                pass = false;
                break;
            }

            if (!temp.matches("")) {

                if (temp.equalsIgnoreCase("Laminate")
                        || temp.equalsIgnoreCase("Tile")
                        || temp.equalsIgnoreCase("Wood")
                        || temp.equalsIgnoreCase("Carpet")) {
                    pass = false;
                    break;

                } else {
                    pass = true;
                    System.out.println("Please type one of the following: \n"
                            + "Laminate, Tile, Wood, or Carpet");
                }
            }
        }
        return temp;
    }

    public void editArea(Order editOrder) {
        String temp = "";
        boolean pass = true;
        while (pass = true) {
            temp = io.readString("Enter the Area, rounded to nearet Sq. Ft. (" + editOrder.getArea() + ")");

            if (temp.matches("")) {

                temp = (editOrder.getArea()).toString();
                pass = false;
                break;
            }

            if (!temp.matches("")) {

                try {
                    Integer.parseInt(temp);
                    pass = false;
                    break;
                } catch (NumberFormatException e) {
                    pass = true;
                    System.out.println("Please provide a whole number.");
                }
            }
        }
        editOrder.setArea(new BigDecimal(temp));
    }

    public void displayExitBanner() {
        io.print("=== EXIT PROGRAM ===");
    }

    public void displayUnknownCommand() {
        io.print("=== Unknown Command ===");
    }

    public void saveWorkReminder() {
        io.print("========================\n"
                + "Please Save Your Work.\n"
                + "========================");
    }

}
