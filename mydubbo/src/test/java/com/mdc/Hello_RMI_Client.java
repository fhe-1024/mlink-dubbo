package com.mdc;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Hello_RMI_Client {
	public static void main(String[] args) {
		try {
			IHello hello = (IHello) Naming.lookup("rmi://127.0.0.1:1099/hello");
			System.out.println(hello.sayHello("fhe"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
