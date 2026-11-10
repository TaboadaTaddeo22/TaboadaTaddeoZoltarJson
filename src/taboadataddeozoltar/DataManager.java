package taboadataddeozoltar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DataManager implements Serializzazione, Deserializzazione {

    private Gson gson;
    private String dati;

    public DataManager() throws IOException {
        this.gson = new Gson();
        this.dati = Files.readString(Paths.get("messaggi.json"),StandardCharsets.UTF_8);
    }

    @Override
    public void serializza() throws IOException {

    }

    @Override
    public Map<String, String[]> deserializza() throws IOException {
        return gson.fromJson(dati, new TypeToken<Map<String, String[]>>() {}.getType());
    }

    public String[] getMessaggi(String nome) throws IOException {
        Map<String, String[]> personalita = deserializza();
        return personalita.get(nome);
    }
}