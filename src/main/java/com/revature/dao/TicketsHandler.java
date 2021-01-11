package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import com.revature.models.Ticket;
import com.revature.models.TicketType;
import com.revature.models.User;
import com.revature.services.Log;

public class TicketsHandler implements TicketsDao {

	public Ticket getTicket(int id) {
		// TODO Auto-generated method stub
		Ticket ticket = null;
		String sql = "SELECT * FROM exp_tickets WHERE ticket_id = ?";
		try(Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				ticket = (new Ticket(rs.getInt("ticket_id"),rs.getInt("user_id"), rs.getString("title"), rs.getString("description"), rs.getInt("ticket_type"), rs.getBytes("image"), rs.getDouble("amount"), rs.getBoolean("approved"), rs.getString("timestamp")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticket;
	}

	public List<Ticket> getTickets(boolean approved) {
		// TODO Auto-generated method stub
		List<Ticket> tickets = new ArrayList<Ticket>();
		String sql = "SELECT * FROM exp_tickets WHERE approved = ?";
		try(Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, approved);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				tickets.add(new Ticket(rs.getInt("ticket_id"),rs.getInt("user_id"), rs.getString("title"), rs.getString("description"), rs.getInt("ticket_type"), rs.getBytes("image"), rs.getDouble("amount"), rs.getBoolean("approved"), rs.getString("timestamp")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User getTicketUser(int id) {
		User user = null;
		String sql = "CALL get_ticket_user(?)";
		try(Connection conn = Connector.getConnection()) {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			if(rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getString("user_type"), rs.getString("email"), rs.getString("timestamp"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<Ticket> getTickets(User user) {
		// TODO Auto-generated method stub
		List<Ticket> tickets = new ArrayList<Ticket>();
		String sql = "SELECT * FROM exp_tickets WHERE user_id = ?";
		try(Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				tickets.add(new Ticket(rs.getInt("ticket_id"),rs.getInt("user_id"), rs.getString("title"), rs.getString("description"), rs.getInt("ticket_type"), rs.getBytes("image"), rs.getDouble("amount"), rs.getBoolean("approved"), rs.getString("timestamp")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}
	
	public List<Ticket> getTickets(int id) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		String sql = "SELECT * FROM exp_tickets WHERE user_id = ?";
		try(Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				tickets.add(new Ticket(rs.getInt("ticket_id"),rs.getInt("user_id"), rs.getString("title"), rs.getString("description"), rs.getInt("ticket_type"), rs.getBytes("image"), rs.getDouble("amount"), rs.getBoolean("approved"), rs.getString("timestamp")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	public List<Ticket> getTickets(TicketType tType) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		String sql = "SELECT * FROM exp_tickets WHERE ticket_type = ?";
		try(Connection conn = Connector.getConnection()) {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, TicketType.getTicketValue(tType));
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				tickets.add(new Ticket(rs.getInt("ticket_id"),rs.getInt("user_id"), rs.getString("title"), rs.getString("description"), rs.getInt("ticket_type"), rs.getBytes("image"), rs.getDouble("amount"), rs.getBoolean("approved"), rs.getString("timestamp")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}
	
	@Override
	public List<Ticket> getTickets(User user, boolean approved) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		String sql = "SELECT * FROM exp_tickets WHERE user_id = ? AND approved = ?";
		try(Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.setBoolean(2, approved);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				tickets.add(new Ticket(rs.getInt("ticket_id"),rs.getInt("user_id"), rs.getString("title"), rs.getString("description"), rs.getInt("ticket_type"), rs.getBytes("image"), rs.getDouble("amount"), rs.getBoolean("approved"), rs.getString("timestamp")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	@Override
	public List<Ticket> getTickets(User user, TicketType tType) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		String sql = "SELECT * FROM exp_tickets WHERE user_id = ? AND ticket_type = ?";
		try(Connection conn = Connector.getConnection()) {
			PreparedStatement cs = conn.prepareStatement(sql);
			cs.setInt(1, user.getId());
			cs.setInt(2, TicketType.getTicketValue(tType));
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				tickets.add(new Ticket(rs.getInt("ticket_id"),rs.getInt("user_id"), rs.getString("title"), rs.getString("description"), rs.getInt("ticket_type"), rs.getBytes("image"), rs.getDouble("amount"), rs.getBoolean("approved"), rs.getString("timestamp")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}


	public List<Ticket> getAllTickets() {
		List<Ticket> tickets = new ArrayList<Ticket>();
		String sql = "SELECT * FROM exp_tickets";
		try(Connection conn = Connector.getConnection()) {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				tickets.add(new Ticket(rs.getInt("ticket_id"),rs.getInt("user_id"), rs.getString("title"), rs.getString("description"), rs.getInt("ticket_type"), rs.getBytes("image"), rs.getDouble("amount"), rs.getBoolean("approved"), rs.getString("timestamp")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	public void approveTicket(int id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE exp_tickets SET approved = 'true' WHERE ticket_id = ?";
		try(Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rejectTicket(int id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE exp_tickets SET approved = 'false' WHERE ticket_id = ?";
		try(Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			Log.warn("Error, SQL Exception in Rejecting Ticket " + id);
			e.printStackTrace();
		}
	}

	public void createTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO exp_tickets (user_id, title, ticket_type, description, amount, timestamp) VALUES (?,?,?,?,?, ?)";
		try(Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ticket.getUser().getId());
			ps.setString(2, ticket.getTitle());
			ps.setInt(3,TicketType.getTicketValue(ticket.getTicketType()));
			ps.setString(4, ticket.getDescription());
			ps.setDouble(5, ticket.getAmount());
			ps.setString(6, ticket.getTimeStamp());
			ps.execute();
		} catch (SQLException e) {
			Log.warn("Error, SQL Exception in creating Ticket " + ticket.getId());
			e.printStackTrace();
		}		
	}

	public void updateTicket(int id) {
		// TODO Auto-generated method stub
		
	}

	public void removeTicket(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM exp_tickets WHERE ticket_id = ?";
		try(Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			ps.execute();
		} catch (SQLException e) {
			Log.warn("Error, SQL Exception in Removing Ticket " + id);
			e.printStackTrace();
		}
	}

	@Override
	public List<Ticket> getAllTickets(String term, OrderDirection order) {
		// TODO Auto-generated method stub
		List<Ticket> tickets = new ArrayList<Ticket>();
		String sql = "SELECT * FROM exp_tickets ORDER BY ? ?";
		try(Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, term);
			ps.setString(2, OrderDirection.getString(order));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				tickets.add(new Ticket(rs.getInt("ticket_id"),rs.getInt("user_id"), rs.getString("title"), rs.getString("description"), rs.getInt("ticket_type"), rs.getBytes("image"), rs.getDouble("amount"), rs.getBoolean("approved"), rs.getString("timestamp")));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	@Override
	public void removeAllTickets() {
		// TODO Auto-generated method stub
		String sql = "TRUNCATE TABLE exp_tickets";
		try(Connection conn = Connector.getConnection()) {
			Statement s = conn.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			Log.warn("Error, SQL Exception in Removing All Tickets");
			e.printStackTrace();
		}
	}

	@Override
	public void removeAllTickets(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM exp_tickets WHERE user_id = ?";
		try(Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			ps.execute();
		} catch (SQLException e) {
			Log.warn("Error, SQL Exception in Removing All Tickets for user " + id);
			e.printStackTrace();
		}
	}

	@Override
	public void removeAllRejectedTickets() {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM exp_tickets WHERE approved = 'false'";
		try(Connection conn = Connector.getConnection()) {
			Statement s = conn.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			Log.warn("Error, SQL Exception in Removing Rejected Tickets");
			e.printStackTrace();
		}
	}

}
