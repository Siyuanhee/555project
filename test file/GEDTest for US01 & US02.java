/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gedcomreader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chenshuaishuai
 */
public class GEDTest {
    
    public GEDTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testDatesBeforeCurrentDate () throws IOException, FileNotFoundException, ParseException {
        System.out.println("Testing US01");
        GED instance = new GED();
        instance.traversal();
        instance.errorsPrint();
        
        if (instance.errors.size() != 3) {
            fail("the exception number is wrong");
        }
        else {
            System.out.println("the number of errors is correct");
        }
        
        if (!instance.errors.contains("Error US01: Birthday of John /Brown/(I1) occurrs after the current date")) {
            fail("the first exception information is wrong");
        }
        else {
            System.out.println("set of errors contains the first error");
        }
        
        if (!instance.errors.contains("Error US01: Death day of Jennifer /Brown/(I3) occurrs after the current date")) {
            fail("the second exception information is wrong");
        }
        else {
            System.out.println("set of errors contains the second error");
        }
        
        System.out.println("test of US01 passed");
    }
    
    @Test
    public void testBirthBeforeMarriage () throws IOException, FileNotFoundException, ParseException {
        System.out.println("Testing US02");
        GED instance = new GED();
        instance.traversal();
        instance.errorsPrint();
        
        if (instance.errors.size() != 3) {
            fail("the exception number is wrong");
        }
        else {
            System.out.println("the number of errors is correct");
        }
        
        if (!instance.errors.contains("Error US02: Marriaged date of John /Brown/(I1) in the family of F1 is before the birthday")) {
            fail("the first exception information is wrong");
        }
        else {
            System.out.println("set of errors contains the expected error");
        }
        
        System.out.println("test of US02 passed");
    }
    
}

