/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package taboadataddeozoltar;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Contratto per chi sa trasformare i messaggi (nome personalità -> elenco
 * di frasi) in un formato testuale e scriverli su un Writer.
 */
public interface Serializzazione {
    void serializza() throws IOException;
}
