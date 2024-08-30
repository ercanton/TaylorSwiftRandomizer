/*
 * This class is for the songs. It will create Song objects featuring the song name and the
 * album it is on. There are also getter and setter methods and a toString() method;
 */


public class Song 
{
    private String songName, albumName;
    
    public Song (String songName)
    {
        this.songName = songName;
        albumName = "singles";
    }

    public Song (String songName, String albumName)
    {
        this.songName = songName;
        this.albumName = albumName;
    }

    public String getSongName()
    {
        return songName;
    }

    public String getAlbumName()
    {
        return albumName;
    }

    public void setSongName(String song)
    {
        songName = song;
    }

    public void setAlbumName(String album)
    {
        albumName = album;
    }

    public String toString()
    {
        String result = songName;
        if (!albumName.equals("singles") && !albumName.equals("remixes"))
            result += "\n"+ albumName;
        return result;
    }
}
