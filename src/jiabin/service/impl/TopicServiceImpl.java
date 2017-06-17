package jiabin.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jiabin.dao.BaseDAO;
import jiabin.entity.PageBean;
import jiabin.entity.Topic;
import jiabin.service.TopicService;
import jiabin.util.StringUtil;

@Service("topicService")
public class TopicServiceImpl implements TopicService {

	@Resource
	private BaseDAO<Topic> baseDAO;
	
	@Override
	public void saveTopic(Topic topic) {
		baseDAO.merge(topic);
	}

	@Override
	public void deleteTopic(Topic topic) {
		baseDAO.delete(topic);
	}

	@Override
	public List<Topic> findTopicList(Topic s_topic, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Topic t");
		if (s_topic!=null) {
			if (StringUtil.isNotEmpty(s_topic.getTitle())) {
				hql.append(" and t.title like ?");
				param.add("%"+s_topic.getTitle()+"%");
			}
			if (s_topic.getUser()!=null) {
				if (StringUtil.isNotEmpty(s_topic.getUser().getNickName())) {
					hql.append(" and t.user.nickName like ?");
					param.add("%"+s_topic.getUser().getNickName()+"%");
				}
			}
			if (StringUtil.isNotEmpty(s_topic.getContent())) {
				hql.append(" and t.content like ?");
				param.add("%"+s_topic.getContent()+"%");
			}
			if (s_topic.getPublishTime()!=null) {
				hql.append(" and t.publishTime=?");
				param.add(s_topic.getPublishTime());
			}
			if (s_topic.getModifyTime()!=null) {
				hql.append(" and t.modifyTime=?");
				param.add(s_topic.getModifyTime());
			}
			if (s_topic.getSection()!=null) {
				if (s_topic.getSection().getId()>0) {
					hql.append(" and t.section.id=?");
					param.add(s_topic.getSection().getId());
				}
			}
			if (s_topic.getTop()!=2) {
				hql.append(" and t.top=?");
				param.add(s_topic.getTop());
			}
			if (s_topic.getGood()!=2) {
				hql.append(" and t.good=?");
				param.add(s_topic.getGood());
			}
		}
		if (pageBean!=null) {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param);
		}
	}

	@Override
	public Long getTopicCount(Topic s_topic) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Topic t");
		if (s_topic!=null) {
			if (StringUtil.isNotEmpty(s_topic.getTitle())) {
				hql.append(" and t.title like ?");
				param.add("%"+s_topic.getTitle()+"%");
			}
			if (s_topic.getUser()!=null) {
				if (StringUtil.isNotEmpty(s_topic.getUser().getNickName())) {
					hql.append(" and t.user.nickName like ?");
					param.add("%"+s_topic.getUser().getNickName()+"%");
				}
			}
			if (StringUtil.isNotEmpty(s_topic.getContent())) {
				hql.append(" and t.content like ?");
				param.add("%"+s_topic.getContent()+"%");
			}
			if (s_topic.getPublishTime()!=null) {
				hql.append(" and t.publishTime=?");
				param.add(s_topic.getPublishTime());
			}
			if (s_topic.getModifyTime()!=null) {
				hql.append(" and t.modifyTime=?");
				param.add(s_topic.getModifyTime());
			}
			if (s_topic.getSection()!=null) {
				if (s_topic.getSection().getId()>0) {
					hql.append(" and t.section.id=?");
					param.add(s_topic.getSection().getId());
				}
			}
			if (s_topic.getTop()!=2) {
				hql.append(" and t.top=?");
				param.add(s_topic.getTop());
			}
			if (s_topic.getGood()!=2) {
				hql.append(" and t.good=?");
				param.add(s_topic.getGood());
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public Topic findTopicById(int topicId) {
		return baseDAO.get(Topic.class, topicId);
	}

	@Override
	public List<Topic> findZdTopicListBySectionId(int sectionId,PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Topic");
		if (sectionId>0) {
			hql.append(" and sectionId=?");
			param.add(sectionId);
		}
		hql.append(" and top=1");
		if (pageBean!=null) {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param);
		}
	}

	@Override
	public List<Topic> findPtTopicListBySectionId(int sectionId,PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Topic");
		if (sectionId>0) {
			hql.append(" and sectionId=?");
			param.add(sectionId);
		}
		hql.append(" and top=0 order by modifyTime desc");
		if (pageBean!=null) {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param);
		}
	}

	@Override
	public Long getNoReplyTopicCount(Topic s_topic) {
		List<Object> param=new LinkedList<Object>();
		//StringBuffer hql=new StringBuffer("select count(*) from Topic t where t.id not in (select t.id from Reply r,Topic t where r.topic.id=t.id)");
		StringBuffer hql=new StringBuffer("select count(*) from Topic where id not in (select r.topic.id from Reply r)");
		if (s_topic!=null) {
			if (s_topic.getSection().getId()>0) {
				hql.append(" and sectionId=?");
				param.add(s_topic.getSection().getId());
			}
		}
		return baseDAO.count(hql.toString(), param);
	}

	@Override
	public List<Topic> findGoodTopicListBySectionId(int sectionId,
			PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Topic");
		if (sectionId>0) {
			hql.append(" and sectionId=?");
			param.add(sectionId);
		}
		hql.append(" and good=1");
		if (pageBean!=null) {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param);
		}
	}

	@Override
	public List<Topic> findNotGoodTopicListBySectionId(int sectionId,
			PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Topic");
		if (sectionId>0) {
			hql.append(" and sectionId=?");
			param.add(sectionId);
		}
		hql.append(" and good=0");
		if (pageBean!=null) {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param);
		}
	}

	@Override
	public Long getPtTopicCountBySectionId(int sectionId) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Topic");
		if (sectionId>0) {
			hql.append(" and sectionId=?");
			param.add(sectionId);
		}
		hql.append(" and top=0");
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}
}
