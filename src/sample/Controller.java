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
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Controller implements Initializable {
    @FXML
    private TableView<Food> table;
    @FXML
    private TableView<Food> tablePool;
    @FXML
    private TableView<Food> table2;
    @FXML
    private TableView<Food> tableHistory;
    @FXML
    private TableColumn<Food, Integer> vndColumnHistory;
    @FXML
    private TableColumn<Food, String> nameColumnHistory;
    @FXML
    private TableColumn<Food, Integer> kgColumnHistory;
    @FXML
    private TableColumn<Food, Integer> thanhTienColumnHistory;
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
    @FXML
    private TableColumn<Food, String> nameColimn2;
    @FXML
    private TableColumn<Food, String> timeColum;
    @FXML
    private TableColumn<Food, Integer> vndColumn2;
    @FXML
    private TableColumn<Food, Integer> kgColumn2;
    public ObservableList<Food> moneyList;
    public ObservableList<Food> foodListAll;
    public ObservableList<Food> foodListGa;
    public ObservableList<Food> foodListBo;
    public ObservableList<Food> listPool;
    public ObservableList<Food> foodListHeo;
    public ObservableList<Food> listHistory;
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
        NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
        moneyList =FXCollections.observableArrayList();
        foodListHeo =FXCollections.observableArrayList();
        foodListAll = FXCollections.observableArrayList();
        listPool = FXCollections.observableArrayList(readFile());
        foodListBo = FXCollections.observableArrayList();
        foodListGa = FXCollections.observableArrayList(readFile());
        listHistory = FXCollections.observableArrayList();
        foodListAll.addAll(foodListHeo);
        foodListAll.addAll(foodListGa);
        foodListAll.addAll(foodListBo);

        idColumn.setCellValueFactory(new PropertyValueFactory<Food, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
        vndColumn.setCellValueFactory(new PropertyValueFactory<Food, Integer>("vnd"));
        kgColumn.setCellValueFactory(new PropertyValueFactory<Food, Integer>("kg"));

        idColumn1.setCellValueFactory(new PropertyValueFactory<Food, Integer>("id"));
        nameColumn1.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
        vndColumn1.setCellValueFactory(new PropertyValueFactory<Food, Integer>("vnd"));
        kgColumn1.setCellValueFactory(new PropertyValueFactory<Food, Integer>("kg"));
        table.setItems(foodListHeo);
        table2.setItems(moneyList);
        table.setItems(foodListAll);
        table.setItems(foodListBo);
        table.setItems(foodListGa);
        tablePool.setItems(listPool);

        nameColimn2.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
        timeColum.setCellValueFactory(new PropertyValueFactory<Food, String>("time"));
        vndColumn2.setCellValueFactory(new PropertyValueFactory<Food, Integer>("vnd"));
        kgColumn2.setCellValueFactory(new PropertyValueFactory<Food, Integer>("kg"));
        thanhTienColumn.setCellValueFactory(new PropertyValueFactory<Food, Integer>("thanhTien"));

        nameColumnHistory.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
        vndColumnHistory.setCellValueFactory(new PropertyValueFactory<Food, Integer>("vnd"));
        kgColumnHistory.setCellValueFactory(new PropertyValueFactory<Food, Integer>("kg"));
        thanhTienColumnHistory.setCellValueFactory(new PropertyValueFactory<Food, Integer>("thanhTien"));

        this.search();
    }

    public void history(ActionEvent event) {
        moneyList.clear();
        moneyList.addAll(listHistory);
    }
    public void selectListGa(ActionEvent event) {
        foodListAll.clear();
        foodListAll.addAll(foodListGa);
    }
    public void selectListHeo(ActionEvent event) {
        foodListAll.clear();
        foodListAll.addAll(foodListHeo);
    }
    public void selectListBo(ActionEvent event) {
        foodListAll.clear();
        foodListAll.addAll(foodListBo);
    }
    public void slectListAll(ActionEvent event) {
        foodListAll.clear();
        foodListAll.addAll(listPool);
    }

    public void update(ActionEvent event) throws ParseException {
        Food selected = table.getSelectionModel().getSelectedItem();
        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
        Number number= nf.parse(selected.getVnd());
        nameText.setText(String.valueOf(selected.getName()));
        kgText.setText(String.valueOf(selected.getKg()));
        vndText.setText(String.valueOf(number));
        foodListAll.remove(selected);
        for (int i = 0; i < listPool.size(); i++) {
            if (listPool.get(i).getName().equals(selected.getName()) ) {
                listPool.remove(listPool.get(i));
            }
        }
    }
    public void select(ActionEvent event)  {
        Food selected = table.getSelectionModel().getSelectedItem();
        buyIDText.setText(String.valueOf(selected.getId()));
        buyNameText.setText(String.valueOf(selected.getName()));
        buyKgText.setText(String.valueOf(selected.getKg()));
        buyVndText.setText(String.valueOf(selected.getVnd()));
        foodListAll.remove(selected);
    }
    public void buy(ActionEvent event) throws ParseException {
        Food money = new Food();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd/MM/yy");
        LocalDateTime now = LocalDateTime.now();
        String timeIn = dtf.format(now);
        money.setTime(timeIn);
        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
        Number gia = nf.parse(buyVndText.getText());
        long gia2 = (long) gia;
        money.setName(buyNameText.getText());
        money.setVnd(buyVndText.getText());
        money.setKg(parseInt(newKgText.getText()));
        money.setThanhTien((gia2) * Long.parseLong(buyKgText.getText()));
        if (Integer.parseInt(buyKgText.getText()) < Integer.parseInt(newKgText.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("lỗi lặp");
            alert.setHeaderText(null);
            alert.setContentText("Số Lượng KG không đủ");
            alert.showAndWait();
        } else {
            moneyList.add(money);
            listHistory.add(moneyList.get(moneyList.size()-1));
        }
        foodListAll.remove(money);
    }

    public void finish() {
        Food newFood = new Food();
        newFood.setId(parseInt(buyIDText.getText()));
        newFood.setName(buyNameText.getText());
        newFood.setVnd(buyVndText.getText());
        newFood.setKg((parseInt(buyKgText.getText()))- parseInt(newKgText.getText()));
        if (newFood.getKg() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Hết");
            alert.setHeaderText(null);
            alert.setContentText("Đã hết hàng");
            alert.showAndWait();
            buyIDText.clear();
            buyNameText.clear();
            buyVndText.clear();
            buyKgText.clear();
            newKgText.clear();
        } else {
            foodListAll.add(newFood);
            buyIDText.clear();
            buyNameText.clear();
            buyVndText.clear();
            buyKgText.clear();
            newKgText.clear();
        }
    }
    public void addHeo(ActionEvent event) {
        Food newFood = new Food();
        try {
            newFood.setName(nameText.getText());
            NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
            String vnd = nf.format(Integer.parseInt(vndText.getText()));
            newFood.setVnd(vnd);
            newFood.setKg(parseInt(kgText.getText()));
                foodListHeo.add(newFood);
                foodListAll.add(foodListHeo.get(foodListHeo.size()-1));
                foodListHeo.remove(newFood);
                foodListHeo.add(newFood);
                listPool.add(foodListHeo.get(foodListHeo.size()-1));
                nameText.clear();
                vndText.clear();
                kgText.clear();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input!");
            alert.setHeaderText(null);
            alert.setContentText("lỗi nhập thông tin");
            alert.showAndWait();
        }


    }
    public void addGa(ActionEvent event) {
        Food newFood = new Food();
        try {
            NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
            String vnd = nf.format(Integer.parseInt(vndText.getText()));
            newFood.setName(nameText.getText());
            newFood.setVnd(vnd);
            newFood.setKg(parseInt(kgText.getText()));
                foodListGa.add(newFood);
                foodListAll.add(foodListGa.get(foodListGa.size()-1));
                foodListGa.remove(newFood);
                foodListGa.add(newFood);
                listPool.add(foodListGa.get(foodListGa.size()-1));
                nameText.clear();
                vndText.clear();
                kgText.clear();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input!");
            alert.setHeaderText(null);
            alert.setContentText("Chưa nhập đúng thông tin");
            alert.showAndWait();
        }


    }
    public void addBo(ActionEvent event) {
        Food newFood1 = new Food();
        try {
            NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
            String vnd = nf.format(Integer.parseInt(vndText.getText()));
            newFood1.setName(nameText.getText());
            newFood1.setVnd(vnd);
            newFood1.setKg(parseInt(kgText.getText()));
                    foodListBo.add(newFood1);
                    foodListAll.add(foodListBo.get(foodListBo.size()-1));
                    foodListBo.remove(newFood1);
                    foodListBo.add(newFood1);
                    listPool.add(foodListBo.get(foodListBo.size()-1));
                    nameText.clear();
                    vndText.clear();
                    kgText.clear();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input!");
            alert.setHeaderText(null);
            alert.setContentText("Please try again");
            alert.showAndWait();
        }
    }

    public void search() {
        FilteredList<Food> searchList = new FilteredList<>(foodListAll, b-> true);
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
                foodListAll.remove(selectedFood);
                for (int i = 0; i < listPool.size(); i++) {
                    if (listPool.get(i).getId() == selectedFood.getId()) {
                        listPool.remove(listPool.get(i));
                    }
                }
                for (int i = 0; i < foodListGa.size(); i++) {
                    if (foodListGa.get(i).getId() == selectedFood.getId()) {
                        foodListGa.remove(foodListGa.get(i));
                    }
                }
                for (int i = 0; i < foodListHeo.size(); i++) {
                    if (foodListHeo.get(i).getId() == selectedFood.getId()) {
                        foodListHeo.remove(foodListHeo.get(i));
                    }
                }
                for (int i = 0; i < foodListBo.size(); i++) {
                    if (foodListBo.get(i).getId() == selectedFood.getId()) {
                        foodListBo.remove(foodListBo.get(i));
                    }
                }
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
    public void deletePool(ActionEvent event) {
        Food selected1 = tablePool.getSelectionModel().getSelectedItem();
        listPool.remove(selected1);
    }


    public void writeFile(){
        FileOutputStream fileHeo;
        ObjectOutputStream objectHeo;
        try {
            fileHeo= new FileOutputStream("Food.txt");
            objectHeo = new ObjectOutputStream(fileHeo);
            for(Food food : foodListGa){
                objectHeo.writeObject(food);
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
}

