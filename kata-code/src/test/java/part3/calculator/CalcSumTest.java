package part3.calculator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcSumTest {

    @Nested
    class testCalculatorV1 {

        private CalculatorV1 calculator;

        @BeforeEach
        public void setUp(){
            this.calculator = new CalculatorV1();
        }

        @Test
        public void test_calcSum() throws IOException {
            // given
            CalculatorV1 calculator = new CalculatorV1();

            // when
            int sum = calculator.calcSum(getClass().getResource("numbers.txt").getPath()); // getResource find "out" directory

            // then
            assertThat(sum).isEqualTo(10);
        }
    }

    @Nested
    class testCalculatorV2 {

        private CalculatorV2 calculator;

        @BeforeEach
        public void setUp(){
            this.calculator = new CalculatorV2();
        }

        @Test
        public void calcSum() throws IOException {
            // when
            int sum = calculator.calcSum(getClass().getResource("numbers.txt").getPath()); // getResource find "out" directory

            // then
            assertThat(sum).isEqualTo(10);
        }

        @Test
        public void calcMultiply() throws IOException {
            // when
            int sum = calculator.calcMultiply(getClass().getResource("numbers.txt").getPath());

            // then
            assertThat(sum).isEqualTo(24);
        }
    }

    @Nested
    class testCalculatorV3 {

        private CalculatorV3 calculator;

        @BeforeEach
        public void setUp(){
            this.calculator = new CalculatorV3();
        }

        @Test
        public void calcSum() throws IOException {
            // when
            int sum = calculator.calcSum(getClass().getResource("numbers.txt").getPath()); // getResource find "out" directory

            // then
            assertThat(sum).isEqualTo(10);
        }

        @Test
        public void calcMultiply() throws IOException {
            // when
            int sum = calculator.calcMultiply(getClass().getResource("numbers.txt").getPath());

            // then
            assertThat(sum).isEqualTo(24);
        }
    }

    @Nested
    class testCalculatorV3withGeneric {

        private CalculatorV3withGeneric calculator;

        @BeforeEach
        public void setUp(){
            this.calculator = new CalculatorV3withGeneric();
        }

        @Test
        public void calcSum() throws IOException {
            // when
            String result = calculator.concatenate(getClass().getResource("numbers.txt").getPath()); // getResource find "out" directory

            // then
            assertThat(result).isEqualTo("1234");
        }
    }


}
