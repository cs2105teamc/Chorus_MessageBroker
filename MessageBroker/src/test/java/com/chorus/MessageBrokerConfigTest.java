package com.chorus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.hamcrest.CoreMatchers.instanceOf;
import java.util.Properties;

import junit.framework.TestCase;

public class MessageBrokerConfigTest extends TestCase {

	private MessageBrokerConfig config;
	private Properties mockProp;
	private String configFile = "C:\\Users\\garfijam\\OneDrive\\CS2105_Spring\\MessageBroker\\src\\main\\resources\\config.properties";
	//private String configFile = "config.properties";
	private String dbConnectionString;
	private String JDBCDriver;
	private String PollingInterval;
	private String UserName;
	private String Password;
	
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();				
		
		// Setup the mock for the test
		this.mockProp = createStrictMock(Properties.class);
		//return values
		this.dbConnectionString = "localhost\\SQL2008r2;";
		this.JDBCDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		this.PollingInterval = "5";
		this.UserName = "Test";
		this.Password = "Test";
		
		expect(mockProp.getProperty("ConnectionString"))
			.andReturn(this.dbConnectionString);
		expect(mockProp.getProperty("JDBCDriver"))
			.andReturn(this.JDBCDriver);
		expect(mockProp.getProperty("PollingInterval"))
			.andReturn(this.PollingInterval);
		expect(mockProp.getProperty("UserName"))
			.andReturn(this.UserName);
		expect(mockProp.getProperty("Password"))
			.andReturn(this.Password);

	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		config = null;
	}

	
	/*
	 * Domain tests
	 */
	@Test
	public void testConstructorProperties() {
		config = null;
		
		replay(mockProp);		
		config = new MessageBrokerConfig(mockProp);
		assertThat(config, instanceOf(MessageBrokerConfig.class));
		verify(mockProp);
		
	}
	
	@Test
	public void testValidate() {
		config = null;
		
		replay(mockProp);		
		config = new MessageBrokerConfig(mockProp);
		assertTrue(config.Validate());
		verify(mockProp);
		
	}
	
	/*
	 * Integration tests
	 */
	//Test object creation and loading of the properties file
	@Test
	public void testConstructorConfigFile() {
		System.out.println("Integration test");
		config = new MessageBrokerConfig(configFile);
		assertEquals(config.getConfigFile(), configFile);
				
	}


}
