import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class HorseTest {

    public static Horse testHorse;
    public static Horse testHorse2;

    @BeforeAll
    public static void init() {
        testHorse = new Horse("Mike", 20.0);
        testHorse2 = new Horse("Mike", 20.0, 45.0);
    }

    @Test
    @DisplayName("Horse(), исключение: имя null")
    void catchIllegalExceptionIfNameIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest()
    @ValueSource(strings = {"", "  "})
    @DisplayName("Horse(), исключение: имя isBlank")
    void catchIllegalExceptionIfNameIsBlankOrOnlySpaces(String strings) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(strings, 10.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    @DisplayName("Horse(), исключение: скорость отрицательная")
    void negativeSecondParamInConstructor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Mike", -20.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Horse(), исключение: дистанция отрицательная")
    void negativeThirdParamInConstructor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Mike", 20.0, -20.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Horse.getName()")
    void getName() {
        assertEquals("Mike", testHorse.getName());
    }

    @Test
    @DisplayName("Horse.getSpeed()")
    void getSpeed() {
        assertEquals(20.0, testHorse.getSpeed());
    }

    @Test
    @DisplayName("Horse.getDistance(): конструктор из 2 параметров")
    void getDistanceWith2ParamInConstructor() {
        assertEquals(0, testHorse.getDistance());
    }

    @Test
    @DisplayName("Horse.getDistance()")
    void getDistance() {
        assertEquals(45.0, testHorse2.getDistance());
    }

    @ParameterizedTest()
    @CsvSource({
            "0.2, 0.9"
    })
    @DisplayName("Horse.move() + static getRandomDouble()")
    public void move(double min, double max) {
        try (MockedStatic<Horse> mockHorse = Mockito.mockStatic(Horse.class)) {
            mockHorse.when(() -> Horse.getRandomDouble(anyDouble(), anyDouble())).thenReturn(0.5);
            testHorse.move();
            mockHorse.verify(() -> Horse.getRandomDouble(min, max), times(1));
            assertEquals(10.0, testHorse.getDistance());
        }
    }
}