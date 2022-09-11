package com.company.Repository;

import com.company.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<UserEntity,Long> {
    List<UserEntity> findByName(String name);
    List<UserEntity> findByNameAndId(String name,Long id);
    List<UserEntity> findByNameOrSalaryGreaterThan(String name,int sal);
    List<UserEntity> findByNameOrSalaryGreaterThanOrderByIdDesc(String name, int salary);

    @Query("SELECT COUNT(de) FROM UserEntity de WHERE de.id > ?1")
    Long getCount(Long id);
    @Query("SELECT de FROM UserEntity de WHERE de.salary > :s1")
    List<UserEntity> getBySalary(@Param("s1") int salary);

    @Query(value = "SELECT id, name, salary FROM data WHERE salary > ?1",nativeQuery = true)
    List<UserEntity> getBySalaryNative(@Param("s1") int salary);

    @Modifying
    @Query("UPDATE UserEntity de SET de.salary = :s WHERE de.name = :n")
    void changeSalaryByName(@Param("s") int salary,@Param("n") String name);

}
