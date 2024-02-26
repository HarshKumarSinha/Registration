import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Registration implements ActionListener {
    String[] date = new String[31];
    String[] month = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    String[] years = new String[74];
    JFrame f;
    JButton b1 = new JButton("Submit");
    JButton b2 = new JButton("Clear");
    JCheckBox cb1 = new JCheckBox("Accept Terms and Conditions");
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JRadioButton r1 = new JRadioButton("Male");
    JRadioButton r2 = new JRadioButton("Female");
    JComboBox cm1;
    JComboBox cm2 = new JComboBox(month);
    JComboBox cm3;
    JTextArea t3 = new JTextArea();
    JTextArea ta = new JTextArea();
    JLabel l6 = new JLabel();
    public Registration() {
        f = new JFrame("Registration Form");
        f.getContentPane().setBackground(Color.BLACK);
        f.getContentPane().setForeground(Color.white);
        JLabel label = new JLabel("Registration Form");
        label.setBounds(150, 10, 250, 60);
        label.setFont(new Font("Times New Roman",Font.BOLD|Font.ITALIC,30));
        label.setForeground(Color.CYAN);
        JLabel l1  = new JLabel("Name");
        l1.setBounds(20,80,50,30);
        l1.setForeground(Color.white);
        t1.setBounds(70,80,150,30);
        JLabel l2  = new JLabel("Mobile");
        l2.setForeground(Color.white);
        l2.setBounds(20,112,50,30);
        t2.setBounds(70,115,150,30);
        JLabel l3 = new JLabel("Gender");
        l3.setBounds(20,145,50,30);
        l3.setForeground(Color.white);
        r1.setBounds(80,145,70,30);
        r1.setBackground(Color.black);
        r1.setForeground(Color.CYAN);
        r2.setBounds(150,145,100,30);
        r2.setBackground(Color.black);
        r2.setForeground(Color.CYAN);
        JLabel l5 = new JLabel("DOB");
        l5.setForeground(Color.white);
        l5.setBounds(20,175,50,30);
        for (int i = 0;i<31;i++)
        {
            date[i]=String.valueOf(i+1);
        }
        cm1 = new JComboBox(date);
        cm1.setBackground(Color.black);
        cm1.setForeground(Color.cyan);
        cm2.setBackground(Color.black);
        cm2.setForeground(Color.cyan);
        int year = 1950;
        for (int i =0; i<74;i++)
        {
            years[i] = String.valueOf(year);
            year = year+1;
        }
        cm3 = new JComboBox(years);
        cm3.setBackground(Color.black);
        cm3.setForeground(Color.cyan);
        cm1.setBounds(60,175,50,30);
        cm2.setBounds(120,175,70,30);
        cm3.setBounds(200,175,70,30);
        JLabel l4 = new JLabel("Address");
        l4.setForeground(Color.white);
        l4.setBounds(20,210,50,30);
        t3.setBounds(80,210,180,70);
        cb1.setBounds(40,285,200,30);
        cb1.setBackground(Color.black);
        cb1.setForeground(Color.red);
        l6.setBounds(70,360,200,30);
        ta.setBounds(280,80,250,320);
        b1.setBounds(40,320,100,30);
        b2.setBounds(150,320,100,30);
        ta.setFont(new Font("Times New Roman",Font.ITALIC,15));
        ta.setBackground(Color.GRAY);
        ta.setForeground(Color.white);
        t1.setBackground(Color.GRAY);
        t1.setForeground(Color.white);
        t2.setBackground(Color.GRAY);
        t2.setForeground(Color.white);
        t3.setBackground(Color.GRAY);
        t3.setForeground(Color.white);
        ButtonGroup b = new ButtonGroup();
        b.add(r1);b.add(r2);
        f.setSize(550, 500);
        f.add(label);
        f.add(l1);
        f.add(t1);
        f.add(l2);
        f.add(r1);
        f.add(r2);
        f.add(l3);
        f.add(t2);
        f.add(l4);
        f.add(t3);
        f.add(cb1);
        f.add(b1);
        f.add(b2);
        f.add(l5);
        f.add(cm1);
        f.add(cm2);
        f.add(cm3);
        f.add(ta);
        f.add(l6);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setVisible(true);
        b1.setBackground(Color.ORANGE);
        b2.setBackground(Color.ORANGE);
        b1.addActionListener(this);
        b2.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver is load");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Details", "root", "");
                System.out.println("Connect");
                Statement st = con.createStatement();
                if (cb1.isSelected()) {
                    String Gender;
                    String Name = t1.getText();
                    String Mobile = t2.getText();
                    if (r1.isSelected())
                        Gender = "Male";
                    else
                        Gender = "Female";
                    String Date_Of_Birth= cm1.getSelectedItem() + " / " + cm2.getSelectedItem() + " / " + cm3.getSelectedItem();
                    String Address = t3.getText();
                    ta.setText(Name + "\n" + Gender + "\n" + Date_Of_Birth + "\n" + Mobile + "\n" + Address);
                    int v = st.executeUpdate("INSERT INTO `persons`values('"+Name+"','"+Mobile+"','"+Gender+"','"+Date_Of_Birth+"','"+Address+"')");
                    if(v>0)
                        System.out.println("connect");
                    con.close();
                    l6.setText("Registration Successful");
                } else {
                    l6.setText("Please accept the Terms and Conditions");
                }
            }
            catch (ClassNotFoundException e1) {
                System.out.println("Driver is not load." + e1);
            }
            catch (SQLException e2)
            {
                System.out.println("Error = "+e2);
            }
        }
        if(e.getSource()==b2)
        {
            t1.setText("");
            t2.setText("");
            t3.setText("");
            ta.setText("");
            l6.setText("");
            r1.setSelected(false);
            r2.setSelected(false);
            cb1.setSelected(false);
            cm1.setSelectedIndex(0);
            cm2.setSelectedIndex(0);
            cm3.setSelectedIndex(0);
        }
    }
    public static void main(String[] args)
    {
        Registration ob = new Registration();
    }
}