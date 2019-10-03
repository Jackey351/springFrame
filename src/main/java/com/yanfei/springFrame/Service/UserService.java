package com.yanfei.springFrame.Service;

import com.yanfei.springFrame.Entity.User;
import com.yanfei.springFrame.Utils.ResultUtil;

public interface UserService {
	public User createUser(User user);
	
	public ResultUtil modifyUserInfo(User user, int currentUserId);
	
	public User getUserInfo(int currentUserId);
}
