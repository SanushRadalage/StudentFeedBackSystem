package com.feedback.backend;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RmiServer extends DataBaseFunctions {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6274493897277648394L;

	public RmiServer() throws Exception {}

	public static void main(String args[]) {
		   try { 
			   DataBaseFunctions dbObject = new DataBaseFunctions(); 
				Registry reg = LocateRegistry.createRegistry(1099);
				reg.bind("QuestionService",dbObject);

				System.out.println("RMI service started.");
		    
		      } catch (Exception e) { 
		         System.err.println("Server exception: " + e.toString()); 
		         e.printStackTrace(); 
		      } 	
	}

}
