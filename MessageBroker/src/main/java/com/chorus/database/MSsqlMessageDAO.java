package com.chorus.database;

import java.sql.Connection;
import java.util.Collection;

import javax.sql.RowSet;

import com.chorus.Message;

public class MSsqlMessageDAO implements MessageDAO {

	private static Connection con;
	
	public MSsqlMessageDAO ()
	{
		
	}
	
	
	@Override
	public int insertMessage() {
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	public boolean deleteMessage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Message findMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateMessage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RowSet selectMessageRS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<?> selectMessagesTO() {
		// TODO Auto-generated method stub
		return null;
	}

}
