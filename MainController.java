import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Map;
import java.util.TreeMap;

public class MainController {

    @FXML
    private TextField searchField; // TextField for search input

    @FXML
    private ListView<String> listView; // ListView to display key-value pairs
    private final TreeMap<String, String> treeMap = new TreeMap<>();// TreeMap to store key-value pairs
    private final ObservableList<String> dataList = FXCollections.observableArrayList();// ObservableList to bind data to ListView
    private final AlertType alertType = AlertType.ERROR; // Default alert type for error messages

    // Initialize the ListView with the dataList
    @FXML
    public void initialize() {
        listView.setItems(dataList);
    }

    @FXML
    private void handleAdd() {
        // Handle adding a new item
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add New Item");
            stage.showAndWait();

            AddController controller = loader.getController();
            String key = controller.getKey();
            String value = controller.getValue();
            if (key != null && !key.trim().isEmpty() && value != null && !value.trim().isEmpty()) {
                treeMap.put(key, value);
                updateListView();
            }
        } catch (Exception e) {
            // Handle exceptions by displaying an error alert
            handleOperationException(new OperationException("Failed to load the add dialog.", e));
        }
    }

    // Handle removing an item
    @FXML
    private void handleRemove() {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String key = selectedItem.split(":")[0].trim();
            treeMap.remove(key);
            updateListView();
        } else {
            // Show a warning alert if no item is selected
            showAlert(AlertType.WARNING, "No Selection", "No item selected", "Please select an item to remove.");
        }
    }

    // Handle updating an item
    @FXML
    private void handleUpdate() {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("update.fxml"));
                Parent root = loader.load();
                UpdateController controller = loader.getController();
                String key = selectedItem.split(":")[0].trim();
                controller.setInitialValues(key, treeMap.get(key));

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Update Item");
                stage.showAndWait();

                String newKey = controller.getKey();
                String newValue = controller.getValue();
                if (newKey != null && !newKey.trim().isEmpty() && newValue != null && !newValue.trim().isEmpty()) {
                    treeMap.remove(key);
                    treeMap.put(newKey, newValue);
                    updateListView();
                }
            } catch (Exception e) {
                // Handle exceptions by displaying an error alert
                handleOperationException(new OperationException("Failed to load the update dialog.", e));
            }
        } else {
            // Show a warning alert if no item is selected
            showAlert(AlertType.WARNING, "No Selection", "No item selected", "Please select an item to update.");
        }
    }

    // Handle searching for an item
    @FXML
    private void handleSearch() {
        String query = searchField.getText();
        if (query != null && !query.trim().isEmpty()) {
            boolean found = false;
            for (Map.Entry<String, String> entry : treeMap.entrySet()) {
                if (entry.getKey().startsWith(query)) {
                    listView.getSelectionModel().select(entry.getKey() + ": " + entry.getValue());
                    listView.scrollTo(entry.getKey() + ": " + entry.getValue());
                    found = true;
                    break;
                }
            }
            if (!found) {
                // Show an information alert if the item is not found
                showAlert(AlertType.INFORMATION, "Not Found", "Item not found", "The item you searched for was not found.");
            }
        } else {
            // Show a warning alert if no search query is entered
            showAlert(AlertType.WARNING, "No Input", "No search query entered", "Please enter a search query.");
        }
    }

    // Update the ListView with the current items in the TreeMap
    private void updateListView() {
        dataList.clear();
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            dataList.add(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Show an alert with the specified parameters
    private void showAlert(AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Handle operation exceptions by displaying an error alert
    private void handleOperationException(OperationException e) {
        showAlert(alertType, "Operation Error", e.getMessage(), e.getCause().getMessage());
    }
}