package com.z.controller;

import com.z.model.User;
import com.z.service.UserService;
import com.z.utils.Page;
import com.z.utils.PageUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("userAction")
@RequestMapping("/user")
public class UserAction {

    @Resource
    private UserService userService;
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "/login.jsp";
	}

    @RequestMapping(value="/loginSubmit", method=RequestMethod.POST)
	public String loginSubmit(HttpServletRequest request) {
        String username = request.getParameter("username");
        if(StringUtils.isBlank(username)) {
            return "/index.jsp";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        
        //分页
        Page page = PageUtil.getPage(0, 1);
        if (userService.getList(map, page) == null) {
            return "/index.jsp";
        }
		return "redirect:/user/list";
	}

    @RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
	    request.getSession().invalidate();
		return "redirect:/index.html";
	}

    @RequestMapping("/list")
	public String list(HttpServletRequest request) {
        //分页
        Page page = PageUtil.getPage(0, 2);

        List<User> userList = userService.getList(null, page);
        request.setAttribute("userList", userList);
		return "/list.jsp";
	}

    @RequestMapping("/register")
	public String register() {
		return "/register.jsp";
	}

    @RequestMapping("/registerSubmit")
	public String registerSubmit(HttpServletRequest request) {
	    String username = request.getParameter("username");
	    if(StringUtils.isBlank(username)) {
	        return "/index.jsp";
	    }
	    User user = new User();
	    user.setId(getUUID());
	    user.setUsername(username);
	    userService.save(user);
		return "redirect:/user/login";
	}

   /**
     * 得到32位的随机字符串
     * @return
     */
    private String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
