/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Date;

/**
 *
 * 
 */
public class Person {
    
    private int id;
    private Date dateOfBirth;
    private int age;
    private String name;
    private ClassRoom ck;
    private Date mmrVaccine1stDose;
    private Date mmrVaccine2ndDose;
    private Date Varicella1stDose;
    private Date Varicella2ndDose;
    private String emergencyName;
    private String emergencyPhone;

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public Date getMmrVaccine1stDose() {
        return mmrVaccine1stDose;
    }

    public void setMmrVaccine1stDose(Date mmrVaccine1stDose) {
        this.mmrVaccine1stDose = mmrVaccine1stDose;
    }

    public Date getMmrVaccine2ndDose() {
        return mmrVaccine2ndDose;
    }

    public void setMmrVaccine2ndDose(Date mmrVaccine2ndDose) {
        this.mmrVaccine2ndDose = mmrVaccine2ndDose;
    }

    public Date getVaricella1stDose() {
        return Varicella1stDose;
    }

    public void setVaricella1stDose(Date Varicella1stDose) {
        this.Varicella1stDose = Varicella1stDose;
    }

    public Date getVaricella2ndDose() {
        return Varicella2ndDose;
    }

    public void setVaricella2ndDose(Date Varicella2ndDose) {
        this.Varicella2ndDose = Varicella2ndDose;
    }
    
    public Person(int id, Date dateOfBirth, int age, String name,
            String contactName,String emergencyPhone,
            Date mmrVacc1, Date mmrVacc2, 
            Date varicella1, Date varicella2){
        this.dateOfBirth = dateOfBirth;
        this.id = id;
        this.age = age;
        this.name = name;
        this.emergencyPhone = emergencyPhone;
        this.emergencyName = contactName;
        this.Varicella1stDose = varicella1;
        this.Varicella2ndDose = varicella2;
        this.mmrVaccine1stDose = mmrVacc1;
        this.mmrVaccine2ndDose = mmrVacc2;
    }
    
    public Person(int id, Date dateOfBirth, int age, String name){
        this.dateOfBirth = dateOfBirth;
        this.id = id;
        this.age = age;
        this.name = name;
    }
    
    public Person(){
    
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassRoom getClassroom(){
        return this.ck;
    }
    public void setClassroom(ClassRoom classroom){
        this.ck = classroom;
    }

    Object getClassRoom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}
