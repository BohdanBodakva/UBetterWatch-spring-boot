package ua.lviv.iot.ubetterwatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.ubetterwatch.entity.BraceletDataEntity;
import ua.lviv.iot.ubetterwatch.entity.BraceletEntity;

import java.util.Optional;

@Repository
public interface BraceletDataRepository extends JpaRepository<BraceletDataEntity, Long> {

    @Query("select d from BraceletDataEntity d join BraceletEntity b on b.braceletData.id=d.id join UserEntity u on b.user.id=u.id join SupervisorEntity s on s.username=u.supervisor.username where u.id=:userId and s.username=:supervisorUsername and b.serialNumber=:braceletId")
    Optional<BraceletDataEntity> getBraceletDataByBraceletIdByUserIdAndSupervisorUsername(@Param("braceletId") String braceletId, @Param("userId") Long userId, @Param("supervisorUsername") String supervisorUsername);

}
