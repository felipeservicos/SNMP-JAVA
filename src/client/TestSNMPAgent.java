/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import modelo.Popula;
import org.snmp4j.TransportMapping;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.smi.Integer32;

import org.snmp4j.smi.OID;
import org.snmp4j.transport.TransportMappings;

import server.MOCreator;
import server.SNMPAgent;

/**
 *
 * @author FR22497
 */
public class TestSNMPAgent {

    static final OID sysDescr = new OID(".1.3.6.1.2.1.1.1.0");
    static final OID sysTemp = new OID(".1.3.6.1.2.1.99.2.0");
    static final OID sysHum = new OID(".1.3.6.1.2.1.100.2.0");
    static final OID sysDataLigado = new OID(".1.3.6.1.2.1.101.2.0");
//    static final OID sysTesteInteiro = new OID(".1.3.6.1.2.1.102.2.0");

    public static void main(String[] args) throws IOException, Throwable {

        while(true){
    
        TestSNMPAgent client = new TestSNMPAgent("udp:127.0.0.1/161");
        Long tempo = System.currentTimeMillis();


//        TestSNMPAgent client = new TestSNMPAgent("udp:" + args[0] + "/161");
        client.init();
        client = null;
        //    while (true);
//        Agent client = null;
        while ((System.currentTimeMillis() - tempo) < 60000) ;
    }

    
    
    }
    /**
     * This is the client which we have created earlier
     */
//    SNMPManager client = null;

    String address = null;

    /**
     * Constructor
     *
     * @param add
     */
    public TestSNMPAgent(String add) {
        address = add;
    }

    public void init() throws IOException {

       SNMPAgent agent = new SNMPAgent("0.0.0.0/2001");

        agent.start();
        Popula dados = new Popula();
        ArrayList<Popula> popula = new ArrayList<Popula>();

        popula = (ArrayList<Popula>) dados.obtemDados();
        int teste = 25;
        String temperatura = "";
        String umidade = "";
        temperatura = popula.get(0).getTemperatura();
        umidade = popula.get(0).getUmidade();

        // Pegar a data e jogar para String
        Date hoje = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
        String dataligado = formatter.format(hoje);

        // Since BaseAgent registers some MIBs by default we need to unregister
        // one before we register our own sysDescr. Normally you would
        // override that method and register the MIBs that you need
        agent.unregisterManagedObject(agent.getSnmpv2MIB());

        // Register a system description, use one from you product environment
        // to test with
//        while (true) {
        agent.registerManagedObject(MOCreator.createReadOnly(sysDescr,
                "Medidor de Temperatura e Umidade GIR"));
        agent.registerManagedObject(MOCreator.createReadOnly(sysTemp,
                temperatura));
        agent.registerManagedObject(MOCreator.createReadOnly(sysHum,
                umidade));
        agent.registerManagedObject(MOCreator.createReadOnly(sysDataLigado,
                dataligado));
        agent = null;
        address=null;

//                agent.registerManagedObject(MOCreator.createReadOnly(sysTesteInteiro,
//                teste));
//Integer32 t = new Integer32();
//        MOScalar snmpEngineMaxMessageSize = new MOScalar(new OID("1.3.6.1.6.3.10.2.1.4.0"),
//                MOAccessImpl.ACCESS_READ_ONLY,
//                t);
//        System.out.println(agent.getAgentState());
//        System.out.println(agent.getAgent().getRequestList());
//        System.out.println("Na classe de Teste, o valor da temperatura:" + temperatura);
//        System.out.println("Na classe de Teste, o valor da umidade:" + umidade);
        //   }
    }
//  Setup the client to use our newly started agent
//       client = new SNMPManager("udp:127.0.0.1/2001");

//        client.start();
    // Get back Value which is set
    //   System.out.println(client.getAsString(sysDescr));
}
