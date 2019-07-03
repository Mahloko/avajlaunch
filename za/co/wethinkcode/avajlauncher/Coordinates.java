package za.co.wethinkcode.avajlauncher;

public class Coordinates
{
    /*
    * Attributes
    */
    private int longitude;
    private int latitude;
    private int height;

    /*
    * Constructor
    */
    public Coordinates(int longitude, int latitude, int height)
    {
        this.longitude =  longitude >= 0 ? longitude : 0;
        this.latitude = latitude >= 0 ? latitude : 0 ;
        this.height = height <= 100 ? ((height >= 0) ? height : 0) : 100;
        return;
    }

    /*
    * Methods
    */
    public int getLongitude() { return longitude; }

    public int getLatitude() { return latitude; }
    
    public int getHeight() { return height; }
}
