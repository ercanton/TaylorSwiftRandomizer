/*
 * This class is for creating the albums. Each album has a name, a checkbox for the JavaFX visual and 
 * a boolean excluded stating if the checkbox is checked or not, determining if the album should be 
 * filtered out. There are also getter and setter methods.
 */


import javafx.scene.control.*;

public class Album 
{

    private String name; 
    private CheckBox checkbox;
    private boolean excluded; 

    public Album(String name, CheckBox checkbox, boolean excluded)
    {
        this.name = name;
        this.checkbox = checkbox;
        this.excluded = excluded;
    }

    public String getName()
    {
        return name;
    }

    public CheckBox getCheckBox()
    {
        return checkbox;
    }

    public boolean getExcluded()
    {
        return excluded;
    }

    public void setName(String album)
    {
        name = album;
    }

    public void setExcluded(boolean b)
    {
        excluded = b;
    }

    public void setCheckBox(String newTitle)
    {
        checkbox.setText(newTitle);
    }
}
