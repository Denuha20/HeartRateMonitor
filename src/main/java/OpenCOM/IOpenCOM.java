/*
 * IOpenCOM.java
 *
 * OpenCOMJ is a flexible component model for reconfigurable reflection developed at Lancaster University.
 * Copyright (C) 2005 Paul Grace
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty 
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program; if not, 
 * write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

package OpenCOM;

import java.lang.String.*;
import java.lang.reflect.*;
import java.util.*;

/**
 * Java Interface describing the methods of OpenCOM runtime kernel.
 * The primary runtime operations are: create, delete connect, disconnect,
 * and the meta-operations are list components, get component name etc.
 *
 * Note: This is a direct attempt to port the OpenCOM v1 interface to Java
 * @author  Paul Grace
 * @version 1.3.5
 *
 **/

public interface IOpenCOM extends IUnknown{
    
    /**
     * Create a new instance of a component and insert it into the OpenCOM runtime. Ideally, the
     * creator should cast the resulting component to IUnknown to enable OpenCOM style programming. 
     * @param componentType the string describing the component type i.e. the Java class of the component.
     * @param componentName the string represting the unique (user defined) name of the component.
     * @return an Object that is the reference to the created component. Null indicates failure.
     * @see java.lang.Object
     **/
    Object createInstance(String componentType, String componentName);

     /**
     * Deletes a component instance which has been previously created. 
     * @param pComponentIUnknown an IUnknown reference of the component to delete.
     * @return Indication of success or failure of operation.
     * @see OpenCOM.IUnknown
     **/
    boolean deleteInstance(IUnknown pComponentIUnknown);

    /**
     * Connects a receptacle on the Source component to an interface on the Sink component. 
     * @param pSourceComponentIUnk Reference to component with the receptacle.
     * @param ComponentIUnk Reference to component with the interface.
     * @param InterfaceType a string describing the interface type of the connection.
     * @return a long describing the unique connection identifier generated by the run-time.
     * @see OpenCOM.IUnknown
     **/	
    long connect(IUnknown pSourceComponentIUnk, IUnknown ComponentIUnk, String InterfaceType);
      
    /**
     * Disconnects a receptacle from an interface. 
     * @param connID a long describing the unique identifier of the connection to destroy.
     * @return Indication of success or failure of operation.
     */
    boolean disconnect(long connID);

    /**
     * Returns meta-information about a given connection. 
     * @param connID a long describing the unique identifier of the connection to inspect.
     * @return meta-information about the connection. e.g. Source, Sink, Interface type. Null indicates operation failure.
     * @see OpenCOM.OCM_ConnInfo_t
     */
    OCM_ConnInfo_t getConnectionInfo(long connID);

    /**
     * Fills the given Vector with the complete set of components currently instantiated in the runtime. 
     * @param ppComps Vector to fill with IUnknown pointers of current components.
     * @return an integer describing the number of components currently in the run-time.
     */
    int enumComponents(Vector<IUnknown> ppComps);

    /**
     * Returns the registered unique component name for a given component reference.
     * @param pIUnknown an IUnknown reference describing a component instantiation.
     * @return a string describing the unique name of the component.
     * @see OpenCOM.IUnknown
     */
    String getComponentName(IUnknown pIUnknown);

    /**
     * Returns the registered component reference of a named component.
     * @param name a string describing the unique name of the component.
     * @return an IUnknown reference describing the instantiation of the component.
     * @see OpenCOM.IUnknown
     */
    IUnknown getComponentPIUnknown(String name);

    /**
     * Returns the registered component type of a given component instantiation. In this case the Java class
     * describing the component.
     * @param pIUnknown an IUnknown reference describing a component instantiation.
     * @return - Class of component.
     * @see OpenCOM.IUnknown
     * @see java.lang.Class
     */
    Class getComponentCLSID(IUnknown pIUnknown);
    
    /**
     * Return the meta-data stored in the architectural meta model about an individual component
     * @param pIUnknown an IUnknown reference describing a component instantiation.
     * @return - OCM_GraphNode_t data structure describing the component
     * @see OpenCOM.OCM_GraphNode_t
     */
    OCM_GraphNode_t getComponentMetaData(IUnknown pIUnknown);
    
    /**
     * Set a component to be primitive i.e. it has no contained (composing)
     * components within, or to be composed.
     * @param componentID The unique ID of the component to set the value of
     * @param boolValue True if the component is primitive, false if the component is a framework
     */
    void setContained(String componentID, boolean boolValue);
    
     /**
     * Find out if a component is primitive or it resides within a framework
     * @param componentID The unique id of the component
     * @return true if the component is contained, false if it is isn't
     */
    public boolean isContained(String componentID);
    
    
    /**
     * Notify the kernel that an external event has occured.
     * @param EventType The type of event generated.
     * @param Event The instance of the event object.
     */
    public void notifyEvent(int EventType, Object Event);
    
};

