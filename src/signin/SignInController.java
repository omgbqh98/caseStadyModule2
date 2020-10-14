package signin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
public class SignInController {
    @FXML
    private TextField nameText;
    @FXML
    private PasswordField passText;
    @FXML
    void login(ActionEvent event) throws IOException {
        if (nameText.getText().equals("admin") && passText.getText().equals("admin")){
            Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(getClass().getResource("../sample/sample.fxml"));
            Parent view= loader.load();
            Scene scene= new Scene(view, 900,550);
            stage.setScene(scene);
            stage.show();
        }else {
            Alert.AlertType alertAlertType;
            Alert alert= new Alert(AlertType.INFORMATION);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText(" Tên đăng nhập hoặc mật khẩu không hợp lệ ");
            alert.show();
        }
    }
}