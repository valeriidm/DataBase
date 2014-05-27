package musicstoreproject;

/**
 *
 * @author Leo Dubovyi, Valerii Doroshenko
 * Vanier College
 * System Analysis
 * Course Project
 * @Project  MusicStore @Class User
 */
public class User {

    public String[] getName() {
        return name;
    }

    public String[] getAddress() {
        return address;
    }
    private String[] name; /* Fstname Lstname username e-mail*/
    private String[] address; /* Country Provance Street ZIP Phone*/

    /**
     * Default constructor
     * @Postcondition: members of class was initiliazed
     */
    public User(){
        this.address = new String[5];
        this.name = new String[6];
    }

    /**
     * Method to set the fields belongs to address group
     * @param address[]
     * @Postconditon: address fields setted
     */
    public void setAddress(String ... address){
        for (int i= 0; i < this.address.length; i++){
            this.address[i] = address[i];
        }
    }

    /**
     * Method to set the fields belongs to name group
     * @param name[]
     * @Postconditon: name fields setted
     */
    public void setName(String ... name){
        for (int i= 0; i < this.name.length; i++){
            this.name[i] = name[i];
        }
    }


}
