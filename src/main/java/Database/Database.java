/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


import Database.IDatabase;
import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOMComponent;
import java.io.*;

/**
 *
 * @author denuha
 */
//store patient info
public class Database extends OpenCOMComponent implements IUnknown, IDatabase, IMetaInterface, ILifeCycle {
    
    static File file;
    static FileWriter fw;
    static BufferedWriter writer;

    static void RecordDatabase(int a) throws IOException {
        file = new File("/Users/denuha/NetBeansProjects/OpenComTest/src/main/java/kernel/RecordRates.txt");
        fw = new FileWriter(file.getAbsoluteFile());
        writer = new BufferedWriter(fw);
        FileWriteHandler.writer("hello");
        FileWriteHandler.writer(a);
        FileWriteHandler.close();
    }
    /** Creates a new instance
     * @param pRuntime */
    public Database(IUnknown pRuntime) {
        super(pRuntime);
    }

    /*static void in(int a) throws IOException {
                
    try {
            FileWriter writer = new FileWriter("/Users/denuha/NetBeansProjects/OpenComTest/src/main/java/kernel/RecordRates.txt", true);
            writer.write("Hello World");
            writer.write("\r\n");   // write new line
            writer.write(a);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     */
    // ILifeCycle Interface
    public boolean startup(Object pIOCM) {
        return true;
    }
    
    public boolean shutdown() {
        return true;
    }
}
