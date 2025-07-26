package service;

public class PlayerService {
    private static final String[] stopPaths = {"/stop1.wav", "/stop2.wav", "/stop3.wav", "/stop4.wav"};
    private static final String[] startPaths = {"/start1.wav", "/start2.wav", "/start3.wav", "/start4.wav"};

    public static String getRandomStopPath() {
        return stopPaths[(int) (Math.random() * stopPaths.length)];
    }

    public static String getRandomStartPath() {
        return startPaths[(int) (Math.random() * startPaths.length)];
    }
}
