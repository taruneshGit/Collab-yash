package in.co.sunrays.proj4.model.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.model.UserModel;

public class UserModelTest {
	public static UserModel model = new UserModel();

	public static void main(String[] args) {

		//testAdd();
		testDelete();

	}

	private static void testDelete() {
		try{
		UserBean bean=new UserBean();
		long pk=66L;
		bean.setId(pk);
		model.delete(bean);
		System.out.println("test deleted success "+bean.getId());
		UserBean deletedbean=model.findByPk(pk);
		if(deletedbean==null) {
			System.out.println("test delete fail");
		}
		
	    }catch (Exception e) {
	       e.printStackTrace();	
	    }
	 }
	private static void testAdd() {

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

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
