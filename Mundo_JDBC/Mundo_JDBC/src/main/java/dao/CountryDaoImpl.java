package dao;

import idao.ICountryDao;
import javafx.scene.chart.PieChart;
import models.City;
import models.Country;
import utils.DatabaseConecction;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CountryDaoImpl implements ICountryDao {
    public Set<Country> listaPaises() {
        Connection co =null;
        Statement stm= null;
        ResultSet rs=null;

        String sql="SELECT * FROM country ORDER BY ID";

        Set<Country> listaCountry=  new HashSet<Country>();

        try {
            co= DatabaseConecction.getConnection();
            stm=co.createStatement();
            rs=stm.executeQuery(sql);
            while (rs.next()) {
                Country c=new Country();
                c.setCode(rs.getString(1));
                c.setName(rs.getString(2));
                c.setContinent(rs.getString(3));
                c.setRegion(rs.getString(4));
                c.setSurfaceArea(rs.getFloat(5));
                c.setIndepYear(rs.getInt(6));
                c.setPopulation(rs.getInt(7));
                c.setLifeExpectancy(rs.getFloat(8));
                c.setGNP(rs.getFloat(9));
                c.setGNPOld(rs.getFloat(10));
                c.setLocalName(rs.getString(11));
                c.setGovernmentForm(rs.getString(12));
                c.setHeadOfState(rs.getString(13));
                c.setCapital(rs.getInt(14));
                c.setCode2(rs.getString(15));
                listaCountry.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método obtener");
            e.printStackTrace();
        }

        return listaCountry;
    }

    public Boolean existePais(String codigoPais) {
        Connection co =null;
        PreparedStatement stm= null;


        String sql="SELECT * FROM country where Code=?";
        boolean res=false;

        try {
            co= DatabaseConecction.getConnection();
            stm=co.prepareStatement(sql);
            stm.setString(1,codigoPais);
            ResultSet rs=stm.executeQuery();
            res= rs.next();

            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método obtener");
            e.printStackTrace();
        }

        return res;
    }

    public Country getCountry(String codigoPais) {
        String query
                = "select * from country where code= ?";
        PreparedStatement ps
                = null;
        boolean check = false;
        Country country = new Country();
        Connection con=null;
        try {
            con= DatabaseConecction.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, codigoPais);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                check = true;
                country.setCode(rs.getString("code"));
                country.setName(rs.getString("name"));
                country.setContinent(rs.getString("continent"));
                country.setRegion(rs.getString("region"));
                country.setSurfaceArea(rs.getInt("surfacearea"));
                country.setIndepYear(rs.getInt("indepyear"));
                country.setPopulation(rs.getInt("population"));
                country.setLifeExpectancy(rs.getInt("lifeexpectancy"));
                country.setGNP(rs.getInt("gnp"));
                country.setGNPOld(rs.getInt("gnpold"));
                country.setLocalName(rs.getString("localname"));
                country.setGovernmentForm(rs.getString("governmentform"));
                country.setHeadOfState(rs.getString("headofstate"));
                country.setCapital(rs.getInt("capital"));
                country.setCode2(rs.getString("code2"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (check == true) {
            return country;
        } else
            return null;
    }


    public Country getPaisDeCiudad(Integer codigoCiudad) {

        String query = "select country.* from city join country on city.CountryCode=country.Code where city.ID = ?";
        Country country = new Country();
        PreparedStatement ps;
        Connection con;
        try {
            con = DatabaseConecction.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, codigoCiudad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                country.setCode(rs.getString("Code"));
                country.setName(rs.getString("Name"));
                country.setContinent(rs.getString("Continent"));
                country.setRegion(rs.getString("Region"));
                country.setSurfaceArea(rs.getFloat("SurfaceArea"));
                country.setIndepYear(rs.getInt("IndepYear"));
                country.setPopulation(rs.getInt("Population"));
                country.setLifeExpectancy(rs.getFloat("LifeExpectancy"));
                country.setGNP(rs.getFloat("GNP"));
                country.setGNPOld(rs.getFloat("GNPOld"));
                country.setLocalName(rs.getString("LocalName"));
                country.setGovernmentForm(rs.getString("GovernmentForm"));
                country.setHeadOfState(rs.getString("HeadOfState"));
                country.setCapital(rs.getInt("Capital"));
                country.setCode2(rs.getString("Code2"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }


    public Boolean aniadirPais(Country nuevaCiudad) throws SQLException {
        CountryDaoImpl countryDao = new CountryDaoImpl();
        String query = "insert into country values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            Connection con = DatabaseConecction.getConnection();
            if (countryDao.existePais(nuevaCiudad.getCode())) {
                System.out.println("Ya existe");
            } else {
                ps = con.prepareStatement(query);
                ps.setString(1, nuevaCiudad.getCode());
                ps.setString(2,nuevaCiudad.getName());
                ps.setString(3,nuevaCiudad.getContinent());
                ps.setString(4,nuevaCiudad.getRegion());
                ps.setFloat(5,nuevaCiudad.getSurfaceArea());
                ps.setInt(6,nuevaCiudad.getIndepYear());
                ps.setInt(7,nuevaCiudad.getPopulation());
                ps.setFloat(8,nuevaCiudad.getLifeExpectancy());
                ps.setFloat(9,nuevaCiudad.getGNP());
                ps.setFloat(10, nuevaCiudad.getGNPOld());
                ps.setString(11,nuevaCiudad.getLocalName());
                ps.setString(12,nuevaCiudad.getGovernmentForm());
                ps.setString(13, nuevaCiudad.getHeadOfState());
                ps.setInt(14,nuevaCiudad.getCapital());
                ps.setString(15,nuevaCiudad.getCode2());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return true;
    }
}
