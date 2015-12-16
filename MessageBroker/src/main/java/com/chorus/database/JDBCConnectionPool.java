/**
 * 
 */
package com.chorus.database;

import java.sql.*;
/**
 * @author garfijam
 * @since 7 Dec 2015
 * @version 1.0
 *
 */
public class JDBCConnectionPool extends ObjectPool<Connection> {

	  private String dsn, usr, pwd;

	  /**
	   * @param driver
	   * @param dsn
	   * @param usr
	   * @param pwd
	   */
	  public JDBCConnectionPool(String driver, String dsn, String usr, String pwd) {
	    super();
	    if(!dsn.contains("jdbc:sqlserver://")){
	    	dsn = "jdbc:sqlserver://" + dsn;
	    }
	    	
	    
	    try {
	      Class.forName(driver).newInstance();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    this.dsn = dsn;
	    this.usr = usr;
	    this.pwd = pwd;
	  }

	  @Override
	  protected Connection create() {
	    try {	    
	    	//System.out.println(this.dsn.equals("jdbc:sqlserver://localhost\\SQL2008R2"));
	    	//System.out.println(this.dsn);
	    	//System.out.println("jdbc:sqlserver://localhost\\SQL2008R2");
	    	//Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQL2008R2", this.usr, this.pwd);	    	
	    	Connection conn = DriverManager.getConnection(this.dsn, this.usr, this.pwd);
	    	return conn;
	    } catch (SQLException e) {
	      e.printStackTrace();
	      return (null);
	    }
	  }

	  @Override
	  public void expire(Connection o) {
	    try {
	      ((Connection) o).close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }

	  @Override
	  public boolean validate(Connection o) {
	    try {
	      return (!((Connection) o).isClosed());
	    } catch (SQLException e) {
	      e.printStackTrace();
	      return (false);
	    }
	  }
	}
