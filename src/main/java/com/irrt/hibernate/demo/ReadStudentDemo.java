package com.irrt.hibernate.demo;

import com.irrt.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ReadStudentDemo {


    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create a student object
            System.out.println("Creating new student object...");
            List<Student> students = Arrays.asList(new Student("Daffy", "Duck", "daffy@google.com"),
                    new Student("John", "Mercury", "maercury@google.com"));

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student...");
            for (Iterator<Student> iterator = students.iterator(); iterator.hasNext(); ) {
                Student next = iterator.next();
                System.out.println(next);
                session.save(next);
            }


            // commit transaction
            session.getTransaction().commit();

            // MY NEW CODE

            // find out the student's id: primary key

            for (Iterator<Student> iterator = students.iterator(); iterator.hasNext(); ) {
                Student next = iterator.next();

            System.out.println("Saved student. Generated id: " + next.getId());
            }

            // now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the id: primary key
            for (Iterator<Student> iterator = students.iterator(); iterator.hasNext(); ) {
                Student next = iterator.next();

            System.out.println("\nGetting student with id: " + next.getId());

            Student myStudent = session.get(Student.class, next.getId());

            System.out.println("Get complete: " + myStudent);}

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }

}



