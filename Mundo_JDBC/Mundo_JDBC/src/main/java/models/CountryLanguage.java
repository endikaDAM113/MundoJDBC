package models;

public class CountryLanguage {
    private String CountryCode;
    private String Language;
    private int IsOfficial;
    private float Percentage;

    public CountryLanguage() {
    }

    public CountryLanguage(String countryCode, String language, int isOfficial, float percentage) {
        CountryCode = countryCode;
        Language = language;
        IsOfficial = isOfficial;
        Percentage = percentage;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public int getIsOfficial() {
        return IsOfficial;
    }

    public void setIsOfficial(int isOfficial) {
        IsOfficial = isOfficial;
    }

    public float getPercentage() {
        return Percentage;
    }

    public void setPercentage(float percentage) {
        Percentage = percentage;
    }

    @Override
    public String toString() {
        return "CountryLanguage{" +
                "CountryCode='" + CountryCode + '\'' +
                ", Language='" + Language + '\'' +
                ", IsOfficial=" + IsOfficial +
                ", Percentage=" + Percentage +
                '}';
    }
}
