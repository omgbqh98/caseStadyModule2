package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Food;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TableView<Food> table;
    @FXML
    private TableView<Food> tablePool;
    @FXML
    private TableView<Food> table2;
    @FXML
    private TableColumn<Food, Integer> idColumn;
    @FXML
    private TableColumn<Food, String> nameColumn;
    @FXML
    private TableColumn<Food, Integer> vndColumn;
    @FXML
    private TableColumn<Food, Integer> kgColumn;
    @FXML
    private TableColumn<Food, Integer> idColumn1;
    @FXML
    private TableColumn<Food, String> nameColumn1;
    @FXML
    private TableColumn<Food, Integer> vndColumn1;
    @FXML
    private TableColumn<Food, Integer> kgColumn1;
    @FXML
    private TableColumn<Food, Integer> thanhTienColumn;
    public ObservableList<Food> moneyList;
    public ObservableList<Food> foodListHeo;
    public ObservableList<Food> foodListGa;
    public ObservableList<Food> foodListBo;
    public ObservableList<Food> listPool;
    @FXML
    public TextField idText;
    @FXML
    public TextField nameText;
    @FXML
    public TextField vndText;
    @FXML
    public TextField kgText;
    @FXML
    public TextField buyKgText;
    @FXML
    public TextField buyVndText;
    @FXML
    public TextField buyIDText;
    @FXML
    public TextField buyNameText;
    @FXML
    private TextField searchField;
    @FXML
    private TextField newKgText;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moneyList =FXCollections.observableArrayList(
                readFile());
        foodListHeo = FXCollections.observableArrayList(
                readFile());
        listPool = FXCollections.observableArrayList(
                readFile());
        foodListBo = FXCollections.observableArrayList(
                readFile());
        foodListGa = FXCollections.observableArrayList(
                readFile());



        idColumn.setCellValueFactory(new PropertyValueFactory<Food, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
        vndColumn.setCellValueFactory(new PropertyValueFactory<Food, Integer>("vnd"));
        kgColumn.setCellValueFactory(new PropertyValueFactory<Food, Integer>("kg"));

        idColumn1.setCellValueFactory(new PropertyValueFactory<Food, Integer>("id"));
        nameColumn1.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
        vndColumn1.setCellValueFactory(new PropertyValueFactory<Food, Integer>("vnd"));
        kgColumn1.setCellValueFactory(new PropertyValueFactory<Food, Integer>("kg"));

        table2.setItems(moneyList);
//        table.setItems(foodListBo);
//        table.setItems(foodListGa);
//        table.setItems(foodListHeo);
        tablePool.setItems(listPool);
        thanhTienColumn.setCellValueFactory(new PropertyValueFactory<Food, Integer>("thanhTien"));

        this.search();
    }

    public void addList(ActionEvent event) {
        listPool.addAll(foodListHeo);
        foodListHeo.clear();
    }

    public void update(ActionEvent event) {
        Food selected = table.getSelectionModel().getSelectedItem();
        idText.setText(String.valueOf(selected.getId()));
        nameText.setText(String.valueOf(selected.getName()));
        kgText.setText(String.valueOf(selected.getKg()));
        vndText.setText(String.valueOf(selected.getVnd()));
        foodListHeo.remove(selected);

    }
    public void select(ActionEvent event) {
        Food selected = table.getSelectionModel().getSelectedItem();
        buyIDText.setText(String.valueOf(selected.getId()));
        buyNameText.setText(String.valueOf(selected.getName()));
        buyKgText.setText(String.valueOf(selected.getKg()));
        buyVndText.setText(String.valueOf(selected.getVnd()));
        foodListHeo.remove(selected);
    }
    public void buy(ActionEvent event) {
        Food money = new Food();
        money.setThanhTien(Integer.parseInt(buyVndText.getText())*Integer.parseInt(buyKgText.getText()));
        moneyList.add(money);
        foodListHeo.remove(money);
    }

    public void finish() {
        Food newFood = new Food();
        newFood.setId(Integer.parseInt(buyIDText.getText()));
        newFood.setName(buyNameText.getText());
        newFood.setVnd(Integer.parseInt(buyVndText.getText()));
        newFood.setKg((Integer.parseInt(buyKgText.getText()))-Integer.parseInt(newKgText.getText()));
        foodListHeo.add(newFood);
        buyIDText.clear();
        buyNameText.clear();
        buyVndText.clear();
        buyKgText.clear();
        newKgText.clear();
    }
    public void addHeo(ActionEvent event) {
        Food newFood = new Food();
        try {
            newFood.setId(Integer.parseInt(idText.getText()));
            newFood.setName(nameText.getText());
            newFood.setVnd(Integer.parseInt(vndText.getText()));
            newFood.setKg(Integer.parseInt(kgText.getText()));
            boolean check = true;
            for (int i = 0; i < foodListHeo.size(); i++) {
                if (Integer.parseInt(idText.getText()) == foodListHeo.get(i).getId()) {
                    check = false;
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("lỗi lặp");
                    alert.setHeaderText(null);
                    alert.setContentText("ID đã tồn tại");
                    alert.showAndWait();
                }
            }
            if (check) {
                foodListHeo.add(newFood);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input!");
            alert.setHeaderText(null);
            alert.setContentText("Chưa nhập đúng thông tin");
            alert.showAndWait();
        }
        idText.clear();
        nameText.clear();
        vndText.clear();
        kgText.clear();

    }
    public void addGa(ActionEvent event) {
        Food newFood = new Food();
        try {
            newFood.setId(Integer.parseInt(idText.getText()));
            newFood.setName(nameText.getText());
            newFood.setVnd(Integer.parseInt(vndText.getText()));
            newFood.setKg(Integer.parseInt(kgText.getText()));
            boolean check = true;
            for (int i = 0; i < foodListGa.size(); i++) {
                if (Integer.parseInt(idText.getText()) == foodListGa.get(i).getId()) {
                    check = false;
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("lỗi lặp");
                    alert.setHeaderText(null);
                    alert.setContentText("ID đã tồn tại");
                    alert.showAndWait();
                }
            }
            if (check) {
                foodListGa.add(newFood);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input!");
            alert.setHeaderText(null);
            alert.setContentText("Chưa nhập đúng thông tin");
            alert.showAndWait();
        }
        idText.clear();
        nameText.clear();
        vndText.clear();
        kgText.clear();

    }
//    public void addBo(ActionEvent event) {
//        Food newFood1 = new Food();
//        try {
//            newFood1.setId(Integer.parseInt(idText.getText()));
//            newFood1.setName(nameText.getText());
//            newFood1.setVnd(Integer.parseInt(vndText.getText()));
//            newFood1.setKg(Integer.parseInt(kgText.getText()));
//            boolean check = true;
//            for (int i = 0; i < foodListBo.size(); i++) {
//                if (Integer.parseInt(idText.getText()) == foodListBo.get(i).getId()) {
//                    check = false;
//                    Alert alert = new Alert(Alert.AlertType.WARNING);
//                    alert.setTitle("lỗi lặp");
//                    alert.setHeaderText(null);
//                    alert.setContentText("ID đã tồn tại");
//                    alert.showAndWait();
//                }
//            }
//            if (check) {
//                foodListBo.add(newFood1);
//            }
//        } catch (Exception e) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Invalid Input!");
//            alert.setHeaderText(null);
//            alert.setContentText("Please try again");
//            alert.showAndWait();
//        }
//        idText.clear();
//        nameText.clear();
//        vndText.clear();
//        kgText.clear();
//    }

    public void search() {
        FilteredList<Food> searchList = new FilteredList<>(foodListHeo, b-> true);
        searchField.textProperty().addListener(((observable, oldValue, newValue) -> {
            searchList.setPredicate(food -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercaseValue = newValue.toLowerCase();
                if (String.valueOf(food.getId()).indexOf(lowercaseValue) != -1) return true;
                else if (food.getName().toLowerCase().indexOf(lowercaseValue) != -1) return true;
                else if (String.valueOf(food.getVnd()).indexOf(lowercaseValue) != -1) return true;
                else if (String.valueOf(food.getKg()).indexOf(lowercaseValue) != -1) return true;
                else return false;
            });
        }));
        table.setItems(searchList);
    }
    public void delete(ActionEvent event) {
        Food selectedFood = table.getSelectionModel().getSelectedItem();
        if (selectedFood != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc muốn xóa?");
            ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> choice = alert.showAndWait();
            if (choice.get().getButtonData() == ButtonBar.ButtonData.YES) {
                foodListHeo.remove(selectedFood);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("Please choose one!");
            alert.showAndWait();
        }
    }
    public void delete2(ActionEvent event) {
        Food selected1 = table2.getSelectionModel().getSelectedItem();
        moneyList.remove(selected1);
    }
    public void writeFile(){
        FileOutputStream file;
        ObjectOutputStream object;
        try {
            file= new FileOutputStream("Food.txt");
            object = new ObjectOutputStream(file);
            for(Food food : foodListHeo){
                object.writeObject(food);
            }
            for(Food food : foodListGa){
                object.writeObject(food);
            }
            for(Food food : foodListBo){
                object.writeObject(food);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }



    public List<Food> readFile(){
        List<Food> list = new ArrayList<>();
        FileInputStream file;
        ObjectInputStream object;
        try {
            file= new FileInputStream("Food.txt");
            object= new ObjectInputStream(file);
            while (true){
                list.add((Food) object.readObject());
            }
        } catch (EOFException exception){
            exception.getMessage();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }


    public void editName(TableColumn.CellEditEvent cellEditEvent) {
        Food sneakerSelected = table.getSelectionModel().getSelectedItem();
        sneakerSelected.setName(cellEditEvent.getNewValue().toString());
    }
}

