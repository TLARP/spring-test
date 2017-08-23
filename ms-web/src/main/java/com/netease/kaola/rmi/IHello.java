package com.netease.kaola.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by hzwangqiqing
 * on 2017/8/23.
 */
public interface IHello extends Remote{
    public String hello() throws RemoteException;
}
