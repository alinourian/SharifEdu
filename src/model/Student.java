package model;

import main.Controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {
    private String name;
    private String studentId;
    private String password;
    private String field;
    private String subField;
    private String supervisor;
    private ArrayList<Course> courses;

    public Student(String name, String studentId, String password,
                   String field, String subField, String supervisor) {
        this.name = name;
        this.studentId = studentId;
        this.password = password;
        this.field = field;
        this.subField = subField;
        this.supervisor = supervisor;
        this.courses = new ArrayList<>();
    }

    public void addCourse(String string) {
        Pattern pattern = Pattern.compile("\\((\\d+)[,،](.)\\)");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            Course course = Controller.findCourseWithCourseNumAndGroup(matcher.group(1), matcher.group(2));
            courses.add(course);
        }
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getField() {
        return field;
    }

    public String getSubField() {
        return subField;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    private String getCoursesInString() {
        StringBuilder string = new StringBuilder();
        string.append("[");
        int counter = 1;
        for (Course course : courses) {
            string.append("(").append(course.getCourseNum()).append("،").append(course.getCourseGroup()).append(")");
            if (counter != courses.size()) {
                string.append("،");
            }
            counter++;
        }
        string.append("]");
        return string.toString();
    }

    @Override
    public String toString() {
        return name + "  " +
                studentId + "  " +
                password + "  " +
                field + "  " +
                subField + "  " +
                supervisor + "  " +
                getCoursesInString();
    }
    //معین صیادی  98101087  Sayad@m9887  مهندسی کامپیوتر  نرم افزار  دکتر معصومیان  [(22034،1)،(25755،1)]
}
