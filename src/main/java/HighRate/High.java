/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HighRate;

import OpenCOM.*;

/**
 *
 * @author denuha
 */
public class High extends OpenCOMComponent implements IUnknown, IHigh, IMetaInterface, ILifeCycle {
    
    /** Creates a new instance */
    public High(IUnknown pRuntime) {
        super(pRuntime);
    }

    public String MessageHigh(int a) {
        if(a>120){
            return "Your heart rate is too high";
        }
        else{
        return "Your heart rate is  high";
        }
    }
     
    // ILifeCycle Interface
    public boolean startup(Object pIOCM) {
        return true;
    }
    
    public boolean shutdown() {
        return true;
    }
}
