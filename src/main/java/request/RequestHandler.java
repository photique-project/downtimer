package request;

import dto.DowntimeResult;
import io.Color;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RequestHandler {
    private static final String METHOD = "GET";
    private static final int TIMEOUT = 60000;
    private static final int WAITING_TIME = 200;

    public boolean validateEndpoint(String endpoint) {
        try {
            int responseCode = sendRequest(endpoint);

            return responseCode == HttpURLConnection.HTTP_OK;

        } catch (IOException e) {
            throw new RuntimeException("Server Error");
        }
    }

    public DowntimeResult startDowntimeMeasurement(String endpoint) {
        System.out.println("==============================================");
        System.out.println("|     📢 Currently waiting for downtime      |");
        System.out.println("|         " + Color.GREEN + "Please redeploy the server" + Color.RESET + "         |");
        System.out.println("==============================================\n");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime downtimeStart = null;
        LocalTime downtimeEnd = null;

        try {
            int responseCode = 0;

            System.out.print("Waiting for downtime...");

            while (true) {
                responseCode = sendRequest(endpoint);

                if (responseCode != HttpURLConnection.HTTP_OK) {
                    downtimeStart = LocalTime.now();
                    System.out.println("\n😴 " + Color.CYAN + "Downtime started at: " + downtimeStart.format(formatter) + Color.RESET + "\n");
                    break;
                }

                System.out.print(".");
                Thread.sleep(WAITING_TIME);
            }

            System.out.print("Waiting for server recovery...");

            while (true) {
                responseCode = sendRequest(endpoint);
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    downtimeEnd = LocalTime.now();

                    System.out.println("\n😁 " + Color.CYAN + "Downtime ended at: " + downtimeEnd.format(formatter) + Color.RESET +"\n");

                    break;
                }
                System.out.print(".");
                Thread.sleep(WAITING_TIME);
            }

            Duration duration = Duration.between(downtimeStart, downtimeEnd);

            return new DowntimeResult(downtimeStart.format(formatter), downtimeEnd.format(formatter), String.valueOf(duration.toSeconds()) + "sec");

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Server Error");
        }
    }

    private int sendRequest(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(METHOD);
        connection.setConnectTimeout(TIMEOUT);
        connection.setReadTimeout(TIMEOUT);

        return connection.getResponseCode();
    }
}