package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DBConnectionSpec {

	@Test
	void testGetUrl() throws Exception {
		UtilProp.loadProperty();
		Assertions.assertEquals("jdbc:mysql://ec2-3-14-71-197.us-east-2.compute.amazonaws.com:3306/FoodTrackerDB", DBConnection.getURL());
	}
	
	@Test
	void testGetUserName() throws Exception {
		UtilProp.loadProperty();
		Assertions.assertEquals("testuser", DBConnection.getUserName());
	}

}
