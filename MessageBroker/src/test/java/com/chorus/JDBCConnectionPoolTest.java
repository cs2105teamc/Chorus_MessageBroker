/**
 * 
 */
package com.chorus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.chorus.database.JDBCConnectionPool;
import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import java.sql.*;
import junit.framework.TestCase;

/**
 * @author garfijam
 * @since 8 Dec 2015
 * @version 1.0
 *
 */
public class JDBCConnectionPoolTest extends TestCase {

	private JDBCConnectionPool pool;
	private String dsn;
	private String JDBCDriver;
	private String UserName;
	private String Password;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		
		this.dsn = "localhost\\SQL2008R2";
		this.JDBCDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		this.UserName = "test";
		this.Password = "This1$aT3st!";
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		
		if (pool != null) {
			pool = null;
		}		
	}

	/**
	 * Test method for {@link com.chorus.database.JDBCConnectionPool#JDBCConnectionPool(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testJDBCConnectionPool() {
		pool = new JDBCConnectionPool(this.JDBCDriver, this.dsn, this.UserName, this.Password);
		assertThat(pool, instanceOf(JDBCConnectionPool.class));
	}

	/**
	 * Test method for {@link com.chorus.database.JDBCConnectionPool#create()}.
	 * 
	 * Creates a ConnectionPool
	 * Checks out a connection
	 * Asserts it is of type Connection & is open
	 * Closes connections
	 */
	@Test
	public void testCreate() {
		
		pool = new JDBCConnectionPool(this.JDBCDriver, this.dsn, this.UserName, this.Password); 
		try {
			Connection conn = pool.checkOut();
			assertThat(conn, instanceOf(Connection.class));
			assertTrue(!conn.isClosed());
			if(!conn.isClosed()) {
				 conn.close();
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Test method for {@link com.chorus.database.JDBCConnectionPool#expire(java.sql.Connection)}.
	 */
	@Test
	public void testExpireConnection() {
		pool = new JDBCConnectionPool(this.JDBCDriver, this.dsn, this.UserName, this.Password); 
		
		Connection mockConn = createStrictMock(Connection.class);		
		
		try {
			//Close Connection
			mockConn.close();
			expectLastCall().times(1);			
		} catch (SQLException e) {
			//this should do nothing.  ITS A MOCK!
			e.printStackTrace();
		}
		replay(mockConn);		
		pool.expire(mockConn);
		verify(mockConn);
	}

	/**
	 * Test method for {@link com.chorus.database.JDBCConnectionPool#validate(java.sql.Connection)}.
	 */
	@Test
	public void testValidateConnection() {
		pool = new JDBCConnectionPool(this.JDBCDriver, this.dsn, this.UserName, this.Password); 
		
		Connection mockConn = createStrictMock(Connection.class);		
		
		try {
			//Validate true
			expect(mockConn.isClosed())
				.andReturn(false);
			//Validate false
			expect(mockConn.isClosed())
			.andReturn(true);
		} catch (SQLException e) {
			//this should do nothing.  ITS A MOCK!
			e.printStackTrace();
		}
		replay(mockConn);
		assertTrue(pool.validate(mockConn));
		assertFalse(pool.validate(mockConn));				
	}

	/**
	 * Test method for {@link com.chorus.database.ObjectPool#checkOut()}.
	 */
	@Test
	public void testCheckOut() {
		pool = new JDBCConnectionPool(this.JDBCDriver, this.dsn, this.UserName, this.Password); 					
		assertThat(pool.checkOut(), instanceOf(Connection.class));			
	}


}
