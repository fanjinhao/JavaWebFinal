package jiabin.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jiabin.entity.PageBean;
import jiabin.entity.Reply;
import jiabin.entity.Section;
import jiabin.entity.Topic;
import jiabin.entity.User;
import jiabin.service.ReplyService;
import jiabin.service.SectionService;
import jiabin.service.TopicService;
import jiabin.util.PageUtil;
import jiabin.util.ResponseUtil;
import jiabin.util.StringUtil;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class TopicAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	
	@Resource
	private TopicService topicService;
	
	@Resource
	private ReplyService replyService;
	
	@Resource
	private SectionService sectionService;
	
	private List<Topic> topicList;
	
	private String page;
	private String rows;
	private Long total;
	private String pageCode;
	private Topic s_topic;
	
	private int sectionId;
	private String sectionName;
	private Section section;
	
	private User user;
	
	private Topic topic;
	
	private int topicId;
	
	private String ids;
	
	private String mainPage;

	private String crumb1;
	
	private Map<Topic, Reply> topicLastReply=new HashMap<Topic, Reply>();
	private Map<Topic, Long> topicReplyCount=new HashMap<Topic, Long>();
	
	private List<Topic> zdTopicList;
	private List<Topic> ptTopicList;
	
	private Section curSection;
	private List<Section> sectionList;
	
	private List<Reply> replyList;
	
	private int topicGood;
	private int topicTop;
	
	
	public int getTopicGood() {
		return topicGood;
	}

	public void setTopicGood(int topicGood) {
		this.topicGood = topicGood;
	}

	public int getTopicTop() {
		return topicTop;
	}

	public void setTopicTop(int topicTop) {
		this.topicTop = topicTop;
	}

	public List<Reply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}

	public List<Section> getSectionList() {
		return sectionList;
	}

	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}

	public Section getCurSection() {
		return curSection;
	}

	public void setCurSection(Section curSection) {
		this.curSection = curSection;
	}

	public List<Topic> getZdTopicList() {
		return zdTopicList;
	}

	public void setZdTopicList(List<Topic> zdTopicList) {
		this.zdTopicList = zdTopicList;
	}

	public List<Topic> getPtTopicList() {
		return ptTopicList;
	}

	public void setPtTopicList(List<Topic> ptTopicList) {
		this.ptTopicList = ptTopicList;
	}

	public List<Topic> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<Topic> topicList) {
		this.topicList = topicList;
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

	public Topic getS_topic() {
		return s_topic;
	}

	public void setS_topic(Topic s_topic) {
		this.s_topic = s_topic;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
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
	
	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}
	
	public Map<Topic, Reply> getTopicLastReply() {
		return topicLastReply;
	}

	public void setTopicLastReply(Map<Topic, Reply> topicLastReply) {
		this.topicLastReply = topicLastReply;
	}

	public Map<Topic, Long> getTopicReplyCount() {
		return topicReplyCount;
	}

	public void setTopicReplyCount(Map<Topic, Long> topicReplyCount) {
		this.topicReplyCount = topicReplyCount;
	}

	public String preSave()throws Exception{
		HttpSession session=request.getSession();
		HttpServletResponse response=ServletActionContext.getResponse();
		User currentUser=(User) session.getAttribute("currentUser");
		if (currentUser==null) {
			//response.sendRedirect(request.getContextPath()+"/login.jsp");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		sectionList=sectionService.findSectionList(null, null);
		return "preSave";
	}
	
	public String save()throws Exception{
		topic.setPublishTime(new Date());
		topic.setModifyTime(new Date());
		topicService.saveTopic(topic);
		return "save";
	}
	
	public String modify()throws Exception{
		if (topicId>0) {
			Topic topic=topicService.findTopicById(topicId);
			topic.setTop(topicTop);
			topic.setGood(topicGood);
			topicService.saveTopic(topic);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return SUCCESS;
	}
	
	public String list()throws Exception{
		HttpSession session=request.getSession();
		if (curSection==null) {
			Object o=session.getAttribute("curSection");
			if(o!=null&&sectionId==0){
				curSection=(Section)o;
			}else{
				curSection=new Section();
				curSection.setId(sectionId);
				session.setAttribute("curSection", curSection);
			}
		}else{
			session.setAttribute("curSection", curSection);
		}
		
		section=sectionService.findSectionById(curSection.getId());
		
		session.setAttribute("section", section);
		
		zdTopicList=topicService.findZdTopicListBySectionId(curSection.getId(), null);
		
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),3);
		ptTopicList=topicService.findPtTopicListBySectionId(curSection.getId(), pageBean);
		long total=topicService.getPtTopicCountBySectionId(curSection.getId());
		pageCode=PageUtil.genPagination(request.getContextPath()+"/Topic_list.action", total, Integer.parseInt(page), 3,null);
		for (Topic topic : zdTopicList) {
			Reply reply=replyService.findLastReplyByTopicId(topic.getId());
			Long replyCount=replyService.getReplyCountByTopicId(topic.getId());
			if (reply!=null) {
				topicLastReply.put(topic, reply);
			}
			topicReplyCount.put(topic, replyCount);
		}
		for (Topic topic : ptTopicList) {
			Reply reply=replyService.findLastReplyByTopicId(topic.getId());
			Long replyCount=replyService.getReplyCountByTopicId(topic.getId());
			if (reply!=null) {
				topicLastReply.put(topic, reply);
			}
			topicReplyCount.put(topic, replyCount);
		}
		return SUCCESS;
	}
	
	public String listAdmin()throws Exception{
		HttpSession session=request.getSession();
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		if (s_topic==null) {
			Object o=session.getAttribute("s_topic");
			if(o!=null){
				s_topic=(Topic)o;
			}else{
				s_topic=new Topic();	
				s_topic.setTop(2);
				s_topic.setGood(2);
			}
		}else{
			session.setAttribute("s_topic", s_topic);
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 10);
		topicList=topicService.findTopicList(s_topic, pageBean);
		total=topicService.getTopicCount(s_topic);
		pageCode=PageUtil.genPagination(request.getContextPath()+"/admin/Topic_listAdmin.action", total, Integer.parseInt(page), 10,null);
		sectionList=sectionService.findSectionList(null, null);
		mainPage="topic.jsp";
		return "successAdmin";
	}
	
	public String details()throws Exception{
		topic=topicService.findTopicById(topicId);
		
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 10);
		replyList=replyService.findReplyListByTopicId(topicId, pageBean);
		total=replyService.getReplyCountByTopicId(topicId);
		StringBuffer param=new StringBuffer();
		if (topicId>0) {
			param.append("topicId="+topicId);
		}
		pageCode=PageUtil.genPagination(request.getContextPath()+"/Topic_details.action", total, Integer.parseInt(page), 10,param.toString());
		return "details";
	}
	
	public String delete()throws Exception{
		JSONObject result=new JSONObject();
		Topic topic=topicService.findTopicById(topicId);
		topicService.deleteTopic(topic);
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	public String delete1()throws Exception{
		JSONObject result=new JSONObject();
		String[] idsStr=ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			Topic e=topicService.findTopicById(Integer.parseInt(idsStr[i]));
			topicService.deleteTopic(e);
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}
