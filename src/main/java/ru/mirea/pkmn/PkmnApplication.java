package ru.mirea.pkmn;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.common.io.Resources;
import ru.mirea.pkmn.polupanpolina.CardExport;
import ru.mirea.pkmn.polupanpolina.CardImport;

public class PkmnApplication {

    public static void main(String[] args) {

        testMethods();
    }

    public static void testMethods() {

        Logger logger = Logger.getLogger(PkmnApplication.class.getName());

        try {
            URL resource =  Resources.getResource("my_card.txt");

            Path path = Paths.get(resource.toURI());

            logger.setLevel(Level.FINE);

            Card card = CardImport.parseCard(path.toString());

            CardExport.serializeCard(card, "card_for_lera.crd");

            URL resource1 =  Resources.getResource("Hisuian_Arcanine.crd");

            Path path1 = Paths.get(resource1.toURI());

            CardImport.deserializeCard(path1.toString());

        } catch (URISyntaxException e) {

            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}