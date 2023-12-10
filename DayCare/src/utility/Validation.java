/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;

/**
 *
 * 
 */
public class Validation {
    
    public static boolean validateName(JTextField jf){
        String name = jf.getText().toString();
        int strLength = name.trim().length(); //Removes empty characters around the input string
        System.out.println(name);
        System.out.println("String length : " + strLength);
        if (strLength > 0){
            for(int i=0; i < strLength; i++){
                if(Character.isDigit(name.charAt(i))){
                    return false;
                }
            }
            System.out.println("No integer found in name");
            Pattern namePattern = Pattern.compile("[a-zA-Z\\s]{2,}");
            Matcher nameMatcher = namePattern.matcher(name);
            if(!nameMatcher.matches()) return false;
        }
        System.out.println("Outside If block");
        return true;
    }
    
    public static boolean validateStreetAddress(JTextField jf){
        String address = jf.getText().toString();
        Pattern addressPattern = Pattern.compile("^[:.0-9a-zA-Z\\s,-]+$");
        Matcher addressMatcher = addressPattern.matcher(address);
        if (addressMatcher.matches()){
            return true;
        }
        return false;
    }
    
    public static boolean validateWage(JTextField jf){
        String wage = jf.getText().toString();
        try{
           Integer.parseInt(wage);
           return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
    public static boolean validateAge(JTextField jf){
        String age = jf.getText().toString();
        try{
           int ageInt = Integer.parseInt(age);
           if (ageInt > 0 && ageInt < 100){
               return true;
           }
           else{
               return false;
           }

        }catch(NumberFormatException e){
            return false;
        }
    }
    
    public static boolean validateCredits(JTextField jf){
        String age = jf.getText().toString();
        try{
           int ageInt = Integer.parseInt(age);
           if (ageInt > 0 && ageInt < 40){
               return true;
           }
           else{
               return false;
           }

        }catch(NumberFormatException e){
            return false;
        }
    }
    
    
    
    public static boolean validateDateOfBirth(String dateOfBirth){
        //Checks if total string length including seperator is 10(MM/DD/YYYY)
        if (dateOfBirth.trim().length() == 10){ 
            String[] dateArr = dateOfBirth.split("-");
            int month, day, year;
            /* dateArr[0] - month
               dateArr[1] - day
               dateArr[2] - year */
            month = Integer.parseInt(dateArr[0]);
            day = Integer.parseInt(dateArr[1]);
            year = Integer.parseInt(dateArr[2]);
            if(year > 1920 && year < 2022){
                // Valid year
               if (month==4 || month==6 || month==9 || month==11){
                   // 30days in a month
                   if (day >= 1 && day < 31){
                       return true;
                   }
               }
               else if(month==1 || month==3 || month==5|| month==7 || month==8 || month==10 || month==12){
                   // 31days in a month
                   if (day >= 1 && day < 31){
                       return true;
                   }
               }
               else{
                   if (year %4 ==0){
                       // leap year - 29days
                       if (day >= 1 && day < 30){
                            return true;
                        }
                   }
                   else{
                       // Feb 28 days
                       if (day >= 1 && day < 29){
                            return true;
                        }
                   }
               }
            }
        }
        
        return false;
    }
    
    public static boolean validatePhoneNumber(JTextField jf){
        if (jf.getText().isEmpty()){
            return false;
        }
        String phoneNumber = jf.getText().toString();
        
        String enteredPhoneNumber = (phoneNumber.replace("-", "").
                replace(".", "").replace(" ", "").replace("(", "").replace(")", "").
                replace("+", ""));
        System.out.println(enteredPhoneNumber.length());
        System.out.println(enteredPhoneNumber.startsWith("1"));
            if(enteredPhoneNumber.length()==10){
                return true;
            }
        return false;
    }
    
    public static boolean validateStudentID(JTextField jf){
        if (jf.getText().isEmpty()){
            return false;
        }
        String studentId = jf.getText().toString();
        try{
            int studentIdInt = Integer.parseInt(studentId); 
            return true;
        }catch(Exception e){
            return false;
        }
        
    }
    
    public static boolean validateGpa(JTextField jf){
        if (jf.getText().isEmpty()){
            return false;
        }
        String enteredGpa = jf.getText().toString();
        try{
            Double stundetGpa = Double.parseDouble(enteredGpa); 
            return true;
        }catch(Exception e){
            return false;
        }
        
    }
    
    
}
