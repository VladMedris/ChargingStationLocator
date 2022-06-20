package ro.ubbcluj.econ.chargingstationlocator.locator.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "access")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessData implements Serializable {

    @Id
    @Column
    private int id;

    @Column(name = "access")
    private String access;

    @Column(name = "service")
    private String service;

    @EqualsAndHashCode.Exclude
    @OneToMany
    @JoinColumn(name = "access_status_id")
    private Set<ChargingStationData> chargingStationData;
}
