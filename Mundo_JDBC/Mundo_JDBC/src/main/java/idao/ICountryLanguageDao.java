package idao;

import models.CountryLanguage;

import java.util.List;
import java.util.Set;

public interface ICountryLanguageDao {
    public List<CountryLanguage> getAllLanguages();
    public Set<CountryLanguage> listaIdiomas(String codigoPais);
}
