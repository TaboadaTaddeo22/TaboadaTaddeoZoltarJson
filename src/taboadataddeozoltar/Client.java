/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package taboadataddeozoltar;


import java.io.*;
import java.net.*;
import javax.swing.JTextArea;
/**
 *
 * @author CASOLARO.DIEGO
 */
public class Client {
    // Attributi
    private JTextArea output;
    private DataOutputStream out;
 
    public Client(JTextArea output) {
        this.output = output;
    }
    
    // Creazione del Client e collegamento al Server
    public void utente() throws IOException {
        Socket socket = new Socket("localhost", 12345);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
 
        while (true) {
            mostra(in.readUTF() + "\n");
        }
    }

    // Scrittura dell'output nella textarea
    public void mostra(String testo) {
        output.append(testo + "\n" + "\n");
    }
    
    // Invio del messaggio al server
    public void invia(String messaggio) {
        if (out == null) {
            return;
        }
        try {
            out.writeUTF(messaggio);
        } catch (IOException e) {
            mostra("Errore di invio: " + e.getMessage());
        }
    }
}
