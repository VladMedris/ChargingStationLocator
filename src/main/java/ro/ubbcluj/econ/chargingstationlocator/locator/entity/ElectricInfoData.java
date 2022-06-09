package ro.ubbcluj.econ.chargingstationlocator.locator.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "electric_info")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class ElectricInfoData implements Serializable {

    @Id
    @Column
    private int id;

    @Column(name = "power")
    private int power;

    @Column(name = "current")
    private int current;

    @Column(name = "voltage")
    private int voltage;

    @EqualsAndHashCode.Exclude
    @OneToMany
    @JoinColumn(name = "electric_info_id")
    private Set<PlugData> plugData;

}
