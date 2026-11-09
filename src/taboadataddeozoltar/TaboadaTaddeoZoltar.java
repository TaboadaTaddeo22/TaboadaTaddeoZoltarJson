/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package taboadataddeozoltar;

import static java.awt.Frame.MAXIMIZED_BOTH;

/**
 *
 * @author taboada.taddeo
 */
public class TaboadaTaddeoZoltar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FrameZoltar frmZoltar = new FrameZoltar();
        // Schermo intero
        frmZoltar.setExtendedState(MAXIMIZED_BOTH);
        frmZoltar.setVisible(true);
        frmZoltar.iniziaZoltar();
    }
    
}
