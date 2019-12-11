package in.co.sunrays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
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

	public BaseCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected boolean validate(HttpServletRequest request) {
		return true;
	}

	protected void preload(HttpServletRequest request) {
	}

	protected BaseBean populateBean(HttpServletRequest request) {
		return null;
	}

	protected BaseBean populateDTO(BaseBean dto, HttpServletRequest request) {
		String createdBy = request.getParameter("createdBy");
		String modifiedBy = null;
		UserBean userBean = (UserBean) request.getSession().getAttribute("user");
		if (userBean == null) {
			createdBy = "root";
			modifiedBy = "root";
		} else {
			modifiedBy = userBean.getLogin();
			if ("null".equalsIgnoreCase(createdBy) || DataValidator.isNull(createdBy)) {
				createdBy = modifiedBy;
			}
		}
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);

		long cdt = DataUtility.getLong(request.getParameter("createdDatetime"));

		if (cdt > 0) {

			dto.setCreatedDatetime(DataUtility.getTimestamp(cdt));

		} else {

			dto.setCreatedDatetime(DataUtility.getCurrentTimestamp());
		}
		dto.setModifiedDatetime(DataUtility.getCurrentTimestamp());
		return dto;

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op) && !OP_VIEW.equalsIgnoreCase(op)
				&& !OP_DELETE.equalsIgnoreCase(op)) {
			// Check validation, If fail then send back to page with error
			// messages

			if (!validate(request)) {
				BaseBean bean = (BaseBean) populateBean(request);
				// ServletUtility.setBean(bean, request);
				// ServletUtility.forward(getView(), request, response);
				return;
			}
		}
		super.service(request, response);
	}

	protected abstract String getView();

}
