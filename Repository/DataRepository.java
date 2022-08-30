package com.company.Repository;

import com.company.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<UserEntity,Long> {
    List<UserEntity> findByNameAndId(String name,Long id);
    List<UserEntity> findByNameOrSalaryGreaterThan(String name,int sal);
    List<UserEntity> findByNameOrSalaryGreaterThanOrderByIdDesc(String name, int salary);

    @Query("SELECT COUNT(de) FROM UserEntity de WHERE de.id > ?1")
    Long getCount(Long id);
    @Query("SELECT de FROM UserEntity de WHERE de.salary > ?1")
    List<UserEntity> getBySalary(int salary);
}
