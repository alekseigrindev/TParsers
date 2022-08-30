package domain;

//CcyNtry
public class Currency {

    //Entity
    //CtryNm
    private String countryName;

    //Currency
    //CcyNm
    private String currencyName;

    //Alphabetic code
    //Ccy
    private String cca3;

    //Numeric code
    //CcyNbr
    private String ccn3;

    //Minor unit
    //CcyMnrUnts
    private String minorUnit;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCca3() {
        return cca3;
    }

    public void setCca3(String cca3) {
        this.cca3 = cca3;
    }

    public String getCcn3() {
        return ccn3;
    }

    public void setCcn3(String ccn3) {
        this.ccn3 = ccn3;
    }

    public String getMinorUnit() {
        return minorUnit;
    }

    public void setMinorUnit(String minorUnit) {
        this.minorUnit = minorUnit;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "countryName='" + countryName + '\'' +
                ", currencyName='" + currencyName + '\'' +
                ", cca3='" + cca3 + '\'' +
                ", ccn3='" + ccn3 + '\'' +
                ", minorUnit='" + minorUnit + '\'' +
                '}';
    }
}

