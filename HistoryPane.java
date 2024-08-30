// This pane will show the randomizer's history of selected songs

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import java.util.ArrayList;

public class HistoryPane extends VBox{
    
    private ArrayList<Song> songHistory;
	private ListView<Song> songLV;

	private Label title;
	private ObservableList<Song> data;

    public HistoryPane(ArrayList<Song> songHistory)
    {
        this.songHistory = songHistory;
        data = FXCollections.observableArrayList(songHistory);
        songLV = new ListView<Song>(data);
        songLV.setMaxSize(550, 600);
        setAlignment(Pos.TOP_CENTER);
        setSpacing(10);
        title = new Label("Song History");
        title.setStyle("-fx-font: normal 20px \"Spot Mono\";");

        getChildren().addAll(title, songLV);
    }

    public void updateSongList(Song s)
    {
        songHistory.add(s);
        data.add(s);
    }
}
