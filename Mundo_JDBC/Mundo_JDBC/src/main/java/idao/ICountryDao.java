package idao;

import models.Country;

import java.sql.SQLException;
import java.util.Set;

public interface ICountryDao {
    public Set<Country> listaPaises();
    public Boolean existePais(String codigoPais);
    public Country getCountry(String codigoPais);
    public Country getPaisDeCiudad(Integer codigoCiudad);
    public Boolean aniadirPais(Country nuevaCiudad) throws SQLException;

}
