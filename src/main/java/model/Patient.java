package model;

public class Patient {
    private int id;
    private String firstName;
    private String lastName;
    private String department;
    private String doctor;
    private String nurse;
    private String phone;
    private String email;
    private String address;
    private String payment;

    public Patient(int id, String firstName, String lastName, String department, String doctor, String nurse, String phone, String email, String address, String payment) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.doctor = doctor;
        this.nurse = nurse;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDepartment() {
        return department;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getNurse() {
        return nurse;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPayment() {
        return payment;
    }
}
