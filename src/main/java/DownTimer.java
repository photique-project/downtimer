import static contant.Signal.*;

import dto.DowntimeResult;
import io.InputHandler;
import io.OutputHandler;
import java.io.IOException;
import request.RequestHandler;

public class DownTimer {
    private final OutputHandler outputHandler;
    private final InputHandler inputHandler;
    private final RequestHandler requestHandler;

    public DownTimer(
            final OutputHandler outputHandler,
            final InputHandler inputHandler,
            final RequestHandler requestHandler
    ) {
        this.outputHandler = outputHandler;
        this.inputHandler = inputHandler;
        this.requestHandler = requestHandler;
    }

    public void start() {
        while(true) {
            printEmptyLine();
            printWelcomeMessage();
            printStartOption();
            String input = getStartOption();

            if(isExit(input)) {
                return;
            }

            printEmptyLine();
            printEndpointOptions();
            String endpoint = getEndpoint();

            if(isExit(input)) {
                return;
            }

            validateEndpoint(endpoint);
            printDowntimeMeasurementOption();
            input = getStartOption();

            if(isExit(input)) {
                return;
            }

            printEmptyLine();
            DowntimeResult downtimeResult = startDowntimeMeasurement(endpoint);
            printResult(downtimeResult);
            printRestartOption();
            input = getStartOption();

            if(isExit(input)) {
                return;
            }
        }
    }

    private void printRestartOption() {
        outputHandler.printRestartOption();
    }

    private void printResult(DowntimeResult downtimeResult) {
        outputHandler.printResult(downtimeResult);
    }

    private DowntimeResult startDowntimeMeasurement(String endpoint) {
        try {
            return requestHandler.startDowntimeMeasurement(endpoint);
        } catch(RuntimeException e) {
            outputHandler.printErrorMessage(e.getMessage());
            return startDowntimeMeasurement(endpoint);
        }
    }

    private boolean isExit(String input) {
        return input.equals(EXIT);
    }

    private void printDowntimeMeasurementOption() {
        outputHandler.printDowntimeMeasurementOption();
    }

    private void validateEndpoint(String endpoint) {
        if(requestHandler.validateEndpoint(endpoint)) {
            printEmptyLine();
            outputHandler.printValidEndpointMessage(endpoint);
            return;
        }

        outputHandler.printInvalidEndpointMessage(endpoint);
        printEndpointOptions();
        endpoint = getEndpoint();

        if (endpoint.equals(EXIT)) {
            printExitMessage();
            return;
        }

        validateEndpoint(endpoint);
    }

    private String getEndpoint() {
        try {
            return inputHandler.getEndpoint();
        } catch(IOException e) {
            outputHandler.printErrorMessage(e.getMessage());
            printEndpointOptions();
            return getEndpoint();
        }
    }

    private void printEndpointOptions() {
        outputHandler.printEndpointOptions();
    }

    private void printEmptyLine() {
        outputHandler.printEmptyLines();
    }


    private void printExitMessage() {
        outputHandler.printExitMessage();
    }

    private void printStartOption() {
        outputHandler.printStartOption();
    }

    private void printWelcomeMessage() {
        outputHandler.printWelcomeMessage();
    }

    private String getStartOption() {
        try {
            return inputHandler.getStartOption();
        } catch(IllegalArgumentException | IOException e) {
            outputHandler.printErrorMessage(e.getMessage());
            printStartOption();
            return getStartOption();
        }
    }
}
