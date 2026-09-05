package dev.lommebok.lommebok.util.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;
import java.awt.Color;

public class PdfReportWriter implements AutoCloseable {
    private static final float MARGIN = 48;
    private static final float FONT_SIZE = 11;
    private static final float LINE_HEIGHT = 16;
    private final PDDocument document;
    private final PDType1Font regular = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
    private final PDType1Font bold = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
    private PDPageContentStream content;
    private float y;

    public PdfReportWriter(PDDocument document) throws IOException {
        this.document = document;
        newPage();
    }

    private void newPage() throws IOException {
        if (content != null) content.close();
        var page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        content = new PDPageContentStream(document, page);
        draw("LOMMEBOK | Relatório de despesas", bold, 17, page.getMediaBox().getHeight() - MARGIN);
        draw("Página " + document.getNumberOfPages(), regular, 9, 28);
        y = page.getMediaBox().getHeight() - MARGIN - 38;
    }

    public void line(String value, boolean emphasized) throws IOException {
        var font = emphasized ? bold : regular;
        String text = printable(value == null ? "-" : value, font);
        float availableWidth = PDRectangle.A4.getWidth() - 2 * MARGIN;
        while (!text.isEmpty()) {
            int end = 0;
            float width = 0;
            while (end < text.length()) {
                float nextWidth = font.getStringWidth(text.substring(end, end + 1)) / 1000 * FONT_SIZE;
                if (width + nextWidth > availableWidth) break;
                width += nextWidth;
                end++;
            }
            if (end < text.length()) {
                int space = text.lastIndexOf(' ', end);
                if (space > 0) end = space;
            }
            if (y < MARGIN + LINE_HEIGHT) newPage();
            draw(text.substring(0, end), font, FONT_SIZE, y);
            y -= LINE_HEIGHT;
            text = text.substring(end).stripLeading();
        }
    }

    // Standard PDF fonts support Portuguese. Replace unsupported glyphs (e.g. emoji).
    private String printable(String value, PDType1Font font) throws IOException {
        var result = new StringBuilder();
        for (int codePoint : value.codePoints().toArray()) {
            String character = Character.isWhitespace(codePoint) || Character.isISOControl(codePoint)
                    ? " " : new String(Character.toChars(codePoint));
            try {
                font.encode(character);
                result.append(character);
            } catch (IllegalArgumentException exception) {
                result.append('?');
            }
        }
        return result.toString();
    }

    public void ensureSpace(int lines) throws IOException {
        if (y - lines * LINE_HEIGHT < MARGIN) newPage();
    }

    public void space() { y -= 10; }

    public void tableHeader() throws IOException {
        ensureSpace(2);
        float top = y + 8;
        fillRect(MARGIN, top - 22, PDRectangle.A4.getWidth() - 2 * MARGIN, 26, new Color(241, 254, 200));
        textAt("Despesa", MARGIN + 8, y, bold, 10, Color.BLACK);
        textAt("Data", MARGIN + 205, y, bold, 10, Color.BLACK);
        textAt("Categoria", MARGIN + 300, y, bold, 10, Color.BLACK);
        textAt("Valor", MARGIN + 470, y, bold, 10, Color.BLACK);
        y -= 30;
    }

    public void expenseRow(String title, String description, String date, String category, String amount) throws IOException {
        var titleLines = wrap(title, 28);
        var descriptionLines = wrap(description, 36);
        int rowLines = Math.max(1, titleLines.size()) + descriptionLines.size();
        ensureSpace(rowLines + 1);
        float lineY = y;
        for (String line : titleLines) {
            textAt(line, MARGIN + 8, lineY, bold, 9, Color.BLACK);
            lineY -= 12;
        }
        for (String line : descriptionLines) {
            textAt(line, MARGIN + 8, lineY, regular, 8, Color.DARK_GRAY);
            lineY -= 12;
        }
        textAt(date, MARGIN + 205, y, regular, 9, Color.BLACK);
        textAt(shorten(category, 20), MARGIN + 300, y, regular, 9, Color.BLACK);
        textAtRight(amount, MARGIN + 500, y, regular, 9, Color.BLACK);
        y -= rowLines * 12 + 12;
    }

    public void totalBox(String value) throws IOException {
        ensureSpace(2);
        fillRect(MARGIN + 350, y - 20, 150, 30, new Color(241, 254, 200));
        textAtRight(value, MARGIN + 490, y - 2, bold, 11, Color.BLACK);
        y -= 40;
    }

    private void fillRect(float x, float y, float width, float height, Color color) throws IOException {
        content.setNonStrokingColor(color);
        content.addRect(x, y, width, height);
        content.fill();
    }

    private void textAt(String text, float x, float y, PDType1Font font, float size, Color color) throws IOException {
        content.beginText();
        content.setNonStrokingColor(color);
        content.setFont(font, size);
        content.newLineAtOffset(x, y);
        content.showText(printable(text, font));
        content.endText();
    }

    private void textAtRight(String text, float rightX, float y, PDType1Font font, float size, Color color) throws IOException {
        float width = font.getStringWidth(printable(text, font)) / 1000 * size;
        textAt(text, rightX - width, y, font, size, color);
    }

    private java.util.List<String> wrap(String text, int maxChars) {
        if (text == null || text.isBlank()) return java.util.List.of();
        var lines = new java.util.ArrayList<String>();
        String remaining = text.trim();
        while (!remaining.isEmpty()) {
            int end = Math.min(maxChars, remaining.length());
            if (end < remaining.length()) {
                int space = remaining.lastIndexOf(' ', end);
                if (space > 0) end = space;
            }
            lines.add(shorten(remaining.substring(0, end).trim(), maxChars));
            remaining = remaining.substring(end).stripLeading();
        }
        return lines;
    }

    private String shorten(String text, int max) {
        if (text == null) return "-";
        return text.length() <= max ? text : text.substring(0, max - 3) + "...";
    }

    private void draw(String text, PDType1Font font, float size, float position) throws IOException {
        content.beginText();
        content.setFont(font, size);
        content.newLineAtOffset(MARGIN, position);
        content.showText(text);
        content.endText();
    }

    @Override
    public void close() throws IOException {
        if (content != null) content.close();
    }
}
