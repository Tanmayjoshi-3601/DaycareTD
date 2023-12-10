/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import javax.swing.JTextField;

/**
 *
 * 
 */
public class ClearUtility {
    
    public static void clearTextField(JTextField[] jk){
        for( JTextField pk : jk){
            pk.setText("");
        }
    }
    
}
