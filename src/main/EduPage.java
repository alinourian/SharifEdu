package main;

import model.Course;
import model.MyTable;
import model.Student;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EduPage {
    private final JFrame frame;
    private final JPanel servicePagePanel;
    private final JPanel statusPanel;
    private final JLabel statusLabel;
    private final Student student;
    private int width;
    private int height;
    private boolean inServicePage;
    private final JLabel labelTotalCredits;
    private final JLabel sharifIconLabel;
    private final JLabel firstPageLabel;
    private final JLabel firstPageSubLabel;
    private final JLabel wallpaper;

    public EduPage(Student student) {
        this.student = student;
        this.frame = new JFrame("سامانه آموزشی دانشگاه صنعتی شریف");
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.servicePagePanel = new JPanel();
        this.statusPanel = new JPanel();
        this.statusLabel = new JLabel();
        this.inServicePage = false;
        this.labelTotalCredits = new JLabel();
        this.sharifIconLabel = new JLabel("", new ImageIcon(Controller.getSharifIconAddress()), JLabel.CENTER);
        this.firstPageLabel = new JLabel();
        this.firstPageSubLabel = new JLabel();
        this.wallpaper = new JLabel("", new ImageIcon(Controller.getWallpaperAddress()), JLabel.CENTER);
    }

    public void run() {
        Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setMaximumSize(DimMax);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getRootPane().setBounds(0, 100, width, 100);
        frame.setContentPane(wallpaper);

        Border border = BorderFactory.createLineBorder(Color.black, 3, false);
        frame.getRootPane().setBorder(border);
        frame.setVisible(true);
        width = frame.getSize().width;
        height = frame.getSize().height;
        frame.setVisible(false);
        JLabel label = new JLabel("سامانه آموزش شریف");
        label.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        label.setBounds(width - 220, 10, 200, 40);
        frame.setLayout(null);
        frame.add(label);

        setTopPanel();
        setExitButton();
        setSaveButton();

        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void setSaveButton() {
        Button saveButton = new Button("ذخیره تغییرات");
        saveButton.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
        saveButton.setBackground(Color.lightGray);
        saveButton.setBounds(100, 30, 100, 30);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int status = JOptionPane.showConfirmDialog(frame, "آیا از ذخیره تغییرات مطمئن هستید؟",
                        "ذخیر اطلاعات", JOptionPane.YES_NO_OPTION);
                if (status == JOptionPane.YES_OPTION) {
                    Controller.saveOnFile();
                }
            }
        });
        frame.add(saveButton);
    }

    private void setExitButton() {
        Button exitButton = new Button("خروج");
        exitButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        exitButton.setBounds(20, 30, 50, 30);
        exitButton.setBackground(Color.lightGray);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int status = JOptionPane.showConfirmDialog(frame, "آیا می خواهید خارج شوید؟",
                        "خروج", JOptionPane.YES_NO_OPTION);
                if (status == JOptionPane.YES_OPTION) {
                    inServicePage = false;
                    frame.setVisible(false);
                    frame.dispose();
                    new LoginPage().run();
                }
            }
        });
        frame.add(exitButton);
    }

    private void setTopPanel() {
        Font font = new Font(Font.SERIF, Font.PLAIN, 18);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("امور ثبت نام و ترمیم");
        JMenu menu2 = new JMenu("خدمات آموزشی");
        JMenu menu3 = new JMenu("امور کارنامه");
        JMenu menu4 = new JMenu("نظارت و ارزیابی");
        JMenu menu5 = new JMenu("مطلوبات کاربر");
        JMenu menu6 = new JMenu("گزارش های تحصیلات تکمیلی");
        menuBar.add(Box.createHorizontalGlue());
        addSubMenu(menu1, menu2, menu3, menu4, menu5);
        menu1.setFont(font); menu2.setFont(font); menu3.setFont(font);
        menu4.setFont(font); menu5.setFont(font); menu6.setFont(font);
        menuBar.add(menu5); menuBar.add(menu6); menuBar.add(menu4);
        menuBar.add(menu3); menuBar.add(menu2); menuBar.add(menu1);
        addActionListenerForMenus(menu1, "امور ثبت نام و ترمیم");
        addActionListenerForMenus(menu2, "خدمات آموزشی");
        addActionListenerForMenus(menu3, "امور کارنامه");
        addActionListenerForMenus(menu4, "نظارت و ارزیابی");
        addActionListenerForMenus(menu5, "مطلوبات کاربر");
        addActionListenerForMenus(menu6, "گزارش های تحصیلات تکمیلی");
        menuBar.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        JRootPane rootPane = new JRootPane();
        rootPane.setBounds(0, 80, width - 20, 32);
        rootPane.setJMenuBar(menuBar);
        frame.add(rootPane);
        setPageLabelPanel();
        addStatusPanel("صفحه اصلی");
    }

    private void setPageLabelPanel() {
        firstPageLabel.setText("وب سایت معاونت آموزشی و تحصیلات تکمیلی");
        firstPageLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        firstPageSubLabel.setText("_____________________________");
        firstPageSubLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        firstPageLabel.setBounds(width / 2 - 150, 340, 700, 50);
        firstPageSubLabel.setBounds(width / 2 - 150, 380, 700, 50);
        frame.add(firstPageLabel);
        frame.add(firstPageSubLabel);
        sharifIconLabel.setBounds(width / 2 - 470, 250, 250, 250);
        frame.add(sharifIconLabel);
    }

    private void addStatusPanel(String status) {
        statusPanel.setBounds(10, 115, 200, 40);
        statusPanel.setBackground(Color.lightGray);
        statusPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        statusLabel.setText(status);
        statusLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
        statusLabel.setBounds(30, 120, 200, 30);
        statusPanel.add(statusLabel);
        frame.add(statusPanel);
    }

    private void addActionListenerForMenus(JMenu menu, String status) {
        menu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                if (!inServicePage) {
                    addStatusPanel(status);
                }
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
    }

    private void addSubMenu(JMenu menu1, JMenu menu2, JMenu menu3, JMenu menu4, JMenu menu5) {
        Font font = new Font(Font.SERIF, Font.PLAIN, 14);
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "این صفحه در دسترس نمی باشد!",
                        "", JOptionPane.ERROR_MESSAGE);
            }
        };
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runEduServicePage();
            }
        };
        JMenuItem item11 = new JMenuItem("اطلاعیه و راهنمای ثبت نام و ترمیم");
        JMenuItem item12 = new JMenuItem("لیست دروس ارایه شده دانشکده ها در ترم جاری");
        JMenuItem item13 = new JMenuItem("فرم مشاوره انتخاب واحد");
        JMenuItem item14 = new JMenuItem("پرداخت اینترنتی دانشجویان");
        JMenuItem item21 = new JMenuItem("ثبت اطلاعات بانکی دانشجو");
        JMenuItem item22 = new JMenuItem("تطبیق دروس دانشجو با چارت");
        JMenuItem item23 = new JMenuItem("کارتابل در خواست");
        JMenuItem item24 = new JMenuItem("درخواست احراز کارشناسی");
        JMenuItem item25 = new JMenuItem("درخواست های آموزشی");
        JMenuItem item26 = new JMenuItem("فرم ثبت نام دانشجو"); // only page...
        JMenuItem item27 = new JMenuItem("لیست امتحانات پایان ترم");
        JMenuItem item28 = new JMenuItem("برنامه هفتگی دانشجو");
        JMenuItem item31 = new JMenuItem("وضعیت تحصیلی و ریزنمرات دانشجو");
        JMenuItem item32 = new JMenuItem("لیست نمرات موقت و ثبت اعتراض");
        JMenuItem item41 = new JMenuItem("ارزشیابی استاد توسط دانشجو");
        JMenuItem item42 = new JMenuItem("ارزشیابی دستیاران توسط دانشجو");
        JMenuItem item51 = new JMenuItem("ورود و ویرایش پست الکترونیکی و تلفن همراه");
        JMenuItem item52 = new JMenuItem("تغیر رمز عبور");
        JMenuItem item53 = new JMenuItem("پیغامهای دریافتی");
        JMenuItem item54 = new JMenuItem("دریافت کد اختصاصی");
        JMenuItem item55 = new JMenuItem("مشخصات واسط کاربر");

        //add acton listener
        item11.addActionListener(actionListener); item12.addActionListener(actionListener);
        item13.addActionListener(actionListener); item14.addActionListener(actionListener);
        item21.addActionListener(actionListener); item22.addActionListener(actionListener);
        item23.addActionListener(actionListener); item24.addActionListener(actionListener);
        item25.addActionListener(actionListener); item27.addActionListener(actionListener);
        item28.addActionListener(actionListener); item26.addActionListener(action);
        item31.addActionListener(actionListener); item32.addActionListener(actionListener);
        item41.addActionListener(actionListener); item42.addActionListener(actionListener);
        item51.addActionListener(actionListener); item52.addActionListener(actionListener);
        item53.addActionListener(actionListener); item54.addActionListener(actionListener);
        item55.addActionListener(actionListener);

        //set font
        item11.setFont(font); item12.setFont(font); item13.setFont(font); item14.setFont(font);
        item21.setFont(font); item22.setFont(font); item23.setFont(font); item24.setFont(font);
        item25.setFont(font); item27.setFont(font); item28.setFont(font); item26.setFont(font);
        item31.setFont(font); item32.setFont(font);
        item41.setFont(font); item42.setFont(font);
        item51.setFont(font); item52.setFont(font); item53.setFont(font); item54.setFont(font);
        item55.setFont(font);

        //menu add
        menu1.add(item11); menu1.add(item12); menu1.add(item13); menu1.add(item14);
        menu2.add(item21); menu2.add(item22); menu2.add(item23); menu2.add(item24);
        menu2.add(item25); menu2.add(item26); menu2.add(item27); menu2.add(item28);
        menu3.add(item31); menu3.add(item32); menu4.add(item41); menu4.add(item42);
        menu5.add(item51); menu5.add(item52); menu5.add(item53); menu5.add(item54);
        menu5.add(item55);
    }

    //EDU SERVICE PAGE

    private void runEduServicePage() {
        frame.setVisible(false);
        frame.remove(firstPageLabel);
        frame.remove(firstPageSubLabel);
        frame.remove(sharifIconLabel);

        inServicePage = true;
        String status = "فرم ثبت نام دانشجو";
        addStatusPanel(status);
        servicePagePanel.setBounds(0, 112, width, height - 110);
        servicePagePanel.setLayout(null);

        addServiceLabel();
        addCourseDetails();

        frame.add(servicePagePanel);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void addServiceLabel() {
        JLabel label = new JLabel("نیمسال دوم 99-1398");
        label.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        Font font = new Font(Font.DIALOG, Font.PLAIN, 14);
        JLabel labelStudentId = new JLabel("شماره دانشجو: " + student.getStudentId());
        JLabel labelName = new JLabel("نام و نام خانوادگی: " + student.getName());
        JLabel labelCollage = new JLabel("دانشکده: " + student.getField());
        JLabel labelField = new JLabel("رشته: " + student.getField());
        JLabel labelSubField = new JLabel("گرایش: " + student.getSubField());
        JLabel labelSupervisor = new JLabel("استاد راهنما: " + student.getSupervisor());
        label.setBounds(width / 2 + 100, 0, 200, 50);

        labelStudentId.setHorizontalAlignment(SwingConstants.RIGHT);
        labelName.setHorizontalAlignment(SwingConstants.RIGHT);
        labelCollage.setHorizontalAlignment(SwingConstants.RIGHT);
        labelField.setHorizontalAlignment(SwingConstants.RIGHT);
        labelSubField.setHorizontalAlignment(SwingConstants.RIGHT);
        labelSupervisor.setHorizontalAlignment(SwingConstants.RIGHT);

        labelStudentId.setFont(font); labelName.setFont(font); labelCollage.setFont(font);
        labelField.setFont(font); labelSubField.setFont(font); labelSupervisor.setFont(font);
        labelStudentId.setBounds(width - 200, 50, 150, 20);
        labelName.setBounds(width - 500, 50, 200, 20);
        labelCollage.setBounds(width - 800, 50, 200, 20);
        labelField.setBounds(width - 200, 80, 150, 20);
        labelSubField.setBounds(width - 500, 80, 200, 20);
        labelSupervisor.setBounds(width - 800, 80, 200, 20);
        servicePagePanel.add(label);
        servicePagePanel.add(labelStudentId); servicePagePanel.add(labelName); servicePagePanel.add(labelCollage);
        servicePagePanel.add(labelField); servicePagePanel.add(labelSubField); servicePagePanel.add(labelSupervisor);
    }

    private void addCourseDetails() {
        JLabel labelCourse, labelGroup, labelCredit;
        labelCourse = new JLabel("درس");
        labelGroup = new JLabel("گروه");
        labelCredit = new JLabel("واحد");
        JTextField textFieldCourse, textFieldCredit;
        textFieldCourse = new JTextField(); textFieldCredit = new JTextField();
        Button buttonAddCourse = new Button("اضافه");
        Button buttonDropCourse = new Button("حذف دروس");
        labelCourse.setBounds(width - 70, 120, 50, 20);
        labelGroup.setBounds(width - 210, 120, 50, 20);
        labelCredit.setBounds(width - 320, 120, 50, 20);
        textFieldCourse.setBounds(width - 175, 120, 100, 25);
        textFieldCredit.setBounds(width - 390, 120, 60, 25);
        String[] group = {"1", "2", "3", "4"};
        JComboBox comboBoxGroup = new JComboBox(group);
        comboBoxGroup.setBounds(width - 280, 120, 60, 25);
        buttonAddCourse.setBounds(width - 510, 116, 100, 32);
        buttonDropCourse.setBounds(width - 220, height - 250, 120, 30);
        buttonAddCourse.setBackground(Color.LIGHT_GRAY);
        buttonDropCourse.setBackground(Color.LIGHT_GRAY);
        buttonAddCourse.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        buttonDropCourse.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        servicePagePanel.add(labelCourse); servicePagePanel.add(labelGroup);
        servicePagePanel.add(labelCredit); servicePagePanel.add(textFieldCourse);
        servicePagePanel.add(textFieldCredit); servicePagePanel.add(comboBoxGroup);
        servicePagePanel.add(buttonAddCourse); servicePagePanel.add(buttonDropCourse);
        addTable(buttonAddCourse, buttonDropCourse, textFieldCourse, comboBoxGroup, textFieldCredit);
    }

    private void addTable(Button buttonAddCourse, Button buttonDropCourse,
                          JTextField textFieldCourse, JComboBox comboBoxGroup, JTextField textFieldCredit) {
        MyTable tablePanel = new MyTable(student, width, height);
        servicePagePanel.add(tablePanel);

        JLabel label1 = new JLabel();
        label1.setOpaque(true);
        labelTotalCredits.setOpaque(true);
        labelTotalCredits.setHorizontalAlignment(SwingConstants.RIGHT);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setText("جمع واحدها");
        labelTotalCredits.setText(getTotalCredits() + "   ");
        label1.setBackground(Color.lightGray);
        label1.setBorder(BorderFactory.createLineBorder(Color.gray, 1, false));
        labelTotalCredits.setBackground(Color.lightGray);
        labelTotalCredits.setBorder(BorderFactory.createLineBorder(Color.gray, 1, false));
        label1.setBounds(width - 200, height - 290, 150, 25);
        labelTotalCredits.setBounds(width - 1330, height - 290, 1130, 25);

        servicePagePanel.add(label1);
        servicePagePanel.add(labelTotalCredits);


        buttonAddCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseNum = textFieldCourse.getText();
                String courseGroup = (String)comboBoxGroup.getItemAt(comboBoxGroup.getSelectedIndex());
                String courseCredit = textFieldCredit.getText();
                addCourse(tablePanel, courseNum, courseGroup, courseCredit);
                textFieldCourse.setText("");
                textFieldCredit.setText("");
                labelTotalCredits.setText(getTotalCredits() + "   ");
            }
        });
        buttonDropCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int status = JOptionPane.showConfirmDialog(frame, "آیا از حذف دروس انتخاب شده مطمعن هستید؟",
                        "حذف", JOptionPane.YES_NO_OPTION);
                if (status == JOptionPane.YES_OPTION) {
                    dropCourse(tablePanel);
                    labelTotalCredits.setText(getTotalCredits() + "   ");
                }
            }
        });
    }

    private int getTotalCredits() {
        int totalCredits = 0;
        for (Course course : student.getCourses()) {
            totalCredits += course.getCredit();
        }
        return totalCredits;
    }

    private void addCourse(MyTable tablePanel, String courseNum, String courseGroup, String courseCredit) {
        try {
            Course course = Controller.findCourseWithCourseNumAndGroup(courseNum, courseGroup);
            if (course != null && Integer.parseInt(courseCredit) == course.getCredit()) {
                if (course.getRegistrationsNum() < course.getMaxCapacity()) {
                    doAddCourse(tablePanel, course);
                } else {
                    JOptionPane.showMessageDialog(frame, "ظرفیت درس تکمیل است!",
                            "تکمیل ظرفیت", JOptionPane.ERROR_MESSAGE);
                }
            } else if (course != null && Integer.parseInt(courseCredit) != course.getCredit()) {
                JOptionPane.showMessageDialog(frame, "تعداد واحد نادرست است!");
            } else {
                for (Course testCourse : Controller.getCourses()) {
                    if (testCourse.getCourseNum().equals(courseNum)) {
                        JOptionPane.showMessageDialog(frame, "درس شامل گروه انتخابی نمی باشد!");
                        break;
                    }
                }
                JOptionPane.showMessageDialog(frame, "درسی با شماره وارد شده موجود نیست!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "ورودی نامعتبر است!");
        }
    }

    private void dropCourse(MyTable tablePanel) {
        ArrayList<Course> dropCourses = new ArrayList<>();
        ArrayList<Integer> dropRows = new ArrayList<>();
        System.out.println(tablePanel.getTableModel().getRowCount());
        for (int i = tablePanel.getTableModel().getRowCount() - 1; i >= 0; i--) {
            Object testObject = tablePanel.getTableModel().getValueAt(i, 11);
            if ((boolean)testObject) {
                dropCourses.add(
                        Controller.findCourseWithCourseNumAndGroup(
                                (String)tablePanel.getTableModel().getValueAt(i, 10),
                                (String)tablePanel.getTableModel().getValueAt(i, 9))
                );
                dropRows.add(i);
                tablePanel.getTableModel().removeRow(i);
            }
        }
        for (Course dropCourse : dropCourses) {
            student.getCourses().remove(dropCourse);
            dropCourse.setRegistrationsNum(dropCourse.getRegistrationsNum() - 1);
        }
        if (student.getCourses().size() <= 5) {
            tablePanel.getTable().getColumnModel().getColumn(7).setPreferredWidth(200);
        }
    }

    private void doAddCourse(MyTable tablePanel, Course course) {
        boolean bool = true;
        Course studentCourse = null;
        for (Course test : student.getCourses()) {
            if (test.getCourseNum().equals(course.getCourseNum())) {
                bool = false;
                studentCourse = test;
                break;
            }
        }
        if (bool) {
            course.setRegistrationsNum(course.getRegistrationsNum() + 1);
            student.getCourses().add(course);
            tablePanel.addCourse(course);
            if (student.getCourses().size() > 5) {
                tablePanel.getTable().getColumnModel().getColumn(7).setPreferredWidth(185);
            }
        } else {
            if (studentCourse.getCourseGroup().equals(course.getCourseGroup())) {
                JOptionPane.showMessageDialog(frame, "شما قبلا این درس را گرفته اید!",
                        "", JOptionPane.ERROR_MESSAGE);
            } else {
                int status = JOptionPane.showConfirmDialog(frame,
                        "شما قبلا این درس را در گروه دیگر گرفته اید\n آیا می خواهید گروه درس را تغییر دهید؟",
                        "تغیر گروه", JOptionPane.YES_NO_OPTION);
                if (status == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < tablePanel.getTableModel().getRowCount(); i++) {
                        if (tablePanel.getTableModel().getValueAt(i, 10).equals(course.getCourseNum())) {
                            tablePanel.getTableModel().removeRow(i);
                            break;
                        }
                    }
                    course.setRegistrationsNum(course.getRegistrationsNum() + 1);
                    student.getCourses().remove(studentCourse);
                    student.getCourses().add(course);
                    tablePanel.addCourse(course);
                }
            }
        }
    }

}
