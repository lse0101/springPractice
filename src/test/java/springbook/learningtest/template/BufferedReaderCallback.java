package springbook.learningtest.template;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by lse0101 on 03/02/2017.
 */
public interface BufferedReaderCallback {
    Integer doSomethingWithReader(BufferedReader br) throws IOException;
}
