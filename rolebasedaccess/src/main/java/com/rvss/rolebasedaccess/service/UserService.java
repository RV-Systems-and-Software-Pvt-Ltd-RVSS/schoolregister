
package com.rvss.rolebasedaccess.service;

import com.rvss.rolebasedaccess.dto.AuthRequest;
import com.rvss.rolebasedaccess.entity.User;

public interface UserService {
    void registerUser(AuthRequest request);
    User getByUsername(String username);
}
