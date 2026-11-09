package taboadataddeozoltar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class Personalita {
    // Attributi
    String[] messaggi;
    private Random random = new Random();
    // Il file JSON viene letto una sola volta e condiviso da tutte le personalità
    private Map<String, String[]> tuttiMessaggi;

    // Ogni classe "figlia" passa il proprio nome (la chiave nel JSON)
    public Personalita(String nome) {
        String[] letti = caricaMessaggi().get(nome);
        if (letti == null || letti.length == 0) {
            letti = new String[] { "..." };
        }
        this.messaggi = letti;
    }

    // Legge messaggi.json con Gson
    private synchronized Map<String, String[]> caricaMessaggi() {
        if (tuttiMessaggi == null) {
            try (InputStream is = Personalita.class.getResourceAsStream("messaggi.json");
                Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                Type tipo = new TypeToken<Map<String, String[]>>() {}.getType();
                tuttiMessaggi = new Gson().fromJson(reader, tipo);
            } catch (IOException | NullPointerException e) {
                System.err.println("Impossibile leggere messaggi.json: " + e.getMessage());
                tuttiMessaggi = new java.util.HashMap<>();
            }
        }
        return tuttiMessaggi;
    }

    // Metodo che restituisce un messaggio casuale
    public String risposta() {
        return messaggi[random.nextInt(messaggi.length)];
    }
}