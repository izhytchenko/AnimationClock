import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

/**
 * In the center of the screen is a circle (like a clock).
 * An arrow runs along the circumference of the clock.
 * In the center of the screen is a 5-second report timer
 */
public class AnimationClock extends WindowProgram {
    public static final int RADIUS_OF_CLOCK = 200; // value for GOval size
    public static final int MILLISECOND_OF_WORK = 5000; // millisecond util finished animation

    public void run() {
        createClockBorder();
        createClockLineAndCounter();
    }

    /**
     * Create GLine (clock line) and GLabel (timer) in the middle of screen.
     * Get time of starting and finish program
     */
    private void createClockLineAndCounter() {
        GLine clockLine = new GLine(getWidth() / 2.0, getHeight() / 2.0, RADIUS_OF_CLOCK, RADIUS_OF_CLOCK);
        add(clockLine);
        GLabel counterLabel = new GLabel("", getWidth() / 2.0, getHeight() / 2.0);
        counterLabel.setFont("Verdana-30");
        add(counterLabel);
        long startTime = System.currentTimeMillis(); // get current time of starting program
        long finishTime = startTime + MILLISECOND_OF_WORK; // count time for finish

        rotateLineAndCheckTime(clockLine, counterLabel, startTime, finishTime);
    }

    /**
     * Rotate clock line around the circle. Change position every 10 millisecond
     * there are 60 position on the circle for arrow line
     * When 5 second is finished animation interrupted
     *
     * @param clockLine    arrow on circle (clock line)
     * @param counterLabel timer in the middle of screen
     * @param startTime    value in millisecond when animation started
     * @param finishTime   value in millisecond when animation finished
     */
    private void rotateLineAndCheckTime(GLine clockLine, GLabel counterLabel, long startTime, long finishTime) {
        int tack = 60; // amount of position for arrow on circle
        for (int i = 0; ; i++) { // endless
            // formula for calculate every new position on circle for arrow
            clockLine.setEndPoint(Math.cos(i * Math.PI / (tack / 2.0) - Math.PI / 2) * RADIUS_OF_CLOCK + getWidth() / 2.0,
                    Math.sin(i * Math.PI / (tack / 2.0) - Math.PI / 2) * RADIUS_OF_CLOCK + getHeight() / 2.0);

            counterLabel.setLabel(String.valueOf((System.currentTimeMillis() - startTime)));
            counterLabel.setLocation(getWidth() / 2.0 - counterLabel.getWidth() / 2, getHeight() / 2.0);

            if (System.currentTimeMillis() >= finishTime) {
                break; // stop animation when reached finishTime
            } else {
                pause(10);
            }
        }
    }

    /**
     * create GOval (clock) in the middle of screen
     */
    private void createClockBorder() {
        add(new GOval(getWidth() / 2.0 - RADIUS_OF_CLOCK, getHeight() / 2.0 - RADIUS_OF_CLOCK,
                RADIUS_OF_CLOCK * 2, RADIUS_OF_CLOCK * 2));
    }
}
