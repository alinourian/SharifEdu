package main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class LoginPage {
    private final JFrame jFrame = new JFrame("سامانه آموزشی دانشگاه صنعتی شریف");
    private final JPanel mainPanel = new JPanel();
    private final JTextField userName = new JTextField();
    private final JPasswordField password = new JPasswordField();
    private final JTextField code = new JTextField();
    private final JPanel codePanel = new JPanel();
    private final JLabel codeLabel = new JLabel();
    private String user;
    private String pass;
    private int correctCode;
    private String enteredCode;
    private int width;
    private final ImageIcon refreshImg = new ImageIcon(Controller.getRefreshIconAddress());

    public void run() {
        Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setMaximumSize(DimMax);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        width = jFrame.getSize().width;
        int height = jFrame.getSize().height;
        jFrame.setVisible(false);
        Border border = BorderFactory.createLineBorder(Color.black, 3, false);
        jFrame.getRootPane().setBorder(border);
        jFrame.setLayout(null);
        mainPanel.setLayout(null);
        setLabel();
        setText();
        setPanel();
        jFrame.add(mainPanel);

        ImageIcon img = new ImageIcon(Controller.getLoginImgAddress());
        JLabel imgLabel = new JLabel("", img, JLabel.CENTER);
        imgLabel.setBounds(0, 0, width, height);
        jFrame.add(imgLabel);


        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }

    private void setLabel() {
        JLabel l1 = new JLabel("بسم اللّه الرحمن الرحیم");
        l1.setBounds(width / 2 - 80, 20, 160, 50);
        l1.setFont(new Font("Serif", Font.PLAIN, 20));
        l1.setForeground(Color.black);
        JLabel l2 = new JLabel("دانشگاه صنعتی شریف - معاونت آموزشی و تحصیلات تکمیلی - واحد انفورماتیک");
        l2.setBounds(width / 2 - 320, 120, 640, 50);
        l2.setFont(new Font("Serif", Font.PLAIN, 24));
        l2.setForeground(Color.black);
        jFrame.add(l1); jFrame.add(l2);
    }

    private void setText() {
        mainPanel.setBounds(width / 2 - 210, 190, 420, 400);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        mainPanel.setLayout(null);
        Font font = new Font("Serif", Font.PLAIN, 18);
        userName.setFont(font);
        password.setFont(font);
        code.setFont(font);
        userName.setBounds(width / 2 - 180, 300, 210, 30);
        password.setBounds(width / 2 - 180, 350, 210, 30);
        code.setBounds(width / 2 - 100, 460, 100, 30);
        jFrame.add(userName);
        jFrame.add(password);
        jFrame.add(code);
        setCode();
    }

    private void setCode() {
        codePanel.setBounds(width / 2 - 100, 410, 100, 30);
        Border border = BorderFactory.createLineBorder(Color.black, 1, false);
        codePanel.setBorder(border);
        codePanel.setBackground(Color.gray);
        correctCode = giveCode();
        String codeNum = Integer.toString(correctCode);
        codeLabel.setText("  " + codeNum + "  ");
        Font font = new Font(Font.DIALOG, Font.ITALIC, 16);
        codeLabel.setFont(font);
        codePanel.add(codeLabel);
        jFrame.add(codePanel);
    }

    private void  setPanel() {
        JPanel jPanel2 = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.black, 1, false);
        jPanel2.setBounds(0, 0, 420, 60);
        jPanel2.setBackground(Color.blue);
        jPanel2.setBorder(border);
        JLabel l0 = new JLabel("سیستم آموزش");
        l0.setBounds(width / 2 + 80, 200, 200, 50);
        l0.setFont(new Font("Serif", Font.PLAIN, 20));
        l0.setForeground(Color.black);
        jFrame.add(l0);

        JLabel l1 = new JLabel("شناسه کاربر:");
        JLabel l2 = new JLabel("رمز عبور:");
        JLabel l3 = new JLabel("متن بالا را وارد کنید:");
        l1.setBounds(width / 2 + 50, 300, 100, 20);
        l2.setBounds(width / 2 + 50, 350, 100, 20);
        l3.setBounds(width / 2 + 20, 450, 300, 50);
        l1.setFont(new Font("Serif", Font.PLAIN, 18));
        l2.setFont(new Font("Serif", Font.PLAIN, 18));
        l3.setFont(new Font("Serif", Font.PLAIN, 18));

        JButton refreshButton = new JButton();
        JLabel refreshLabel = new JLabel("", refreshImg, JLabel.CENTER);
        refreshLabel.setBounds(0, 0, 60, 60);
        refreshButton.add(refreshLabel);
        JButton enterButton = new JButton("ورود");
        refreshButton.setFont(new Font("Serif", Font.PLAIN, 12));
        enterButton.setFont(new Font("Serif", Font.PLAIN, 12));
        refreshButton.setBounds(width / 2 - 150, 410, 45, 30);
        enterButton.setBounds(width / 2 - 150, 510, 200, 30);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCode();
            }
        });
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user = userName.getText();
                pass = String.valueOf(password.getPassword());
                enteredCode = code.getText();
                goToEduPage();
            }
        });
        jFrame.add(refreshButton);
        jFrame.add(enterButton);


        jFrame.add(l1); jFrame.add(l2); jFrame.add(l3);
        mainPanel.add(jPanel2);
    }

    private int giveCode() {
        Random random = new Random();
        return random.nextInt(9000) + 1000;
    }

    private void goToEduPage() {
        try {
            if (Integer.parseInt(enteredCode) == correctCode) {
                try {
                    if (Controller.getIdInformation().containsKey(user)) {
                        if (Controller.getIdInformation().get(user).equals(pass)) {
                            jFrame.setVisible(false);
                            jFrame.dispose();
                            Controller.runEduMainPage(user);
                        } else {
                            System.out.println("wrong password");
                            loginFailed(LoginFailed.WRONG_PASSWORD);
                        }
                    } else {
                        System.out.println("user name is false");
                        loginFailed(LoginFailed.USER_NAME_NOT_FOUND);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("user name is false");
                    loginFailed(LoginFailed.USER_NAME_NOT_FOUND);
                }
            } else {
                System.out.println("code is false");
                loginFailed(LoginFailed.WRONG_CODE);
            }
        } catch (NumberFormatException e) {
            System.out.println("code is false");
            loginFailed(LoginFailed.WRONG_CODE);
        }
    }

    private void loginFailed(LoginFailed failed) {
        if (failed == LoginFailed.USER_NAME_NOT_FOUND) {
            JOptionPane.showMessageDialog(jFrame, "نام کاربری موجود نیست!");
        } else if (failed == LoginFailed.WRONG_PASSWORD) {
            JOptionPane.showMessageDialog(jFrame, "گذرواژه نادرست است!");
        } else {
            JOptionPane.showMessageDialog(jFrame, "کد وارد شده نادرست است!");
        }
        setCode();
        code.setText("");
        password.setText("");
    }

}
