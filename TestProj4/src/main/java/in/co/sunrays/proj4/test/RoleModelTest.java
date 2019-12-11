package in.co.sunrays.proj4.model.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.RoleModel;

public class RoleModelTest {

	public static RoleModel model = new RoleModel();

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		// RoleModel model1=new RoleModel();

		// testAdd();
		// testDelete();
		// test();
		// model.findByName1("student");
		// RoleBean addedbean = model.findByPK(1);
		testFindbyName();
		// testUpdate();
		testFindByPk();
		testSearch();
		// testList();

		// testList1();

	}

	public static void testList1() {
		try {
			RoleBean bean = new RoleBean();
			List list = new ArrayList();
			list = model.list();
			if (list.size() < 0) {
				System.out.println("Test List Fails");
			}
			Iterator it = list.iterator();
			System.out.println(
					"ID\tNAME\tDescription\tCreated_by\tModified_by\t    Created_Datetime\t        Modified_Datetime\n--------------------------------------------------------------------------------------------------------------------		");

			while (it.hasNext()) {
				bean = (RoleBean) it.next();
				System.out.print(bean.getId());
				System.out.print("\t" + bean.getName());
				System.out.print("\t" + bean.getDescription());
				System.out.print("\t" + bean.getCreatedBy());
				System.out.print("\t" + bean.getModifiedBy());
				System.out.print("\t" + bean.getCreatedDatetime());
				System.out.println("\t" + bean.getModifiedDatetime());

			}
		} catch (ApplicationException e) {
			e.printStackTrace();

		}

	}

	public static void testList() throws ApplicationException {
		try {
			RoleBean bean = new RoleBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test List Fails");
			}
			Iterator it = list.iterator();
			System.out.println(
					"ID\tNAME\tDescription\tCreated_by\tModified_by\t    Created_Datetime\t        Modified_Datetime\n--------------------------------------------------------------------------------------------------------------------		");

			while (it.hasNext()) {
				bean = (RoleBean) it.next();
				System.out.print(bean.getId());
				System.out.print("\t" + bean.getName());
				System.out.print("\t" + bean.getDescription());
				System.out.print("\t" + bean.getCreatedBy());
				System.out.print("\t" + bean.getModifiedBy());
				System.out.print("\t" + bean.getCreatedDatetime());
				System.out.println("\t" + bean.getModifiedDatetime());

			}
		} catch (ApplicationException e) {
         
            throw new ApplicationException(
                    "Exception : Exception in search Role");
        }

	}

	public static void testSearch() {
		try {
			RoleBean bean = new RoleBean();
			List list = new ArrayList();
			bean.setName("Admin");
			bean.setDescription("Admin Role");
			list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			System.out.println(
					"ID\tNAME\tDescription\tCreated_by\tModified_by\t    Created_Datetime\t        Modified_Datetime\n-----------------------------------------------------------------------------------------		");

			while (it.hasNext()) {
				bean = (RoleBean) it.next();
				System.out.print(bean.getId());
				System.out.print("\t" + bean.getName());
				System.out.print("\t" + bean.getDescription());
				System.out.print("\t" + bean.getCreatedBy());
				System.out.print("\t" + bean.getModifiedBy());
				System.out.print("\t" + bean.getCreatedDatetime());
				System.out.println("\t" + bean.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
            e.printStackTrace();
        }
		}
	

	private static void testFindByPk() {
		long pk = 7l;
	try {	// TODO Auto-generated method stub
		RoleBean bean = model.findByPK(pk);
		System.out.println(bean.getId());
		System.out.println(bean.getName());
	}
	catch (ApplicationException e) {
            e.printStackTrace();
        }
	}

	public static void testUpdate() {

		try {
			RoleBean bean = model.findByPK(11L);
			bean.setName("ramesh");
			bean.setDescription("faculty role");
			model.update(bean);
			RoleBean updatedbean = model.findByPK(7L);
			if (!"ramesh".equals(updatedbean.getName())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	public static void testFindbyName() {
		// TODO Auto-generated method stub

		RoleBean bean = new RoleBean();
		try {
			bean = model.findByName("Faculty");
			
			  if (bean == null) {
	                System.out.println("Test Find By PK fail");
	            }
	            System.out.println(bean.getId());
	            System.out.println(bean.getName());
	            System.out.println(bean.getDescription());
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	}

	public static void testDelete() {

		try {
			RoleBean bean = new RoleBean();
			long pk = 13L;
			bean.setId(pk);
			model.delete(bean);
			RoleBean deletedBean = model.findByPK(pk);
			if (deletedBean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testAdd() throws ParseException {
		// TODO Auto-generated method stub
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("2019-12-08");
			RoleBean bean = new RoleBean();
			bean.setName("mahesh");
			bean.setDescription("mahesh Role");
			bean.setCreatedBy("Rahul Sahu");
			bean.setModifiedBy("rahulsahu@gmail.com");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			long pk = model.add(bean);
			// RoleBean addedbean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void test() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_ors", "root", "root");
		// int id = 50;// String fname="sachin";String lname="tendulkar";int
		// salary=5500;
		String name = "sanjay";
		String sql = "select * from st_role where name=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, name);

		// ps.setString(2, fname);
		// ps.setString(3, lname);
		// ps.setInt(4, salary);*/
		ResultSet rs = ps.executeQuery();
		System.out.println("ID\tFNAME\tLNAME\tSARALY\t");
		while (rs.next()) {
			System.out.print(rs.getString("id"));
			System.out.print("\t" + rs.getString("name"));
			System.out.print("\t" + rs.getString(3));
			System.out.println("\t" + rs.getString(4));
		}
		System.out.println("record selected from employee table");
		ps.close();
		conn.close();
	}
}