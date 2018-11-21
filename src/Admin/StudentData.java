/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ggupt
 */
public class StudentData {
    private StringProperty ID;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty address;
    private StringProperty email;
    private StringProperty subject;
    private StringProperty fee;
    private StringProperty payment;
    private StringProperty timings;
    
    public StudentData(String id, String first, String last, String add, String email, String sub, String fee, String time, String payment){
        this.ID = new SimpleStringProperty(id);
        this.firstName = new SimpleStringProperty(first);
        this.lastName = new SimpleStringProperty(last);
        this.address = new SimpleStringProperty(add);
        this.email = new SimpleStringProperty(email);
        this.subject = new SimpleStringProperty(sub);
        this.fee = new SimpleStringProperty(fee);
        this.timings = new SimpleStringProperty(time);
        this.payment = new SimpleStringProperty(payment);
    }

    /**
     * @return the ID
     */
    public StringProperty getID() {
        return ID;
    }

    /**
     * @return the firstName
     */
    public StringProperty getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public StringProperty getLastName() {
        return lastName;
    }

    /**
     * @return the address
     */
    public StringProperty getAddress() {
        return address;
    }

    /**
     * @return the email
     */
    public StringProperty getEmail() {
        return email;
    }

    /**
     * @return the subject
     */
    public StringProperty getSubject() {
        return subject;
    }

    /**
     * @return the fee
     */
    public StringProperty getFee() {
        return fee;
    }

    /**
     * @return the timings
     */
    public StringProperty getTimings() {
        return timings;
    }

    public StringProperty getPayment(){
        return payment;
    }
    
    public void setPayment(StringProperty payment){
        this.payment = payment;
    }
    /**
     * @param ID the ID to set
     */
   
    public void setID(StringProperty ID) {
        this.ID = ID;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(StringProperty firstName) {
        this.firstName = firstName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(StringProperty lastName) {
        this.lastName = lastName;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(StringProperty address) {
        this.address = address;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(StringProperty email) {
        this.email = email;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(StringProperty subject) {
        this.subject = subject;
    }

    /**
     * @param fee the fee to set
     */
    public void setFee(StringProperty fee) {
        this.fee = fee;
    }

    /**
     * @param timings the timings to set
     */
    public void setTimings(StringProperty timings) {
        this.timings = timings;
    }
    
    public StringProperty IDProperty(){
        return ID;
    }
    public StringProperty firstNameProperty(){
        return firstName;
    }
    public StringProperty lastNameProperty(){
        return lastName;
    }
    public StringProperty addressProperty(){
        return address;
    }
    public StringProperty emailProperty(){
        return email;
    }
    public StringProperty subjectProperty(){
        return subject;
    }
    public StringProperty feeProperty(){
        return fee;
    }
    public StringProperty timingsProperty(){
        return timings;
    }
    public StringProperty paymentProperty(){
        return payment;
    }
    
}
