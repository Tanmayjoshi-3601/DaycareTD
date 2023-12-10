/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

/**
 *
 * 
 */

import Models.Person;
import Models.School;
import Models.Student;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utility.FileUtil;

public class StudentFactory {
    private static StudentFactory instance;

    private StudentFactory(){
        instance = null;
    }

    public static synchronized StudentFactory getInstance(){
        if(instance == null){
            instance = new StudentFactory();
        }
        return instance;
    }
    
    public static Student getObject(int id, Date dob, int age, String name, 
            double gpa, String contactName,String emergencyPhone,String address,
            Date mmrVacc1, Date mmrVacc2, 
            Date varicella1, Date varicella2, Date registrationDate ) {
        
        Student tmpstudent = new Student(id, dob, age, name, gpa,
                      contactName, emergencyPhone,address, mmrVacc1, mmrVacc2,
                      varicella1,  varicella2, registrationDate);
        writeStudentToCSV(tmpstudent,"Students2.csv");
        School.addStudent(tmpstudent);
        return tmpstudent;
    }
    public static void writeStudentToCSV(Student student, String csvFileName)
    {
         SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");
        
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFileName, true))) {
            
             
             String output = String.valueOf(student.getId())+ "," + DATE_FORMAT.format(student.getDateOfBirth()) + "," + String.valueOf(student.getAge()) + ","+ student.getName() + 
                    "," + String.valueOf(student.getGpa()) + "," + student.getEmergencyName() + "," + student.getEmergencyPhone() + "," 
                     + student.getPaddress() + "," + DATE_FORMAT.format(student.getMmrVaccine1stDose()) + "," + DATE_FORMAT.format(student.getMmrVaccine2ndDose())
                     + "," + DATE_FORMAT.format(student.getVaricella1stDose()) + "," + DATE_FORMAT.format(student.getVaricella2ndDose()) ;
                       

            bw.write(output + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
   

    public static List<Person> addObject(String csvFile) {
//        String[] fileOutput = csvFile.split("\\r?\\n");
	FileUtil fu = new FileUtil();
        System.out.println("Inside addObject method");
        List<String> fileOutput = fu.filereader(csvFile);
        Person tmpStudent = null;
        List<Person> tmplist = new ArrayList<>();
        for (String inputLine : fileOutput) {
            try{
//                System.out.println(inputLine);
                String[] values = inputLine.split(",");                
                int id;
                try{
                    id =Integer.parseInt(values[0]);
                }catch(java.lang.NumberFormatException e){
                    id =Integer.parseInt(values[0].substring(1));
                }
                String dateOfBirthStr = values[1];
                Date dob = null;
                try{
                    dob = new SimpleDateFormat("MM-dd-yyyy").parse(dateOfBirthStr);
                }catch(Exception e){
//                    System.err.println("Exception ocurred : " + e);
                }
                int age = Integer.parseInt(values[2]);
                String name = values[3];
                double gpa = Double.parseDouble(values[4]);
                String contactName = values[5];
                String contactPhone = values[6];
                String pad = values[7];
                
                
                Date mmrVacc1 = null;
                try{
                    String mmrVaccine1 = values[8];
                    mmrVacc1 = new SimpleDateFormat("MM-dd-yyyy").parse(mmrVaccine1);
                }catch(Exception e){
//                    System.err.println("Exception ocurred : " + e);
                }
                

                Date mmrVacc2 = null;
                try{
                    String mmrVaccine2 = values[9];
                    mmrVacc2 = new SimpleDateFormat("MM-dd-yyyy").parse(mmrVaccine2);
                }catch(Exception e){
//                    System.err.println("Exception ocurred : " + e);
                }
                
                
                Date varicellaVacc1 = null;
                try{
                    String varicella1 = values[10];
                    varicellaVacc1 = new SimpleDateFormat("MM-dd-yyyy").parse(varicella1);
                }catch(Exception e){
//                    System.err.println("Exception ocurred : " + e);
                }
                
                Date varicellaVacc2 = null;
                try{
                    String varicella2 = values[11];
                    varicellaVacc2 = new SimpleDateFormat("MM-dd-yyyy").parse(varicella2);
                }catch(Exception e){
//                    System.err.println("Exception ocurred : " + e);
                }
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

                // Get the current date
                LocalDate currentDate = LocalDate.now();
                LocalDateTime localDateTime = LocalDateTime.now();
                Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
      
                tmpStudent = StudentFactory.getObject(id, dob, age, name, gpa,
                      contactName, contactPhone,pad, mmrVacc1, mmrVacc2, varicellaVacc1,
                      varicellaVacc2, date);
//                System.out.println(tmpStudent.toString());
                tmplist.add(tmpStudent);
            }catch(Exception e){
                System.err.println("Exception ocurred : " + e.getStackTrace()[0].getLineNumber());
                continue;
            }
            
        }
        return tmplist;
    }
    
    public static void addStudentObject(String fileName, String studentData) {
        FileUtil fu =  new FileUtil();
        fu.fileStudentWriter(studentData, fileName);
    }
}

