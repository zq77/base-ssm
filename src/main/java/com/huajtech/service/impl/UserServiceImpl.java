package com.huajtech.service.impl;

import com.huajtech.model.User;
import com.huajtech.service.UserService;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Override
    public String getEntityName() {
        return "com.huajtech.model.User";
    }
}
