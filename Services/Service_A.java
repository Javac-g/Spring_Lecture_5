package com.company.Services;

import com.company.Model.UserDTO;
import com.company.Model.UserEntity;
import com.company.Repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("Service_A")
public class Service_A implements A_ServiceImpl {

    @Autowired
    private DataRepository dataRepository;

    @Override
    public void setData(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setName(userDTO.getName());
        userEntity.setSalary(userDTO.getSalary());
        dataRepository.save(userEntity);

    }

    @Override
    public List<UserEntity> getData(){
        return dataRepository.findAll();
    }


}
