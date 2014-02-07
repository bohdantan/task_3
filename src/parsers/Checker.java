package parsers;

import java.io.File;

import org.w3c.dom.Document;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 * This class checks if xml file corresponds to xsd (xml schema)
 */
public class Checker {
    public static boolean check(String xmlName, String xsdName) {
        try {
            // Преобразование XML документа в дерево DOM.
            DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = parser.parse(new File(xmlName));

            // Создание объекта SchemaFactory спосбного работать с WXS схемами.
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // Загрузка WXS схемы, представленной элементом Schema.
            Source schemaFile = new StreamSource(new File(xsdName));
            Schema schema = factory.newSchema(schemaFile);

            // Создание объекта Validator, используемого для проверки правильности документа.
            Validator validator = schema.newValidator();

            // Поверка правильности дерева DOM.
            validator.validate(new DOMSource(document));

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
