package com.mdc;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements IHello {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7846816401287635458L;

	protected HelloImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public String sayHello(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return "Hello" + name + "^_^";
	}

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			IHello hello = new HelloImpl();
			Naming.rebind("rmi://127.0.0.1:1099/hello", hello);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
