package ds.kontrolnaya.models;
import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class Student implements Serializable {
    private String name;
    private int year;
    private int group;
    private String id;
    private double averageGrade;


    public Student() {
        this.name = "";
        this.id = UUID.randomUUID().toString();
    }

    public Student(String name, int year, int group, double averageGrade) {
        this();
        this.name = name;
        this.year=year;
        this.group = group;
        this.averageGrade = averageGrade;
    }

    public Student(String id, String name, int year, int group, double averageGrade) {
        this(name, year, group, averageGrade);
        this.id = id;
    }



    public Student(Student o) {
        this.id = o.id;
        this.name = o.name;
        this.group = o.group;
        this.averageGrade = o.averageGrade;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getGroup() {return group;}

    public void setGroup(int group) {this.group = group;}

    public String getId() {return id;}

    public double getAverageGrade() {return averageGrade;}

    public void setAverageGrade(double averageGrade) {this.averageGrade = averageGrade;}

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Student student = (Student) o;

        return group == student.group && name.equals(student.name);
    }


    public static ArrayList<Student> generateFakeData() {
        ArrayList<Student> data = new ArrayList<>();
        data.add(new Student("Perry Hodges", 2, 1, 4.0));
        data.add(new Student("Patrick Richardson",3, 2, 5.0));
        data.add(new Student("Doug Evans",1,  3, 9.0));
        data.add(new Student("Cathy Russell",4,  4, 8.5));
        data.add(new Student("Cecil Murphy",3, 1, 10.0));
        data.add(new Student("Roberto Ball",4, 2, 6.7));
        data.add(new Student("Glenda Romero",5, 3, 2.1));
        data.add(new Student("Tomas King", 2,4, 4.5));
        data.add(new Student("Sophia Simpson", 1,1, 7.6));
        data.add(new Student("Wade Maxwell",4, 2, 4.3));
        return data;
    }
}
