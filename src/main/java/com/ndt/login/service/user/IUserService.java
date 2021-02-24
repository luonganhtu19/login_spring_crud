package com.ndt.login.service.user;

import com.ndt.login.model.User;
import com.ndt.login.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findUserByName(String username);
    User getCurrentUser();
}
