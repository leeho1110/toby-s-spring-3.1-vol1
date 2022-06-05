package part3.calculator;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface LineCallback {
    Integer doSomethingWithLine(String line, Integer value) throws IOException;
}
