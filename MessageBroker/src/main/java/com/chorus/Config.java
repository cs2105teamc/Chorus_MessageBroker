/**
 * 
 */
package com.chorus;

import java.util.Properties;

/**
 * @author garfijam
 * @since 9 Dec 2015
 * @version 1.0
 *
 */
public interface Config {

	/** ====================================================
	 ** <h2>Accessors</h2>
	 ** ====================================================
	 **/
	void setProp(Properties prop);

	String getDbConnection();

	long getPollingInterval();

	void setPollingInterval(long interval);

	String getConfigFile();

	void setConfigFile(String configFile);

	String getJDBCDriver();

	void setJDBCDriver(String dbDriver);

	String getUsername();

	void setUsername(String username);

	String getPassword();

	void setPassword(String password);

	/** ====================================================
	 ** <h2>Methods</h2>
	 ** ====================================================
	 */
	/**
	 * <h2>Load</h2>
	 * 
	 * Loads properties from properties file or if properties 
	 * object exists it will load directly
	 * 
	 * @return boolean Success or not
	 */
	boolean Load();

	/**
	 * <h2>Validate</h2>
	 * 
	 * Checks all properties are correctly set
	 * 
	 * @return boolean set or not.
	 */
	boolean Validate();

}