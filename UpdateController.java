
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateController {

    @FXML
    private TextField keyField;

    @FXML
    private TextField valueField;

    private String key;
    private String value;


    // Method to set the initial values in the key and value fields
    public void setInitialValues(String key, String value) {
        keyField.setText(key);
        valueField.setText(value);
    }

    @FXML
    private void handleUpdate() {
        key = keyField.getText();
        value = valueField.getText();

        // Check if both key and value are not null and not empty
        if (key != null && value != null && !key.trim().isEmpty() && !value.trim().isEmpty()) {
            // If both key and value are valid, close the window
            Stage stage = (Stage) keyField.getScene().getWindow();
            stage.close();
        }
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
