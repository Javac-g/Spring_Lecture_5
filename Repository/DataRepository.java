package com.company.Repository;

import com.company.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<UserEntity,Long> {
    List<UserEntity> findByNameAndId(String name,Long id);
    List<UserEntity> findByNameOrSalaryGreaterThan(String name,int sal);
    List<UserEntity> findByNameOrSalaryGreaterThanOrderByIdDesc(String name, int salary);
}
