package ro.ubbcluj.econ.chargingstationlocator.locator.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(schema = "chargingstationsdatabase", name = "charging_stations")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChargingStationData implements Serializable {

    @Id
    @Column
    private int id;

    @Column(name = "Name")
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

    @Column(name = "plug_status")
    private String plugStatus;

    @Column(name = "plug_type")
    private String plugType;

    @Column(name = "phase")
    private String phase;

    @Column(name = "high_power")
    private boolean highPower;

    @Column(name = "power")
    private int power;

    @Column(name = "current")
    private int current;

    @Column(name = "voltage")
    private int voltage;

    @Column(name = "access")
    private String access;

    @Column(name = "service")
    private String service;

    @Column(name = "status")
    private String status;

    @Column(name = "reservable")
    private boolean reservable;
}
