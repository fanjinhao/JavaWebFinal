package jiabin.service;

import java.util.List;

import jiabin.entity.PageBean;
import jiabin.entity.Topic;

public interface TopicService {

	public void saveTopic(Topic topic);
	
	public void deleteTopic(Topic topic);
	
	public List<Topic> findTopicList(Topic s_topic,PageBean pageBean);
	
	public Long getTopicCount(Topic s_topic);
	
	public Topic findTopicById(int topicId);	
	
	public List<Topic> findZdTopicListBySectionId(int sectionId,PageBean pageBean);
	
	public List<Topic> findPtTopicListBySectionId(int sectionId,PageBean pageBean);
	
	public Long getPtTopicCountBySectionId(int sectionId);
	
	public List<Topic> findGoodTopicListBySectionId(int sectionId,PageBean pageBean);
	
	public List<Topic> findNotGoodTopicListBySectionId(int sectionId,PageBean pageBean);
	
	public Long getNoReplyTopicCount(Topic s_topic);
}
