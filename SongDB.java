/*
 * This class is the Song Database. It will read songs from the songs.txt file that lists all Taylor Swift
 * songs and create song and album objects for each item. Songs and albums are separated by semicolons in 
 * the text file.
 */

import java.io.*;
import java.util.ArrayList;

public class SongDB 
{
    private ArrayList<Song> songs;
    private File file;
    private ArrayList<String> albums;

    public SongDB() 
    {
        try {
            file = new File("songs.txt");
            FileReader r = new FileReader(file);
            BufferedReader br = new BufferedReader(r); 
            songs = new ArrayList<Song>();
            albums = new ArrayList<String>();
            String line = br.readLine();
            while (line != null)
                {
                    Song song;
                    if (line.indexOf("Remix") != -1)
                    {
                        int semicolon = line.indexOf(";");
                        String songName = line.substring(0, semicolon);
                        song = new Song(songName, "remixes");
                        if (albums.indexOf("remixes") == -1)
                            albums.add("remixes");

                    }
                    else if (line.indexOf(";") != -1)
                    {
                        int semicolon = line.indexOf(";");
                        String songName = line.substring(0, semicolon);
                        String albumName = line.substring(semicolon+2);
                        song = new Song(songName, albumName);
                        if (albums.indexOf(albumName) == -1)
                            albums.add(albumName);
                    }
                    else
                    {
                        String songName = line;
                        song = new Song(songName);
                            if (albums.indexOf("singles") == -1)
                                albums.add("singles");
                    }
                    songs.add(song);
                    line = br.readLine();
                }
            br.close();
        }
        catch(FileNotFoundException fileExc) {
            System.out.println("songs.txt not found error");
        }
        catch(IOException exception) {
            System.out.println("Read string from the file error");
        }
    }

    public int getLength()
    {
        return songs.size();
    }

    public Song getSong(int i)
    {
        return songs.get(i);
    }

    public ArrayList<String> getAlbumList()
    {
        return albums;
    }
}