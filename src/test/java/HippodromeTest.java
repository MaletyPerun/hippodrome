import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    public static List<Horse> testListOfHorses = new ArrayList<>();
    public static Hippodrome hippodrome;

    @BeforeAll
    public static void init() {
        for (int i = 0; i < 30; i++) {
            testListOfHorses.add(new Horse("name_" + i, 5.0 + i, i));
        }

        hippodrome = new Hippodrome(testListOfHorses);
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

//    метод getHorses
//    Проверить, что метод возвращает список, который содержит те же объекты и в той же последовательности, что и
//    список который был передан в конструктор. При создании объекта Hippodrome передай в конструктор список из 30 разных
//            лошадей;

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


//    @ParameterizedTest()
//    @ValueSource(strings = {"", "  "})
//    @DisplayName("Horse(), исключение: имя isBlank")
//    void catchIllegalExceptionIfNameIsBlankOrOnlySpaces(String strings) {
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(strings, 10.0));
//        assertEquals("Name cannot be blank.", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Horse(), исключение: скорость отрицательная")
//    void negativeSecondParamInConstructor() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Mike", -20.0));
//        assertEquals("Speed cannot be negative.", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Horse(), исключение: дистанция отрицательная")
//    void negativeThirdParamInConstructor() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Mike", 20.0, -20.0));
//        assertEquals("Distance cannot be negative.", exception.getMessage());
//    }

//    @Test
//    @DisplayName("Horse.getName()")
//    void getName() {
//        assertEquals("Mike", testHorse.getName());
//    }

//    @Test
//    @DisplayName("Horse.getSpeed()")
//    void getSpeed() {
//        assertEquals(20.0, testHorse.getSpeed());
//    }

//    @Test
//    @DisplayName("Horse.getDistance(): конструктор из 2 параметров")
//    void getDistanceWith2ParamInConstructor() {
//        assertEquals(0, testHorse.getDistance());
//    }

//    @Test
//    @DisplayName("Horse.getDistance()")
//    void getDistance() {
//        assertEquals(45.0, testHorse2.getDistance());
//    }

//    @ParameterizedTest()
//    @CsvSource({
//            "0.2, 0.9"
//    })
//    @DisplayName("Horse.move() + static getRandomDouble()")
//    public void move(double min, double max) {
//        try (MockedStatic<Horse> mockHorse = Mockito.mockStatic(Horse.class)) {
//            mockHorse.when(() -> Horse.getRandomDouble(anyDouble(), anyDouble())).thenReturn(0.5);
//            testHorse.move();
//            mockHorse.verify(() -> Horse.getRandomDouble(min, max), times(1));
//            assertEquals(10.0, testHorse.getDistance());
//        }
//    }
}