package ua.lviv.iot.ubetterwatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.ubetterwatch.entity.BraceletEntity;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Repository
public interface BraceletRepository extends JpaRepository<BraceletEntity, String> {
    @Query("select b from BraceletEntity b join UserEntity u on b.user.id=u.id join SupervisorEntity s on s.username=u.supervisor.username where u.id=:userId and s.username=:supervisorUsername")
    List<BraceletEntity> findUserBraceletsByUserIdAndSupervisorUsername(@Param("userId") Long userId, @Param("supervisorUsername") String supervisorUsername);

    @Query("select b from BraceletEntity b join UserEntity u on b.user.id=u.id join SupervisorEntity s on s.username=u.supervisor.username where u.id=:userId and s.username=:supervisorUsername and b.serialNumber=:braceletId")
    Optional<BraceletEntity> getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(@Param("braceletId") String braceletId, @Param("userId") Long userId, @Param("supervisorUsername") String supervisorUsername);

}


