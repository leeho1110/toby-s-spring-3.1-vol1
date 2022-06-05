package part3.calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CalculatorV3withGeneric {

    public String concatenate(String filepath) throws IOException {
        LineCallbackWithGeneric<String> callbackWithGeneric = new LineCallbackWithGeneric<String>() {
            @Override
            public String doSomethingWithLine(String line, String value) throws IOException {
                return value + line;
            }
        };

        return lineReadTemplateWithGeneric(filepath, callbackWithGeneric, "");
    }

    public <T> T lineReadTemplateWithGeneric(String filepath, LineCallbackWithGeneric<T> callback, T initVal) throws IOException {
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(filepath));
            T result = initVal;

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
