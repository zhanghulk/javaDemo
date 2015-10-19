
package javax.util.demo;

import java.util.Vector;

public class ObserverTest {

    public static void main(String[] args) {
        Vector<Observer> students = new Vector<Observer>();
        Teacher teacher = new Teacher();
        for (int i = 0; i < 10; i++) {
            Student st = new Student("Student_" + i, teacher);
            students.add(st);
            teacher.attach(st);
        }
        teacher.setPhone("88803807");
        for (int i = 0; i < 10; i++)
            ((Student) students.get(i)).show();
        
        System.out.println("\n\nThe cell phone of teacher is updated : " + 88808880 + "  >>>>>>>>>>>>>>>");
        teacher.setPhone("88808880");
        for (int i = 0; i < 10; i++)
            ((Student) students.get(i)).show();
    }

    // Subject代码：
    public static interface Subject {
        public void attach(Observer o);

        public void detach(Observer o);

        public void notice();
    }

    // Observer代码：
    public static interface Observer {
        public void update();
    }

    // Teacher代码；
    public static class Teacher implements Subject {
        private String phone;
        private Vector students;

        public Teacher() {
            phone = "";
            students = new Vector();
        }

        public void attach(Observer o) {
            students.add(o);
        }

        public void detach(Observer o) {
            students.remove(o);
        }

        public void notice() {
            for (int i = 0; i < students.size(); i++)
                ((Observer) students.get(i)).update();
        }

        public void setPhone(String phone) {
            this.phone = phone;
            notice(); // --关键
        }

        public String getPhone() {
            return phone;
        }
    }

    // Student代码：
    public static class Student implements Observer {
        private String name;
        private String phone;
        private Teacher teacher;

        public Student(String name, Teacher t) {
            this.name = name;
            teacher = t;
        }

        public void show() {
            System.out.println("Name:" + name + "\nTeacher'sphone:" + phone);
        }

        public void update() {
            phone = teacher.getPhone();
        }
    }
}
