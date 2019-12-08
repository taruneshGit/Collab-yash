package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import com.mysql.jdbc.Statement;

//import javax.naming.spi.DirStateFactory.Result;

import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;
import sun.net.www.ApplicationLaunchException;

public class RoleModel {

	public long add(RoleBean bean) throws DuplicateRecordException, ApplicationException {
		Connection conn = null;
		int pk = 0;
		System.out.println("role name in add method :- " + bean.getName());
		RoleBean duplicateRole = findByName(bean.getName());
		// System.out.println("role name in add method :- " + duplicateRole.getName());

		if (duplicateRole != null) {
			// System.out.println("role already exists");
			throw new DuplicateRecordException("Role already exists");

		}
		try {
			conn = JDBCDataSource.getConnection();

			pk = nextPK();
			System.out.println(pk + "in role model jdbc");
			conn.setAutoCommit(false);
			PreparedStatement psmt = conn.prepareStatement("insert into st_role value(?,?,?,?,?,?,?)");
			psmt.setInt(1, pk);
			psmt.setString(2, bean.getName());
			psmt.setString(3, bean.getDescription());
			psmt.setString(4, bean.getCreatedBy());
			psmt.setString(5, bean.getModifiedBy());
			psmt.setTimestamp(6, bean.getCreatedDatetime());
			psmt.setTimestamp(7, bean.getModifiedDatetime());
			psmt.executeUpdate();
			conn.commit();
			psmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				// ex.printStackTrace();
				throw new ApplicationException("Exception :add rollback exception" + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Role");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public Integer nextPK() throws DatabaseException{

		Connection conn = null;
		int pk = 0;
		try {
			StringBuffer sql = new StringBuffer("select max(id)from st_role");
			conn = JDBCDataSource.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql.toString());
			System.out.println("query:- " + sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);

			}
			rs.close();
		} catch (Exception e) {
          
            throw new DatabaseException("Exception : Exception in getting PK");
        } finally {
            JDBCDataSource.closeConnection(conn);
        } 
		return pk + 1;
	}

	public RoleBean findByName(String name) throws ApplicationException {
		Connection conn = null;
		// name = "Student";
		RoleBean bean = null;

		// System.out.println("findbyname method role name " + bean.getName());

		StringBuffer sql = new StringBuffer("select * from st_role where name=?");
		try {
			System.out.println("-------------------");
			conn = JDBCDataSource.getConnection();
			System.out.println("-------------------");
			PreparedStatement psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, name);
			System.out.println("-------------------");
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {

				bean = new RoleBean();
				System.out.println(rs.getLong(1));
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedBy(rs.getString(5));
				bean.setModifiedDatetime(rs.getTimestamp(7));

				/*
				 * System.out.println(bean.getName()); System.out.println(rs.getString(2));
				 * System.out.println(rs.getString(3));
				 */
			}

			rs.close();
		}catch (Exception e) {
                 throw new ApplicationException(
                    "Exception : Exception in getting User by emailId");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
     
        
		System.out.println(bean);
		return bean;
	}

