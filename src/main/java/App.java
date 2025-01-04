import config.AppConfig;

public class App {
    public static void main(String[] args) {
        DownTimer downTimer = new DownTimer(
            AppConfig.getOutputHandler(),
            AppConfig.getInputHandler(),
            AppConfig.getRequestHandler()
        );

        downTimer.start();
    }
}
