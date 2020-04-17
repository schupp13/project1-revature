//Canonical JUnit import statement:
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.model.User;
import com.revature.services.UserService;
public class LoginTest {
	
	private static UserService service;
	
	@Before
	public void setUp() {
		service = new UserService();	
	
	}
	
	
	  @After
	  public void tearDown() {
		  service = null;
	    System.out.println("After is running!");
	  }
	  
	  @Test
	  public void login() {
	    User result = service.login("chuck", "password");
	  
		   assertTrue(result.getFirstName().equals("Philip"));
		   
	  }
	  
	  @Test
	  public void logout() {
		  
	  }

}
