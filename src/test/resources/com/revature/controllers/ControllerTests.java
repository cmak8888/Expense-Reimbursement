import static org.junit.Assert.*;

import com.revature.models.*;
import com.revature.dao.*;
import org.junit.Test;

public class ControllerTests {

	static List<User> users = new ArrayList<User>();
	static UserDao uDao = new UserHandler();
	
	@Before
	public void generateUsers() {
		User user = new User("User1", "Employee", "employee1", "pass123", "newemail@mail.com");
		uDao.addUser(user, "User", "1");
		
		User user2 = new User("User2", "Manager", "manager1", "pass123", "newmanager@gmail.com");
		uDao.addUser(user2, "User", "2");
	}
	
	@Test
	public void verifyUserExists() {
		
	}
	
	//Resets the data
	@After
	public void clearData() {
		for(User i: users) {
			uDao.remove_user(i);
		}
	}

}
