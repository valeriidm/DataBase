package musicstoreproject;

/**
 *
 * @author Leo Dubovyi, Valerii Doroshenko
 * Vanier College
 * System Analysis
 * Course Project
 * @Project  MusicStore @Class Song
 */

public class Song {
    private int id;
    private String title;
    private String singer;
    private String album;
    private double price;
    private int qtty;

    /**
     * Method to set value of id
     * @param id
     * @Postcondition: value of id was setted
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method to get the value of id
     * @Postcondition: value of id returned
     */
    public int getId() {
        return id;
    }

    /**
     * Method to set value of quantity
     * @param qtty
     * @Postcondition: value of quantity was setted
     */
    public void setQtty(int qtty) {
        this.qtty = qtty;
    }

    /**
     * Method to get the value of quantity
     * @Postcondition: value of quantity returned
     */
    public int getQtty() {
        return qtty;
    }

    /**
     * Default constructor
     * @param title
     * @param singer
     * @param album
     * @param price
     * @Postcondition: define all class members with passed parameters
     */
    public Song(String title, String singer, String album, double price) {
        this.title = title;
        this.singer = singer;
        this.album = album;
        this.price = price;
    }

    public Song() {
        this.title = null;
        this.singer = null;
        this.album = null;
        this.price = 0.0;
    }

    /**
     * Method to set value of price
     * @param price
     * @Postcondition: value of price was setted
     */
    public void setPrice(double price) {
        if(price >= 0)
            this.price = price;
    }

    /**
     * Method to get the value of price
     * @Postcondition: value of price returned
     */
    public double getPrice() {
        return price;
    }

    /**
     * Method to get the value of album
     * @Postcondition: value of album returned
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Method to set value of album
     * @param album
     * @Postcondition: value of album was setted
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * Method to set value of title
     * @param title
     * @Postcondition: value of title was setted
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method to get the value of title
     * @Postcondition: value of title returned
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method to get the value of singer
     * @Postcondition: value of singer returned
     */
    public String getSinger() {
        return singer;
    }

    /**
     * Method to set value of singer
     * @param singer
     * @Postcondition: value of singer was setted
     */
    public void setSinger(String singer) {
        this.singer = singer;
    }

}
