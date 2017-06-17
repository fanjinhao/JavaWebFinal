package jiabin.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import jiabin.entity.Zone;
import jiabin.entity.PageBean;
import jiabin.entity.Zone;
import jiabin.service.ZoneService;
import jiabin.util.PageUtil;
import jiabin.util.ResponseUtil;
import jiabin.util.StringUtil;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class ZoneAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private ZoneService zoneService;
	
	private HttpServletRequest request;
	
	private List<Zone> zoneList;
	
	private String page;
	private String rows;
	private Long total;
	private String pageCode;
	private Zone s_zone;
	
	private Zone zone;
	
	private int zoneId;
	
	private String ids;
	
	private String mainPage;

	private String crumb1;
	
	public List<Zone> getZoneList() {
		return zoneList;
	}



	public void setZoneList(List<Zone> zoneList) {
		this.zoneList = zoneList;
	}



	public String getPage() {
		return page;
	}



	public void setPage(String page) {
		this.page = page;
	}



	public String getRows() {
		return rows;
	}



	public void setRows(String rows) {
		this.rows = rows;
	}



	public Long getTotal() {
		return total;
	}



	public void setTotal(Long total) {
		this.total = total;
	}



	public String getPageCode() {
		return pageCode;
	}



	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}



	public Zone getS_zone() {
		return s_zone;
	}



	public void setS_zone(Zone s_zone) {
		this.s_zone = s_zone;
	}



	public Zone getZone() {
		return zone;
	}



	public void setZone(Zone zone) {
		this.zone = zone;
	}



	public int getZoneId() {
		return zoneId;
	}



	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}



	public String getIds() {
		return ids;
	}



	public void setIds(String ids) {
		this.ids = ids;
	}



	public String getMainPage() {
		return mainPage;
	}



	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	
	public String getCrumb1() {
		return crumb1;
	}



	public void setCrumb1(String crumb1) {
		this.crumb1 = crumb1;
	}



	public String list()throws Exception{
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 6);
		zoneList=zoneService.findZoneList(s_zone, pageBean);
		total=zoneService.getZoneCount(s_zone);
		pageCode=PageUtil.genPagination(request.getContextPath()+"/admin/Zone_list.action", total, Integer.parseInt(page), 6,null);
		mainPage="zone.jsp";
		crumb1="´ó°å¿é¹ÜÀí";
		return SUCCESS;
	}

	public String save()throws Exception{
		zoneService.saveZone(zone);
		return SUCCESS;
	}
	
	public String delete()throws Exception{
		JSONObject result=new JSONObject();
		Zone e=zoneService.findZoneById(zoneId);
		zoneService.deleteZone(e);
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}
