/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import utility.FileUtil;

/**
 *
 * 
 */
public class TeacherFactory {
    private static TeacherFactory instance;

    private TeacherFactory(){
        instance = null;
    }

    public static synchronized TeacherFactory getInstance(){
        if(instance == null){
            instance = new TeacherFactory();
        }
        return instance;
    }
    
    public static List<Person> addObject(String csvFile) {

        FileUtil fu = new FileUtil();
        List<String> fileOutput = fu.filereader(csvFile);
        Person tmpTeacher = null;
        
        List<Person> tmplist = new ArrayList<>();
        for (String inputLine : fileOutput) {
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
                System.err.println("Exception ocurred : " + e);
            }
            
            int age = Integer.parseInt(values[2]);
            String name = values[3];
            int credits = Integer.parseInt(values[4]);
            int salary = Integer.parseInt(values[5]);
            tmpTeacher = TeacherFactory.getObject(id, dob, age, name, credits, salary);
            tmplist.add(tmpTeacher);
        }
        return tmplist;
    }
    
    public static Teacher getObject(int id, Date dob, int age, String name, int credits, int salary){
        Teacher tmpteacher = new Teacher(id, dob, age, name, credits, salary);
        School.addTeacher(tmpteacher);
        return tmpteacher;
    }
    
    public static void addTeacherObject(String fileName, String teacherData) {
        FileUtil fu =  new FileUtil();
        fu.fileTeacherWriter(teacherData, fileName);
    }
}
