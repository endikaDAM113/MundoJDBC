package dao;

import idao.ICityDao;
import javafx.scene.chart.PieChart;
import models.City;
import utils.DatabaseConecction;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CityDaoImpl implements ICityDao {

    public Set<City> listaCiudades() {
        Connection co =null;
        Statement stm= null;
        ResultSet rs=null;

        String sql="SELECT * FROM city ORDER BY ID";

        Set<City> listaCiudad=  new HashSet<City>();

        try {
            co= DatabaseConecction.getConnection();
            stm=co.createStatement();
            rs=stm.executeQuery(sql);
            while (rs.next()) {
                City c=new City();
                c.setID(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setCountryCode(rs.getString(3));
                c.setDistrict(rs.getString(4));
                c.setPopulation(rs.getInt(5));
                listaCiudad.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método obtener");
            e.printStackTrace();
        }

        return listaCiudad;
    }

    public Boolean existeCiudad(Integer codigoCiudad) {
        Connection co =null;
        PreparedStatement stm= null;


        String sql="SELECT * FROM city where ID=?";
        boolean res=false;

        try {
            co= DatabaseConecction.getConnection();
            stm=co.prepareStatement(sql);
            stm.setInt(1,codigoCiudad);
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

    public City getCity(Integer codigoCiudad) {
        String query
                = "select * from city where ID= ?";
        PreparedStatement ps
                = null;
        boolean check = false;
        City city = new City();
        Connection con=null;
        try {
            con= DatabaseConecction.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, codigoCiudad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                check = true;
                city.setID(rs.getInt("id"));
                city.setName(rs.getString("name"));
                city.setCountryCode(rs.getString("countrycode"));
                city.setDistrict(rs.getString("district"));
                city.setPopulation(rs.getInt("population"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (check == true) {
            return city;
        } else
            return null;
    }

    public Set<City> listaCiudades(String nombrePais) {
        Connection co =null;
        PreparedStatement stm= null;
        ResultSet rs=null;

        String sql="SELECT * FROM city where CountryCode=?";
        Set<City> citylista=  new HashSet<City>();

        try {
            co= DatabaseConecction.getConnection();
            stm=co.prepareStatement(sql);
            stm.setString(1, nombrePais);
            rs=stm.executeQuery();
            while (rs.next()) {
                City c=new City();
                c.setID(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setCountryCode(rs.getString(3));
                c.setDistrict(rs.getString(4));
                c.setPopulation(rs.getInt(5));
                citylista.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método obtener");
            e.printStackTrace();
        }

        return citylista;

    }

    public Boolean estaCiudadEnPais(Integer codigoCiudad, String codigoPais) {
        CityDaoImpl cdi = new CityDaoImpl();
        if(cdi.getCity(codigoCiudad) != null && cdi.getCity(codigoCiudad).getCountryCode().equals(codigoPais)){
            return true;
        }
        return false;
    }

    public Boolean cambiarNombreCiudad(Integer codigoCiudad, String nuevoNombre) {
        if (existeCiudad(codigoCiudad)) {
            String query
                    = "UPDATE city SET Name=? WHERE ID=?";
            PreparedStatement ps;
            int rs = 0;
            try {
                Connection con = DatabaseConecction.getConnection();
                ps = con.prepareStatement(query);
                ps.setString(1, nuevoNombre);
                ps.setInt(2, codigoCiudad);
                rs = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (rs > 0) {
                return true;
            }
        }
        return false;
    }

    public void aniadirCiudad(City nuevaCiudad) {
        String query = "insert into city (Name,CountryCode,District,Population) values(?,?,?,?)";
        PreparedStatement ps;
        CountryDaoImpl cd = new CountryDaoImpl();

        try {
            Connection con = DatabaseConecction.getConnection();
            ps = con.prepareStatement(query);
            con.setAutoCommit(false);
            if (con != null){
                if(cd.existePais(nuevaCiudad.getCountryCode())){
                    ps.setString(1, nuevaCiudad.getName());
                    ps.setString(2, nuevaCiudad.getCountryCode());
                    ps.setString(3, nuevaCiudad.getDistrict());
                    ps.setInt(4, nuevaCiudad.getPopulation());
                    ps.executeUpdate();
                    con.commit();
                }else {
                    con.rollback();
                    System.out.println("No existe");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
