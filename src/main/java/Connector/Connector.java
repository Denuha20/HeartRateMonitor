/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connector;

import NormalRate.INormal;

import OpenCOM.IConnections;
import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OCM_SingleReceptacle;
import OpenCOM.OpenCOMComponent;

/**
 *
 * @author denuha
 */
public class Connector extends OpenCOMComponent implements IConnector, IConnections, ILifeCycle, IUnknown, IMetaInterface {
    
    
    //normal interface
    public OCM_SingleReceptacle<INormal> m_PSR_INormal;


    /** Creates a new instance 
     * @param binder */
    public Connector(IUnknown binder) {
        super(binder);
        
        // Initiate the receptacles
        m_PSR_INormal = new OCM_SingleReceptacle<INormal>(INormal.class);

    }
    

    
    public String MessageHigh(int a) {
        /*
         * Standard single receptacle invocation to retrieve the functionality required.
         */
        return m_PSR_INormal.m_pIntf.MessageHigh(a);
    }
    
    public String MessageLow(int a) {
        /*
         * Standard single receptacle invocation to retrieve the functionality required.
         */
        return m_PSR_INormal.m_pIntf.MessageLow(a);
    }
    
    public String MessageNormal(int a) {
        /*
         * Standard single receptacle invocation to retrieve the functionality required.
         */
        return m_PSR_INormal.m_pIntf.MessageNormal(a);
        //return "lol";
    }    
    /**
     * Concatenate a display message to the passed message.
     * @param message The message to attach to.
     * @return The concatenated string.
     */
    public String display(String message) {
    
        return message;
    }
    
    /**
     * Time passing function. 
     * @param seconds The time to wait for.
     */
    public void Wait(long seconds) {
        long time0 = System.currentTimeMillis();
        long time1 = -1;
        while(time1 < (seconds*1000)){
            time1 = System.currentTimeMillis()-time0;
        }
    }  

    // IConnections Interface
    public boolean connect(IUnknown pSinkIntf, String riid, long provConnID) {
        if(riid.toString().equalsIgnoreCase("NormalRate.INormal")){
		return m_PSR_INormal.connectToRecp(pSinkIntf, riid, provConnID);        
	}

	return false;
    }
    
    public boolean disconnect(String riid, long connID) {
	if(riid.toString().equalsIgnoreCase("NormalRate.INormal")){
		return m_PSR_INormal.disconnectFromRecp(connID);	
        }
	return false;
    }
    
    // ILifeCycle Interface
    public boolean shutdown() {
        return true;
    }
    
    public boolean startup(Object pIOCM) {
        return true;
    }  
    
}
