/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package NormalRate;

// OpenCOM and Java
import OpenCOM.*;
import java.util.*;

// Interfaces
import HighRate.IHigh;
import LowRate.ILow;
import Database.IDatabase;

/**
 *
 * @author denuha
 */
public class Normal extends OpenCOMComponent implements INormal, IConnections, ILifeCycle, IUnknown, IMetaInterface {
    
    /**
     * Requires Interface of type IHigh.
     */
    public OCM_SingleReceptacle<IHigh> m_PSR_IHigh;
    
    /**
     * Requires Interface of type ILow.
     * 
     */
    public OCM_SingleReceptacle<ILow> m_PSR_ILow;
    
    public OCM_SingleReceptacle<IDatabase> m_PSR_IDatabase;

    /** Creates a new instance 
     * @param binder */
    public Normal(IUnknown binder) {
        super(binder);
        
        // Initiate the receptacles
        m_PSR_IHigh = new OCM_SingleReceptacle<IHigh>(IHigh.class);
        m_PSR_ILow = new OCM_SingleReceptacle<ILow>(ILow.class);
        m_PSR_IDatabase = new OCM_SingleReceptacle<IDatabase>(IDatabase.class);

    }
    

    
    public String MessageHigh(int a) {
        /*
         * Standard single receptacle invocation to retrieve the functionality required.
         */
        return m_PSR_IHigh.m_pIntf.MessageHigh(a);
    }
    
    public String MessageLow(int a) {
        /*
         * Standard single receptacle invocation to retrieve the functionality required.
         */
        return m_PSR_ILow.m_pIntf.MessageLow(a);
    }
    
    public String MessageNormal(int a) {
        /*
         * Standard single receptacle invocation to retrieve the functionality required.
         */
        return ("Your heart rate is normal");
    }    
    
        public void RecordDatabase(int a) {
        /*
         * Standard single receptacle invocation to retrieve the functionality required.
         */
        m_PSR_IDatabase.m_pIntf.RecordDatabase(a);
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
     * Time passing function. Simply operates in the component
     * doing nothing for the specified time period then 
     * returns.
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
        if(riid.toString().equalsIgnoreCase("HighRate.IHigh")){
		return m_PSR_IHigh.connectToRecp(pSinkIntf, riid, provConnID);
	}
        else if(riid.toString().equalsIgnoreCase("LowRate.ILow")){
		return m_PSR_ILow.connectToRecp(pSinkIntf, riid, provConnID);
	}
        else if(riid.toString().equalsIgnoreCase("Database.IDatabase")){
		return m_PSR_IDatabase.connectToRecp(pSinkIntf, riid, provConnID);
	}
        return false;
    }
    
    public boolean disconnect(String riid, long connID) {
        
	if(riid.toString().equalsIgnoreCase("HighRate.IHigh")){
		return m_PSR_IHigh.disconnectFromRecp(connID);
	}
        else if(riid.toString().equalsIgnoreCase("LowRate.ILow")){
		return m_PSR_ILow.disconnectFromRecp(connID);
	}
        else if(riid.toString().equalsIgnoreCase("Database.IDatabase")){
		return m_PSR_IDatabase.disconnectFromRecp(connID);
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
