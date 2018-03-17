import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainStage extends Application {

	private Stage primaryStage;
	protected static Scene scene;
	static Pane pane;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader Loader = new FXMLLoader();
		Loader.setLocation(MainStage.class.getResource("2048.fxml"));
		pane = Loader.load();
		MainStage.scene = new Scene(pane);
		this.primaryStage = primaryStage;
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}

}
