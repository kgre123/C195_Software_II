package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class Appointment {

    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private Timestamp startDate;
    private Timestamp endDate;
    private int customerId;
    private int userId;
    private int contactId;

    public Appointment(int appointmentId, String title, String description, String location, String type, Timestamp startDate, Timestamp endDate, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * @return the appointment id
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * @param appointmentId setter
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title setter
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description setter
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location setter
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type setter
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the start date
     */
    public Timestamp getStartDate() {
        return startDate;
    }

    /**
     * @param startDate setter
     */
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the end date
     */
    public Timestamp getEndDate() {
        return endDate;
    }

    /**
     * @param endDate setter
     */
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getStartDay(Timestamp startDate){

        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return startDate.toLocalDateTime().format(date);

    }

    public String getEndDay(Timestamp endDate){

        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return endDate.toLocalDateTime().format(date);

    }

    public String getStartTime(Timestamp startDate){

        DateTimeFormatter time = DateTimeFormatter.ofPattern("hh:mm:ss");
        return startDate.toLocalDateTime().format(time);

    }

    public String getEndTime(Timestamp endDate){

        DateTimeFormatter time = DateTimeFormatter.ofPattern("hh:mm:ss");
        return endDate.toLocalDateTime().format(time);

    }
    /**
     * @return the customer id
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId setter
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId setter
     */
    public void setUserId(int userId) {
        this.userId = userId;
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

}
