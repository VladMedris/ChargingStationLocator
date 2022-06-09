package ro.ubbcluj.econ.chargingstationlocator.locator.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "availability_status")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class AvailabilityData implements Serializable {

    @Id
    @Column
    private int id;

    @Column(name = "status")
    private String status;

    @Column(name = "reservable")
    private boolean reservable;

    @EqualsAndHashCode.Exclude
    @OneToMany
    @JoinColumn(name = "availability_status_id")
    private Set<ChargingStationData> chargingStationData;
}
