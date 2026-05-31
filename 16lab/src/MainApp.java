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
 * JavaFX GUI РґР»СЏ Р›Р  16/18.
 */
public class MainApp extends Application {
    private Store store;
    private TextArea listArea;
    private TextArea detailArea;

    @Override
    public void start(Stage stage) {
        store = FileService.loadStore();
        stage.setTitle("РњР°РіР°Р·РёРЅ РѕРґСЏРіСѓ");

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

        form.add(new Label("РўРёРї"), 0, 0);
        form.add(typeBox, 1, 0);
        form.add(new Label("РќР°Р·РІР°"), 0, 1);
        form.add(nameField, 1, 1);
        form.add(new Label("Р‘СЂРµРЅРґ"), 0, 2);
        form.add(brandField, 1, 2);
        form.add(new Label("Р¦С–РЅР°"), 0, 3);
        form.add(priceField, 1, 3);
        form.add(new Label("Р РѕР·РјС–СЂ"), 0, 4);
        form.add(sizeField, 1, 4);
        form.add(new Label("РљРѕР»С–СЂ"), 0, 5);
        form.add(colorField, 1, 5);

        Button addBtn = new Button("Р”РѕРґР°С‚Рё");
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
                    clothes = new Jacket(name, brand, price, size, color, cat, false, "РїРѕР»С–РµСЃС‚РµСЂ");
                } else {
                    clothes = new Skirt(name, brand, price, size, color, cat, 60, "Рђ-СЃРёР»СѓРµС‚");
                }
                store.addNewClothes(clothes, 1);
                refreshList();
                detailArea.setText("Р”РѕРґР°РЅРѕ:\n" + clothes);
            } catch (RuntimeException ex) {
                detailArea.setText("РџРѕРјРёР»РєР°: " + ex.getMessage());
            }
        });

        listArea = new TextArea();
        listArea.setEditable(false);
        listArea.setPrefRowCount(8);

        TextField uuidField = new TextField();
        Button findBtn = new Button("Р—РЅР°Р№С‚Рё Р·Р° UUID");
        findBtn.setOnAction(e -> {
            try {
                UUID uuid = UUID.fromString(uuidField.getText().trim());
                Clothes found = store.findByUuid(uuid);
                if (found == null) {
                    detailArea.setText("РќРµ Р·РЅР°Р№РґРµРЅРѕ");
                } else {
                    detailArea.setText(found.toString());
                }
            } catch (IllegalArgumentException ex) {
                detailArea.setText("РќРµРєРѕСЂРµРєС‚РЅРёР№ UUID");
            }
        });

        detailArea = new TextArea();
        detailArea.setEditable(false);
        detailArea.setPrefRowCount(6);

        VBox root = new VBox(10, form, addBtn, new Label("РЎРїРёСЃРѕРє (РєРѕСЂРѕС‚РєРѕ):"), listArea,
                new Label("UUID:"), uuidField, findBtn, new Label("Р”РµС‚Р°Р»С–:"), detailArea);
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
            sb.append("(РїРѕСЂРѕР¶РЅСЊРѕ)");
        }
        listArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

