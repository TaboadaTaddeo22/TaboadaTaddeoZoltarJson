package taboadataddeozoltar;
 
import java.io.IOException;
import java.util.Random;
 
public class Personalita {
    // Attributi
    private String nome;
    private String[] messaggi;
 
    // Ogni classe "figlia" passa il proprio nome (la chiave nel JSON)
    public Personalita(String nome) throws IOException {
        DataManager dM = new DataManager();
        this.nome = nome;
        
        String[] letti = dM.getMessaggi(nome);
        if (letti == null || letti.length == 0) {
            letti = new String[] { "..." };
        }
        this.messaggi = letti;
    }
 
    // Metodo che restituisce un messaggio casuale
    public String risposta() {
        Random r = new Random();
        return messaggi[r.nextInt(messaggi.length)];
    }

    public String getNome() {
        return nome;
    }

    public String[] getMessaggi() {
        return messaggi;
    }
    
}