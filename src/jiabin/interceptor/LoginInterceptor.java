package jiabin.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jiabin.util.StringUtil;

import org.apache.struts2.ServletActionContext;
import org.jboss.logging.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log=Logger.getLogger(LoginInterceptor.class);
	
	public final static String SESSION_KEY="regUserId";
	//public final static String GOTO_URL_KEY="GOING_TO";
	
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		if (session!=null&&session.getAttribute("currentUser")!=null) {
			return invocation.invoke();
		}
		
		setGoingURL(request, session, invocation);
		return invocation.invoke();
	}
	
	private void setGoingURL(HttpServletRequest request,HttpSession session,ActionInvocation invocation){
		//如果refer不为空，直接使用它。如果为空我们分别获得命名空间，action名，以及请求参数，重新构造成一个URL保存在SESSION中
		String url=request.getHeader("referer");
		String url2;
		if (url==null||url.equals("")) {
			url="";
			String path=request.getContextPath();
			String actionName=invocation.getProxy().getActionName();
			String nameSpace=invocation.getProxy().getNamespace();
			if (StringUtil.isNotEmpty(nameSpace)) {
				url=url+nameSpace;
			}
			if (StringUtil.isNotEmpty(actionName)) {
				url=url+"/"+actionName+".action"+"?";
			}
			Map<String,String[]> zzMap=request.getParameterMap();
			if (zzMap!=null) {
				for (String s : zzMap.keySet()) {
					String[] value=zzMap.get(s);
					for (String val : value) {
						url=url+s+"="+val+"&";
					}
				}
			}
		}
		if (url.indexOf("prePage")>0) {
			url2=StringUtil.getParamFromUrl(url);
			session.setAttribute("prepareGoingTo", url2);
			session.setAttribute("backGoingTo", url);
		}else {
			session.setAttribute("GOING_TO", url);
		}
	}

}
