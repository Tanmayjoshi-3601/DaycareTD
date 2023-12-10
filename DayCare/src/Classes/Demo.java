/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import static Classes.School.studentlist;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 *
 * 
 */
public class Demo {
    
    public static final Map<String, Integer> studentRatioRules = new HashMap<>(){};

    static{
        studentRatioRules.put("6-12", 4);
        studentRatioRules.put("13-24", 5);
        studentRatioRules.put("25-35", 6);
        studentRatioRules.put("36-47", 8);
        studentRatioRules.put("48-59", 12);
        studentRatioRules.put("60-Above", 15);
    }
    
    
    public void run(){
        School schoolInstance = School.getInstance();
        Path relativePathToDumpFile = Paths.get("", "src", "Resources", "Students.csv");
        String studentCsvFile = relativePathToDumpFile.toAbsolutePath().toString();
        StudentFactory.getInstance().addObject(studentCsvFile);
        schoolInstance.viewStudentInformation();
                

        Path teacherPathToDumpFile = Paths.get("", "src", "Resources", "Teachers.csv");
        String teacherCsvFile = teacherPathToDumpFile.toAbsolutePath().toString();
        TeacherFactory.getInstance().addObject(teacherCsvFile);
        schoolInstance.viewTeacherInformation();
        
        System.out.println("*** Creating groups with teachers and stundets **** ");
        
        List<Student> studentList = schoolInstance.getStudentlist();
        List<Teacher> teacherList = schoolInstance.getTeacherlist();
        
        int getClassRooms = this.getNumberOfClassesToCreate(studentList, teacherList);
        System.out.println("*** Total Classrooms to Create : " + getClassRooms);
        while(getClassRooms > 0){
            schoolInstance.addClassRoom(new ClassRoom("Class_" + getClassRooms));
            getClassRooms--;
        }
        System.out.println("*** Created New Classroom Successfully");
//        this.createGroups(schoolInstance);
        Map<String, Integer> studentDistribution = getAgeDistribution(schoolInstance.getStudentlist());
        Map<String, Integer> groupDistribution = this.getGroupsDistribution(studentDistribution);
        
        for (Map.Entry entry : groupDistribution.entrySet()) {
            System.out.println(entry.getKey().toString() +  " : "  + entry.getValue().toString());
        }
        
        
        System.out.println("*** Creating Groups for Teachers and Students");
        List<ClassRoom> classRoomList = schoolInstance.getClassRooms();
        List<Teacher> teacherNewList = new ArrayList<Teacher>();
        for(ClassRoom classRoomObj : classRoomList){
            for ( Teacher teacherObj : teacherList){
                for (Map.Entry entry : groupDistribution.entrySet()) {
                    if(Integer.parseInt(entry.getValue().toString()) > 0){
                        System.out.println(teacherObj.getName());
                        teacherObj.setAgeGroupAssigned(entry.getKey().toString());
                        teacherNewList.add(teacherObj);
                        groupDistribution.put(entry.getKey().toString(),
                                    Integer.parseInt(entry.getValue().toString()) - 1);
                        System.out.println(entry.getKey().toString() +  " has value "  + entry.getValue().toString());
                        break;
                    }               
                    
                } 
                classRoomObj.teacherStudentGroup.put(teacherObj, new ArrayList<Student>());
                
                if (classRoomObj.teacherStudentGroup.size() == 3){
                    break;
                }
                
            }
        }
        
        Map<String, List<Student>> studentsPerGroup = 
                (studentList.stream().collect(groupingBy(Student::getAgeGroup, toList())));
        
        List<Student> studentLit = schoolInstance.getStudentlist();
        
        for(ClassRoom classRoomObj : classRoomList){
            for (Map.Entry entry : classRoomObj.teacherStudentGroup.entrySet()){
                
                System.out.println("#### Processing for Teacher : " + entry.getKey());

                System.out.println(entry.getKey());
                String ageGroup = ((Teacher) entry.getKey()).getAgeGroupAssigned();
                int maxSize = studentRatioRules.get(ageGroup);
                System.out.println("Max size allowed : " + maxSize);
                ArrayList<Student> studentTempList = (ArrayList<Student>) studentsPerGroup.get(((Teacher) entry.getKey()).getAgeGroupAssigned());
                System.out.println(studentTempList);
                
                ArrayList<Student> listToAddData = new ArrayList<>(); 
                int valueTillIterate = 0;
                if(studentTempList.size() > maxSize ){
                    valueTillIterate = maxSize;
                }
                else{
                    valueTillIterate = studentTempList.size();
                }
                
                for(int i=0; i < valueTillIterate; i++){
                    try{
                        listToAddData.add(studentTempList.get(i));
                    }catch(Exception e){
                        break;
                    }
                }
                
                for(int i=0; i < valueTillIterate; i++){
                    try{
                        studentTempList.remove(0);
                    }catch(Exception e){
                        break;
                    }
                }
                System.out.println("Removed Elements from Student List");
                System.out.println(studentTempList);
                studentsPerGroup.put(ageGroup, studentTempList);
                System.out.println("**** Adding Below Students to Teacher ***** ");
                System.out.println(entry.getKey());
                System.out.println(listToAddData.toString());
                classRoomObj.teacherStudentGroup.put((Teacher) entry.getKey(), listToAddData);
            } 
        }
        
        System.out.println("");
        System.out.println("");
        System.out.println("************** Final Summary of the Mapping **************");
        for(ClassRoom classRoomObj : classRoomList){
            for (Map.Entry entry : classRoomObj.teacherStudentGroup.entrySet()){
                System.out.println("Inside To Check if the mapping is present Or not");
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
            }
        }
        
        schoolInstance.classrooms = classRoomList;
        
    }
    
