package ua.lviv.iot.ubetterwatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.ubetterwatch.entity.CoordinatesEntity;
import ua.lviv.iot.ubetterwatch.entity.VoiceMessageEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoordinatesRepository extends JpaRepository<CoordinatesEntity, Long> {

    @Query("select c from CoordinatesEntity c join BraceletEntity b on b.serialNumber=c.braceletEntity.serialNumber join UserEntity u on b.user.id=u.id join SupervisorEntity s on s.username=u.supervisor.username where u.id=:userId and s.username=:supervisorUsername and b.serialNumber=:braceletId")
    List<CoordinatesEntity> getCoordinatesByBraceletIdByUserIdAndSupervisorUsername(@Param("braceletId") String braceletId, @Param("userId") Long userId, @Param("supervisorUsername") String supervisorUsername);

    @Query("select c from CoordinatesEntity c join BraceletEntity b on b.serialNumber=c.braceletEntity.serialNumber join UserEntity u on b.user.id=u.id join SupervisorEntity s on s.username=u.supervisor.username where u.id=:userId and s.username=:supervisorUsername and b.serialNumber=:braceletId and c.id=:coordinatesId")
    Optional<CoordinatesEntity> getCoordinatesByCoordinatesIdBraceletIdByUserIdAndSupervisorUsername(@Param("braceletId") String braceletId, @Param("userId") Long userId, @Param("supervisorUsername") String supervisorUsername, @Param("coordinatesId") Long coordinatesId);

}
