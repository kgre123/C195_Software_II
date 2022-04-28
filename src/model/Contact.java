package model;

public class Contact {

    private int contactId;
    private String contactName;

    public Contact(int contactId, String contactName) {
        this.contactId = contactId;
        this.contactName = contactName;
    }

    /**
     * @return the contact id
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * @param contactId setter
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * @return the contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName setter
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public String toString(){
        return(contactName); }
}
