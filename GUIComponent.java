import javax.swing.JComponent;

public abstract class GUIComponent {
    // The key is used to identify and manage GUI components in the factory
    protected String key;

    // Constructor
    public GUIComponent(String key) {
        this.key = key;
    }

    // Abstract method that needs to be implemented by concrete classes
    public abstract JComponent createComponent();

    // Other common methods or properties can be added here
    // For example, you could add common event listeners or styling methods
}
