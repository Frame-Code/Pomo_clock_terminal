import service.PlayerService;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Objects;

public class Player {
    private Player() {}

    public static void playStop() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        playAudio(PlayerService.getRandomStopPath());
    }

    public static void playStart() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        playAudio(PlayerService.getRandomStartPath());
    }

    private static void playAudio(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.
                getAudioInputStream(new BufferedInputStream(Objects.requireNonNull(Player.class.getResourceAsStream(filePath)))));
        clip.start();
        Thread.sleep(clip.getMicrosecondLength() / 1000);
    }

}
