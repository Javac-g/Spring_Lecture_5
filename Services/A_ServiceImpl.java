package com.company.Services;

import com.company.Model.UserDTO;
import com.company.Model.UserEntity;

import java.util.List;

public interface A_ServiceImpl {
    void setData(UserDTO userDTO);
    List<UserEntity>getData();
}
