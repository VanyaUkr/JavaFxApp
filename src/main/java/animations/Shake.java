package animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition tt;

    public Shake(Node node) {
        tt = new TranslateTransition(Duration.millis(80),node);
        tt.setFromX(0f);
        tt.setByX(8f);
        tt.setCycleCount(5);
        tt.setAutoReverse(true);
    }

    public void playAnimation () {
        tt.playFromStart();
    }
}
