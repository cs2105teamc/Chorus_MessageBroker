package com.chorus.database;

import com.chorus.*;
import com.chorus.enums.*;

/**
 * 
 * @author garfijam
 *
 */
public abstract class DAOFactory {
	/**
	* List of DAO types supported by the factory
	* public static final int MSSQLSERVER = 1;
	*/
	public static DAOtype type;

	public static Config config;

	// Declare the JDBC objects.
	public static JDBCConnectionPool pool;

	/*
	 * ==================================================== 
	 * Accessors
	 * ====================================================
	 */
	/**
	 * <h2>setConfig</h2>
	 * 
	 * Takes a config object.  If valid sets up a static connection pool for later use
	 *  
	 * @param mbConfig Preloaded config
	 * @return
	 */
	public boolean setConfig(Config mbConfig) {
		config = mbConfig;
		if (config.Validate()) {			
			setPool(new JDBCConnectionPool(config.getJDBCDriver(), config.getDbConnection(), config.getUsername(),
					config.getPassword()));
			return true;
		}
		
		return false;
	}

	public static Config getConfig() {
		return config;
	}
	
	/**
	 * <h2>setPool</h2>
	 * 
	 * Sets a static variable of connection pool.  All connections are static and thread safe.
	 * 
	 * @param JDBCpool
	 */
	public static void setPool(JDBCConnectionPool JDBCpool) {
		if (pool == null) {
			pool = JDBCpool;
		}
	}

	/*
	 * ==================================================== 
	 * Static Methods
	 * ====================================================
	 */

	/** 
	 * <h2>getDAOFactory</h2>
	 * @param type
	 * @return
	 */
	public static DAOFactory getDAOFactory(DAOtype daoType) {

		switch (daoType) {
		case MSSQLSERVER:
			return new MSSqlserverdbDAOFactory();
		default:
			return null;
		}
	}
	
	/** 
	 * <h2>getDAOFactory</h2>
	 * @param type
	 * @param config
	 * @return
	 */
	public static DAOFactory getDAOFactory(DAOtype daoType, Config config) {

		switch (daoType) {
		case MSSQLSERVER:
			return new MSSqlserverdbDAOFactory(config);
		default:
			return null;
		}
	}	


	/*
	 * ==================================================== 
	 * Abstract Methods
	 * ====================================================
	 */

	/**
	 * <h2>getMessageDAO</h2>
	 * @return MessageDAO object
	 */
	public abstract MessageDAO getMessageDAO();
}
