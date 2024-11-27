//package ru.mirea.pkmn.polupanpolina.io;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ru.mirea.pkmn.polupanpolina.model.Card;
//import ru.mirea.pkmn.polupanpolina.model.EnergyType;
//import ru.mirea.pkmn.polupanpolina.model.PokemonStage;
//import ru.mirea.pkmn.polupanpolina.model.Student;
//
//import java.io.*;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static ru.mirea.pkmn.polupanpolina.io.CardImport.deserializeCard;
//
//public class CardDeserializerTest {
//
//    private final String testFilePath = "test_card.crd";
//
//    @BeforeEach
//    public void setUp() throws IOException {
//        // Create a sample card and serialize it to a file
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
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(testFilePath))) {
//            oos.writeObject(testCard);
//        }
//    }
//
//    @Test
//    public void testDeserializeCardSuccess() {
//        // Deserialize the card
//        Card deserializedCard = deserializeCard(testFilePath);
//
//        // Verify that the card was correctly deserialized
//        assertNotNull(deserializedCard);
//        assertEquals("Pikachu", deserializedCard.getName());
//        assertEquals(60, deserializedCard.getHp());
//        assertEquals(EnergyType.LIGHTNING, deserializedCard.getPokemonType());
//        assertEquals('A', deserializedCard.getRegulationMark());
//    }
//
//    @Test
//    public void testDeserializeCardFileNotFound() {
//        // Try to deserialize from a non-existent file
//        Card deserializedCard = deserializeCard("non_existent_file.ser");
//
//        // Verify the result
//        assertNull(deserializedCard);
//    }
//
//    @Test
//    public void testDeserializeCardInvalidFile() throws IOException {
//        // Create a file with invalid data (not a serialized object)
//        String invalidFilePath = "invalid_card.crd";
//        try (FileWriter writer = new FileWriter(invalidFilePath)) {
//            writer.write("This is not a serialized object");
//        }
//
//        // Try to deserialize it
//        Card deserializedCard = deserializeCard(invalidFilePath);
//
//        // Verify the result
//        assertNull(deserializedCard);
//
//        // Clean up the file
//        new File(invalidFilePath).delete();
//    }
//}
