import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController {

    @FXML
    private TextField keyField; // TextField for inputting the key

    @FXML
    private TextField valueField; // TextField for inputting the value

    private String key; // Variable to store the key entered by the user
    private String value; // Variable to store the value entered by the user


    // Method called when the add button is clicked
    @FXML
    private void handleAdd() {

        key = keyField.getText();
        value = valueField.getText();

        // Check if both key and value are not null and not empty
        if (key != null && !key.trim().isEmpty() && value != null && !value.trim().isEmpty()) {
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
