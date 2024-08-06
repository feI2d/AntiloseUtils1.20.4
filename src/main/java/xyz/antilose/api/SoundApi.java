package xyz.antilose.api;

import net.minecraft.util.Identifier;
import xyz.antilose.MinecraftWrapper;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class SoundApi implements MinecraftWrapper {

    private static Clip currentClip = null;
    public static void playSound(String sound, float value, boolean nonstop) {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
        }
        try {
            currentClip = AudioSystem.getClip();
            InputStream is = mc.getResourceManager().getResource(new Identifier("antilose-utils", "sounds/" + sound + ".wav")).get().getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);

            if (audioInputStream == null) {
                System.out.println("Sound not found!");
                return;
            }

            currentClip.open(audioInputStream);
            currentClip.start();
            FloatControl floatControl = (FloatControl) currentClip.getControl(FloatControl.Type.MASTER_GAIN);
            float min = floatControl.getMinimum();
            float max = floatControl.getMaximum();
            float volumeInDecibels = (float) (min * (1 - (value / 100.0)) + max * (value / 100.0));
            floatControl.setValue(volumeInDecibels);

            if (nonstop) {
                currentClip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        currentClip.setFramePosition(0);
                        currentClip.start();
                    }
                });
            }
        } catch (Exception exception) {

        }
    }

}
