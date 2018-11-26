package com.dds.deba.custom.page;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * This is the action class for the
 * pagination.
 * @author Debadatta Mishra(PIKU)
 *
 */
public class InputAction extends Action 
{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception 
	{
		List dataList = new PaginationProcessor().getProcessedList(request);
		return mapping.findForward("success");
	}

}
