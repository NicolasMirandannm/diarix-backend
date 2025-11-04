package nicksolutions.contracts.domain.payment.usecase;

import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.daylaborer.DayLaborer;
import nicksolutions.contracts.domain.enterprise.Enterprise;
import nicksolutions.contracts.domain.payment.Payment;
import nicksolutions.contracts.domain.payment.PaymentMethod;
import nicksolutions.contracts.domain.payment.service.PaymentService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class PaymentStatementPdfGenerator {

  private final PaymentService paymentService;

  private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");
  private static final NumberFormat CURRENCY_FMT =
      NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

  private static final PDFont FONT_BOLD =
      new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
  private static final PDFont FONT_REGULAR =
      new PDType1Font(Standard14Fonts.FontName.HELVETICA);

  @Autowired
  public PaymentStatementPdfGenerator(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  public File generate(String paymentId) {
    Payment payment = paymentService.findByIdOrThrow(paymentId);
    DayLaborer dayLaborer = payment.getDayLaborer();

    try {
      File pdfFile = Files
          .createTempFile("demonstrativo_pagamento_" + dayLaborer.getName() + "_" + payment.getDDMMYYYDate(), ".pdf")
          .toFile();

      buildPdf(payment, pdfFile);
      return pdfFile;
    } catch (IOException e) {
      throw new IllegalStateException("Ocorreu um erro na geração do demonstrativo de pagamento", e);
    }
  }

  private void buildPdf(Payment payment, File pdfFile) throws IOException {
    try (PDDocument document = new PDDocument()) {

      // primeira página
      PDPage page = new PDPage(PDRectangle.A4);
      document.addPage(page);

      PDRectangle mediaBox = page.getMediaBox();
      float margin = 50f;
      float bottomMargin = 50f;
      float startX = margin;
      float pageRight = mediaBox.getUpperRightX() - margin;
      float y = mediaBox.getUpperRightY() - margin;
      float leading = 14f;

      float columnGap = 100f;
      float availableWidth = mediaBox.getWidth() - 2 * margin - columnGap;
      float columnWidth = availableWidth / 2f;
      float col1X = startX;
      float col2X = startX + columnWidth + columnGap;

      List<DailyWage> dailyWages = payment.getDailyWages();

      PDPageContentStream content = new PDPageContentStream(document, page);
      try {
        // --------- CABEÇALHO (TÍTULO + PAGAMENTO + DIARISTA) ----------
        drawText(content, FONT_BOLD, 18, startX, y,
            "Demonstrativo de Pagamento de Diárias");
        y -= 2 * leading;

        float pagamentoY = y;
        float diaristaY = y;

        // Dados do pagamento (esquerda)
        drawText(content, FONT_BOLD, 12, col1X, pagamentoY, "Dados do Pagamento");
        pagamentoY -= leading * 1.5f;

        drawText(content, FONT_BOLD, 10, col1X, pagamentoY, "ID do Pagamento: ");
        drawText(content, FONT_REGULAR, 10, col1X + 110, pagamentoY,
            defaultString(payment.getId()));
        pagamentoY -= leading;

        drawText(content, FONT_BOLD, 10, col1X, pagamentoY, "Data do Pagamento: ");
        String paymentDate = payment.getDate() != null
            ? payment.getDate().format(DATE_FMT)
            : "-";
        drawText(content, FONT_REGULAR, 10, col1X + 110, pagamentoY, paymentDate);
        pagamentoY -= leading;

        drawText(content, FONT_BOLD, 10, col1X, pagamentoY, "Método de Pagamento: ");
        String method = payment.getMethod() != null
            ? translatePaymentMethod(payment.getMethod())
            : "-";
        drawText(content, FONT_REGULAR, 10, col1X + 110, pagamentoY, method);
        pagamentoY -= leading;

        drawText(content, FONT_BOLD, 10, col1X, pagamentoY, "Valor Total: ");
        BigDecimal value = Objects.requireNonNullElse(payment.getValue(), BigDecimal.ZERO);
        drawText(content, FONT_REGULAR, 10, col1X + 110, pagamentoY,
            CURRENCY_FMT.format(value));
        pagamentoY -= leading;

        if (payment.getObservations() != null && !payment.getObservations().isBlank()) {
          pagamentoY -= leading / 2;
          drawText(content, FONT_BOLD, 10, col1X, pagamentoY, "Observações:");
          pagamentoY -= leading;

          for (String line : wrapText(payment.getObservations(), 50)) {
            drawText(content, FONT_REGULAR, 10, col1X, pagamentoY, line);
            pagamentoY -= leading;
          }
        }

        // Dados do diarista (direita)
        if (dailyWages != null && !dailyWages.isEmpty()) {
          DailyWage first = dailyWages.get(0);
          DayLaborer dl = first.getDayLaborer();

          if (dl != null) {
            drawText(content, FONT_BOLD, 12, col2X, diaristaY, "Dados do(a) Diarista");
            diaristaY -= leading * 1.5f;

            drawText(content, FONT_BOLD, 10, col2X, diaristaY, "Nome: ");
            drawText(content, FONT_REGULAR, 10, col2X + 60, diaristaY,
                defaultString(dl.getName()));
            diaristaY -= leading;

            drawText(content, FONT_BOLD, 10, col2X, diaristaY, "CPF: ");
            drawText(content, FONT_REGULAR, 10, col2X + 60, diaristaY,
                defaultString(dl.getCpf()));
            diaristaY -= leading;

            drawText(content, FONT_BOLD, 10, col2X, diaristaY, "Telefone: ");
            drawText(content, FONT_REGULAR, 10, col2X + 60, diaristaY,
                defaultString(dl.getPhoneNumber()));
            diaristaY -= leading;

            if (dl.getPixKey() != null && !dl.getPixKey().isBlank()) {
              drawText(content, FONT_BOLD, 10, col2X, diaristaY, "Chave PIX: ");
              drawText(content, FONT_REGULAR, 10, col2X + 60, diaristaY,
                  defaultString(dl.getPixKey()));
              diaristaY -= leading;
            }

            diaristaY -= leading / 2;
          }
        }

        // descer até o bloco mais baixo
        y = Math.min(pagamentoY, diaristaY) - leading * 1.5f;

        // --------- TABELA DE DIÁRIAS (PAGINADA) ----------
        if (dailyWages != null && !dailyWages.isEmpty()) {

          // título + cabeçalho da tabela nesta página
          float sepY = drawDailyHeader(content, startX, y, leading, pageRight);

          float topPadding = leading * 1.1f;
          float bottomPadding = leading * 0.6f;
          float extrasOffset = leading * 0.8f;
          float rowHeight = topPadding + extrasOffset + bottomPadding;

          for (DailyWage dw : dailyWages) {

            // se não couber mais uma diária inteira, abre nova página
            while (sepY - rowHeight < bottomMargin) {
              content.close();

              page = new PDPage(PDRectangle.A4);
              document.addPage(page);
              mediaBox = page.getMediaBox();
              pageRight = mediaBox.getUpperRightX() - margin;

              content = new PDPageContentStream(document, page);
              float topY = mediaBox.getUpperRightY() - margin;
              sepY = drawDailyHeader(content, startX, topY, leading, pageRight);
            }

            String dateStr = dw.getWorkDate() != null
                ? dw.getWorkDate().format(DATE_FMT)
                : "-";

            String horario = "-";
            if (dw.getStartHour() != null && dw.getEndHour() != null) {
              horario = dw.getStartHour().format(TIME_FMT)
                  + " - "
                  + dw.getEndHour().format(TIME_FMT);
            } else if (dw.getStartHour() != null) {
              horario = dw.getStartHour().format(TIME_FMT);
            }

            Enterprise ent = dw.getEnterprise();
            String enterpriseName = ent != null ? defaultString(ent.getName()) : "-";

            BigDecimal dailyValue = defaultBigDecimal(dw.getDayLaborerPaymentValue());
            BigDecimal bonus = defaultBigDecimal(dw.getBonus());
            BigDecimal deduction = defaultBigDecimal(dw.getDeduction());
            BigDecimal netValue = defaultBigDecimal(dw.computePaymentValue());

            float mainY = sepY - topPadding;
            float extrasY = mainY - extrasOffset;
            float nextSepY = extrasY - bottomPadding;

            // linha principal
            drawText(content, FONT_REGULAR, 9, startX, mainY, dateStr);
            drawText(content, FONT_REGULAR, 9, startX + 70, mainY, horario);
            drawText(content, FONT_REGULAR, 9, startX + 160, mainY,
                truncate(enterpriseName, 28));
            drawText(content, FONT_REGULAR, 9, startX + 340, mainY,
                CURRENCY_FMT.format(dailyValue));
            drawText(content, FONT_REGULAR, 9, startX + 430, mainY,
                CURRENCY_FMT.format(netValue));

            // extras
            String extras = "Bônus: " + CURRENCY_FMT.format(bonus)
                + "   Desconto: " + CURRENCY_FMT.format(deduction);
            if (dw.getNotes() != null && !dw.getNotes().isBlank()) {
              extras += "   Obs.: " + truncate(dw.getNotes(), 80);
            }
            drawText(content, FONT_REGULAR, 8, startX + 10, extrasY, extras);

            // linha da diária
            drawSeparatorLine(content, startX, pageRight, nextSepY);

            // próxima diária usa essa linha como topo
            sepY = nextSepY;
          }
        }

      } finally {
        content.close();
      }

      document.save(pdfFile);
    }
  }

  /** Desenha título "Diárias Executadas Pagas" + cabeçalho + primeira linha divisória. */
  private float drawDailyHeader(PDPageContentStream content,
                                float startX,
                                float y,
                                float leading,
                                float pageRight) throws IOException {

    drawText(content, FONT_BOLD, 12, startX, y, "Diárias Executadas Pagas");
    y -= leading * 1.5f;

    drawText(content, FONT_BOLD, 9, startX, y, "Data");
    drawText(content, FONT_BOLD, 9, startX + 70, y, "Horário");
    drawText(content, FONT_BOLD, 9, startX + 160, y, "Empresa");
    drawText(content, FONT_BOLD, 9, startX + 340, y, "Valor Diária");
    drawText(content, FONT_BOLD, 9, startX + 430, y, "Valor Líquido");
    y -= leading;

    float sepY = y;
    drawSeparatorLine(content, startX, pageRight, sepY);

    return sepY;
  }

  private static void drawText(PDPageContentStream content,
                               PDFont font,
                               float fontSize,
                               float x,
                               float y,
                               String text) throws IOException {
    content.beginText();
    content.setFont(font, fontSize);
    content.newLineAtOffset(x, y);
    content.showText(text != null ? text : "");
    content.endText();
  }

  private static void drawSeparatorLine(PDPageContentStream content,
                                        float xStart,
                                        float xEnd,
                                        float y) throws IOException {
    content.saveGraphicsState();
    content.setLineWidth(0.3f);
    content.setStrokingColor(new Color(0.8f, 0.8f, 0.8f));
    content.moveTo(xStart, y);
    content.lineTo(xEnd, y);
    content.stroke();
    content.restoreGraphicsState();
  }

  private static String defaultString(String s) {
    return s == null ? "-" : s;
  }

  private static BigDecimal defaultBigDecimal(BigDecimal value) {
    return value != null ? value : BigDecimal.ZERO;
  }

  private static String truncate(String text, int maxLength) {
    if (text == null) return "";
    if (text.length() <= maxLength) return text;
    return text.substring(0, maxLength - 3) + "...";
  }

  private static List<String> wrapText(String text, int maxLineLength) {
    List<String> lines = new ArrayList<>();
    for (String originalLine : text.split("\\R")) {
      if (originalLine.length() <= maxLineLength) {
        lines.add(originalLine);
      } else {
        lines.addAll(splitByLength(originalLine, maxLineLength));
      }
    }
    return lines;
  }

  private static List<String> splitByLength(String s, int maxLen) {
    List<String> result = new ArrayList<>();
    int len = s.length();
    for (int i = 0; i < len; i += maxLen) {
      result.add(s.substring(i, Math.min(len, i + maxLen)));
    }
    return result;
  }

  private static String translatePaymentMethod(PaymentMethod method) {
    if (method == null) return "-";
    return switch (method) {
      case PIX -> "PIX";
      case DINHEIRO -> "Dinheiro";
      case OUTRO -> "Outro";
    };
  }
}
