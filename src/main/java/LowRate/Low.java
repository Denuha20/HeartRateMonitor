/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LowRate;

import OpenCOM.*;

/**
 *
 * @author denuha
 */
public class Low extends OpenCOMComponent implements IUnknown, ILow, IMetaInterface, ILifeCycle {
    
    /** Creates a new instance
     * @param pRuntime */
    public Low(IUnknown pRuntime) {
        super(pRuntime);
    }

    public String MessageLow(int a) {
        if (a<40){
        return "Your heart rate is too low";
        }
        else{
        return "Your heart rate is low";
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
