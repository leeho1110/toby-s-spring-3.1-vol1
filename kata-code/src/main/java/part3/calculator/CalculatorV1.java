package part3.calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CalculatorV1 {
    public Integer calcSum(String filepath) throws IOException {
        BufferedReader bufferedReader = null;

        try {
            Integer sum = 0;
            bufferedReader = new BufferedReader(new FileReader(filepath));

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                sum += Integer.valueOf(line);
            }

            return sum;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            bufferedReader.close();
        }
    }
}
