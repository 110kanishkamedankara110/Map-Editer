/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theams;

/**
 *
 * @author Kanishka
 */
import com.formdev.flatlaf.FlatDarkLaf;

public class MyTheam extends FlatDarkLaf {

    public static boolean setup() {
        return setup(new MyTheam());
    }

    @Override
    public String getName() {
        return "MyDarkerLaf";
    }
}
