/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.javaflooringmastery;

import com.sg.javaflooringmastery.controller.FlooringProgramController;
import com.sg.javaflooringmastery.dao.FlooringProgramPersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import static javax.management.Query.value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class App {

    public static void main(String[] args) throws FlooringProgramPersistenceException {

//        UserIO myIO = new UserIOConsoleImpl();
//        Order order = new Order();
//        FlooringProduct product = new FlooringProduct();
//        
//        FlooringProgramView view = new FlooringProgramView(myIO, order);
//        FlooringProductsDaoImpl productsDao = new FlooringProductsDaoImpl(product);
//        FlooringOrdersDaoImpl ordersDao = new FlooringOrdersDaoImpl(productsDao);
//        TaxesDaoImpl taxes = new TaxesDaoImpl();
//        FlooringProgramService service = new FlooringProgramService(
//                ordersDao,taxes, productsDao, order, product);
//        FlooringProgramController controller = new FlooringProgramController(
//        service, view);
//        
//        controller.run();
//        
//        try {
//            File file = new File("data/ProductionVsTest.txt");
//            FileInputStream fileInput = new FileInputStream(file);
//            Properties properties = new Properties();
//            properties.loadFromXML(fileInput);
//            fileInput.close();
//
//            Enumeration enuKeys = properties.keys();
//            while (enuKeys.hasMoreElements()) {
//                String key = (String) enuKeys.nextElement();
//                String value = properties.getProperty(key);
//                System.out.println(key + ": " + value);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
/* Try 2!*/
//        String path = "/home/apprentice/Desktop/BitBucket/erik-larson-individual-work/com.sg_OriginalFlooringMastery/data/ProductionVsTest";
//        String choice = "";
//
//        try {
//            choice = new String(Files.readAllBytes(Paths.get(path)));
//        } catch (IOException ex) {
//            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        if (choice.equalsIgnoreCase("Production")) {
//            ApplicationContext ctx
//                    = new ClassPathXmlApplicationContext("applicationContext.xml");
//            FlooringProgramController controller
//                    = ctx.getBean("controller", FlooringProgramController.class);
//            controller.run();
//
//        } else {
//            ApplicationContext ctx
//                    = new ClassPathXmlApplicationContext("applicationTestContext.xml");
//
//            FlooringProgramController controller
//                    = ctx.getBean("controller", FlooringProgramController.class);
//            controller.run();
//        }

//        Scanner scan;
////        FileReader fr;
////        try {
////        fr = new FileReader("/home/apprentice/Desktop/BitBucket/erik-larson-individual-work/com.sg_OriginalFlooringMastery/data/ProductionVsTest.txt");
////         } catch (FileNotFoundException e) {
////            System.out.println("Could not read the ProductionVsTest File.");
////        }
////        
////        scan = new Scanner(new BufferedReader(fr));
//        try {
//        scan = new Scanner(new BufferedReader(new FileReader("/data/ProductionVsTest.txt")));
////        } catch (FileNotFoundException e) {
////            System.out.println("Could not read the ProductionVsTest File.");
////        }
//        
//        while (scan.hasNextLine()) {
//            String currentLine = scan.nextLine();
//            System.out.println(currentLine);
            
            
            
            try {
//            File file = new File("data/config.properties");
//            FileInputStream fileInput = new FileInputStream(file);
//            Properties properties = new Properties();
//            properties.loadFromXML(fileInput);
//            fileInput.close();
//
//            Enumeration enuKeys = properties.keys();
//            String key;
//            String value="";
//            while (enuKeys.hasMoreElements()) {
//                key = (String) enuKeys.nextElement();
//                value = properties.getProperty(key);
//                System.out.println(key + ": " + value);
//            }
        
            Scanner scan = new Scanner(new BufferedReader(new FileReader("data/ProductionVsTraining.txt")));
            String mode = scan.nextLine();
            if (mode.equalsIgnoreCase("Production")) {
                ApplicationContext ctx
                        = new ClassPathXmlApplicationContext("applicationContext.xml");
                FlooringProgramController controller
                        = ctx.getBean("controller", FlooringProgramController.class);
                controller.run();
            } else {

            ApplicationContext ctx
                    = new ClassPathXmlApplicationContext("applicationTestContext.xml");
            FlooringProgramController controller
                    = ctx.getBean("controller", FlooringProgramController.class);
            controller.run();
            }
        
//        } catch (FileNotFoundException e) {
//            System.out.println("Could not read the ProductionVsTest File.");
//        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
