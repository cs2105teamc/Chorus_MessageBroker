package com.chorus.database;
import java.sql.Connection;
import com.chorus.Config;

public class MSSqlserverdbDAOFactory extends DAOFactory {

	/* ====================================================
	 * Constructors
	 * ====================================================
	 */
	public MSSqlserverdbDAOFactory()
	{}
	
	public MSSqlserverdbDAOFactory(Config mbConfig)
	{
		setConfig(mbConfig);
	}
	
	/* ====================================================
	 * Methods
	 * ====================================================
	 */
	
	public static Connection createConnection(Config mbConfig) {
		if(mbConfig.Validate()){
		DAOFactory.setPool(new JDBCConnectionPool(
				mbConfig.getJDBCDriver(), mbConfig.getDbConnection(),
				mbConfig.getUsername(), mbConfig.getPassword()));
		}
		return pool.checkOut();		
	}

	public static Connection createConnection() {
		if(config.Validate()){
		DAOFactory.setPool(new JDBCConnectionPool(
				config.getJDBCDriver(), config.getDbConnection(),
				config.getUsername(), config.getPassword()));
		}
		return pool.checkOut();		
	}
	
	@Override
	public MessageDAO getMessageDAO() {
		return new MSsqlMessageDAO();
	}

}
