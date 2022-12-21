import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class App extends JFrame {
    private JPanel pnlMain;
    private JRadioButton rbCustomer;
    private JRadioButton rbClerk;
    private JRadioButton rbManager;
    private JTextField tfName;
    private JTextArea taPersons;
    private JButton btnSave;
    private JTextField tfAge;
    private JTextField tfMonths;
    private JTextField tfSalary;
    private JButton btnClear;
    private JTextField tfLoad;
    private JButton btnLoad;
    private JButton btnSayHi;
    private JButton btnSavePerson;
    private JButton btnLoadPerson;
    private JButton btnReward;

    private List<Person> persons;

    public App() {
        persons = new ArrayList<>();
        // TODO add implementations for all milestones here
        btnSave.addActionListener(e -> save());
        rbCustomer.addActionListener(e -> buyer());
        rbClerk.addActionListener(e -> work());
        rbManager.addActionListener(e -> work());
        btnClear.addActionListener(e -> clearing());
        btnLoad.addActionListener(e -> load());
        btnSayHi.addActionListener(e -> sayHi());
        btnReward.addActionListener(e -> giveReward());
    }

    void buyer() {
        tfMonths.setEnabled(false);
        tfSalary.setEnabled(false);
    }

    void work() {
        tfMonths.setEnabled(true);
        tfSalary.setEnabled(true);
    }

    public void save() {
        try {
            if(rbCustomer.isSelected()) {
                String name = tfName.getText();
                if(name.isBlank()) throw new NullPointerException();
                int age = Integer.parseInt(tfAge.getText());
                if(age < 0) throw new NumberFormatException();
                Person person = new Customer(name, age);
                persons.add(person);
                taPersons.append(numPersons + ". Customer - " + person.getName() + " (" + person.getAge() + ")\n");
                numPersons++;
            } else if(rbClerk.isSelected()) {
                String name = tfName.getText();
                if(name.isBlank()) throw new NullPointerException();
                int age = Integer.parseInt(tfAge.getText());
                if(age < 0) throw new NumberFormatException();
                int months = Integer.parseInt(tfMonths.getText());
                if(months < 0) throw new IllegalArgumentException();
                double salary = Double.parseDouble(tfSalary.getText());
                if(salary < 0) throw new ArithmeticException();
                Person person = new Clerk(name, age, months, salary);
                persons.add(person);
                taPersons.append(numPersons + ". Clerk - " + person.getName() + " (" + person.getAge() + ")\n");
                numPersons++;
            } else if(rbManager.isSelected()) {
                String name = tfName.getText();
                if(name.isBlank()) throw new NullPointerException();
                int age = Integer.parseInt(tfAge.getText());
                if(age < 0) throw new NumberFormatException();
                int months = Integer.parseInt(tfMonths.getText());
                if(months < 0) throw new IllegalArgumentException();
                double salary = Double.parseDouble(tfSalary.getText());
                if(salary < 0) throw new ArithmeticException();
                Person person = new Manager(name, age, months, salary);
                persons.add(person);
                taPersons.append(numPersons + ". Manager - " + person.getName() + " (" + person.getAge() + ")\n");
                numPersons++;
            } else {
                throw new Exception();
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Enter a name");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid age");
          } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Invalid months");
          } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(null, "Invalid salary");
          } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select a role");
        }
    }
    public void clearing() {
        tfName.setText("");
        tfAge.setText("");
        tfMonths.setText("");
        tfSalary.setText("");
    }
    public void load() {
        try {
            int index = Integer.parseInt(tfLoad.getText()) - 1;
            Person person = persons.get(index);
            tfName.setText(person.getName());
            tfAge.setText(String.valueOf(person.getAge()));
            tfMonths.setText("");
            tfSalary.setText("");
            rbCustomer.doClick();
            if(person instanceof Clerk) {
                Clerk clerk = (Clerk) person;
                tfMonths.setText(String.valueOf(clerk.getMonths_worked()));
                tfSalary.setText(String.valueOf(clerk.getSalary()));
                rbClerk.doClick();
            } else if (person instanceof Manager) {
                Manager manager = (Manager) person;
                tfMonths.setText(String.valueOf(manager.getMonths_worked()));
                tfSalary.setText(String.valueOf(manager.getSalary()));
                rbManager.doClick();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid index");
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Index out of bounds");
        }
    }
    public void sayHi(){
        for(Person person : persons) {
            System.out.println(person);
        }
    }
    void giveReward(int n) {
        try {
            Person person = persons.get(n);
            if(person instanceof Clerk) {
                Clerk clerk = (Clerk) person;
                if(clerk.getMonths_worked() == 0) throw new ArithmeticException();
                JOptionPane.showMessageDialog(null, "Clerk " + clerk.getName() + " has been rewarded with " + String.format("%.2f", clerk.thirteenmonth()));
            } else if(person instanceof Manager) {
                Manager manager = (Manager) person;
                if(manager.getMonths_worked() == 0) throw new ArithmeticException();
                JOptionPane.showMessageDialog(null, "Manager " + manager.getName() + " has been rewarded with " + String.format("%.2f", manager.thirteenmonth()));
            } else {
                throw new IllegalArgumentException();
            }
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(null, "Invalid months");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Not an employee");
        }

    }

    public static void main(String[] args) {
        // add here how to make GUI visible
        JFrame frame = new JFrame("Person App");
        frame.setContentPane(new App().pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setVisible(true);
    }

    static void giveReward(int n) {

    }
}
