import java.util.ArrayList;

public class Capturer {

    private final ArrayList<Capturable> capturables = new ArrayList<>();


    public void add(Capturable capturable) {
        capturables.add(capturable);
    }

    public void captureAll() {
        for (Capturable capturable : capturables)
            capturable.capture();
    }
}
