package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;

public class UserHandler implements UserDao {

	@Override
	public void addUser(User user, String first, String last) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO exp_users (first_name, last_name, username, password, user_type, email, timestamp) VALUES (?,?,?,?,?,?,?)";
		try(Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, first);
			ps.setString(2, last);
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getUserType());
			ps.setString(6, user.getEmail());
			ps.setString(7, user.getTimeStamp());
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		User user = null;
		String sql = "SELECT user_id, concat_ws(' ', first_name, last_name) as name, username, password, user_type, email from exp_users WHERE user_id = ?;";
		try(Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getString("user_type"), rs.getString("email"), rs.getString("timestamp"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		User user = null;
		String sql = "SELECT user_id, concat_ws(' ', first_name, last_name) as name, username, password, user_type, email from exp_users WHERE username = ?;";
		try(Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getString("user_type"), rs.getString("email"), rs.getString("timestamp"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		String sql = "SELECT user_id, concat_ws(' ', first_name, last_name) as name, username, password, user_type, email from exp_users;";
		try(Connection conn = Connector.getConnection()) {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				users.add(new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getString("user_type"), rs.getString("email"), rs.getString("timestamp")));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public boolean verifyUser(String username, String password) {
		// TODO Auto-generated method stub
		String sql = "select * from exp_users where 'username' = ? and 'password' = ?;";
		try(Connection conn = Connector.getConnection()) {
			PreparedStatement cs = conn.prepareStatement(sql);
			cs.setString(1, username);
			cs.setString(2, password);
			ResultSet rs = cs.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		String sql = "call remove_user(?)";
		try(Connection conn = Connector.getConnection()) {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, id);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAllUsers() {
		// TODO Auto-generated method stub
		String sql = "call remove_all_users()";
		try(Connection conn = Connector.getConnection()) {
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
