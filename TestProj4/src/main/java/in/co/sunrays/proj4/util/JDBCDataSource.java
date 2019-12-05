package in.co.sunrays.proj4.util;

import java.sql.Connection;
import java.sql.SQLException;
//import java.beans.PropertyVetoException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class JDBCDataSource {

	private static JDBCDataSource datasource;

	private JDBCDataSource() {

	}

	private ComboPooledDataSource cpds = null;

	public static JDBCDataSource getInstance() {
		if (datasource == null) {

			ResourceBundle rb = ResourceBundle.getBundle("in.co.sunrays.proj4.bundle.system");
			datasource = new JDBCDataSource();
			datasource.cpds = new ComboPooledDataSource();
			try {
				datasource.cpds.setDriverClass(rb.getString("driver"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			datasource.cpds.setJdbcUrl(rb.getString("url"));
			datasource.cpds.setUser(rb.getString("username"));
			datasource.cpds.setPassword(rb.getString("password"));
			datasource.cpds.setInitialPoolSize(new Integer(rb.getString("initalPoolSize")));
			datasource.cpds.setAcquireIncrement(new Integer(rb.getString("acqiureIncrement")));
			datasource.cpds.setMaxPoolSize(new Integer(rb.getString("maxPoolSize")));
			datasource.cpds.setMaxIdleTime(DataUtility.getInt(rb.getString("timeout")));
			datasource.cpds.setMinPoolSize(new Integer((String) rb.getString("minPoolSize")));
	
		}
		return datasource;
	}

	public static Connection getConnection() throws Exception {

		return getInstance().cpds.getConnection();
	}

	public static void closeConnection(Connection connection) {

		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

		}
	}

	public static void trnRollback(Connection connection) {

		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// throw new ApplicationException(ex.toString());
				e.printStackTrace();
			}
		}
	}
}
