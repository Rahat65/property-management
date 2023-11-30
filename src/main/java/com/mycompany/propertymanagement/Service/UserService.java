package com.mycompany.propertymanagement.Service;

import com.mycompany.propertymanagement.dto.UserDTO;

public interface UserService {
    UserDTO register(UserDTO userDTO);
    UserDTO login(String email, String password);
}
