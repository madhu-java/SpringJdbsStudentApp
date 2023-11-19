package com.madhu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.madhu.modal.Student;

@Repository
public class StudentDao implements IStudentDao {
	private static final String SQL_SELECT = "SELECT * FROM STUDENT";
	private static final String SQL_SELECT_BYID = "SELECT * FROM STUDENT WHERE ID=?";
	private static final String SQL_INSERT = "INSERT INTO STUDENT VALUES(?,?,?,?)";
	private static final String SQL_DELETE = "DELETE FROM STUDENT WHERE ID=?";
	private static final String SQL_UPDATE = "UPDATE STUDENT SET ADDRESS=?WHERE ID=?";
	@Autowired
private DataSource datasource;
	@Override
	public List<Student> findAll() {
		List<Student> students= new ArrayList<>();
		try {
			Connection connection = datasource.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT);
		ResultSet resultSet = prepareStatement.executeQuery();
		
		 
		while(resultSet.next()) {
			Student s=new Student();
			s.setId(resultSet.getInt(1));
			s.setName(resultSet.getString(2)); 
			s.setAge(resultSet.getInt(3));
			s.setAddress(resultSet.getString(4));
			students.add(s);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;
	}

	@Override
	public Student findById(Integer id) {
		 Student s=null;
		try {
			Connection connection= datasource.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_BYID);
			prepareStatement.setInt(1,id);
			  ResultSet resultSet = prepareStatement.executeQuery();
			  s=new Student();
			  while(resultSet.next()) {
					
					s.setId(resultSet.getInt(1));
					s.setName(resultSet.getString(2)); 
					s.setAge(resultSet.getInt(3));
					s.setAddress(resultSet.getString(4));
			  }
			  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return s;
	}

	@Override
	public int addStudent(Student s) {
		Connection connection;
		int insertedRows=0;
		try {
			connection = datasource.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_INSERT);
		prepareStatement.setInt(1, s.getId());
		prepareStatement.setString(2, s.getName());
		prepareStatement.setInt(3, s.getAge());
		prepareStatement.setString(4, s.getAddress());
		
		insertedRows=prepareStatement.executeUpdate();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return insertedRows;
	}

	@Override
	public int deleteStudent(Integer id) {
		 
		int rowsDeleted=0;
		try {
			Connection connection = datasource.getConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(SQL_DELETE);
			prepareStatement.setInt(1, id);
			 rowsDeleted = prepareStatement.executeUpdate();
			
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return rowsDeleted;
	}

	@Override
	public int updateStudent(Integer id, String address) {
		int rowsUpdated=0;
		try {
			Connection connection = datasource.getConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(SQL_UPDATE);
			prepareStatement.setString(1, address);
			prepareStatement.setInt(2, id);
			 rowsUpdated = prepareStatement.executeUpdate();
			
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return rowsUpdated;
	}

}
