/**
 * 
 */
package com.chorus;

import static org.easymock.EasyMock.createStrictMock;

import java.sql.Connection;
import java.util.Properties;

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

import com.chorus.database.DAOFactory;
import com.chorus.database.JDBCConnectionPool;
import com.chorus.database.MSSqlserverdbDAOFactory;
import com.chorus.database.MessageDAO;
import com.chorus.enums.DAOtype;

import junit.framework.TestCase;

/**
 * @author garfijam
 * @since 9 Dec 2015
 * @version 1.0
 *
 */
public class MSSqlserverdbDAOFactoryTest extends TestCase {

	private Config mockConfig;
	private DAOFactory sqlDAO;
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		// Setup the mock for the test
		this.mockConfig = createStrictMock(MessageBrokerConfig.class);
		expect(this.mockConfig.Validate())
			.andReturn(true);	
		expect(this.mockConfig.getJDBCDriver())
			.andReturn("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		expect(this.mockConfig.getDbConnection())
			.andReturn("localhost\\SQL2008r2;");
		expect(this.mockConfig.getUsername())
			.andReturn("test");
		expect(this.mockConfig.getPassword())
			.andReturn("This1$aT3st!");
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.chorus.database.MSSqlserverdbDAOFactory#getMessageDAO()}.
	 */
	@Test
	public void testGetMessageDAO() {
		replay(this.mockConfig);
		sqlDAO = new MSSqlserverdbDAOFactory(mockConfig);
		MessageDAO message = sqlDAO.getMessageDAO();
		assertThat(message, instanceOf(MessageDAO.class));
	}

	/**
	 * Test method for {@link com.chorus.database.MSSqlserverdbDAOFactory#MSSqlserverdbDAOFactory()}.
	 */
	@Test
	public void testConstructorMSSqlserverdbDAOFactory() {		
		sqlDAO = new MSSqlserverdbDAOFactory();
		assertThat(sqlDAO, instanceOf(MSSqlserverdbDAOFactory.class));
	}

	/**
	 * Test method for {@link com.chorus.database.MSSqlserverdbDAOFactory#MSSqlserverdbDAOFactory(com.chorus.MessageBrokerConfig)}.
	 */
	@Test
	public void testMSSqlserverdbDAOFactoryMessageBrokerConfig() {
		replay(this.mockConfig);
		sqlDAO = new MSSqlserverdbDAOFactory(mockConfig);
		assertThat(sqlDAO, instanceOf(MSSqlserverdbDAOFactory.class));
		verify(this.mockConfig);
	}

	/**
	 * Test method for {@link com.chorus.database.MSSqlserverdbDAOFactory#createConnection()}.
	 */
	@Test
	public void testCreateConnection() {
		replay(this.mockConfig);
		MSSqlserverdbDAOFactory.config = this.mockConfig;
		Connection conn = MSSqlserverdbDAOFactory.createConnection();
		assertThat(conn, instanceOf(Connection.class));
	}
	/**
	 * Test method for {@link com.chorus.database.MSSqlserverdbDAOFactory#createConnection(Config)}.
	 */
	@Test
	public void testCreateConnectionConfig() {
		replay(this.mockConfig);
		MSSqlserverdbDAOFactory.config = this.mockConfig;
		Connection conn = MSSqlserverdbDAOFactory.createConnection(this.mockConfig);
		assertThat(conn, instanceOf(Connection.class));
	}

	/**
	 * Test method for {@link com.chorus.database.DAOFactory#setConfig(com.chorus.MessageBrokerConfig)}.
	 */
	@Test
	public void testSetConfig() {
		replay(this.mockConfig);
		sqlDAO = new MSSqlserverdbDAOFactory();
		
		sqlDAO.setConfig(this.mockConfig);
		
		verify(this.mockConfig);
	}

	/**
	 * Test method for {@link com.chorus.database.DAOFactory#getDAOFactory(com.chorus.enums.DAOtype)}.
	 */
	@Test
	public void testGetDAOFactory() {
		
		replay(this.mockConfig);
		 sqlDAO = DAOFactory.getDAOFactory(DAOtype.MSSQLSERVER);
		 assertThat(sqlDAO, instanceOf(MSSqlserverdbDAOFactory.class));
		 
		 sqlDAO = null;
		 
		 sqlDAO = DAOFactory.getDAOFactory(DAOtype.MSSQLSERVER, this.mockConfig);
		 assertThat(sqlDAO, instanceOf(MSSqlserverdbDAOFactory.class));
	}

}
