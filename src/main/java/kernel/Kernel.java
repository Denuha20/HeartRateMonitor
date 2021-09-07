/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kernel;

import OpenCOM.*;
import HighRate.IHigh;
import LowRate.ILow;
import NormalRate.INormal;
import Connector.IConnector;
import Database.Database;
//import java.io.File;

import java.util.*;
import java.io.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author denuha
 */

public class Kernel {
    
    public static void main(String[] args) {
        

        // Create the OpenCOM runtime & Get the IOpenCOM interface reference
        OpenCOM runtime = new OpenCOM();
        IOpenCOM pIOCM =  (IOpenCOM) runtime.QueryInterface("OpenCOM.IOpenCOM");

        // Create the High component
        IUnknown pAdderIUnk = (IUnknown) pIOCM.createInstance("HighRate.High", "High");
        ILifeCycle pILife =  (ILifeCycle) pAdderIUnk.QueryInterface("OpenCOM.ILifeCycle");
        pILife.startup(pIOCM);
        
        // Create the Low component
        IUnknown pSubIUnk = (IUnknown) pIOCM.createInstance("LowRate.Low", "Low");
        pILife =  (ILifeCycle) pSubIUnk.QueryInterface("OpenCOM.ILifeCycle");
        pILife.startup(pIOCM);
        
        // Create the Normal component
        IUnknown pNormIUnk = (IUnknown) pIOCM.createInstance("NormalRate.Normal", "Normal");
        pILife =  (ILifeCycle) pNormIUnk.QueryInterface("OpenCOM.ILifeCycle");
        pILife.startup(pIOCM);
        
        // Create the Connector component
        IUnknown pConIUnk = (IUnknown) pIOCM.createInstance("Connector.Connector", "Connector");
        pILife =  (ILifeCycle) pConIUnk.QueryInterface("OpenCOM.ILifeCycle");
        pILife.startup(pIOCM);
        
        //Record database
        // Create the Low component
        IUnknown pDataIUnk = (IUnknown) pIOCM.createInstance("Database.Database", "Database");
        pILife =  (ILifeCycle) pDataIUnk.QueryInterface("OpenCOM.ILifeCycle");
        pILife.startup(pIOCM);        
        
        // Get the Normal Interface
        //INormal pICalc =  (INormal) pCalcIUnk.QueryInterface("NormalRate.INormal");
        
        //Get connector interface
        IConnector pIRate =  (IConnector) pConIUnk.QueryInterface("Connector.IConnector");
        
        
        long ConnID1 = runtime.connect(pNormIUnk, pAdderIUnk, "HighRate.IHigh");
        long ConnID2 = runtime.connect(pNormIUnk, pSubIUnk, "LowRate.ILow");
        long ConnID3 = runtime.connect(pConIUnk, pNormIUnk, "NormalRate.INormal");
        
        // test low and high
        //System.out.println("180 bpm "+ pICalc.MessageHigh(180));
        //System.out.println("44 bpm "+ pICalc.MessageLow(44));
            
        System.out.println("What is your current heart rate?");
        
        
        try {
            File myObj = new File("/Users/denuha/NetBeansProjects/OpenComTest/src/main/java/kernel/HeartRateSensor.txt");
            Scanner scanner = new Scanner(myObj); 
            ArrayList<Integer> rate = new ArrayList<Integer>();
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    rate.add(scanner.nextInt());

                } 
                else {
                    scanner.next();
                }
            }       
            for (Integer i : rate) {
            //int rates = i;
            //Database record = new Database(rates);
            //pIRate.MessageLow(i);
            pIRate.RecordDatabase(i);
            //rates.getDatabase();
            if(i<60){
                System.out.println(i + "bpm: " + pIRate.MessageLow(i));        
            }
            else if(i>100){
                System.out.println(i + "bpm: " + pIRate.MessageHigh(i));        
            }
            
            else{
                System.out.println(i + "bpm: " + pIRate.MessageNormal(i));        
            }
        //System.out.println(i);
     }                
           // for (int i = 0; i < rate.size; i++) {
            //    System.out.println(rate[i]);
    //} 
    // System.out.println(rate);

      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

        


// Get the debug interface and dump component configuration to console output
        //IDebug pIDebug =  (IDebug) runtime.QueryInterface("OpenCOM.IDebug");
        //pIDebug.dump(); 
        
}
}
