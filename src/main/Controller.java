package main;

import model.Course;
import model.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Controller {
    private static final HashMap<String, String> IdInformation = new HashMap<>();//userName - password
    private static final ArrayList<Student> students = new ArrayList<>();
    private static final ArrayList<Course> courses = new ArrayList<>();
    private static final String fileAddress = "HW.txt";
    private static final String loginImgAddress = "Login-img.jpg";
    private static final String sharifIconAddress = "Sharif-Icon.png";
    private static final String refreshIconAddress = "Refresh-Icon.png";
    private static final String wallpaperAddress = "Wallpaper.png";
    private static final File file = new File(fileAddress);

    public static void runLoginPage() {
        //System.out.println("Here");
        if (Controller.initial()) {
            new LoginPage().run();
        } else {
            System.out.println("فایل یافت نشد! برنامه فاقد هرگونه اطلاعاتی می باشد.");
            System.out.println("لطفا آدرس فایل مربوط را در کلاس Controller اضافه کنید!");
            System.out.println("پ ن: «ورودی فارسی می باشد»");
        }
    }

    public static void runEduMainPage(String userName) {
        new EduPage(getStudentWithUserName(userName)).run();
    }

    public static boolean initial() {
        try {
            Scanner scanner = new Scanner(file);
            boolean isCourse = false;
            ArrayList<String> studentsStrings = new ArrayList<>();
            do {
                String line = scanner.nextLine();
                if (line.equals("کاربران")) {
                    isCourse = false;
                    line = scanner.nextLine();
                } else if (line.equals("دروس")) {
                    isCourse = true;
                    line = scanner.nextLine();
                }
                if (isCourse) {
                    addCourse(line);
                } else {
                    studentsStrings.add(line);
                }
            } while (scanner.hasNextLine());
            addStudent(studentsStrings);
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    private static void addStudent(ArrayList<String> strings) {
        for (String string : strings) {
            addStudent(string);
        }
    }

    public static void saveOnFile() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("دروس");
            fileWriter.write("\n");
            for (Course course : courses) {
                fileWriter.write(String.valueOf(course));
                fileWriter.write("\n");
            }
            fileWriter.write("کاربران");
            fileWriter.write("\n");
            int counter = 1;
            for (Student student : students) {
                fileWriter.write(String.valueOf(student));
                if (counter != students.size()) {
                    fileWriter.write("\n");
                }
                counter++;
            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("something went wrong on saving data!");
        }

    }

    private static void addStudent(String command) {
        String[] splitString = command.split("\\s\\s");
        Student student = new Student(splitString[0], splitString[1], splitString[2],
                splitString[3], splitString[4], splitString[5]);
        student.addCourse(splitString[6]);
        students.add(student);
        IdInformation.put(student.getStudentId(), student.getPassword());
    }

    private static void addCourse(String command) {
        String[] splitString = command.split("\\s\\s");
        Course course = new Course(splitString[4], splitString[0], splitString[1],
                Integer.parseInt(splitString[2]), Integer.parseInt(splitString[6]), Integer.parseInt(splitString[5]),
                splitString[7], splitString[8], splitString[9], splitString[10]);
        addPreCourses(course, splitString[3]);
        courses.add(course);
    }

    private static void addPreCourses(Course course, String string) {
        String[] help = string.split("[,،]");
        ArrayList<String> preCourses = course.getPreCourses();
        for (String s : help) {
            try {
                preCourses.add(s);
            } catch (NumberFormatException e) {
                System.out.println();
            }
        }
    }

    public static Course findCourseWithCourseNumAndGroup(String courseNum, String courseGroup) {
        for (Course course : courses) {
            if (course.getCourseNum().equals(courseNum) && course.getCourseGroup().equals(courseGroup)) {
                return course;
            }
        }
        return null;
    }

    public static Student getStudentWithUserName(String userName) {
        for (Student student : students) {
            if (student.getStudentId().equals(userName)) {
                return student;
            }
        }
        return null;
    }

    public static HashMap<String, String> getIdInformation() {
        return IdInformation;
    }

    public static ArrayList<Course> getCourses() {
        return courses;
    }

    public static String getLoginImgAddress() {
        return loginImgAddress;
    }

    public static String getSharifIconAddress() {
        return sharifIconAddress;
    }

    public static String getRefreshIconAddress() {
        return refreshIconAddress;
    }

    public static String getWallpaperAddress() {
        return wallpaperAddress;
    }
}
