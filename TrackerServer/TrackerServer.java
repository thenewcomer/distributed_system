/*
 * Copyright 2004 Sun Microsystems, Inc. All  Rights Reserved.
 *  
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * -Redistributions of source code must retain the above copyright  
 *  notice, this list of conditions and the following disclaimer.
 * 
 * -Redistribution in binary form must reproduce the above copyright 
 *  notice, this list of conditions and the following disclaimer in 
 *  the documentation and/or other materials provided with the 
 *  distribution.
 *  
 * Neither the name of Sun Microsystems, Inc. or the names of 
 * contributors may be used to endorse or promote products derived 
 * from this software without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN") AND ITS LICENSORS SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR
 * ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT,
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF
 * THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *  
 * You acknowledge that Software is not designed, licensed or 
 * intended for use in the design, construction, operation or 
 * maintenance of any nuclear facility.
 */
//package example.hello;
	
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import org.json.JSONObject;

public class TrackerServer implements TrackerInterface {
	
	int N = -1;
	int K = -1;
	int portNum = -1;
	
    public TrackerServer() {}
    public String sayHello() {
	return "Hello, world!";
    }
	public String returnParameters(){
		JSONObject parameters = new JSONObject();
		parameters.put("N",N);
		parameters.put("K",K);
		String parametersString = parameters.toString();
		return parametersString;
	}
	
    public static void main(String args[]) {
	
	    TrackerServer obj = new TrackerServer();
		if(args.length == 3){
		obj.portNum = Integer.parseInt(args[0]);
		obj.N = Integer.parseInt(args[1]);
		obj.K = Integer.parseInt(args[2]);
		}
		TrackerInterface stub = null;
		Registry registry = null;
	try {
		System.err.println("Tracker Port: " + Integer.toString(obj.portNum));
	    stub = (TrackerInterface) UnicastRemoteObject.exportObject(obj, obj.portNum);
	    registry = LocateRegistry.getRegistry();
	    registry.bind("Hey", stub);
	    System.err.println("Tracker ready");
	} catch (Exception e) {
	    try{
			registry.unbind("Hey");
			registry.bind("Hey",stub);
	    	System.err.println("Tracker ready");
	    }catch(Exception ee){
			System.err.println("Tracker exception: " + ee.toString());
	    	ee.printStackTrace();
	    }
	}
    }
}
