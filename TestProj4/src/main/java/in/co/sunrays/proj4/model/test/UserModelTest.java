package in.co.sunrays.proj4.model.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.catalina.User;

import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
<<<<<<< HEAD
import in.co.sunrays.proj4.exception.DuplicateRecordException;
=======
>>>>>>> branch 'master' of https://github.com/taruneshGit/Collab-yash.git
import in.co.sunrays.proj4.model.UserModel;

public class UserModelTest {
	public static UserModel model = new UserModel();

	public static void main(String[] args)
			throws ParseException, DuplicateRecordException, ClassNotFoundException, SQLException {

		// testAdd();
<<<<<<< HEAD
		// testDelete();
		 testUpdate();
		//testUpdate1();

	}
/*
	private static void testUpdate1() throws ClassNotFoundException, SQLException {
		UserBean bean = null;
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_ors", "root", "root");
		// int id = 50;// String fname="sachin";String lname="tendulkar";int
		// salary=5500;
int id=65;
bean.setFirstName("AAA");
bean.setLastName("bbbb");


		StringBuffer sql = new StringBuffer(
				"UPDATE ST_USER SET FIRST_NAME=?,LAST_NAME=? WHERE ID=?");

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, bean.getFirstName());
		pstmt.setString(2, bean.getLastName());
	//	pstmt.setString(3, bean.getLogin());
	//	pstmt.setString(4, bean.getPassword());
	//	pstmt.setDate(5, "1997-10-02 00:00:00");
		// pstmt.setDate(5, new Date(bean.getDob().getTime()));

		pstmt.setString(5, bean.getMobileNo());
		pstmt.setLong(6, bean.getRoleId());
		pstmt.setInt(7, bean.getUnSuccessfulLogin());
		pstmt.setString(8, bean.getGender());
		pstmt.setTimestamp(9, bean.getLastLogin());
		pstmt.setString(10, bean.getLock());
		pstmt.setString(11, bean.getRegisteredIP());
		pstmt.setString(12, bean.getLastLoginIP());
		pstmt.setString(13, bean.getCreatedBy());
		pstmt.setString(14, bean.getModifiedBy());
		pstmt.setTimestamp(15, bean.getCreatedDatetime());
		pstmt.setTimestamp(16, bean.getModifiedDatetime());
		pstmt.setLong(3, bean.getId());
		int i = pstmt.executeUpdate();
		System.out.println(i + "no of records update");

		pstmt.close();
		conn.close();
	}
*/
	public static void testUpdate() throws DuplicateRecordException {

		try {
			long pk = 65L;
			UserBean bean = model.findByPk(pk);
			System.out.println(bean.getId());
			System.out.println(bean.getLogin());
			// System.out.println("pk= " +bean.getId());
			bean.setFirstName("mahesh");
			bean.setLastName("sharma");

			bean.setPassword("pass1234");
			bean.setLogin("maheshsharma@gmail.com");

			model.update(bean);
			System.out.println();
			UserBean updatedBean = model.findByPk(pk);
			if (!("maheshsharma@gmail.com".equals(updatedBean.getLogin()))) {
				System.out.println("test update fail");
			}

		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
=======
		testDelete();
>>>>>>> branch 'master' of https://github.com/taruneshGit/Collab-yash.git

	}

	private static void testDelete() {
		try {
			UserBean bean = new UserBean();
			long pk = 66L;
			bean.setId(pk);
			model.delete(bean);
			System.out.println("test deleted success " + bean.getId());
			UserBean deletedbean = model.findByPk(pk);
			if (deletedbean == null) {
				System.out.println("test delete fail");
				// throw new DuplicateRecordException("Login Id already exists");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	private static void testAdd() throws Exception {

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-DD-yyyy");
			UserBean bean = new UserBean();
			bean.setFirstName("Ajay");
			bean.setLastName("jain");
			bean.setLogin("ajayjain@gmail.com");
			bean.setId(65L);
			bean.setDob(simpleDateFormat.parse("03-12-2019"));
			bean.setPassword("pass@1234");
			bean.setMobileNo("9926126121");
			bean.setRoleId(2L);
			bean.setUnSuccessfulLogin(2);
			bean.setGender("male");
			bean.setLastLogin(new Timestamp(new Date().getTime()));
			bean.setLock("yes");

			// bean.setConfirmPassword("pass@1234");
			long pk = model.add(bean);
			UserBean addedbean = model.findByPk(pk);
			System.out.println(addedbean);
			System.out.println("test add successfull");
			if (addedbean == null) {
				System.out.println("Test add fail");

			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

}
