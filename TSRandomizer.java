import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class TSRandomizer extends Application 
{
	
	public void start(Stage stage)
	{
		ArrayList<Song> songHistory = new ArrayList<Song>();
		
		StackPane root = new StackPane();
		TabPane tabPane = new TabPane();
	 	HistoryPane hPane = new HistoryPane(songHistory);
		TSPane tsPane = new TSPane(songHistory, hPane);

		Tab tab1 = new Tab();
		tab1.setText("Randomize");
		tab1.setContent(tsPane);

		Tab tab2 = new Tab();
		tab2.setText("History");
		tab2.setContent(hPane);

		tabPane.getSelectionModel().select(0);
		tabPane.getTabs().addAll(tab1, tab2);

		root.getChildren().add(tabPane);

		Scene scene = new Scene(root, 600, 600); 
		stage.setTitle("Taylor Swift Song Randomizer");
		scene.getStylesheets().add("Stylesheets.css");
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[] args)
	{
		launch(args);
	}
}