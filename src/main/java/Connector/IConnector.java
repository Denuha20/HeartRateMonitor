/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connector;

import OpenCOM.IUnknown;

/**
 *
 * @author denuha
 */
public interface IConnector extends IUnknown{
 
    
    public String MessageHigh(int x);

    public String MessageLow(int x);


    public String MessageNormal(int x);

    public void RecordDatabase(int x);

    public String display(String a);
    

    public void Wait(long time);
}
