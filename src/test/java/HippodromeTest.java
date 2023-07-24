import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    public static List<Horse> testListOfHorses = new ArrayList<>();
    public static List<Horse> MockListOfHorses = new ArrayList<>();
    public static Hippodrome hippodrome;
    public static Hippodrome hippodromeWithMokHorses;

    public static Horse horse = null;

    @BeforeAll
    public static void init() {
        for (int i = 0; i < 30; i++) {
            testListOfHorses.add(new Horse("name_" + i, 5.0 + i, i));
        }
        hippodrome = new Hippodrome(testListOfHorses);

        for (int i = 0; i < 50; i++) {
            horse = Mockito.mock(Horse.class);
            MockListOfHorses.add(horse);
        }
        hippodromeWithMokHorses = new Hippodrome(MockListOfHorses);
    }

    @Test
    @DisplayName("Hippodrome(), исключение: список null")
    void catchIllegalExceptionIfListIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Hippodrome(), исключение: список пустой")
    void catchIllegalExceptionIfListIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("Hippodrome.getHorses()")
    void getHorses() {
        List<Horse> listFromMethod = hippodrome.getHorses();
        assertEquals(testListOfHorses, listFromMethod);

        List<Horse> notOrderTestList = new ArrayList<>(testListOfHorses);
        notOrderTestList.add(0, notOrderTestList.get(29));
        notOrderTestList.remove(30);
        assertNotEquals(notOrderTestList, listFromMethod);
    }

    @Test
    @DisplayName("Hippodrome.getWinner()")
    void getWinner() {
        assertEquals(testListOfHorses.get(29).getDistance(), hippodrome.getWinner().getDistance());
    }

    @Test
    @DisplayName("Hippodrome.move()")
    void move() {
        hippodromeWithMokHorses.move();
        for (Horse mock : MockListOfHorses) {
            Mockito.verify(mock).move();
        }
    }
}