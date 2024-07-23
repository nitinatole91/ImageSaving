package com.students;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.xml.sax.InputSource;

public class Test {

	public static void main(String[] args) throws IOException {

		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		// insert data into database
		Student student = new Student();
		student.setName("ram");
		student.setCity("pune");
		student.setMobile("9595972678");
		FileInputStream fi=new FileInputStream("src/main/resources/cool.jpg");
		byte[] data=new byte[fi.available()];
		fi.read(data);
		student.setProfile(data);
		
		
		session.save(student);
		System.out.println("Record saved successfully.");
		
		//get data in mysql
		Student s1 = (Student) session.get(Student.class, 1);
		System.out.println("First student data is>>");
		System.out.println("Name>>" + s1.getName());
		System.out.println("City>>" + s1.getCity());
		System.out.println("Mobile>>" + s1.getMobile());
		System.out.println("image>>" + s1.getProfile());
		
		byte[] profile = s1.getProfile();
		 try (FileOutputStream fos = new FileOutputStream("C:\\Users\\Ni3\\Downloads\\nitin.jpg")) {
	            fos.write(profile);
	            System.out.println("Image saved successfully.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		transaction.commit();
		session.close();

	}
}

