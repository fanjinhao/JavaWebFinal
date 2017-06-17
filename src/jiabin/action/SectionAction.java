package jiabin.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jiabin.entity.PageBean;
import jiabin.entity.Section;
import jiabin.entity.Section;
import jiabin.entity.User;
import jiabin.entity.Zone;
import jiabin.service.SectionService;
import jiabin.service.UserService;
import jiabin.service.ZoneService;
import jiabin.util.DateUtil;
import jiabin.util.PageUtil;
import jiabin.util.ResponseUtil;
import jiabin.util.StringUtil;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class SectionAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private SectionService sectionService;
	
	@Resource
	private ZoneService zoneService;
	
	@Resource
	private UserService userService;
	
	private HttpServletRequest request;
	
	private List<Section> sectionList;
	private List<Zone> zoneList;
	private List<User> masterList;
	
	private String page;
	private String rows;
	private Long total;
	private String pageCode;
	private Section s_section;
	
	private Section section;
	
	private int sectionId;
	
	private String ids;
	
	private String mainPage;

	private String crumb1;
	
	private String nickName;
	private User user;
	private String info;
	
	private File logo;
	private String logoFileName;
	
	public List<Section> getSectionList() {
		return sectionList;
	}


	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
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


	public Section getS_section() {
		return s_section;
	}


	public void setS_section(Section s_section) {
		this.s_section = s_section;
	}


	public Section getSection() {
		return section;
	}


	public void setSection(Section section) {
		this.section = section;
	}


	public int getSectionId() {
		return sectionId;
	}


	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
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

	
	public List<Zone> getZoneList() {
		return zoneList;
	}


	public void setZoneList(List<Zone> zoneList) {
		this.zoneList = zoneList;
	}


	public List<User> getMasterList() {
		return masterList;
	}


	public void setMasterList(List<User> masterList) {
		this.masterList = masterList;
	}
	

	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public File getLogo() {
		return logo;
	}


	public void setLogo(File logo) {
		this.logo = logo;
	}


	public String getLogoFileName() {
		return logoFileName;
	}


	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}


	public String list()throws Exception{
		HttpSession session=request.getSession();
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		if (s_section==null) {
			Object o=session.getAttribute("s_section");
			if(o!=null){
				s_section=(Section)o;
			}else{
				s_section=new Section();				
			}
		}else{
			session.setAttribute("s_section", s_section);
		}
		User master=new User();
		master.setType(2);
		masterList=userService.findUserList(master, null);
		PageBean pageBean=new PageBean(Integer.parseInt(page), 6);
		sectionList=sectionService.findSectionList(s_section, pageBean);
		zoneList=zoneService.findZoneList(null, pageBean);
		total=sectionService.getSectionCount(s_section);
		pageCode=PageUtil.genPagination(request.getContextPath()+"/admin/Section_list.action", total, Integer.parseInt(page), 6,null);
		mainPage="section.jsp";
		crumb1="小板块管理";
		return SUCCESS;
	}

	public String save()throws Exception{
		if (logo!=null) {
			String imageName=DateUtil.getCurrentDateStr();
			String realPath=ServletActionContext.getServletContext().getRealPath("/images/logo");
			String imageFile=imageName+"."+logoFileName.split("\\.")[1];
			File saveFile=new File(realPath,imageFile);
			FileUtils.copyFile(logo, saveFile);
			section.setLogo("images/logo/"+imageFile);
		}else{
			section.setLogo("");
		}
		sectionService.saveSection(section);
		/*User user=userService.getUserById(section.getMaster().getId());
		user.setType(2);*/
		//userService.saveUser(user);
		return "save";
	}
	
	public String delete()throws Exception{
		JSONObject result=new JSONObject();
		Section e=sectionService.findSectionById(sectionId);
		sectionService.deleteSection(e);
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return SUCCESS;
	}
	
	public String delete1()throws Exception{
		JSONObject result=new JSONObject();
		String[] idsStr=ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			Section e=sectionService.findSectionById(Integer.parseInt(idsStr[i]));
			sectionService.deleteSection(e);
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return SUCCESS;
	}
	
	public String getUserByNickName() throws Exception{
		JSONObject result=new JSONObject();
		user=userService.getUserByNickName(nickName);
		if (user!=null) {
			result.put("info", "用户id："+user.getId()+"用户昵称："+user.getNickName());
			result.put("masterId", user.getId());
			//info="用户昵称："+user.getNickName();
		}else {
			result.put("info", "没有此用户！");
			//info="没有此用户！";
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}
