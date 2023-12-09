import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class GUIComponentFactory {
    private final Map<String, JComponent> flyweights = new HashMap<>();

    public JButton getButton(String key) {
        return (JButton) flyweights.computeIfAbsent(key, k -> new JButton(k));
    }

    public JLabel getLabel(String key) {
        return (JLabel) flyweights.computeIfAbsent(key, k -> new JLabel(k));
    }

    // Additional methods for other components can be added here
}
