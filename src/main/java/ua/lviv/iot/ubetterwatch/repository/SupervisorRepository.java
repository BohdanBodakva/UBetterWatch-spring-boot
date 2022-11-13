package ua.lviv.iot.ubetterwatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;

import java.util.Optional;

@Repository
public interface SupervisorRepository extends JpaRepository<SupervisorEntity, Long> {
    SupervisorEntity findSupervisorEntityByUsername(String username);
}
