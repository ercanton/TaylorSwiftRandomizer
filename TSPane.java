import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import java.util.ArrayList;
import javafx.scene.text.*;

public class TSPane extends VBox
{
    ArrayList<Song> songHistory;
    ListView<Song> songLV;
	private ObservableList<Song> data;
	
	private HistoryPane hPane;
    
    private SongDB songDB;
    private Button generate, reset;
    private Label song;
    private VBox filters;
    private ArrayList<String> albumNameList;
    private ArrayList<Album> albumList;

    public TSPane(ArrayList<Song> songHistory, HistoryPane hPane)
    {
        this.songHistory = songHistory;
        this.hPane = hPane;

        songDB = new SongDB();
        albumNameList = songDB.getAlbumList();
        albumList = new ArrayList<Album>();

        filters = new VBox();
        filters.setSpacing(5);

        for (String album : albumNameList)
        {
            Album a = new Album(album, new CheckBox("Exclude songs from " + album), false);
            if (a.getName().equals("singles"))
                a.setCheckBox("Exclude Singles");
            else if (a.getName().equals("remixes"))
                a.setCheckBox("Exclude Remixes");
            albumList.add(a);
            filters.getChildren().addAll(a.getCheckBox());
            a.getCheckBox().setOnAction(new CheckBoxHandler());
        }

        generate = new Button("Generate Random Song");
        reset = new Button ("Reset Filters");
        
        HBox songBox = new HBox();
        songBox.setAlignment(Pos.CENTER);

        song = new Label("");
        song.setStyle("-fx-font: normal 20px \"Spot Mono\";");
        song.setTextAlignment(TextAlignment.CENTER);
        
        songBox.getChildren().add(song);

        data = FXCollections.observableArrayList(songHistory);
        songLV = new ListView<Song>(data);

        setSpacing(5);
        setPadding(new Insets(10));
        getChildren().addAll(filters, reset, generate, songBox);

        generate.setOnAction(new ButtonHandler());
        reset.setOnAction(new ButtonHandler());
    }

    private class CheckBoxHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
            for (Album a : albumList)
            {
                if (a.getCheckBox().isSelected())
                    a.setExcluded(true);
                else
                    a.setExcluded(false);
            }
        }
    }

    public Song filtered(int max)
    {
        int songNum = (int) (Math.random()*(max));
        Song random = songDB.getSong(songNum);
        String album = random.getAlbumName();
        for (Album a : albumList)
        {
            if (a.getExcluded() && album.equals(a.getName()))
                return filtered(max);
        }
        updateSongList(random);
        return random;
    }

    public void updateSongList(Song s)
    {
        songHistory.add(s);
        data.add(s);
        hPane.updateSongList(s);
    }

    private class ButtonHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
            if (e.getSource() == generate)
            {
                boolean allChecked = true;
                for (Album a : albumList)
                {
                    if (!a.getExcluded())
                        allChecked = false;
                }
                if (allChecked == true)
                    song.setText("No songs match filter settings");
                int max = songDB.getLength();
                Song s = filtered(max);
                song.setText(s.toString());
            }
            else if (e.getSource() == reset)
            {
                for (Album a : albumList)
                {
                    a.getCheckBox().setSelected(false);
                    a.setExcluded(false);
                }
            }
        }
    }
}
