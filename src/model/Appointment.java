package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;

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

    /**
     * adding an Appointment to the observable list
     * @param newAppointment specifies the Appointment being added
     */
    public static void addAppointment(Appointment newAppointment){
        allAppointments.add(newAppointment);
    }

    /**
     * deleting an Appointment from the observable list
     * @param selectedAppointment the appointment that is being deleted
     * @return deletes the Appointment if found
     */
    public static boolean deleteAppointment(Appointment selectedAppointment) {
        if(allAppointments.contains(selectedAppointment)){
            allAppointments.remove(selectedAppointment);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * finding the index of the Appointments
     * @param AppointmentId the appointment id being searched for
     * @return index
     */
    public static int getAppointmentIndex(int AppointmentId){

        for (int i = 0; i < allAppointments.size(); i++) {
            if (allAppointments.get(i).getAppointmentId() == AppointmentId) {
                return i;
            }
        }
        return 0;
    }

    /**
     * updating the Appointment to not create duplicates
     * @param index index of the Appointment
     * @param selectedAppointment the Appointment that is being searched for
     */
    public static void updateAppointment(int index, Appointment selectedAppointment) {

        for (int i = 0; i < allAppointments.size(); i++) {
            if (allAppointments.get(i).getAppointmentId() == selectedAppointment.getAppointmentId()) {
                allAppointments.set(i, selectedAppointment);
                break;
            }
        }
    }
}
