package model;

import java.util.ArrayList;

public class Course {
    private final String courseName;
    private final String courseNum;
    private final String courseGroup;
    private final int credit;
    private final int maxCapacity;
    private int registrationsNum;
    private final String professorName;
    private final String examDate;
    private final String classTime;
    private final String moreDetails;
    private final ArrayList<String> preCourses;

    public Course(String courseName, String courseNum, String group, int credit, int maxCapacity,
                  int registrationsNum, String professorName, String examDate, String classTime, String moreDetails) {
        this.courseName = courseName;
        this.courseNum = courseNum;
        this.courseGroup = group;
        this.credit = credit;
        this.maxCapacity = maxCapacity;
        this.registrationsNum = registrationsNum;
        this.professorName = professorName;
        this.examDate = examDate;
        this.classTime = classTime;
        this.moreDetails = moreDetails;
        preCourses = new ArrayList<>();
    }

    public String getCourseNum() {
        return courseNum;
    }

    public String getCourseGroup() {
        return courseGroup;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredit() {
        return credit;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getRegistrationsNum() {
        return registrationsNum;
    }

    public String getProfessorName() {
        return professorName;
    }

    public String getExamDate() {
        return examDate;
    }

    public String getClassTime() {
        return classTime;
    }

    public String getMoreDetails() {
        return moreDetails;
    }

    public ArrayList<String> getPreCourses() {
        return preCourses;
    }

    public void setRegistrationsNum(int registrationsNum) {
        this.registrationsNum = registrationsNum;
    }

    private String getPreCoursesInString() {
        StringBuilder string = new StringBuilder();
        int counter = 1;
        for (String preCourse : preCourses) {
            string.append(preCourse);
            if (counter != preCourses.size()) {
                string.append("،");
            }
            counter++;
        }
        return string.toString();
    }

    @Override
    public String toString() {
        return courseNum + "  " +
                courseGroup + "  " +
                credit + "  " +
                getPreCoursesInString() + "  " +
                courseName + "  " +
                registrationsNum + "  " +
                maxCapacity + "  " +
                professorName + "  " +
                examDate + "  " +
                classTime + "  " +
                moreDetails;
    }
}
