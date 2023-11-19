package com.madhu;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.madhu.dao.StudentDao;
import com.madhu.modal.Student;

@SpringBootApplication
public class SpringJdbcStudentAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringJdbcStudentAppApplication.class, args);
		StudentDao studentDao = context.getBean(StudentDao.class);

		boolean exit = false;
		System.out.println("Welcome to student app..");
		while (!exit) {
			Scanner scanner = new Scanner(System.in);
			
			System.out.println(" \n1.List all the students"
					+ "\n2.Find the student details by student id"
					+"\n3.Add a student"
					+"\n4.Delete a Student"
					+"\n5.Update Student Details"
					+ "\n6.exit");
			int input = Integer.parseInt(scanner.nextLine());
			switch (input) {
			case 1:
				List<Student> students = studentDao.findAll();
				students.forEach(s -> System.out.println(s));

				break;
			case 2:
				System.out.println("enter student id to fetch the details..");
				int id = Integer.parseInt(scanner.nextLine());
				Student s = studentDao.findById(id);
				System.out.println(s);

				break;
				
				case 3:System.out.println("Enter student details to add a student!!");
				Student st= new Student();
				System.out.println("Enter student id:");
				int sid= Integer.parseInt(scanner.nextLine());
				st.setId(sid);
				System.out.println("Enter student name:");
				String name= scanner.nextLine();
				st.setName(name);
				System.out.println("Enter student age:");
				int age= Integer.parseInt(scanner.nextLine());
				st.setAge(age);
				System.out.println("Enter student address:");
				String address= scanner.nextLine();
				st.setAddress(address);
				int rows= studentDao.addStudent(st);
				if(rows>0) {
					System.out.println("Student added successfully");
				}
				

					
					break;
					
			
				case 4:
					System.out.println("Enter student ID to delete a student!!");
				
				int stuId= Integer.parseInt(scanner.nextLine());
				int rowsDeleted=studentDao.deleteStudent(stuId);
				if(rowsDeleted>0) {
					System.out.println("Student deleted successfully");
				}
					break;
				case 5:
					System.out.println("Enter student  ID to update a student!!");
					int stid= Integer.parseInt(scanner.nextLine());
					
					System.out.println("Enter student  new ADDRESS!!");
					String newAddress =scanner.nextLine();
					
					int rowsUpdated=studentDao.updateStudent(stid,newAddress);
					if(rowsUpdated>0) {
						System.out.println("Student address updated successfully");
					}
					break;
					
			case 6:
				System.out.println("Thank you for using Student app!!");
				exit=true;

			default:
				break;
			}
		}

	}

}
