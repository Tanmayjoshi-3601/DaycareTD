/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * 
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import utility.FileUtil;

public class School {
    public static List<ClassRoom> classrooms;
    public static List<Student> studentlist;
    public static List<Teacher> teacherlist;

    private static final School instance = new School();
    private School (){
        classrooms = new ArrayList<>();
        teacherlist = new ArrayList<>();
        studentlist = new ArrayList<>();
        this.initStudentList("Students2.csv");
    }
    public static School getInstance( ) {
        return instance;
    }
      
    public void initStudentList(String csvFile){
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");
        List<String> data = (new FileUtil()).filereader(csvFile);
        for(String record: data){
            Student student = new Student();
            String[] field = record.split(",");
            try {
                student.setId(Integer.parseInt(field[0]));
                student.setDateOfBirth(DATE_FORMAT.parse(field[1]));
                student.setAge(Integer.parseInt(field[2]));
                student.setName(field[3]);
                student.setGpa(Double.parseDouble(field[4]));
                student.setEmergencyName(field[5]);
                student.setEmergencyPhone(field[6]);
                student.setPaddress(field[7]);
                student.setMmrVaccine1stDose(DATE_FORMAT.parse(field[8]));
                student.setMmrVaccine2ndDose(DATE_FORMAT.parse(field[9]));
                student.setVaricella1stDose(DATE_FORMAT.parse(field[10]));
                student.setVaricella2ndDose(DATE_FORMAT.parse(field[11]));
                
                this.addStudent(student);
                
            } catch(Exception e){
                e.printStackTrace();
            } 
        }
    }

    public void printClassRoominformation( ) {
//        for (ClassRoom C: classrooms) {
//            System.out.print(C.getTeacher().getName());
//        }
    }

    public static void addStudent(Student person){studentlist.add(person);}
    public static void addTeacher(Teacher person){teacherlist.add(person);}
    public static void addClassRoom(ClassRoom classroom){classrooms.add(classroom);}

    public static List<ClassRoom> getClassRooms() {return classrooms;}
    public static List<Student> getStudentlist() {return studentlist;}
    public static List<Teacher> getTeacherlist() {return teacherlist;}

    public static void viewStudentInformation(){
        System.out.println("****** Viewing Student list ******");
        studentlist.stream().forEach(System.out::println);
        System.out.println();
    }
    
    public static void viewTeacherInformation(){
        System.out.println("****** Viewing Teacher list ******");
        teacherlist.stream().forEach(System.out::println);
        System.out.println();
    }
    
}