    static <T> Collection<List<T>> partitionBasedOnSize(List<T> inputList, int size) {
        final AtomicInteger counter = new AtomicInteger(0);
        return inputList.stream()
                    .collect(Collectors.groupingBy(s -> counter.getAndIncrement()/size))
                    .values();
    }

    
    
    public int getNumberOfClassesToCreate(List<Student> studentList, 
                   List<Teacher> teacherList){
        Map<String, Integer> studentDistribution = getAgeDistribution(studentList);

        Map<String, Integer> groupDistribution = this.getGroupsDistribution(studentDistribution);

        int totalStudents = 0;

        for (Map.Entry entry : groupDistribution.entrySet()) { 
          String key = entry.getKey().toString();
          int groupSize = Integer.parseInt(entry.getValue().toString());
          int expectedGroupSize = Integer.parseInt(studentRatioRules.get(key).toString()); 
          // do whatever with value1 and value2 
          totalStudents = (groupSize * expectedGroupSize);
        }
        int totalClassrooms = totalStudents / 30;
        totalClassrooms++;
        return totalClassrooms;
        
    }
    
    
    
    public Map<String, Integer> getAgeDistribution(List<Student> studentList){
        Map<String, Integer> studentDistribution = new HashMap<String, Integer>();
        for(Student pk : studentList){
            if (studentDistribution.containsKey(pk.getAgeGroup())) {

                studentDistribution.put(pk.getAgeGroup(),
                        studentDistribution.get(pk.getAgeGroup()) + 1);
            }
            else {
 
                studentDistribution.put(pk.getAgeGroup(), 1);
            }
            
        }
        return studentDistribution;
    }
    
    public Map<String, Integer> getGroupsDistribution(Map<String, Integer> studentDistribution){
        Map<String, Integer> groupDistribution = new HashMap<String, Integer>();
        
        for (Map.Entry entry : studentDistribution.entrySet()) {
            int maxSize = studentRatioRules.get(entry.getKey());
            System.out.println("Processing for Key : " + entry.getKey());
                groupDistribution.put(entry.getKey().toString(),
                     1 + (Integer.parseInt(entry.getValue().toString())/ maxSize));   
        }
        return groupDistribution;
    }
    
}
