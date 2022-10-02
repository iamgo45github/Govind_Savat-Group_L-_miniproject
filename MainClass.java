package com.miniproject.govi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainClass {
	Student student = new Student();
	public static void main(String[] args) throws SQLException {
		Quiz quiz = new Quiz();
		quiz.logic();
	}
	
 }

class Quiz {
	Scanner scanner = new Scanner(System.in);
	int correctAnsCount = 0;
	int wrongAnsCount = 0;
			
	public void logic() throws SQLException {
		Connection connection = null;
		PreparedStatement pre = null;
		Quastions q1 = new Quastions("1. Who invented Java Programming?", "a. Guido van Rossum", "b. James Gosling", "c. Dennis Ritchie", "d. Bjarne Stroustrup");
		Quastions q2 = new Quastions("2. Which statement is true about Java?", "a. Java is a sequence-dependent programming language", "b. Java is a code dependent programming language", "c. Java is a platform-dependent programming language", "d. Java is a platform-independent programming language");
		Quastions q3 = new Quastions("3. Which component is used to compile, debug and execute the java programs?", "a. JRE", "b. JIT", "c. JDK", "d. JVM");
		Quastions q4 = new Quastions("4. Which one of the following is not a Java feature?", "a. Object-oriented", "b. Use of pointers", "c. Portable", "d. Dynamic and Extensible");
		Quastions q5 = new Quastions("5. Which of these cannot be used for a variable name in Java?", "a. identifier & keyword", "b. identifier", "c. keyword", "d. none of the mentioned");
		Quastions q6 = new Quastions("6. What is the extension of java code files?", "a. .js", "b. .class", "c. .text", "d. .java");
		Quastions q7 = new Quastions("7. Which environment variable is used to set the java path?", "a. MAVEN_Path", "b. JavaPATH", "c. JAVA", "d. JAVA_HOME");
		Quastions q8 = new Quastions("8. Which of the following is not an OOPS concept in Java?", "a. Polymorphism", "b. Inheritance", "c. Compilation", "d. Encapsulation");
		Quastions q9 = new Quastions("9. What is not the use of “this” keyword in Java?", "a. Referring to the instance variable when a local variable has the same name", "b. Passing itself to the method of the same class", "c. Passing itself to another method", "d. Calling another constructor in constructor chaining");
		Quastions q10 = new Quastions("10. Which of the following is a type of polymorphism in Java Programming?", "a. Multiple polymorphism", "b. Compile time polymorphism", "c. Multilevel polymorphism", "d. Execution time polymorphism");
		
		Map<Quastions, Character> hmap = new HashMap<Quastions, Character>();
		hmap.put(q1, 'b');
		hmap.put(q2, 'd');
		hmap.put(q3, 'c');
		hmap.put(q4, 'b');
		hmap.put(q5, 'c');
		hmap.put(q6, 'd');
		hmap.put(q7, 'd');
		hmap.put(q8, 'c');
		hmap.put(q9, 'b');
		hmap.put(q10, 'b');
		
		
			 System.out.println("Enter yout name ");
			String studentName = scanner.next();
			
			
			
		for(Map.Entry<Quastions, Character> map:hmap.entrySet() ) {
			System.out.println(map.getKey().getquastion());
			System.out.println(map.getKey().getOption1());
			System.out.println(map.getKey().getOption2());
			System.out.println(map.getKey().getOption3());
			System.out.println(map.getKey().getOption4());
			
			System.out.println("Enter your Answer: ");
			Character ans = scanner.next().charAt(0);
			
			int cans = Character.compare(ans, map.getValue());
			if (cans == 0) {
				System.out.println("Correct");
				correctAnsCount++;
			}else {
				System.out.println("Wrong");
				wrongAnsCount++;
			}
		}
		    
		    System.out.println();
		    System.out.println(studentName + " Result is" );
		    System.out.println("Total Quastions>> " + hmap.size());
		    System.out.println("Correct Answered Quastions>> " + correctAnsCount);
		    System.out.println("Wrong Answered Quastions>> " + wrongAnsCount);
		    System.out.println("Total marks>> " + correctAnsCount +"/"+hmap.size());
		
		    if (correctAnsCount < 5) {
		    	System.out.println("Fail");
		    	System.out.println("Do more Study and Better Luck Next Time");
		    } else if (correctAnsCount <= 5 ) {
		    	System.out.println("Your Grade is>> ");
		    	System.out.println("C Grade");
		    	System.out.println("Need more Study");
		    } else if (correctAnsCount <= 6 || correctAnsCount <= 7 || correctAnsCount <= 8) {
		    	System.out.println("Your Grade is>> ");
		    	System.out.println("B Grade");
		    	System.out.println("Nice performance");
		    } else {
		    	System.out.println("Your Grade is>> ");
		    	System.out.println("A Grade");
		    	System.out.println("Excellant Marks, Impressive");
		    }
		    
		    try {
				Class.forName("com.mysql.jdbc.Driver");
				 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Root");
				 pre = connection.prepareStatement("INSERT INTO studentData (studentName, Marks) VALUES (?,?)");
				pre.setString(1, studentName);
				pre.setInt(2, correctAnsCount);
				
				int in = pre.executeUpdate();
				System.out.println();
				System.out.println("Your Test is submitted " + in);
				System.out.println("Thanks for participation");
				} catch(Exception e) {
					System.out.println(e);
					e.printStackTrace();
				} finally {
					connection.close();
					pre.close();
				}
	}
}
    
