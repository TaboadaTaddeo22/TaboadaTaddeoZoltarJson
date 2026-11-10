/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package taboadataddeozoltar;

import java.io.IOException;
import java.util.*;

/**
 * Contratto per chi sa leggere da un Reader un formato testuale e
 * ricostruire i messaggi (nome personalità -> elenco di frasi).
 */
public interface Deserializzazione {
    Map<String, String[]> deserializza() throws IOException;
}
