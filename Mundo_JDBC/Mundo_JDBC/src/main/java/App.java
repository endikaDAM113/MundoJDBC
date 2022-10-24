import dao.CityDaoImpl;
import dao.CountryDaoImpl;
import dao.CountryLanguageDaoImpl;
import models.City;
import models.Country;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {


        //Country
        CountryDaoImpl countryDao = new CountryDaoImpl();
        //System.out.println(countryDao.listaPaises());
        //System.out.println(countryDao.existePais("ABW"));
        //System.out.println(countryDao.getCountry("ABW"));
        //System.out.println(countryDao.getPaisDeCiudad(46));
        //System.out.println(countryDao.aniadirPais(new Country(("CCC","España","Europa","Este","2","3489","600","89","5","6","España","nose","nose","9","D")));

        //City
        CityDaoImpl cityDao = new CityDaoImpl();
        //System.out.println(cityDao.listaCiudades());
        //System.out.println(cityDao.existeCiudad(1));
        //System.out.println(cityDao.getCity(12));
        //System.out.println(cityDao.listaCiudades("AFG"));
        //System.out.println(cityDao.estaCiudadEnPais(1000,"ESP"));
        //System.out.println(cityDao.cambiarNombreCiudad(10,"Bilbao"));

        //Languages
        CountryLanguageDaoImpl countryLanguageDao = new CountryLanguageDaoImpl();
        //System.out.println(countryLanguageDao.getAllLanguages());
        //System.out.println(countryLanguageDao.listaIdiomas("ESP"));




    }
}
