import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.UUID;

/**
 * JavaFX GUI для ЛР 16/18.
 */
public class MainApp extends Application {
    private Store store;
    private TextArea listArea;
    private TextArea detailArea;

    @Override
    public void start(Stage stage) {
        store = FileService.loadStore();
        stage.setTitle("Магазин одягу");

        GridPane form = new GridPane();
        form.setHgap(8);
        form.setVgap(8);
        form.setPadding(new Insets(10));

        TextField nameField = new TextField();
        TextField brandField = new TextField();
        TextField priceField = new TextField();
        TextField sizeField = new TextField();
        TextField colorField = new TextField();
        ComboBox<String> typeBox = new ComboBox<String>();
        typeBox.getItems().addAll("PANTS", "SHIRT", "JACKET", "SKIRT");
        typeBox.getSelectionModel().selectFirst();

        form.add(new Label("Тип"), 0, 0);
        form.add(typeBox, 1, 0);
        form.add(new Label("Назва"), 0, 1);
        form.add(nameField, 1, 1);
        form.add(new Label("Бренд"), 0, 2);
        form.add(brandField, 1, 2);
        form.add(new Label("Ціна"), 0, 3);
        form.add(priceField, 1, 3);
        form.add(new Label("Розмір"), 0, 4);
        form.add(sizeField, 1, 4);
        form.add(new Label("Колір"), 0, 5);
        form.add(colorField, 1, 5);

        Button addBtn = new Button("Додати");
        addBtn.setOnAction(e -> {
            try {
                String type = typeBox.getValue();
                String name = nameField.getText().trim();
                String brand = brandField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());
                String size = sizeField.getText().trim();
                String color = colorField.getText().trim();
                SizeCategory cat = SizeCategory.M;
                Clothes clothes;
                if ("PANTS".equals(type)) {
                    clothes = new Pants(name, brand, price, size, color, cat, 32, 100);
                } else if ("SHIRT".equals(type)) {
                    clothes = new Shirt(name, brand, price, size, color, cat, "Classic", "Long");
                } else if ("JACKET".equals(type)) {
                    clothes = new Jacket(name, brand, price, size, color, cat, false, "поліестер");
                } else {
                    clothes = new Skirt(name, brand, price, size, color, cat, 60, "А-силует");
                }
                store.addNewClothes(clothes, 1);
                refreshList();
                detailArea.setText("Додано:\n" + clothes);
            } catch (RuntimeException ex) {
                detailArea.setText("Помилка: " + ex.getMessage());
            }
        });

        listArea = new TextArea();
        listArea.setEditable(false);
        listArea.setPrefRowCount(8);

        TextField uuidField = new TextField();
        Button findBtn = new Button("Знайти за UUID");
        findBtn.setOnAction(e -> {
            try {
                UUID uuid = UUID.fromString(uuidField.getText().trim());
                Clothes found = store.findByUuid(uuid);
                if (found == null) {
                    detailArea.setText("Не знайдено");
                } else {
                    detailArea.setText(found.toString());
                }
            } catch (IllegalArgumentException ex) {
                detailArea.setText("Некоректний UUID");
            }
        });

        detailArea = new TextArea();
        detailArea.setEditable(false);
        detailArea.setPrefRowCount(6);

        VBox root = new VBox(10, form, addBtn, new Label("Список (коротко):"), listArea,
                new Label("UUID:"), uuidField, findBtn, new Label("Деталі:"), detailArea);
        root.setPadding(new Insets(10));

        refreshList();
        stage.setScene(new Scene(root, 520, 620));
        stage.show();
    }

    private void refreshList() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Clothes> items = store.getItems();
        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i).shortInfo()).append('\n');
        }
        if (items.isEmpty()) {
            sb.append("(порожньо)");
        }
        listArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
