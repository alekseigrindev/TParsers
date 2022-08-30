package parsers.currency;

import domain.Currency;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDomParserByTheUrl {
    public static List<Currency> parseSixXml(String fileUrl) {
        List<Currency> sixCurrencyList = new ArrayList<>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(fileUrl);
            document.getDocumentElement().normalize();

            NodeList isoNodeList = document.getElementsByTagName("CcyNtry");

            for (int i = 0; i < isoNodeList.getLength(); i++) {
                Node node = isoNodeList.item(i);
                Element element = (Element) node;
                Currency sixCurrency = new Currency();

                sixCurrency.setCountryName(element.getElementsByTagName("CtryNm").item(0).getTextContent());
                if (!element.getElementsByTagName("CcyNm").item(0).getTextContent().isEmpty()) {
                    sixCurrency.setCurrencyName(element.getElementsByTagName("CcyNm").item(0).getTextContent());
                }
                if ((element.getElementsByTagName("CcyNm").item(0).getTextContent()).equals("No universal currency")) {
                    sixCurrency.setCurrencyName("No universal currency");
                    sixCurrencyList.add(sixCurrency);
                    continue;
                } else {
                    sixCurrency.setCurrencyName(element.getElementsByTagName("CcyNm").item(0).getTextContent());
                }
                if (!element.getElementsByTagName("Ccy").item(0).getTextContent().isEmpty()) {
                    sixCurrency.setCca3(element.getElementsByTagName("Ccy").item(0).getTextContent());
                }
                if (!element.getElementsByTagName("CcyNbr").item(0).getTextContent().isEmpty()) {
                    sixCurrency.setCcn3(element.getElementsByTagName("CcyNbr").item(0).getTextContent());
                }
                if (!element.getElementsByTagName("CcyMnrUnts").item(0).getTextContent().isEmpty()) {
                    sixCurrency.setMinorUnit(element.getElementsByTagName("CcyMnrUnts").item(0).getTextContent());
                }

                sixCurrencyList.add(sixCurrency);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.toString());
        }
        return sixCurrencyList;
    }
}