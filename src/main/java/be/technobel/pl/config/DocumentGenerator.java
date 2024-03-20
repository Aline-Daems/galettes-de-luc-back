package be.technobel.pl.config;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;

import org.springframework.stereotype.Service;



@Service
public class DocumentGenerator {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    public byte[] htmlToPdf(String html) {

        try {
            PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);

            Document document = new Document(pdfDocument);


            DefaultFontProvider defaultFontProvider = new DefaultFontProvider();

            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(defaultFontProvider);
            HtmlConverter.convertToPdf(html, pdfDocument, converterProperties);

            document.close();
            pdfDocument.close();


        } catch (RuntimeException e) {
            throw new RuntimeException("Erreur lors de la conversion HTML en PDF", e);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
