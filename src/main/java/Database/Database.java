/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


import java.io.*;

/**
 *
 * @author denuha
 */
//store patient info
public class Database {
    private static int record;
    
    public Database(int a){
        record = a;
    }
    
    public int getRecord(){
        return record;
    }

}
