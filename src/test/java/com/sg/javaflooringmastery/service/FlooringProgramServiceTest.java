package com.sg.javaflooringmastery.service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sg.javaflooringmastery.dao.FlooringOrdersDaoStubImpl;
import com.sg.javaflooringmastery.dao.FlooringProductsDaoImpl;
import com.sg.javaflooringmastery.dao.TaxesDaoImpl;
import com.sg.javaflooringmastery.dto.FlooringProduct;
import com.sg.javaflooringmastery.dto.Order;
import com.sg.javaflooringmastery.dto.StateTax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class FlooringProgramServiceTest {
    
    public FlooringProgramService service;
    FlooringOrdersDaoStubImpl ordersDao;
    TaxesDaoImpl taxesDao;
    FlooringProductsDaoImpl productsDao;
    Order order;
    FlooringProduct floorProduct;
    StateTax stateTax;
    
    public FlooringProgramServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.order = new Order();
        this.floorProduct = new FlooringProduct();
        this.stateTax = new StateTax();
        this.productsDao = new FlooringProductsDaoImpl(floorProduct);
        this.taxesDao = new TaxesDaoImpl(stateTax);
        this.ordersDao = new FlooringOrdersDaoStubImpl(productsDao, taxesDao);
        ordersDao.initFlooringOrdersDaoStubImpl();
        productsDao.loadProductSelection();
        taxesDao.loadStateTaxes();
        //NOTE: Dates are 06012013 and 03022017
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getOrdersByDate method, of class FlooringProgramService.
     */
    @Test
    public void testGetOrdersByDate() {
        ArrayList dateOrders = ordersDao.ordersByDate("03022017");
        assertNotNull(dateOrders);
        assertTrue(dateOrders.size() == 1);
        
    }

    /**
     * Test of createTodaysDate method, of class FlooringProgramService.
     */
    @Test
    public void testCreateTodaysDate() {
    }

    /**
     * Test of retrieveTodaysOrders method, of class FlooringProgramService.
     */
    @Test
    public void testRetrieveTodaysOrders() {
    }

    /**
     * Test of initializeProductAndStateDao method, of class FlooringProgramService.
     */
    @Test
    public void testInitializeProductAndStateDao() {
    }


    /**
     * Test of populateOrder method, of class FlooringProgramService.
     */
    @Test
    public void testPopulateOrder() {
        productsDao.loadProductSelection();
        taxesDao.loadStateTaxes();
        
        Order thirdOrder = new Order();
        thirdOrder.setCustomerName("Pam");
        String state = "PA";
        thirdOrder.setArea(new BigDecimal("1500"));
        String productType = "Wood";
        
                
        thirdOrder.setStateTax(taxesDao.getStateTaxMap(state));
        thirdOrder.setProduct(productsDao.getProductsMap(productType));
        thirdOrder.calculate();
        
        assertEquals("Wood", thirdOrder.getProductType());
        assertEquals(new BigDecimal("5.15"), thirdOrder.getCostPerSqFt());
        assertEquals(new BigDecimal("4.75"), thirdOrder.getLaborCostPerSqFt());
        assertEquals(new BigDecimal("6.75"), thirdOrder.getTaxRate());
        assertEquals(new BigDecimal("7725.00"), thirdOrder.getMaterialCost());
        assertEquals(new BigDecimal("7125.00"), thirdOrder.getLaborCost());
        assertEquals(new BigDecimal("1002.375"), (thirdOrder.getTax()).setScale(3));
        assertEquals(new BigDecimal("15852.375"), (thirdOrder.getTotal()).setScale(3));
        
    }
    
    @Test
    public void updateEditOrder() { // Used in Add Order
        Order thirdOrder = new Order();
        thirdOrder.setCustomerName("Pam");
        String state = "PA";
        thirdOrder.setArea(new BigDecimal("1500"));
        String productType = "Wood";

        StateTax stateTax = taxesDao.getStateTaxMap(state);
        thirdOrder.setStateTax(stateTax);
        FlooringProduct product = productsDao.getProductsMap(productType);
        thirdOrder.setProduct(product); 
        thirdOrder.calculate();
        
        assertEquals("Wood", thirdOrder.getProductType());
        assertEquals(new BigDecimal("5.15"), thirdOrder.getCostPerSqFt());
        assertEquals(new BigDecimal("4.75"), thirdOrder.getLaborCostPerSqFt());
        assertEquals(new BigDecimal("6.75"), thirdOrder.getTaxRate());
        assertEquals(new BigDecimal("7725.00"), thirdOrder.getMaterialCost());
        assertEquals(new BigDecimal("7125.00"), thirdOrder.getLaborCost());
        assertEquals(new BigDecimal("1002.375"), (thirdOrder.getTax()).setScale(3));
        assertEquals(new BigDecimal("15852.375"), (thirdOrder.getTotal()).setScale(3));
    }

    /**
     * Test of writeOrder method, of class FlooringProgramService.
     */
    @Test
    public void testWriteOrder() throws Exception {
    }

    /**
     * Test of writeOrderPassThru method, of class FlooringProgramService.
     */
    @Test
    public void testWriteOrderPassThru() {
    }

    /**
     * Test of generateOrderNumber method, of class FlooringProgramService.
     */
    @Test
    public void testGenerateOrderNumber() throws Exception {
        String todaysDate = "03022017";
        ordersDao.ordersByDate(todaysDate);
        int counter = ordersDao.getLastOrderId();

        int orderId = ++counter;
        String orderNum = Integer.toString(orderId);
        assertEquals("4", orderNum);
    }

    /**
     * Test of deleteOrder method, of class FlooringProgramService.
     */
    @Test
    public void testDeleteOrder() {
        ArrayList<Order> dateOrders = ordersDao.ordersByDate("06012013");
        int orderId = 2;
         
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
    
    @Test
    public void findOrderFromArrayList() {
        
        ArrayList<Order> dateOrders = ordersDao.ordersByDate("06012013");
        int orderId = 2;
        
        int zero = 0;
        String orderNum = Integer.toString(orderId);
        for(Order o : dateOrders) {
            if (o.getOrderNumber().equalsIgnoreCase(orderNum)) {
                break;
            }
            zero++;
        }
        Order testOrder = dateOrders.get(zero); 
        
        assertEquals("Laminate", testOrder.getProductType());
        assertEquals("2", testOrder.getOrderNumber());
        assertEquals("Peter", testOrder.getCustomerName());
        assertEquals("MI", testOrder.getState());
        assertEquals(new BigDecimal("100"), testOrder.getArea());
        
    }
    
    @Test
    public void testGetProductsMap() {
        FlooringProduct p = productsDao.getProductsMap("Wood");
        assertEquals(new BigDecimal("5.15"), p.getCostPerSqFt());
        assertEquals(new BigDecimal ("4.75"), p.getLaborCostPerSqFt());
    }
    
}
