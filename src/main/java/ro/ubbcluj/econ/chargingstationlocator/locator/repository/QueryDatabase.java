package ro.ubbcluj.econ.chargingstationlocator.locator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.ChargingStationData;

import java.util.List;

@Repository
public interface QueryDatabase extends JpaRepository<ChargingStationData, Integer> {

    @Procedure
    List<ChargingStationData> findStationById(String id);

    @Procedure
    List<ChargingStationData> findStationByGeolocation(float latitude, float longitude, int distance);
}
