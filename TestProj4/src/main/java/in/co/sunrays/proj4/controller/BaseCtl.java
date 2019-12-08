package in.co.sunrays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;

/**
 * Servlet implementation class BaseCtl
 */
/**
 * Base controller class of project. It contain (1) Generic operations (2)
 * Generic constants (3) Generic work flow
 **/
public abstract class BaseCtl extends HttpServlet {
	public static final String OP_SAVE = "save";
	public static final String OP_CANCEL = "cancel";
	public static final String OP_DELETE = "Delete";
	public static final String OP_LIST = "List";
	public static final String OP_SEARCH = "Search";
	public static final String OP_VIEW = "View";
	public static final String OP_NEXT = "Next";
	public static final String OP_PREVIOUS = "previous";
	public static final String OP_NEW = "New";
	public static final String OP_BACK = "Back";
	public static final String OP_GO = "Go";
	public static final String OP_LOG_OUT = "Logout";
	public static final String MSG_SUCCESS = "success";
	public static final String MSG_ERROR = "error";

	protected boolean validate(HttpServletRequest request) {
	}

	protected void preload(HttpServletRequest request) {
	}

	protected BaseBean populateBean(HttpServletRequest request) {
		return null;
	}

	protected BaseBean populateDTO(BaseBean dto,HttpServletRequest request) {
		String createdBy=request.getParameter("createdBy");
		String modifiedBy=null;
		UserBean userBean =(UserBean)request.getSession().getAttribute("user");
		if(userBean==null) {
			createdBy="root";
			modifiedBy="root";
		}else {
			modifiedBy=userBean.getLogin();
			if("null".equalsIgnoreCase(createdBy)||DataValidator.isNull(createdBy)) {
				createdBy=modifiedBy;	
			}
		}
		dto.setCreatedBy(createdBy);
        dto.setModifiedBy(modifiedBy);

        long cdt = DataUtility.getLong(request.getParameter("createdDatetime"));

		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		long cdt=DataUtility.getLong(request.getParameter("createdDateTime"));
		if(cdt>0) 
			dto.setCreatedDatetime(DataUtility.getTimestamp(cdt));
	    }else{
	    	dto.set
	    }
	/*
	 * if (cdt > 0) { dto.setCreatedDatetime(DataUtility.getTimestamp(cdt)); } else
	 * { dto.setCreatedDatetime(DataUtility.getCurrentTimestamp()); }
	 * 
	 * dto.setModifiedDatetime(DataUtility.getCurrentTimestamp());
	 * 
	 * return dto; }
	 * 
	 */ }

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BaseCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
