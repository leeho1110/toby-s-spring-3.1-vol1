package part3.calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CalculatorV2 {

    public int calcSum(String filepath) throws IOException {
        BufferedReaderCallback callback = new BufferedReaderCallback() {
            @Override
            public Integer doSomethingWithReader(BufferedReader bufferedReader) throws IOException {
                String line = null;
                Integer sum = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    sum += Integer.valueOf(line);
                }

                return sum;
            }
        };
        return fileReadTemplate(filepath, callback);
    }

    public int calcMultiply(String filepath) throws IOException {
        BufferedReaderCallback callback = new BufferedReaderCallback() {
            @Override
            public Integer doSomethingWithReader(BufferedReader bufferedReader) throws IOException {
                String line = null;
                Integer multiply = 1;
                while ((line = bufferedReader.readLine()) != null) {
                    multiply *= Integer.valueOf(line);
                }

                return multiply;
            }
        };
        return fileReadTemplate(filepath, callback);
    }

    public Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(filepath));
            return callback.doSomethingWithReader(bufferedReader);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            bufferedReader.close();
        }
    }
}
