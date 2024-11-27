//package ru.mirea.pkmn.polupanpolina.io;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ru.mirea.pkmn.polupanpolina.model.Card;
//import ru.mirea.pkmn.polupanpolina.model.EnergyType;
//import ru.mirea.pkmn.polupanpolina.model.PokemonStage;
//import ru.mirea.pkmn.polupanpolina.model.Student;
//
//import java.io.File;
//import java.util.ArrayList;
//
//import static org.junit.Assert.assertTrue;
//import static ru.mirea.pkmn.polupanpolina.io.CardExport.serializeCard;
//
//public class CardSerializerTest {
//
//    private final String outputDirectory = "output/";
//    private final String testFileName = "test_card.crd";
//
//    @BeforeEach
//    public void setUp() {
//        // Create the output directory if it doesn't exist
//        File directory = new File(outputDirectory);
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//    }
//
//    @Test
//    public void testSerializeCardSuccess() {
//        // Create a sample card to serialize
//        Card testCard = new Card(
//                PokemonStage.BASIC,
//                "Pikachu",
//                "0",
//                60,
//                EnergyType.LIGHTNING,
//                null,
//                new ArrayList<>(),
//                EnergyType.FIGHTING,
//                EnergyType.METAL,
//                "1",
//                "Base Set",
//                'A',
//                new Student("Ash", "Ketchum", "Trainer", "Pallet Town Group")
//        );
//
//        // Call the method to serialize the card
//        serializeCard(testCard, testFileName);
//
//        // Verify the file is created
//        File outputFile = new File(outputDirectory + testFileName);
//        assertTrue(outputFile.exists());
//    }
//
//    @Test
//    public void testSerializeCardFailure() {
//
//        // Create a sample card to serialize
//        Card testCard = new Card(
//                PokemonStage.BASIC,
//                "Pikachu",
//                "0",
//                60,
//                EnergyType.LIGHTNING,
//                null,
//                new ArrayList<>(),
//                EnergyType.FIGHTING,
//                EnergyType.METAL,
//                "1",
//                "Base Set",
//                'A',
//                new Student("Ash", "Ketchum", "Trainer", "Pallet Town Group")
//        );
//
//        // Call the method to serialize the card
//        serializeCard(testCard, testFileName);
//    }
//}
