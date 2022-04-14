package com.java.assignments.assignment12;

import java.util.*;
import java.util.stream.*;

class Student{
    int id;
    String name;
    int age;
    String gender;
    String engDepartment;
    int yearOfEnrollment;
    double perTillDate;
    public Student(int id, String name, int age, String gender, String engDepartment, int yearOfEnrollment, double perTillDate){
       this.id=id;
       this.name=name;
       this.age=age;
       this.gender=gender;
       this.engDepartment=engDepartment;
       this.yearOfEnrollment=yearOfEnrollment;
       this.perTillDate=perTillDate;
    }
    public String getDepartment(){
        return this.engDepartment;
    }
    public double getPerTillDate(){
        return this.perTillDate;
    }
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
}

public class StudentStreams {
    public static void main(String args[]){
        List<Student> stList= new ArrayList<Student>();
        stList.add(new Student(111, "Jiya Brein", 17, "Female", "Computer Science", 2018, 70.8));
        stList.add(new Student(122, "Paul Niksui", 18, "Male", "Mechanical", 2016, 50.2));
        stList.add(new Student(133, "Martin Theron", 17, "Male", "Electronic", 2017, 90.3));
        stList.add(new Student(144, "Murali Gowda", 18, "Male", "Electrical", 2018, 80));
        stList.add(new Student(155, "Nima Roy", 19, "Female", "Textile", 2016, 70));
        stList.add(new Student(166, "Iqbal Hussain", 18, "Male", "Security", 2016, 70));
        stList.add(new Student(177, "Manu Sharma", 16, "Male", "Chemical", 2018, 70));
        stList.add(new Student(188, "Wang Liu", 20, "Male", "Computer Science", 2015, 80));
        stList.add(new Student(199, "Amelia Zoe", 18, "Female", "Computer Science", 2016, 85));
        stList.add(new Student(200, "Jaden Dough", 18, "Male", "Security", 2015, 82));
        stList.add(new Student(211, "Jasna Kaur", 20, "Female", "Electronic", 2019, 83));
        stList.add(new Student(222, "Nitin Joshi", 19, "Male", "Textile", 2016, 60.4));
        stList.add(new Student(233, "Jyothi Reddy", 16, "Female", "Computer Science", 2015, 45.6));
        stList.add(new Student(244, "Nicolus Den", 16, "Male", "Electronic", 2017, 95.8));
        stList.add(new Student(255, "Ali Baig", 17, "Male", "Electronic", 2018, 88.4));
        stList.add(new Student(266, "Sanvi Pandey", 17, "Female", "Electric", 2019, 72.4));
        stList.add(new Student(277, "Anuj Chettiar", 18, "Male", "Computer Science", 2017, 57.5));

        //1. Print the name of all departments in the college?
        List<String> allDepts=stList.stream().map(s->s.engDepartment).distinct().collect(Collectors.toList());
        System.out.println(allDepts);

        //2.Get the names of all students who have enrolled after 2018?
        List<String> enrolledAfter2018=stList.stream().filter(s->s.yearOfEnrollment>2018).map(s->s.name).collect(Collectors.toList());
        System.out.println(enrolledAfter2018);

        //3. Get the details of all male student in the computer sci department?
        List<String> maleStCompSc=stList.stream().filter(s->s.engDepartment.equals("Computer Science")&&s.gender.equals("Male")).
                map(s->s.name).collect(Collectors.toList());
        System.out.println(maleStCompSc);

        //4. How many male and female student are there ?
        long maleStudents=stList.stream().filter(s->s.gender.equals("Male")).count();
        long femaleStudents=stList.stream().filter(s->s.gender.equals("Female")).count();
        System.out.println("Male: "+maleStudents);
        System.out.println("Female: "+femaleStudents);

        //5.What is the average age of male and female students?
        float maleAgeSum=stList.stream().filter(s->s.gender.equals("Male")).map(s->s.age).reduce(0,(sum,age)->sum+age);
        float femaleAgeSum=stList.stream().filter(s->s.gender.equals("Female")).map(s->s.age).reduce(0,(sum,age)->sum+age);

        System.out.println("Average age of male: "+maleAgeSum/maleStudents);
        System.out.println("Average age of female: "+femaleAgeSum/femaleStudents);

        //6.Get the details of highest student having highest percentage ?
        Student stMax=stList.stream().max((s1,s2)->s1.perTillDate>s2.perTillDate?1:-1).get();
        double highestPercentage=stMax.perTillDate;
        System.out.println(stMax.name+" "+stMax.perTillDate);

        //7.Count the number of students in each department? (Hint :use Collectors.groupingBy())
        Map<String,Long> result = stList.stream().collect(Collectors.groupingBy(Student::getDepartment, Collectors.counting()));
        result.forEach((key,value)->{
            System.out.println(key+" "+value);
        });
        //8. What is the average percentage achieved in each department?
        Map<String, Double> avgPer = stList.stream().collect(Collectors.groupingBy(Student::getDepartment, Collectors.averagingDouble(s->s.perTillDate)));

        avgPer.forEach((key,value)->{
            System.out.println(key+" "+value);
        });

        //9. Get the details of youngest male student in the Electronic department?
         List<Student> elecMales=  stList.stream().filter(s->s.engDepartment.equals("Electronic")&&s.gender.equals("Male")).
                collect(Collectors.toList());
         Student youngElecMale=elecMales.stream().min((s1,s2)->s1.age > s2.age?1:-1).get();
         System.out.println("Younghest Male Student in Electronic Department: "+youngElecMale.name+", Age: "+youngElecMale.age);

        //10.How many male and female students are there in the computer science department?
        long maleStudentsInCSE=stList.stream().filter(s->s.gender.equals("Male")&&s.engDepartment.equals("Computer Science")).count();
        long femaleStudentsInCSE=stList.stream().filter(s->s.gender.equals("Female")&&s.engDepartment.equals("Computer Science")).count();
        System.out.println("Male: "+maleStudentsInCSE);
        System.out.println("Female: "+femaleStudentsInCSE);

    }
}
