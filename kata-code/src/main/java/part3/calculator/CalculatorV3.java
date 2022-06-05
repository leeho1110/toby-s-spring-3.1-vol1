package part3.calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CalculatorV3 {

    public int calcSum(String filepath) throws IOException {
        LineCallback lineCallback = new LineCallback() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) throws IOException {
                return value + Integer.valueOf(line);
            }
        };

        return lineReadTemplate(filepath, lineCallback, 0);
    }

    public int calcMultiply(String filepath) throws IOException {
        LineCallback lineCallback = new LineCallback() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) throws IOException {
                return value * Integer.valueOf(line);
            }
        };

        return lineReadTemplate(filepath, lineCallback, 1);
    }

    public Integer lineReadTemplate(String filepath, LineCallback callback, Integer initVal) throws IOException {
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(filepath));
            Integer result = initVal;

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                result = callback.doSomethingWithLine(line, result);
            }
            return result;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            bufferedReader.close();
        }
    }
}
