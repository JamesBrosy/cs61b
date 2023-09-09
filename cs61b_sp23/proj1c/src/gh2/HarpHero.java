package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Created by Blander on 9/9/23.
 */
public class HarpHero {
    public static Harp[] strings = new Harp[37];

    public static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    /** get the frequency corresponding to the ith key character */
    public static double getFreq(int index) {
        return 440 * Math.pow(2, (index - 24.0) / 12.0);
    }

    /** get the sum of the samples of strings */
    public static double getTotalSample() {
        double sample = 0;
        for (Harp string : strings) {
            sample += string.sample();
        }
        return sample;
    }

    /* advance the simulation of each guitar string by one step */
    public static void allFlip() {
        for (Harp string : strings) {
            string.flip();
        }
    }

    public static void main(String[] args) {

        /* initialize all the guitar strings */
        for (int i = 0; i < keyboard.length(); ++i) {
            strings[i] = new Harp(getFreq(i));
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int idx = keyboard.indexOf(key);
                if (idx != -1) {
                    strings[idx].pluck();
                }
            }

            /* play the sample on standard audio */
            StdAudio.play(getTotalSample());

            allFlip();
        }
    }

}
