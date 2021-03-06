/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author furnas
 */
public class Popula {

    private String Temperatura;
    private String Umidade;
 

    public String getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(String Temperatura) {
        this.Temperatura = Temperatura;
    }

    public String getUmidade() {
        return Umidade;
    }

    public void setUmidade(String Umidade) {
        this.Umidade = Umidade;
    }

   
//    public String removeChar(String txt) {
//        return txt.replace("\"", "").replace(",", "").replace(")", "").replace(";", "").trim();
//    }

    public ArrayList<Popula> obtemDados() {
        String url = "http://10.1.162.6";

        URL u;
//        InputStream is = null;
//        DataInputStream dis;
        ArrayList<String> parmsbusca = new ArrayList<>();
        ArrayList<Popula> dadosObtidos = new ArrayList<Popula>();
        String s = "";
        String x = "";

        ///Busca
        parmsbusca.add("Temperatura=");
        //    parmsbusca.add("Umidade");

        try {

            for (String item : parmsbusca) {

                u = new URL(url);
                InputStream is = u.openStream();
                String aux = "";

                BufferedReader d = new BufferedReader(new InputStreamReader(is));

                do {

                    if (s != null) {

//                        if (s.contains("\"" + item + "\",")) {
                        if (s.contains(item)) {

                            Popula linha = new Popula();

                            linha.setUmidade(splitaumidade(s));
                            linha.setTemperatura(splitatemperatura(s));

                            dadosObtidos.add(linha);

                        } else {
                        }

                    }
                    aux = s;
                } while ((s = d.readLine()) != null);
                d.close();

            }
            for (int j = 0; j < dadosObtidos.size(); j++) {
                System.out.println("===================================================");

                System.out.println("Temperatura: " + dadosObtidos.get(j).getTemperatura());
                System.out.println("Umidade: " + dadosObtidos.get(j).getUmidade());

            }

        } catch (MalformedURLException mue) {
            System.err.println("Problema - URL errada");
            System.exit(2);
        } catch (IOException ioe) {
            System.err.println("Problema- Problema de IO. SERVIÇO FORA?");
            System.out.println(ioe.getCause());
            System.out.println(ioe);
            System.exit(3);
        } finally {
return dadosObtidos;
        }
    }
    private static String splitatemperatura(String temperatura) {
        String partes[] = temperatura.split(";");

        return partes[0].substring(partes[0].indexOf("Temperatura=") + 13, partes[0].indexOf("00") + 2);
    }

    private static String splitaumidade(String temperatura) {
        String partes[] = temperatura.split(";");

        return partes[1].substring(partes[1].indexOf("Umidade=") + 8, partes[1].indexOf("00") + 2);
    }
}
