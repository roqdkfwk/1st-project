package com.suseok.run.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.suseok.run.model.dao.RankDao;
import com.suseok.run.model.dao.UserDao;
import com.suseok.run.model.dto.Group;
import com.suseok.run.model.dto.UserRankRecord;

@Service
public class RankServiceImpl implements RankService {

	private final UserDao ud;
	private final RankDao rd;
	
	public RankServiceImpl(UserDao ud, RankDao rd) {
		this.ud = ud;
		this.rd = rd;
	}
	
	@Override
	public UserRankRecord selectByUser(String userId) {
		return rd.selectByUserId(userId);
	}

	@Override
	public List<UserRankRecord> selectAllOrderBy(String con) {
		
		List<UserRankRecord> users = rd.selectAllOrderBy(con);
		for (int i = 0; i < users.size(); i++) {
			UserRankRecord user = users.get(i);
			user.setUserName(ud.selectBySeq(user.getUserSeq()).getUserName());
			user.setUserNick(ud.selectBySeq(user.getUserSeq()).getUserNick());
			user.setUserId(ud.selectBySeq(user.getUserSeq()).getUserId());
		}
		
		return users;
	}

	@Override
	public List<UserRankRecord> selectAllOrderBy(String con, String userId) {
		
		List<UserRankRecord> users = rd.selectAllOrderBy(con);
		for (int i = 0; i < users.size(); i++) {
			UserRankRecord user = users.get(i);
			user.setUserName(ud.selectBySeq(user.getUserSeq()).getUserName());
			user.setUserNick(ud.selectBySeq(user.getUserSeq()).getUserNick());
			user.setUserId(ud.selectBySeq(user.getUserSeq()).getUserId());
		}

		return users;
	}

	@Override
	public List<UserRankRecord> selectAllMemberOrderBy(String con, int groupId) {
		
		List<UserRankRecord> users = rd.selectAllMemberOrderBy(con, groupId);
		for (int i = 0; i < users.size(); i++) {
			UserRankRecord user = users.get(i);
			user.setUserName(ud.selectBySeq(user.getUserSeq()).getUserName());
			user.setUserNick(ud.selectBySeq(user.getUserSeq()).getUserNick());
			user.setUserId(ud.selectBySeq(user.getUserSeq()).getUserId());
		}
		
		return users;
	}

	@Override
	public List<Group> selectGroupsOrderBy(String con) {
		return rd.selectGroupsOrderBy(con);
	}

	@Override
	public List<Group> selectMyGroupsOrderBy(String con, String userId) {
		return rd.selectMyGroupsOrderBy(con, userId);
	}

	@Override
	public UserRankRecord selectRival(String userId, String rivalId) {
		return rd.selectRival(userId, rivalId);
	}

	@Override
	public boolean updateRankRecord(UserRankRecord record, int userSeq) {
		return rd.updateRankRecord(record);
	}

	@Override
	public boolean insertRankRecord(UserRankRecord record, String userId) {
		
		int us = ud.selectById(userId).getUserSeq();
		record.setUserSeq(us);
		UserRankRecord urr = rd.selectByUserId(userId);
		if (urr != null) 
			return updateRankRecord(record, us);
		
		return rd.insertRankRecord(record);
	}
	
}
