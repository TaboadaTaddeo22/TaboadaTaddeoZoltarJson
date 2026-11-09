package taboadataddeozoltar;
 
import java.util.Random;
 
public class Personalita {
    // Attributi
    String[] messaggi;
    private Random random = new Random();
 
    // Ogni classe "figlia" passa il proprio nome (la chiave nel JSON)
    public Personalita(String nome) {
        // Il JSON è gestito dal DataManager (letto una sola volta e condiviso)
        String[] letti = DataManager.getInstance().getMessaggi(nome);
        if (letti == null || letti.length == 0) {
            letti = new String[] { "..." };
        }
        this.messaggi = letti;
    }
 
    // Metodo che restituisce un messaggio casuale
    public String risposta() {
        return messaggi[random.nextInt(messaggi.length)];
    }
}