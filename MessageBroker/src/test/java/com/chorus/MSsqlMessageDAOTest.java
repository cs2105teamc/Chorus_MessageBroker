/**
 * 
 */
package com.chorus;

import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.chorus.database.DAOFactory;
import com.chorus.database.MSSqlserverdbDAOFactory;
import com.chorus.database.MSsqlMessageDAO;
import com.chorus.database.MessageDAO;
import com.chorus.enums.DAOtype;

import junit.framework.TestCase;

/**
 * @author garfijam
 * @since 11 Dec 2015
 * @version 1.0
 *
 */
public class MSsqlMessageDAOTest extends TestCase {

	private MSSqlserverdbDAOFactory mockMSSqlDAO;
	private MessageDAO messageDAO;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		this.mockMSSqlDAO = createStrictMock(MSSqlserverdbDAOFactory.class);
		//expect(this.mockConfig.Validate())
			//.andReturn(true);	
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
	 * Test method for {@link com.chorus.database.MSsqlMessageDAO#MSsqlMessageDAO()}.
	 */
	@Test
	public void testMSsqlMessageDAO() {
		expect(this.mockMSSqlDAO.getMessageDAO())
			.andReturn(new MSsqlMessageDAO());	
		replay(this.mockMSSqlDAO);
		
		messageDAO = this.mockMSSqlDAO.getMessageDAO();
		assertThat(messageDAO, instanceOf(MessageDAO.class));
	}

	/**
	 * Test method for {@link com.chorus.database.MSsqlMessageDAO#insertMessage()}.
	 */
	@Test
	public void testInsertMessage() {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.chorus.database.MSsqlMessageDAO#deleteMessage()}.
	 */
	@Test
	public void testDeleteMessage() {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.chorus.database.MSsqlMessageDAO#findMessage()}.
	 */
	@Test
	public void testFindMessage() {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.chorus.database.MSsqlMessageDAO#updateMessage()}.
	 */
	@Test
	public void testUpdateMessage() {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.chorus.database.MSsqlMessageDAO#selectMessageRS()}.
	 */
	@Test
	public void testSelectMessageRS() {
		///fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.chorus.database.MSsqlMessageDAO#selectMessagesTO()}.
	 */
	@Test
	public void testSelectMessagesTO() {
		//fail("Not yet implemented"); // TODO
	}

}
