package ua.lviv.iot.ubetterwatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.ubetterwatch.entity.BraceletStorageEntity;

@Repository
public interface BraceletStorageRepository extends JpaRepository<BraceletStorageEntity, Long> {
}
