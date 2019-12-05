package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.sunrays.proj4.bean.CollegeBean;

import in.co.sunrays.proj4.util.JDBCDataSource;

public class CollegeModel {

	public long add(CollegeBean bean) {

		Connection conn = null;
		int pk = 0;
		System.out.println("in add method" + bean.getName());
		CollegeBean duplicatebean = findByName(bean.getName());
		if (duplicatebean != null) {

			System.out.println("College name already exists");
		}
		pk = nextPk();
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer("insert into st_collegee values(?,?,?,?,?,?,?,?,?,?)");
			PreparedStatement psmt = conn.prepareStatement(sql.toString());
			psmt.setInt(1, pk);
			psmt.setString(2, bean.getName());
			psmt.setString(3, bean.getAddress());
			psmt.setString(4, bean.getState());
			psmt.setString(5, bean.getCity());
			psmt.setString(6, bean.getPhoneNo());
			psmt.setString(7, bean.getCreatedBy());
			psmt.setString(8, bean.getModifiedBy());
			psmt.setTimestamp(9, bean.getCreatedDatetime());
			psmt.setTimestamp(10, bean.getModifiedDatetime());
			int i = psmt.executeUpdate();
			System.out.println(i + "no of records effected inserted");
			conn.commit();
			psmt.close();
		} catch (Exception e) {
			// e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				// TODO: handle exception

				e2.printStackTrace();
			}
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		return pk;

	}

	public Integer nextPk() {
		int pk = 0;

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement psmt = conn.prepareStatement("SELECT MAX(ID)from ST_COLLEGEE");
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		return pk + 1;
	}

	public CollegeBean findByName(String name) {

		Connection conn = null;
		CollegeBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();

			StringBuffer sql = new StringBuffer("select * from st_collegee where name=?");
			PreparedStatement psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, name);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);

		}

		return bean;
	}

	public CollegeBean findByPk(long pk) {

		CollegeBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			StringBuffer sql = new StringBuffer("Select * from st_collegee where id=?");
			PreparedStatement psmt = conn.prepareStatement(sql.toString());
			psmt.setLong(1, pk);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);

		}

		return bean;
	}

	public void delete(CollegeBean bean) {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer("DELETE from st_collegee where id=?	");
			PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());
			preparedStatement.setLong(1, bean.getId());
			int i = preparedStatement.executeUpdate();
			System.out.println(i + "number of records deleted ");
			conn.commit();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		} finally {
			JDBCDataSource.closeConnection(conn);

		}

	}

	public void update(CollegeBean bean) {
		Connection conn = null;
		CollegeBean beanExists = findByName(bean.getName());
		// System.out.println(beanExists.getName());
		if (beanExists != null && beanExists.getId() != bean.getId()) {
			System.out.println("college already exists");
		}
		//

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer(
					"update st_collegee set name=?,address=?,state=?,city=?,phone_no=?,created_by=?,modified_by=?,created_datetime=?,modified_datetime=? where id=?");
			PreparedStatement psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, bean.getName());
			psmt.setString(2, bean.getAddress());
			psmt.setString(3, bean.getState());
			psmt.setString(4, bean.getCity());
			psmt.setString(5, bean.getPhoneNo());
			psmt.setString(6, bean.getCreatedBy());
			psmt.setString(7, bean.getModifiedBy());
			psmt.setTimestamp(8, bean.getCreatedDatetime());
			psmt.setTimestamp(9, bean.getModifiedDatetime());
			psmt.setLong(10, bean.getId());
			int i = psmt.executeUpdate();
			System.out.println(i + "number of records updated");
			conn.commit();
			psmt.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public List search(CollegeBean bean, int pageNo, int pageSize) {

		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COLLEGEE WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}
			if (bean.getAddress() != null && bean.getAddress().length() > 0) {
				sql.append(" AND ADDRESS like '" + bean.getAddress() + "%'");
			}
			if (bean.getState() != null && bean.getState().length() > 0) {
				sql.append(" AND STATE like '" + bean.getState() + "%'");
			}
			if (bean.getCity() != null && bean.getCity().length() > 0) {
				sql.append(" AND CITY like '" + bean.getCity() + "%'");
			}
			if (bean.getPhoneNo() != null && bean.getPhoneNo().length() > 0) {
				sql.append(" AND PHONE_NO = " + bean.getPhoneNo());
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}

}
