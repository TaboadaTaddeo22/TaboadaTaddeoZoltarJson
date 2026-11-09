/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taboadataddeozoltar;

/**
 *
 * @author taboada.taddeo
 */
public class GestoreMessaggi {
    // Attributi
    private Client c;
    
    public GestoreMessaggi(Client c) {
        this.c = c;
    }
    
    // Gestione del messaggio dell'utente
    public void inputUtente(String messaggio) {
        messaggio = messaggio.trim();
        if (messaggio.isEmpty()) {
            return;
        }
        c.mostra("Tu: " + messaggio);
        
        String[] m = messaggio.split("_");
        String tipoRichiesta = m[0];
        String richiesta = "";
        if (m.length > 1)
            richiesta = m[1];
        
        switch (tipoRichiesta) {
            case "PER":  
                personalita(richiesta); 
                break;
            case "DOM":  
                domanda(richiesta);     
                break;
            case "HELP": 
                help();
                break;
            default: 
                c.mostra("Comando non riconosciuto. Scrivi HELP per l'elenco dei comandi.");
        }
    }
    
    // Scrittura nella textarea dell'input dell'utente
    public void messaggioUtente(String messaggio) {
        c.mostra("Tu: " + messaggio);
        domanda(messaggio);
    }
    
    // Cambiamento della personalità
    public void personalita(String nome) {
        if (nome.isEmpty()) {
            c.mostra("Specifica la personalita', ad esempio PER_Eldego");
            return;
        }
        c.invia("PER_" + nome);
    }
    
    // Invio della domanda
    public void domanda(String testo) {
        if (testo.isEmpty()) {
            c.mostra("Scrivi la domanda, ad esempio 'Ciao, Come stai?'");
            return;
        }
        c.invia("DOM_" + testo);
    }
    
    // Scrittura nella textarea del messaggio d'aiuto
    public void help() {
        c.mostra("Personalita' disponibili: \n"
                + "Zoltar  : la personalita' di default\n"
                + "Eldego  : non ha bisogno di presentazioni\n"
                + "Sindaco : il Sindaco di Malmo\n"
                + "Textbox 'Inserisci un messaggio' : fai una domanda alla personalita' scelta\n"
                + "HELP : mostra questo messaggio");
    }
}
