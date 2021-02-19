package com.irrt.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class CreateDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // create the objects
			/*
			Instructor tempInstructor =
					new Instructor("Chad", "Darby", "darby@luv2code.com");

			InstructorDetail tempInstructorDetail =
					new InstructorDetail(
							"http://www.irrt.com/youtube",
							"Box");
			*/

            Instructor tempInstructor =
                    new Instructor("Madhu", "Patel", "ttr@yahoo.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail(
                            "http://www.youtube.com",
                            "Guitar");

            // associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // start a transaction
            session.beginTransaction();

            // save the instructor
            //
            // Note: this will ALSO save the details object
            // because of CascadeType.ALL
            //
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }

}



