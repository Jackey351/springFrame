package com.yanfei.springFrame.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanfei.springFrame.Entity.User;
import com.yanfei.springFrame.Service.UserService;
import com.yanfei.springFrame.Utils.ResultUtil;

@RequestMapping("/user")
@RestController
public class UserController {
	@Autowired
	UserService userService;
	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public ResultUtil login(String jsCode) {
//		String param = "appid=" + Global.appid + "&secret=" + Global.secret + "&js_code=" + jsCode + "&grant_type=authorization_code";
//		String result = HttpUtils.sendGet("https://api.weixin.qq.com/sns/jscode2session", param);
//		if (JSONObject.parseObject(result).getString("openid") == null) {
//			return ResultUtil.fail("登录失败");
//		}
//		String openId = JSONObject.parseObject(result).getString("openid");
//		User user = userService.getUserByOpenId(openId);
//		if (user == null) {
//			user = new User();
//			user = userService.createUser(user);
//		}
//		String jwt = JwtUtil.generateToken(user.getId(), false);
//		return ResultUtil.ok(jwt);
//	}
	
//	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
//	public ResultUtil adminLogin(@Valid @RequestBody Admin admin) {
//		admin = userService.adminLogin(admin);
//		if (admin != null) {
//			String jwt = JwtUtil.generateToken(admin.getId(), true);
//			return ResultUtil.ok(jwt);
//		}
//		return ResultUtil.fail("用户名/密码错误");
//	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ResultUtil modifyUser(@Valid @RequestBody User user, int currentUserId) {
		return userService.modifyUserInfo(user, currentUserId);
	}
	
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	public ResultUtil getUserInfo(int currentUserId) {
		return ResultUtil.ok(userService.getUserInfo(currentUserId));
	}
}