	public RoleBean findByPK(long pk) throws ApplicationException {
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_ROLE WHERE ID=?");
		RoleBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
			}
			rs.close();
		}catch (Exception e) {
            
            throw new ApplicationException(
                    "Exception : Exception in getting User by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
      
     
		
		System.out.println("find by pk-----------------");
		return bean;
	}

	public void delete(RoleBean bean) throws ApplicationException {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer("delete from st_role where id=?");
			PreparedStatement psmt = conn.prepareStatement(sql.toString());
			System.out.println("============" + bean.getId());

			psmt.setLong(1, bean.getId());
			int i = psmt.executeUpdate();
			System.out.println(i + "record deleted");
			// System.out.println("============deleted record is " + bean.getId());

			conn.commit();

			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
				// e2.printStackTrace();
				throw new ApplicationException("Exception : Delete rollback exception " + e2.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(RoleBean bean) throws DuplicateRecordException, ApplicationException {
		// TODO Auto-generated method stub

		Connection conn = null;
		RoleBean duplicatebean = findByName(bean.getName());
		if (duplicatebean != null && duplicatebean.getId() != bean.getId()) {
			// System.out.println("Role already exists");
			throw new DuplicateRecordException("Role already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement psmt = conn.prepareStatement(
					"UPDATE ST_ROLE SET NAME=?,DESCRIPTION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			psmt.setString(1, bean.getName());
			psmt.setString(2, bean.getDescription());
			psmt.setString(3, bean.getCreatedBy());
			psmt.setString(4, bean.getModifiedBy());
			psmt.setTimestamp(5, bean.getCreatedDatetime());
			psmt.setTimestamp(6, bean.getModifiedDatetime());
			psmt.setLong(7, bean.getId());
			int i = psmt.executeUpdate();
			System.out.println(i + "no of records update ");
			conn.commit();
			conn.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				// TODO: handle exception
				throw new ApplicationException("Exception : Delete rollback exception " + e.getMessage());
			}
			throw new ApplicationException("Exception in updating Role ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	/*
	 * public List search(RoleBean bean, int pageNo, int pageSize) {
	 * 
	 * StringBuffer sql = new StringBuffer("select * from st_role where 1=1"); if
	 * (bean != null) {
	 * 
	 * if (bean.getId() > 0) { sql.append("AND id= " + bean.getId()); } if
	 * (bean.getName() != null && bean.getName().length() > 0) {
	 * 
	 * sql.append(" AND NAME LIKE '" + bean.getName() + "%' ");
	 * System.out.println(sql); } if (bean.getDescription() != null &&
	 * bean.getDescription().length() > 0) { sql.append(" AND DESCRIPTION LIKE '" +
	 * bean.getDescription() + "%'"); System.out.println(sql); } } if (pageSize > 0)
	 * {
	 * 
	 * pageNo = (pageNo - 1) * pageSize; sql.append("LIMIT" + pageNo + "," +
	 * pageSize); } ArrayList list = new ArrayList(); Connection conn = null; try {
	 * conn = JDBCDataSource.getConnection(); PreparedStatement psmt =
	 * conn.prepareStatement(sql.toString()); ResultSet rs = psmt.executeQuery();
	 * while (rs.next()) { bean = new RoleBean(); bean.setId(rs.getLong(1));
	 * bean.setName(rs.getString(2)); bean.setDescription(rs.getString(3));
	 * bean.setCreatedBy(rs.getString(4)); bean.setModifiedBy(rs.getString(5));
	 * bean.setCreatedDatetime(rs.getTimestamp(6));
	 * bean.setModifiedDatetime(rs.getTimestamp(7)); list.add(bean); rs.close(); } }
	 * catch (Exception e) { // TODO Auto-generated catch block e.printStackTrace();
	 * } finally { JDBCDataSource.closeConnection(conn); } return list; }
	 */

	/**
	 * @param bean
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List search(RoleBean bean, int pageNo, int pageSize) throws ApplicationException {

		StringBuffer sql = new StringBuffer("SELECT * FROM ST_ROLE WHERE 1=1");
		System.out.println("bena id in search method  " + bean.getId());
		if (bean != null) {
			if (bean.getId() > 0) {
				System.out.println("------------------");
				sql.append(" AND id = " + bean.getId());
				System.out.println("sql :- " + sql);
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%' ");
				System.out.println("sql :- " + sql);
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION like '" + bean.getDescription() + "%' ");
				System.out.println("sql :- " + sql);

			}
		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " +pageNo + "," + pageSize);
		}

		ArrayList<RoleBean> list = new ArrayList<RoleBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				bean = new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
                      throw new ApplicationException(
                    "Exception : Exception in search Role");
        } finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("Select * from st_role");
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			// sql.append(" Limit " + pageNo + "," + pageSize);

			sql.append(" Limit " + pageNo + ", " + pageSize);
			System.out.println("sql:--" + sql);

		}
		   Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				RoleBean bean = new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
				list.add(bean);

			}
			rs.close();

		}	catch (Exception e) {
	         
	            throw new ApplicationException(
	                    "Exception : Exception in getting list of Role");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }

	        
	        return list;

	    }
	

	public List list() throws ApplicationException  {

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("Select * from st_role");

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				RoleBean bean = new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				 bean.setModifiedDatetime(rs.getTimestamp(7));

				list.add(bean);

			}
			rs.close();

      		} catch(Exception e) {
			throw new ApplicationException(
                    "Exception : Exception in getting list of Role");		
			} finally {
			JDBCDataSource.closeConnection(conn);

		}
		return list;
	}
}



/*
 * public RoleBean findByName1(String name) throws Exception { //
 * log.debug("Model findBy EmailId Started");
 * System.out.println("role name in add method :- " + name); StringBuffer sql =
 * new StringBuffer("SELECT * FROM ST_ROLE WHERE NAME=?"); RoleBean bean = null;
 * Connection conn = null; Class.forName("com.mysql.jdbc.Driver"); try { conn =
 * DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_ors", "root",
 * "root"); PreparedStatement pstmt = conn.prepareStatement(sql.toString());
 * System.out.println("--------------"); pstmt.setString(1, name); ResultSet rs
 * = pstmt.executeQuery(); while (rs.next()) { bean = new RoleBean();
 * bean.setId(rs.getLong(1)); bean.setName(rs.getString(2));
 * bean.setDescription(rs.getString(3)); bean.setCreatedBy(rs.getString(4));
 * bean.setModifiedBy(rs.getString(5));
 * bean.setCreatedDatetime(rs.getTimestamp(6));
 * bean.setModifiedDatetime(rs.getTimestamp(7));
 * System.out.println(bean.getName());
 * System.out.println(bean.getDescription()); }
 * 
 * rs.close(); } catch (Exception e) { // log.error("Database Exception..", e);
 * // throw new ApplicationException( //
 * System.out.println("records not found");
 * 
 * } finally { // JDBCDataSource.closeConnection(conn); conn.close(); } // //
 * log.debug("Model findBy EmailId End"); return bean; } }
 */
