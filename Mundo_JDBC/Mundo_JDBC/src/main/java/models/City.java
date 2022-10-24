package models;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class City {
    private int ID;
    private String Name;
    private String CountryCode;
    private String District;
    private int Population;

}
