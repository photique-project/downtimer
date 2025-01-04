package io;

import static contant.Signal.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputHandler {
    public final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public String getStartOption() throws IOException {
        String input = br.readLine();

        if (!input.equals(EXIT) && !input.equals(START)) {
            throw new IllegalArgumentException("\n⚠️ Please enter 0 or 1 only\n");
        }

        return input;
    }

    public String getEndpoint() throws IOException {
        return br.readLine();
    }
}
