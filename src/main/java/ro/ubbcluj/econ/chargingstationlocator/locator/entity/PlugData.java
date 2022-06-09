package ro.ubbcluj.econ.chargingstationlocator.locator.entity;


import lombok.*;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plugs")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class PlugData implements Serializable {

    @Id
    @Column
    private int id;

    @Column(name = "plug_type")
    private String plugType;

    @Column(name = "phase")
    private String phase;

    @Column(name = "high_power")
    private boolean highPower;

    @Column(name = "plug_status")
    private String plugStatus;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "plugData")
    private Set<ChargingStationData> chargingStationData;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "electric_info_id")
    private ElectricInfoData electricInfoData;
}
