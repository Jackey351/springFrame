package com.yanfei.springFrame.Service.Impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.binarywang.java.emoji.EmojiConverter;
import com.yanfei.springFrame.Dao.UserDao;
import com.yanfei.springFrame.Entity.User;
import com.yanfei.springFrame.Service.UserService;
import com.yanfei.springFrame.Utils.ResultUtil;

@Service
public class UserServiceImpl implements UserService {
	private EmojiConverter emojiConverter = EmojiConverter.getInstance();
	
	@Autowired
	UserDao userDao;
	
	@Override
	public User createUser(User user) {
		user.setId(null);
		return userDao.save(user);
	}

	
	@Override
	public ResultUtil modifyUserInfo(User user, int currentUserId) {
		User result = userDao.findById(currentUserId).orElseThrow(() -> new EntityNotFoundException("用户不存在"));
		result.setNickName(emojiConverter.toHtml(user.getNickName()));
		result.setRole(user.getRole());
		userDao.save(result);
		return ResultUtil.ok();
	}

	
	@Override
	public User getUserInfo(int currentUserId) {
		return userDao.findById(currentUserId).orElseThrow(() -> new EntityNotFoundException("用户不存在"));
	}
}
