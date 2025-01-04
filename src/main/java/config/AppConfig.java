package config;

import io.InputHandler;
import io.OutputHandler;
import request.RequestHandler;

public class AppConfig {
    private static OutputHandler outputHandler;
    private static InputHandler inputHandler;
    private static RequestHandler requestHandler;

    public static OutputHandler getOutputHandler() {
        if (outputHandler == null) {
            outputHandler = new OutputHandler();
        }

        return outputHandler;
    }

    public static InputHandler getInputHandler() {
        if (inputHandler == null) {
            inputHandler = new InputHandler();
        }

        return inputHandler;
    }

    public static RequestHandler getRequestHandler() {
        if (requestHandler == null) {
            requestHandler = new RequestHandler();
        }

        return requestHandler;
    }
}
