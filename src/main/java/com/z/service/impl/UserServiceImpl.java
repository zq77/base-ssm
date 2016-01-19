package com.z.service.impl;

import com.z.model.User;
import com.z.service.UserService;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Override
    public String getEntityName() {
        return "com.z.model.User";
    }
}
