import domain.Bank;
import domain.Currency;
import parsers.bank.BankParserOfTheInnerJson;
import parsers.bank.BankSqlScriptBuilder;
import parsers.currency.CurrencyDomParserByThePath;
import parsers.currency.CurrencyDomParserByTheUrl;
import parsers.currency.CurrencyDomParserOfTheInnerXml;
import parsers.currency.CurrencySqlScriptBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose the way to parse currencies: "
                + "\n" + "1 - Parse the XML file included in this project (six.xml)"
                + "\n" + "2 - Parse a local XML Currency file on this computer"
                + "\n" + "3 - Parse XML List One from SIX-GROUP.COM"
                + "\n" + "4 - Parse JSON file included in this project (banks.json)"
                + "\n" + "5 - Parse a local XML Currency file on this computer"
                + "\n" + "6 - To finish the program");

        String str = scanner.nextLine();
        while (str != null) {
            switch (str) {
                case "1":
                    innerSixCurrencyFileParser();
                    break;
                case "2":
                    localSixCurrencyParser();
                    break;
                case "3":
                    urlSixCurrencyParser();
                    break;
                case "4":
                    innerJsonBanksParser();
                    break;
                case "5":
                    localJsonBanksParser();
                    break;
                case "6":
                    str = "fuckOff";
                    break;
                default:
                    System.out.println("There is no same case. Try again.");
                    break;
            }

            if(str.equals("fuckOff")) {
                scanner.close();
                System.out.println("Program is finished");
                str = null;
            } else {
                System.out.println("\n-*-*-*-*-*-\n" + "Choose another way: ");
                str = scanner.nextLine();
            }
        }
        scanner.close();

    }

    private static void innerSixCurrencyFileParser() {
        File sourceXmlFile = new File("six.xml");
        File targetSqlFile = new File("currenciesInputSqlQuery.sql");
        List<Currency> currencyList = CurrencyDomParserOfTheInnerXml.parseSixXml(sourceXmlFile);;
        try {
            CurrencySqlScriptBuilder.build(currencyList, targetSqlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void localSixCurrencyParser() {
        Scanner filePathScanner = new Scanner(System.in);
        System.out.println("Write path to the file: ");
        File file = new File(filePathScanner.nextLine());
        List<Currency> currencyList = CurrencyDomParserByThePath.parseSixXml(file);
        System.out.println("Parsing complete. The List contains " + currencyList.size()+ " values");
    }

    private static void urlSixCurrencyParser() {
        Scanner urlScanner = new Scanner(System.in);
        System.out.println("Write the file's URL: ");
        List<Currency> urlSixCurrencyList = CurrencyDomParserByTheUrl.parseSixXml(urlScanner.nextLine());
        System.out.println("Parsing complete. The List contains " + urlSixCurrencyList.size()+ " values");
    }

    private static void innerJsonBanksParser() {
        File sourceFile = new File("banks.json");
        File banksInputSqlQuery = new File("banksInputSqlQuery.sql");
        try {
            List<Bank> list = BankParserOfTheInnerJson.getBanksList(sourceFile);
            BankSqlScriptBuilder.buildSql(list, banksInputSqlQuery);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void localJsonBanksParser() {

    }
 }
