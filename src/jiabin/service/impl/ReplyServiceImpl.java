package jiabin.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jiabin.dao.BaseDAO;
import jiabin.entity.PageBean;
import jiabin.entity.Reply;
import jiabin.service.ReplyService;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

	@Resource
	private BaseDAO<Reply> baseDAO;
	
	@Override
	public Reply findLastReplyByTopicId(int topicId) {
		List<Reply> replyList=new ArrayList<Reply>();
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Reply");
		if (topicId>0) {
			hql.append(" and topicId = ?");
			param.add(topicId);
		}
		hql.append(" order by publishTime desc");
		replyList=baseDAO.find(hql.toString().replaceFirst("and", "where"),param);
		if (replyList.size()>0) {
			return replyList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Long getReplyCountByTopicId(int topicId) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Reply");
		if (topicId>0) {
			hql.append(" and topicId = ?");
			param.add(topicId);
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public List<Reply> findReplyListByTopicId(int topicId, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Reply");
		if (topicId>0) {
			hql.append(" and topicId=?");
			param.add(topicId);
		}
		//hql.append(" order by publishTime desc");
		if (pageBean!=null) {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param);
		}
	}

	@Override
	public void saveReply(Reply reply) {
		// TODO Auto-generated method stub
		baseDAO.merge(reply);
	}

	@Override
	public void deleteReply(Reply reply) {
		// TODO Auto-generated method stub
		baseDAO.delete(reply);
	}

	@Override
	public Reply findReplyById(int replyId) {
		// TODO Auto-generated method stub
		return baseDAO.get(Reply.class, replyId);
	}

}
