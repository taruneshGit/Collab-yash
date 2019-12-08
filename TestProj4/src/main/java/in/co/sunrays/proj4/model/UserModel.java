package in.co.sunrays.proj4.model;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;

public class UserModel {

	/*
	 * public long add(UserBean bean) { Connection conn = null; int pk = 0;
	 * System.out.println("before finbByLogin method");
	 * 
	 * UserBean existBean = findByLogin(bean.getLogin()); if (existBean != null) {
	 * System.out.println("User already exists");
	 * 
	 * } System.out.println("after finbByLogin method");
	 * 
	 * try {conn = JDBCDataSource.getConnection(); pk = nextPk(); // Get
	 * auto-generated next primary key System.out.println(pk + " in ModelJDBC");
	 * conn.setAutoCommit(false); // Begin transaction
	 * System.out.println("in add method after pk=" + pk); StringBuffer sql = new
	 * StringBuffer("INSERT INTO ST_USER VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
	 * ); PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	 * pstmt.setInt(1, pk); pstmt.setString(2, bean.getFirstName());
	 * pstmt.setString(3, bean.getLastName()); pstmt.setString(4, bean.getLogin());
	 * pstmt.setString(5, bean.getPassword()); pstmt.setDate(6, new
	 * java.sql.Date(bean.getDob().getTime())); pstmt.setString(7,
	 * bean.getMobileNo()); pstmt.setLong(8, bean.getRoleId()); pstmt.setInt(9,
	 * bean.getUnSuccessfulLogin()); pstmt.setString(10, bean.getGender());
	 * pstmt.setTimestamp(11, bean.getLastLogin()); pstmt.setString(12,
	 * bean.getLock()); pstmt.setString(13, bean.getRegisteredIP());
	 * pstmt.setString(14, bean.getLastLoginIP()); pstmt.setString(15,
	 * bean.getCreatedBy()); pstmt.setString(16, bean.getModifiedBy());
	 * pstmt.setTimestamp(17, bean.getCreatedDatetime()); pstmt.setTimestamp(18,
	 * bean.getModifiedDatetime());
	 * 
	 * // pstmt.executeUpdate()
	 * 
	 * psmt.setInt(1, pk); psmt.setString(2, bean.getFirstName()); psmt.setString(3,
	 * bean.getLastName()); psmt.setString(4, bean.getLogin()); psmt.setString(5,
	 * bean.getPassword()); psmt.setDate(6, new Date(bean.getDob().getTime()));
	 * psmt.setString(7, bean.getMobileNo()); psmt.setLong(8, bean.getRoleId());
	 * psmt.setInt(9, bean.getUnSuccessfulLogin()); psmt.setString(10,
	 * bean.getGender()); psmt.setTimestamp(11, bean.getLastLogin());
	 * psmt.setString(12, bean.getLock()); psmt.setString(13,
	 * bean.getLastLoginIP()); psmt.setString(14, bean.getRegisterdIP());
	 * psmt.setString(15, bean.getCreatedBy()); psmt.setString(16,
	 * bean.getModifiedBy()); psmt.setTimestamp(17, bean.getCreatedDatetime());
	 * psmt.setTimestamp(18, bean.getModifiedDatetime());
	 * 
	 * int i = pstmt.executeUpdate(); System.out.println(i + "record update");
	 * conn.commit(); // End transaction pstmt.close(); } catch (Exception e) {
	 * e.printStackTrace(); try { conn.rollback(); } catch (Exception ex) {
	 * ex.printStackTrace(); } } finally { JDBCDataSource.closeConnection(conn); }
	 * return pk;
	 * 
	 * }
	 * 
	 */
	public long add(UserBean bean) throws ApplicationException,DuplicateRecordException {
		// log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		UserBean existbean = findByLogin(bean.getLogin());

		if (existbean != null) {
			 throw new DuplicateRecordException("Login Id already exists");
			//System.out.println("LOGIN ALREADY EXISTS");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ST_USER VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			System.out.println("============");
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setLong(8, bean.getRoleId());
			pstmt.setInt(9, bean.getUnSuccessfulLogin());
			pstmt.setString(10, bean.getGender());
			pstmt.setTimestamp(11, bean.getLastLogin());
			pstmt.setString(12, bean.getLock());
			pstmt.setString(13, bean.getRegisteredIP());
			pstmt.setString(14, bean.getLastLoginIP());
			pstmt.setString(15, bean.getCreatedBy());
			pstmt.setString(16, bean.getModifiedBy());
			pstmt.setTimestamp(17, bean.getCreatedDatetime());
			pstmt.setTimestamp(18, bean.getModifiedDatetime());

			System.out.println("============");
			pstmt.executeUpdate();

			System.out.println("============");
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
           
            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException(
                        "Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException("Exception : Exception in add User");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
 
        return pk;
    }

    
	public Integer nextPk() throws DatabaseException{
		Connection conn = null;
		int pk = 0;
		try {
			StringBuffer sql = new StringBuffer("Select max(id)from st_user");
			conn = JDBCDataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);

			}
			rs.close();
			System.out.println("pk sql:-" + sql);
			System.out.println("pk= " + pk);
		}catch (Exception e) {
           
            throw new DatabaseException("Exception : Exception in getting PK");
        } finally {
			JDBCDataSource.closeConnection(conn);

			System.out.println();
		}

		// TODO Auto-generated method stub
		return pk + 1;
	}

	public UserBean findByLogin(String login) throws ApplicationException {

		UserBean bean = null;
		Connection conn = null;
		StringBuffer sql = new StringBuffer("Select * from st_user where login=?");
		System.out.println(login);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());
			preparedStatement.setString(1, login);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setUnSuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIP(rs.getString(13));
				bean.setLastLoginIP(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));

				// bean.setConfirmPassword(rs.getString(6));
				System.out.println(bean.getLogin());
			}
			rs.close();

		}catch (Exception e) {
            e.printStackTrace();
           
            throw new ApplicationException(
                    "Exception : Exception in getting User by login");
        } finally {
            JDBCDataSource.closeConnection(conn);

		}
		return bean;
	}

	public UserBean findByPk(long pk) throws ApplicationException {

		Connection conn = null;
		UserBean bean = null;
		StringBuffer sql = new StringBuffer("select * from st_user where id=?");
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
			
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setUnSuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIP(rs.getString(13));
				bean.setLastLoginIP(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
			}
			
		
			rs.close();

		} catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(
                    "Exception : Exception in getting User by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return bean;
    
	}

	public void delete(UserBean bean) throws ApplicationException {
		Connection conn = null;
		StringBuffer sql = new StringBuffer("delete from st_user where id=?");
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement psmt = conn.prepareStatement(sql.toString());
			psmt.setLong(1, bean.getId());
			long l = psmt.executeUpdate();
			System.out.println(l + "  no of records deleted ");
			conn.commit();
			psmt.close();

	    	}  catch (Exception e) {
           
              try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : Delete rollback exception "
                                + ex.getMessage());
            }
            throw new ApplicationException(
                    "Exception : Exception in delete User");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
       
}
}
