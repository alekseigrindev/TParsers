package parsers.bank;

import domain.Bank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BankSqlScriptBuilder {

    private static BufferedWriter bufferedWriter;

    public static void buildSql(List<Bank> bankList, File banksInputSqlQuery) throws IOException {
        if(bankList.size() > 0) {
            bufferedWriter = new BufferedWriter(new FileWriter(banksInputSqlQuery));
            bufferedWriter.write("INSERT INTO banks (bank_code, bank_name, regular_bank_name, bank_address, bank_province_name, bank_swift, bank_is_on, bank_state) \nVALUES\n");

            for (int i = 0; i < bankList.size(); i++) {
                Bank bank = bankList.get(i);

                String bankCode = getValue(bank.getCode());
                String bankName = getValue(bank.getName());
                String bankAddress = getValue(bank.getAddress());
                String bankProvince = getValue(bank.getProvinceName());

                String resultString = String.format("(%s,%s,%s,%s,%s,DEFAULT,FALSE,'IN_USE')", bankCode, bankName, bankName, bankAddress, bankProvince);

                StringBuilder stringBuilder = new StringBuilder(resultString);
                if (i < bankList.size() - 1) stringBuilder.append(",\n");
                bufferedWriter.write(stringBuilder.toString());
            }

            bufferedWriter.close();
        }
    }

    static String getValue(String value) {
        return value != null && value.length() > 0
                ? "'" + value + "'"
                : "DEFAULT";
    }
}