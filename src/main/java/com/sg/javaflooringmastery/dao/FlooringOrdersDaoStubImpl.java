/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.javaflooringmastery.dao;

import com.sg.javaflooringmastery.dto.Order;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class FlooringOrdersDaoStubImpl {

    Order firstOrder;
    Order secondOrder;
//    Order thirdOrder;  //This one is not in the "Load" / HashMap
    ArrayList<Order> order1ArrayList = new ArrayList<>();
    ArrayList<Order> order2ArrayList = new ArrayList<>();
    Map<String, ArrayList<Order>> productOrders = new HashMap<>();

    public void initFlooringOrdersDaoStubImpl() {
        firstOrder = new Order();

        firstOrder.setOrderNumber("2");
        firstOrder.setCustomerName("Peter");
        firstOrder.setState("MI");
        firstOrder.setArea(new BigDecimal("100"));
        firstOrder.setProduct(this.productsDao.getProductsMap("Laminate"));
        order1ArrayList.add(firstOrder);

        productOrders.put("06012013", order1ArrayList);

        secondOrder = new Order();

        secondOrder.setOrderNumber("3");
        secondOrder.setCustomerName("Zippy");
        secondOrder.setState("OH");
        secondOrder.setArea(new BigDecimal("10"));
        secondOrder.setProduct(this.productsDao.getProductsMap("Tile"));
        order2ArrayList.add(secondOrder);

        productOrders.put("03022017", order2ArrayList);

//        thirdOrder = new Order();
//        thirdOrder.setCustomerName("Pam");
//        thirdOrder.setState("PA");
//        thirdOrder.setArea(new BigDecimal("1500"));
//        thirdOrder.setProductType("Wood");
    }

    FlooringProductsDaoImpl productsDao;
    TaxesDaoImpl taxesDao;

    public FlooringOrdersDaoStubImpl(FlooringProductsDaoImpl productsDao, TaxesDaoImpl taxesDao) {
        this.productsDao = productsDao;
        this.taxesDao = taxesDao;
    }

    public int getLastOrderId() {
        return 3;
    }

    public ArrayList<Order> ordersByDate(String dateRequested) {
        ArrayList currentOrder = productOrders.get(dateRequested);
        return currentOrder;
    }

    public ArrayList returnTodaysOrder(String fileDate) {
        ArrayList currentOrder = productOrders.get(fileDate);
        return currentOrder;
    }

    public void writeOrder() throws FlooringProgramPersistenceException {

    }

//    public void removeItem(Order order) {
////        currentOrders.remove(order);
////        String key = productOrders.containsValue(order);
//        for (Order order : productOrders.containsValue("06012013")) {
//            if (order.equals(productOrders.containsValue("06012013"))) {
//                System.out.println("We have found " + order);
//                productOrders.remove("06012013");
//            }
//        }
//    }
    public void removeItem(Order order) {
        productOrders.forEach((k, v) -> {
            if (order.equals(v)) {
                String mapKey = k;
                System.out.println("The Key of the ArrayList to be deleted is: " + k);
            }
        });
    }

}
