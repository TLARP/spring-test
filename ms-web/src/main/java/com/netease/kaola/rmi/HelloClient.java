package com.netease.kaola.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by hzwangqiqing
 * on 2017/8/23.
 */
public class HelloClient {
    public static void main(String[] args) {
        try {
            IHello iHello = (IHello) Naming.lookup("rmi://localhost:8888/RHello");
            System.out.println(iHello.hello());
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
