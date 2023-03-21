package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
 
public class Main extends Application {
	
	private double x=0;
	private double y=0;
	
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			
			root.setOnMousePressed((MouseEvent event) -> {
				x= event.getSceneX();
				y= event.getSceneY();
			});
			
			root.setOnMouseDragged((MouseEvent event) -> {
				stage.setX(event.getScreenX()-x);
				stage.setY(event.getScreenY()-y);
				stage.setOpacity(.8);
			});
			
			root.setOnMouseReleased((MouseEvent event) -> {
				stage.setOpacity(1);
			});
			
			stage.setResizable(false);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
