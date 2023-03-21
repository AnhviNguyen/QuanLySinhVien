package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class loginController implements Initializable{
	
	@FXML
    private PasswordField password;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private Button loginButton;

    @FXML
    private Button close;

    @FXML
    private TextField username;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private double x=0; 
    private double y=0;
    
    
    
    public void loginAdmin() {
    	String sql= "SELECT * FROM admin WHERE username = ? and password = ?";
    	
    	connect = Database.connectDb();
    	try {
    		Alert alert ;
    		
    		prepare= connect.prepareStatement(sql);
    		prepare.setString(1 , username.getText());
    		prepare.setString(2 , password.getText());
    		
    		result= prepare.executeQuery();
    		
    		//check if field are empty
    		if (username.getText().isEmpty() || password.getText().isEmpty()) {
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Please fill all blank field");
    			alert.showAndWait();
    		}
    		else {
    			if (result.next()) {
    				// then proceed to dashboard form
    				
    				getData.username = username.getText();
    				
    				alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Information Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Successfully login !!!");
        			alert.showAndWait();
        			
        			// to hide the login form
        			loginButton.getScene().getWindow().hide();
        			
        			//link your dashboard
        			Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        			Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                    root.setOnMousePressed((MouseEvent event) ->{
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });
                    
                    root.setOnMouseDragged((MouseEvent event) ->{
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });
                    
                    stage.initStyle(StageStyle.TRANSPARENT);
                    
                    stage.setScene(scene);
                    stage.show();
        			
    			}
    			else {
    				//then error message will appear
    				alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Error Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Wrong username/ Password");
        			alert.showAndWait();
    			}
    		}
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void Close() {
    	System.exit(0);
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
