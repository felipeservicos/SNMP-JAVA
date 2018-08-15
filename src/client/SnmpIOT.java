/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;

/**
 *
 * @author FR22497
 */
public class SnmpIOT extends TestSNMPAgent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Throwable {

        long tempoInicio = System.currentTimeMillis();

        while (true) {
            System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
            while ((System.currentTimeMillis() - tempoInicio) != 1000);
            Process exec = Runtime.getRuntime().exec("java -cp SnmpBateria.jar client.TestSNMPAgent "+args[1]);
            while ((System.currentTimeMillis() - tempoInicio) < (Long.parseLong(args[0])*1000));
            exec.destroy();
            System.out.print("Tempo Total: " + (System.currentTimeMillis() - tempoInicio)+ " == App iniciado ==");
            tempoInicio = System.currentTimeMillis();
            
        }

    }

    public SnmpIOT(String add) {
        super(add);
    }

}
