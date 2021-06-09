package model;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTable extends JPanel {

    private final DefaultTableModel tableModel;
    private final Object[] columns = {"محدودیت", "وضعیت", "وضعیت پیشنیازی ها",
            "زمان کلاس", "زمان امتحان", "نام استاد", "تعداد ثبت نامی", "نام",
            "واحد", "گروه", "شماره درس", "حذف"};
    private final Object[][] objects;
    private final JTable table;

    public MyTable(Student student, int width, int height) {
        objects = new Object[student.getCourses().size()][];
        int i = 0;
        for (Course course : student.getCourses()) {
            Object[] object = {
                    course.getMoreDetails(), "گرفته شده",
                    getPreCourses(course),
                    getClassTime(course.getClassTime()), course.getExamDate(), course.getProfessorName(),
                    Integer.toString(course.getRegistrationsNum()), course.getCourseName(),
                    Integer.toString(course.getCredit()), course.getCourseGroup(),
                    course.getCourseNum(), false};
            objects[i] = object;
            i++;
        }
        setBounds(width - 1330, 160, 1280, height - 450);
        setLayout(null);

        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(0, 0, 1280, height - 450);
        add(scroll);

        table = new JTable();
        table.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
        table.setBackground(Color.lightGray);
        table.setRowHeight(50);
        scroll.setViewportView(table);

        tableModel = new DefaultTableModel()
        {
            public Class<?> getColumnClass(int column)
            {
                if (column == 11) {
                    return Boolean.class;
                }
                return String.class;
            }
        };
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(String.class, centerRenderer);
        table.setModel(tableModel);
        for (int k = 0; k < 12; k++) {
            tableModel.addColumn(columns[k]);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setShowVerticalLines(true);
        table.getColumnModel().getColumn(11).setPreferredWidth(40);
        table.getColumnModel().getColumn(10).setPreferredWidth(70);
        table.getColumnModel().getColumn(9).setPreferredWidth(40);
        table.getColumnModel().getColumn(8).setPreferredWidth(40);
        if (student.getCourses().size() <= 5) {
            table.getColumnModel().getColumn(7).setPreferredWidth(200);
        } else {
            table.getColumnModel().getColumn(7).setPreferredWidth(185);
        }
        table.getColumnModel().getColumn(6).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(207);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(70);
        table.getColumnModel().getColumn(0).setPreferredWidth(180);

        for(int k = 0; k < objects.length; k++) {
            tableModel.addRow(new Object[0]);
            tableModel.setValueAt(objects[k][0], k, 0);
            tableModel.setValueAt(objects[k][1], k, 1);
            tableModel.setValueAt(objects[k][2], k, 2);
            tableModel.setValueAt(objects[k][3], k, 3);
            tableModel.setValueAt(objects[k][4], k, 4);
            tableModel.setValueAt(objects[k][5], k, 5);
            tableModel.setValueAt(objects[k][6], k, 6);
            tableModel.setValueAt(objects[k][7], k, 7);
            tableModel.setValueAt(objects[k][8], k, 8);
            tableModel.setValueAt(objects[k][9], k, 9);
            tableModel.setValueAt(objects[k][10], k, 10);
            tableModel.setValueAt(false, k, 11);
        }


    }

    public void addCourse(Course course) {
        Object[] localObject = {course.getMoreDetails(), "گرفته شده", getPreCourses(course),
                course.getClassTime(), course.getExamDate(), course.getProfessorName(),
                Integer.toString(course.getRegistrationsNum()), course.getCourseName(),
                Integer.toString(course.getCredit()), course.getCourseGroup(),
                course.getCourseNum(), false};
        tableModel.addRow(localObject);

    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    private String getPreCourses(Course course) {
        StringBuilder totalPreCourse = new StringBuilder();
        int counter = 1;
        for (String preCourse : course.getPreCourses()) {
            totalPreCourse.append("(").append(preCourse).append(")");
            counter++;
            if (counter != course.getPreCourses().size()) {
                totalPreCourse.append("\n");
            }
        }
        return totalPreCourse.toString();
    }

    private String getClassTime(String string) {
        String classTime;
        String help = string.replaceAll("\\(", " (");
        Pattern pattern = Pattern.compile("(\\S+)\\s(\\(.+\\))");
        Matcher matcher = pattern.matcher(help);
        if (matcher.find()) {
            classTime = matcher.group(1) + "\n" + matcher.group(2);
            return classTime;
        }
        return "null";
    }

    public JTable getTable() {
        return table;
    }
}
