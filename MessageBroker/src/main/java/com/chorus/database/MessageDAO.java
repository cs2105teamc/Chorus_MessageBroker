package com.chorus.database;

import java.util.Collection;
import javax.sql.RowSet;
import com.chorus.*;


public interface MessageDAO {

	public int insertMessage();

	public boolean deleteMessage();

	public Message findMessage();

	public boolean updateMessage();

	public RowSet selectMessageRS();

	public Collection<?> selectMessagesTO();
}
