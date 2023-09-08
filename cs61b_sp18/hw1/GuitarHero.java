import edu.princeton.cs.algs4.StdAudio;
import synthesizer.GuitarString;


/**
 * Created by Blander on 9/8/23.
 */
public class GuitarHero {
    public static GuitarString[] strings = new GuitarString[37];

    public static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    /** get the frequency corresponding to the ith key character */
    public static double getFreq(int index) {
        return 440 * Math.pow(2, (index - 24.0) / 12.0);
    }

    /** get the sum of the samples of strings */
    public static double getTotalSample() {
        double sample = 0;
        for (GuitarString string : strings) {
            sample += string.sample();
        }
        return sample;
    }

    /* advance the simulation of each guitar string by one step */
    public static void allTic() {
        for (GuitarString string : strings) {
            string.tic();
        }
    }

    public static void main(String[] args) {

        /* initialize all the guitar strings */
        for (int i = 0; i < keyboard.length(); ++i) {
            strings[i] = new GuitarString(getFreq(i));
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

            allTic();
        }
    }
}
