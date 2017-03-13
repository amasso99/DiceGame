package Controller;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Oussama on 11.03.2017.
 */
public class FontLoader {

    public static Font DICE_FONT = loadFont(new File("Fonts\\dice_font.ttf"));

    private static Font loadFont(File file){
        try {
            return Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(16f);
        } catch (IOException | FontFormatException e) {
            return null;
        }
    }
}
