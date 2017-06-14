package jiabin.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import jiabin.entity.Section;
import jiabin.entity.Topic;
import jiabin.entity.Zone;
import jiabin.service.SectionService;
import jiabin.service.TopicService;
import jiabin.service.ZoneService;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class InitAction implements ServletContextListener,ApplicationContextAware{

	private static ApplicationContext applicationContext;
	private Map<Section, Long> sectionTopicCount=new HashMap<Section, Long>();
	private Map<Section, Long> sectionGoodTopicCount=new HashMap<Section, Long>();
	private Map<Section, Long> sectionNoReplyTopicCount=new HashMap<Section, Long>();
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext application=servletContextEvent.getServletContext();
		ZoneService zoneService=(ZoneService) applicationContext.getBean("zoneService");
		SectionService sectionService=(SectionService) applicationContext.getBean("sectionService");
		TopicService topicService=(TopicService) applicationContext.getBean("topicService");
		
		
		List<Zone> zoneList=zoneService.findZoneList(null, null);
		application.setAttribute("zoneList", zoneList);
		
		for (Zone zone : zoneList) {
			for (Section section : zone.getSectionList()) {
				Topic s_topic=new Topic(); 
				s_topic.setSection(section);
				Long tatolCount=topicService.getTopicCount(s_topic);			//板块的总帖数
				s_topic.setGood(1);
				Long goodCount=topicService.getTopicCount(s_topic);			//板块的精华帖数
				s_topic.setGood(0);
				Long noReplyCount=topicService.getNoReplyTopicCount(s_topic);			//板块的无回复帖数
				
				sectionTopicCount.put(section, tatolCount);
				sectionGoodTopicCount.put(section, goodCount);
				sectionNoReplyTopicCount.put(section, noReplyCount);
			}
		}
		
		application.setAttribute("sectionTopicCount", sectionTopicCount);
		application.setAttribute("sectionGoodTopicCount", sectionGoodTopicCount);
		application.setAttribute("sectionNoReplyTopicCount", sectionNoReplyTopicCount);
	}

}
