/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.javaflooringmastery.dao;

import java.io.FileNotFoundException;

/**
 *
 * @author apprentice
 */
public class FlooringProgramPersistenceException extends Exception{

    public FlooringProgramPersistenceException(String message) {
        super(message);
    }
    
    public FlooringProgramPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
