/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taboadataddeozoltar;

import java.util.Random;

/**
 *
 * @author tadde
 */
public class Personalita {
    // Attributi
    // Ogni classe "figlia" ha i suoi messaggi personalizzati
    String[] messaggi;

    public Personalita() {
    }

    // Metodo che restituisce un messaggio casuale
    public String risposta() {
        Random r = new Random();
        int n = r.nextInt(messaggi.length);
        String risposta = messaggi[n];

        return risposta;
    }
}
