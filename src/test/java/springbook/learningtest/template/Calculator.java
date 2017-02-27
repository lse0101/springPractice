package springbook.learningtest.template;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by lse0101 on 03/02/2017.
 */
public class Calculator {


    public Integer calcSum(final String filePath) throws IOException {
        return lineReadTemplate(filePath,
                new LineCallback<Integer>() {
                    @Override
                    public Integer doSomethingWithLine(String line, Integer value) {
                        return Integer.parseInt(line) + value;
                    }
                }
                , 0);
    }

    public Integer calcMultiply(final String numFlepath) throws IOException {
        return lineReadTemplate(numFlepath,
                new LineCallback<Integer>() {
                    @Override
                    public Integer doSomethingWithLine(String line, Integer value) {
                        return Integer.parseInt(line) * value;
                    }
                }
                , 1);
    }

    private <T> T lineReadTemplate(String filePath, LineCallback<T> callback, T initVal) throws IOException {
        BufferedReader br = null;
        T res = initVal;

        try {
            br = new BufferedReader(new FileReader(filePath));
            res = initVal;
            String line = null;

            while ((line = br.readLine()) != null) {
                res = callback.doSomethingWithLine(line, res);
            }
            return res;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return res;
    }
}
