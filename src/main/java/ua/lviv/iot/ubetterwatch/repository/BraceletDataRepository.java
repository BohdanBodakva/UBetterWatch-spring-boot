package ua.lviv.iot.ubetterwatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.ubetterwatch.entity.BraceletDataEntity;

@Repository
public interface BraceletDataRepository extends JpaRepository<BraceletDataEntity, Long> {
}
