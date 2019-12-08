package in.co.sunrays.proj4.model.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.model.CollegeModel;

public class CollegeModelTest {

	public static CollegeModel model = new CollegeModel();

	public static void main(String[] args) {

		// testAdd();
		// testFindByName();
		// testFindByPk();
		// testDelete();
		// testUpdate();
		testSearch();
	}

	private void pubbl() {
		// TODO Auto-generated method stub

	}

	public static void testSearch() {
		try {
			CollegeBean bean = new CollegeBean();
			List list = new ArrayList();
			bean.setName("IIM");
			// bean.setAddress("borawan");
			list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			}
			System.out.println(
					"ID\tNAME\tAddress\tState\tCity\tPhoneno\tCreated_by\tModified_by\t    Created_Datetime\t        Modified_Datetime\n-----------------------------------------------------------------------------------------		");

			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (CollegeBean) it.next();
				System.out.print("\t" + bean.getId());
				System.out.print("\t" + bean.getName());
				System.out.print("\t" + bean.getAddress());
				System.out.print("\t" + bean.getState());
				System.out.print("\t" + bean.getCity());
				System.out.print("\t" + bean.getPhoneNo());
				System.out.print("\t" + bean.getCreatedBy());
				System.out.print("\t" + bean.getCreatedDatetime());
				System.out.print("\t" + bean.getModifiedBy());
				System.out.print("\t" + bean.getModifiedDatetime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() {
		try {
			CollegeBean bean = new CollegeBean();
			bean = model.findByPk(114L);
			System.out.println("findby pk called");
			bean.setName("IIM");
			bean.setAddress("khandwa road ");
			bean.setCity("Indore");
			model.update(bean);
			System.out.println("updated bean successfully");
			CollegeBean updatedbean = model.findByPk(114L);
			if (!"IIM".equals(updatedbean.getName())) {
				System.out.println("Test Update fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testDelete() {
		try {
			CollegeBean bean = new CollegeBean();
			long pk = 116;
			bean.setId(pk);
			model.delete(bean);
			System.out.println("test deleted recod succ");
			CollegeBean deletedbean = model.findByPk(pk);
			if (deletedbean != null) {

				System.out.println("test delete fail");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void testFindByPk() {

		try {
			CollegeBean bean = new CollegeBean();
			long pk = 116L;
			bean = model.findByPk(pk);
			if (bean == null) {

				System.out.println("Test Find By Pk fail");

			}
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.print("\t" + bean.getModifiedDatetime());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void testFindByName() {
		try {
			CollegeBean bean = model.findByName("SOC IPS ACADEMY");
			System.out.println(
					"ID\tNAME\t\tADDRESS\t\tSTATE\t\tCITY\t\tPHONENO\t\tCREATED_BY\t\tMODIFIED_BY\t\tCREATED_DATETIME\t\tMODIFIED_DATETIME\n-------------------------------------------------------------------------------------");

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.print("\t" + bean.getModifiedDatetime());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testAdd() {

		try {
			CollegeBean bean = new CollegeBean();

			bean.setName("Medicaps INDORE");
			bean.setAddress("Rau A. B.Road");
			bean.setState("MADHYA PRADESH");
			bean.setCity("INDORE");
			bean.setPhoneNo("07315252435");
			bean.setCreatedBy("Faculty");
			bean.setModifiedBy("Faculty Role");
			bean.setCreatedDatetime(null);
			bean.setModifiedDatetime(null);
			long pk = model.add(bean);
			System.out.println("college record add successfully");
			CollegeBean addedbean = model.findByPk(pk);
			if (addedbean == null) {

				System.out.println("Test Add Fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
