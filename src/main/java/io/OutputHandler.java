package io;

import dto.DowntimeResult;

public class OutputHandler {

    public void printWelcomeMessage() {
        System.out.println("=======================================");
        System.out.println("|      👋🏻 Welcome to Downtimer!       |");
        System.out.println("|       Please choose an option       |");
        System.out.println("=======================================");
        System.out.println();
    }

    public void printStartOption() {
        System.out.println("1 - " + Color.GREEN +"Start Downtime Measurement " + Color.RESET + "     ");
        System.out.println("0 - " + Color.RED + "Exit " + Color.RESET + "                           ");
        System.out.println("---------------------------------------");
    }

    public void printExitMessage() {
        System.out.println("\nExiting...");
    }

    public void printEmptyLines() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    public void printEndpointOptions() {
        System.out.println(Color.GREEN + "Please enter the health check endpoint " + Color.RESET + " (e.g., http://example.com:8080/health) ");
        System.out.println("If you want to " + Color.RED + "exit" + Color.RESET + ", please enter 0");
        System.out.println("-----------------------------------------------------------------------------");
    }

    public void printErrorMessage(String input) {
        System.out.println(input);
    }

    public void printValidEndpointMessage(String endpoint) {
        System.out.println("[" + endpoint + "] is valid 👍\n");

    }

    public void printInvalidEndpointMessage(String endpoint) {
        System.out.println("\nThe endpoint [" + endpoint + "] is invalid. Please check and try again ☹️\n");
    }

    public void printDowntimeMeasurementOption() {
        System.out.println("1 - " + Color.GREEN +" Start Downtime Measurement " + Color.RESET + "     ");
        System.out.println("0 - " + Color.RED + " Exit " + Color.RESET + "                           ");
        System.out.println("---------------------------------------");
    }

    public void printResult(DowntimeResult downtimeResult) {
        System.out.println("⏳ Result");
        System.out.println("==============================================");
        System.out.println("|     start     |     end     |   downtime   |");
        System.out.println("|---------------|-------------|--------------|");
        System.out.println("|   " + downtimeResult.downtimeStart() + "   |   " + downtimeResult.downtimeEnd() + "   |     " + Color.CYAN + downtimeResult.duration() + Color.RESET + "    |");
        System.out.println("==============================================\n");
    }

    public void printRestartOption() {
        System.out.println("====================================================================");
        System.out.println("|     🎉 Downtime measurement has been successfully completed      |");
        System.out.println("|                      Please choose an option                     |");
        System.out.println("====================================================================\n");

        System.out.println("1 - " + Color.GREEN +"Try again? " + Color.RESET + "     ");
        System.out.println("0 - " + Color.RED + "Exit " + Color.RESET + "                           ");
        System.out.println("---------------------------------------");
    }
}
