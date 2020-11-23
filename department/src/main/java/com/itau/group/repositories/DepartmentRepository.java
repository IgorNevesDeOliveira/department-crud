package com.itau.group.repositories;

import com.itau.group.models.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {
    List<DepartmentEntity> findAllByOrderByCodeAsc();
    Optional<DepartmentEntity> findByCode(Integer code);
}
