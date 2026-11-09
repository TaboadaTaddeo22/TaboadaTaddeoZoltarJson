/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taboadataddeozoltar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Gestisce il file messaggi.json: lo legge (Deserializzazione) e lo scrive
 * (Serializzazione). Il file viene letto una sola volta e tenuto in cache,
 * quindi è condiviso da tutte le personalità.
 */
public class DataManager implements Serializzazione, Deserializzazione {
    // Attributi
    private static final String RISORSA = "messaggi.json";
    private static final Type TIPO = new TypeToken<Map<String, String[]>>() {}.getType();
    private static final DataManager ISTANZA = new DataManager();

    // disableHtmlEscaping evita che l'apostrofo diventi \u0027 quando si scrive
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();
    private Map<String, String[]> cache;

    private DataManager() {
    }

    // Istanza unica condivisa
    public static DataManager getInstance() {
        return ISTANZA;
    }

    // Implementazione di Serializzazione: Map -> JSON
    @Override
    public void serializza(Map<String, String[]> dati, Writer writer) throws IOException {
        try {
            gson.toJson(dati, TIPO, writer);
            writer.flush();
        } catch (JsonParseException e) {
            throw new IOException("Errore di serializzazione: " + e.getMessage(), e);
        }
    }

    // Implementazione di Deserializzazione: JSON -> Map
    @Override
    public Map<String, String[]> deserializza(Reader reader) throws IOException {
        try {
            Map<String, String[]> letti = gson.fromJson(reader, TIPO);
            return letti != null ? letti : new HashMap<>();
        } catch (JsonParseException e) {
            throw new IOException("Errore di deserializzazione: " + e.getMessage(), e);
        }
    }

    // Carica messaggi.json dalle risorse (una sola volta)
    public synchronized Map<String, String[]> caricaMessaggi() {
        if (cache == null) {
            try (InputStream is = DataManager.class.getResourceAsStream(RISORSA)) {
                if (is == null) {
                    throw new FileNotFoundException(RISORSA + " non trovato");
                }
                try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                    cache = deserializza(reader);
                }
            } catch (IOException e) {
                System.err.println("Impossibile leggere " + RISORSA + ": " + e.getMessage());
                cache = new HashMap<>();
            }
        }
        return cache;
    }

    // Messaggi di una singola personalità (null se non esiste)
    public String[] getMessaggi(String nome) {
        return caricaMessaggi().get(nome);
    }

    // Salva i dati su un file esterno (le risorse dentro il jar non sono scrivibili)
    public synchronized void salvaSuFile(Map<String, String[]> dati, Path percorso) throws IOException {
        try (Writer writer = new OutputStreamWriter(
                new FileOutputStream(percorso.toFile()), StandardCharsets.UTF_8)) {
            serializza(dati, writer);
        }
        cache = dati;
    }

    // Legge i dati da un file esterno e aggiorna la cache
    public synchronized Map<String, String[]> caricaDaFile(Path percorso) throws IOException {
        try (Reader reader = new InputStreamReader(
                new FileInputStream(percorso.toFile()), StandardCharsets.UTF_8)) {
            cache = deserializza(reader);
        }
        return cache;
    }
}
