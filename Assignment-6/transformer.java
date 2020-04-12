package xml.assignment;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;

import java.io.File;


public class transformer {
    public static void main(String[] args) {
        String XSLFILE = "C:\\Users\\liuzh\\IdeaProjects\\cs5200_sp20_Liuzc_xml\\src\\xml\\assignment\\site2website.xsl";
        String INFILE = "C:\\Users\\liuzh\\IdeaProjects\\cs5200_sp20_Liuzc_xml\\src\\xml\\assignment\\site.xml";
        String OUTFILE = "C:\\Users\\liuzh\\IdeaProjects\\cs5200_sp20_Liuzc_xml\\src\\xml\\assignment\\website.xml";

        StreamSource xslcode =
                new StreamSource(new File(XSLFILE));
        StreamSource input =
                new StreamSource(new File(INFILE));
        StreamResult output =
                new StreamResult(new File(OUTFILE));
        try {
            TransformerFactory tf =
                    TransformerFactory
                            .newInstance();

            Transformer trans =
                    tf.newTransformer(xslcode);

            trans.transform(input, output);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
