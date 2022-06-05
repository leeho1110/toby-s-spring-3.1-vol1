package part3.calculator;

import java.io.IOException;

public interface LineCallbackWithGeneric <T>{
    T doSomethingWithLine(String line, T value) throws IOException;
}
