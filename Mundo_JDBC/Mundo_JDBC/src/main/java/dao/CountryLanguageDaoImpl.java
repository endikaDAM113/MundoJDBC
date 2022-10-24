package dao;

import idao.ICountryLanguageDao;
import javafx.scene.chart.PieChart;
import models.CountryLanguage;
import utils.DatabaseConecction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class CountryLanguageDaoImpl implements ICountryLanguageDao {
    public List<CountryLanguage> getAllLanguages() {
        String query = "select * from countrylanguage";
        PreparedStatement ps;
        List<CountryLanguage> languages = new ArrayList<>();
        Connection con;
        try {
            con = DatabaseConecction.getConnection();
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CountryLanguage cl = new CountryLanguage();
                cl.setCountryCode(rs.getString("CountryCode"));
                cl.setLanguage(rs.getString("Language"));
                cl.setIsOfficial(rs.getInt(2));
                cl.setPercentage(rs.getFloat("Percentage"));
                languages.add(cl);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return languages;
    }

    public Set<CountryLanguage> listaIdiomas(String codigoPais) {
        String query = "select * from countrylanguage where CountryCode = ?";
        PreparedStatement ps;
        Set<CountryLanguage> languages = new HashSet<>();
        Connection con;
        try {
            con = DatabaseConecction.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, codigoPais);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CountryLanguage cl = new CountryLanguage();
                cl.setCountryCode(rs.getString("CountryCode"));
                cl.setLanguage(rs.getString("Language"));
                cl.setIsOfficial(rs.getInt(2));
                cl.setPercentage(rs.getFloat("Percentage"));
                languages.add(cl);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return languages;
    }
}
