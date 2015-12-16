package com.chorus;

/**
 * 
 */
import java.util.Properties;

import com.chorus.utils.StringChorus;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;


/**
 * <h1> Chorus Message Broker Configuration </h1>
 * This stores all configuration information relating to the 
 * MessageBroker
 * <p>
 * 
 * @author garfijam
 * @version 1.0
 * @since 2015-12-07
 *
 */

public class MessageBrokerConfig implements Config {

	private Properties prop;
	private String configFile = "";
	private String dbConnection = "";
	private String JDBCDriver = "";
	private String username = "";
	private String password = "";
	private long pollingInterval = 0;
	
	/* (non-Javadoc)
	 * @see com.chorus.Config#setProp(java.util.Properties)
	 */
	@Override
	public void setProp(Properties prop) {
		this.prop = prop;
	}
	
	/* (non-Javadoc)
	 * @see com.chorus.Config#getDbConnection()
	 */
	@Override
	public String getDbConnection() {
		return dbConnection;
	}

	private void setDbConnection(String dbConnection) {
		this.dbConnection = dbConnection;
	}

	/* (non-Javadoc)
	 * @see com.chorus.Config#getPollingInterval()
	 */
	@Override
	public long getPollingInterval() {
		return pollingInterval;
	}
	
	/* (non-Javadoc)
	 * @see com.chorus.Config#setPollingInterval(long)
	 */
	@Override
	public void setPollingInterval(long interval) {
		pollingInterval = interval;
	}

	/* (non-Javadoc)
	 * @see com.chorus.Config#getConfigFile()
	 */
	@Override
	public String getConfigFile() {
		return configFile;
	}

	/* (non-Javadoc)
	 * @see com.chorus.Config#setConfigFile(java.lang.String)
	 */
	@Override
	public void setConfigFile(String configFile) {
		this.configFile = configFile;		
	}

	/* (non-Javadoc)
	 * @see com.chorus.Config#getJDBCDriver()
	 */
	@Override
	public String getJDBCDriver() {
		return JDBCDriver;
	}

	/* (non-Javadoc)
	 * @see com.chorus.Config#setJDBCDriver(java.lang.String)
	 */
	@Override
	public void setJDBCDriver(String dbDriver) {
		this.JDBCDriver = dbDriver;
	}

	/* (non-Javadoc)
	 * @see com.chorus.Config#getUsername()
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/* (non-Javadoc)
	 * @see com.chorus.Config#setUsername(java.lang.String)
	 */
	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	/* (non-Javadoc)
	 * @see com.chorus.Config#getPassword()
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/* (non-Javadoc)
	 * @see com.chorus.Config#setPassword(java.lang.String)
	 */
	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	/** ====================================================
	 ** <h2>Constructors</h2>
	 ** ====================================================
	 */
	public MessageBrokerConfig(String fileName)
	{
		this.setConfigFile(fileName);
		this.Load();
	}
	
	
	public MessageBrokerConfig(Properties prop) {
		this.prop = prop;
		this.Load();
	}

	/** ====================================================
	 ** <h2>Methods</h2>
	 ** ====================================================
	 */
	/* (non-Javadoc)
	 * @see com.chorus.Config#Load()
	 */
	@Override
	public boolean Load()
	{		
		boolean loaded = false;
		
		if(this.LoadProperties()) {
			this.setDbConnection(prop.getProperty("ConnectionString"));
			this.setJDBCDriver(prop.getProperty("JDBCDriver"));
			this.setPollingInterval(Long.parseLong(prop.getProperty("PollingInterval")));
			this.setUsername(prop.getProperty("UserName"));
			this.setPassword(prop.getProperty("Password"));
			
			loaded = this.Validate();
		}
		return loaded;
	}
	
	/**
	 * <h2>LoadProperties</h2>
	 * Loads the properties from a file if it exists.
	 * 
	 * @return boolean success or not
	 */
	private boolean LoadProperties() {
		InputStream input = null;
		boolean loaded = false;
		try {
			if(prop == null){
				prop = new Properties();
				input = new FileInputStream(this.getConfigFile());
				prop.load(input);
				}
						
			loaded = true;
		}
		catch (IOException ex){ ex.printStackTrace();}
		finally {
					if (input != null)
					{
						try {input.close();} 
						catch (IOException e) {e.printStackTrace();}
					}
				}		
		
		return loaded;
	}
	
	/* (non-Javadoc)
	 * @see com.chorus.Config#Validate()
	 */
	@Override
	public boolean Validate()
	{
		if(prop != null
				&& !StringChorus.IsNullorEmpty(this.getDbConnection())
				&& !StringChorus.IsNullorEmpty(this.getJDBCDriver())
				&& !StringChorus.IsNullorEmpty(this.getUsername())
				&& !StringChorus.IsNullorEmpty(this.getPassword())
						) 
			{return true;}
		
		return false;
	}


}
