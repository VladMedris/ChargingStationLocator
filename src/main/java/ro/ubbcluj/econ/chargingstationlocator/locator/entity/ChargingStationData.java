package ro.ubbcluj.econ.chargingstationlocator.locator.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "charging_stations")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChargingStationData implements Serializable {

    @Id
    @Column
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "station_id")
    private String stationId;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @Column(name = "hours")
    private String hours;

    @Column(name = "contact")
    private String contact;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "longitude")
    private float longitude;

    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "charging_stations_has_plugs",
            joinColumns =@JoinColumn(name = "charging_stations_id"),
            inverseJoinColumns = @JoinColumn(name = "plugs_id"))
    private Set<PlugData> plugData;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "access_status_id")
    private AccessData accessData;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "availability_status_id")
    private AvailabilityData availabilityData;

}
