package model;

import javafx.scene.control.Alert;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class Appointment {

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

    public Appointment(){

    }

    public Appointment(int appointmentId, Timestamp startDate, int customerId) {
        this.appointmentId = appointmentId;
        this.startDate = startDate;
        this.customerId = customerId;
    }

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

    public Appointment(int appointmentId, String title, String description, String type, Timestamp startDate, Timestamp endDate, int customerId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerId = customerId;
        this.contactId = contactId;
    }

    public Appointment(int appointmentId, Timestamp startDate) {
        this.appointmentId = appointmentId;
        this.startDate = startDate;
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
     * @return the start day in the pattern shown
     * @param startDate the date to be formatted
     */
    public String getStartDay(Timestamp startDate){

        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return startDate.toLocalDateTime().format(date);

    }

    /**
     * @return the end day in the pattern shown
     * @param endDate the date to be formatted
     */
    public String getEndDay(Timestamp endDate){

        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return endDate.toLocalDateTime().format(date);

    }

    /**
     * @return the start time in the pattern shown
     * @param startDate the date to be formatted
     */
    public String getStartTime(Timestamp startDate){

        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        return startDate.toLocalDateTime().format(time);

    }

    /**
     * @return the end time in the pattern shown
     * @param endDate the date to be formatted
     */
    public String getEndTime(Timestamp endDate){

        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
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

    /**
     * creates an alert for when the title is left empty
     */
    public static void emptyTitle(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Empty Title Field");
        alert.setContentText("The title field must have a value.");
        alert.showAndWait();
    }

    /**
     * creates an alert for when the description is left empty
     */
    public static void emptyDescription(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Empty Description Field");
        alert.setContentText("The description field must have a value.");
        alert.showAndWait();
    }

    /**
     * creates an alert for when the location is left empty
     */
    public static void emptyLocation(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Empty Location Field");
        alert.setContentText("The location field must have a value.");
        alert.showAndWait();
    }

    /**
     * creates an alert for when the type is left empty
     */
    public static void emptyType(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Empty Type Field");
        alert.setContentText("The type field must have a value.");
        alert.showAndWait();
    }

    /**
     * creates an alert for when the customer id is invalid
     */
    public static void invalidCustomerId(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Customer ID Field");
        alert.setContentText("Please enter a valid number for the Customer ID  field.");
        alert.showAndWait();
    }

    /**
     * creates an alert for when the start date is left empty
     */
    public static void emptyStartDate(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Empty Start Date Field");
        alert.setContentText("The start date field must have a value.");
        alert.showAndWait();
    }
    /**
     * creates an alert for when the start time is left empty
     */
    public static void emptyStartTime(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Empty Start Time Field");
        alert.setContentText("The Customer ID field must have a value.");
        alert.showAndWait();
    }
    /**
     * creates an alert for when the end date is left empty
     */
    public static void emptyEndDate(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Empty End Date Field");
        alert.setContentText("The end date field must have a value.");
        alert.showAndWait();
    }
    /**
     * creates an alert for when the customer id is left empty
     */
    public static void emptyEndTime(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Empty End Time Field");
        alert.setContentText("The end time field must have a value.");
        alert.showAndWait();
    }

    /**
     * creates an alert for when the user id is invalid
     */
    public static void invalidUserId(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid User ID Field");
        alert.setContentText("The User ID field must contain a valid number.");
        alert.showAndWait();
    }

    /**
     * creates an alert for when the start time is not before the end time
     */
    public static void startBeforeEnd() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Start and End Time");
        alert.setContentText("The start time must be before the end time.");
        alert.showAndWait();
    }
}
