package parsers.currency;

import domain.Currency;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CurrencySqlScriptBuilder {
    private static BufferedWriter bufferedWriter;

    public static void build(List<Currency> list, File targetFile) throws IOException {
        bufferedWriter = new BufferedWriter(new FileWriter(targetFile));
        StringBuilder stringBuilder = new StringBuilder("INSERT INTO currencies (country_name, currency_name, cca3, ccn3, minor_unit, currency_is_on, currency_state)\nVALUES\n");
        if(list.size() > 0) bufferedWriter.write(stringBuilder.toString());

        for(int i = 0; i < list.size(); i++) {
            Currency currency = list.get(i);
            String countryName = getValue(currency.getCountryName());
            String currencyName = getValue(currency.getCurrencyName());
            String cca3 = getValue(currency.getCca3());
            String ccn3 = getValue(currency.getCcn3());
            String minorUnit = getValue(currency.getMinorUnit());

            String sqlInsert = String.format("(%s,%s,%s,%s,%s,FALSE,'IN_USE')",countryName,currencyName,cca3,ccn3,minorUnit);

            stringBuilder = new StringBuilder(sqlInsert);
            if(i < list.size() - 1) stringBuilder.append(",\n");
            bufferedWriter.write(stringBuilder.toString());
        }

        bufferedWriter.close();
    }

    private static String getValue(String value) {
        return value != null && value.length() > 0
                ? "'" + value + "'"
                : "DEFAULT";
    }
}
