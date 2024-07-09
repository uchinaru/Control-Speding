package com.jb.erp.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;
import com.jb.erp.model.Despesa;

public class ExportUtils implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	DateUtils dateUtils;
	
	public Workbook exportExcelDespesa(String tituloArquivo, String[] tituloColunas, List<Despesa> listarDespesas) throws IOException {
		
		final Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet(tituloArquivo);

		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue(tituloArquivo + ": " + dateUtils.dateTimeStampFormat());
		
		Row row1 = sheet.createRow(2);
		for (int i = 0; i < tituloColunas.length; i++) {
			row1.createCell(i).setCellValue(tituloColunas[i]);
		}
		
        int rowIndex = 3;
        for (Despesa despesa : listarDespesas) {
            Row row2 = sheet.createRow(rowIndex++);

            row2.createCell(0).setCellValue(despesa.getNome());
            row2.createCell(1).setCellValue(despesa.getValor());
            row2.createCell(2).setCellValue(dateUtils.transformaDataSimplesString(despesa.getDataCusto()));
            row2.createCell(3).setCellValue(despesa.getMesGasto());
            row2.createCell(4).setCellValue(despesa.getQuantidade());
            row2.createCell(5).setCellValue(despesa.getTipoPagamentos().toString());
            row2.createCell(6).setCellValue(despesa.getDescricao());
        }

		try (OutputStream fileOut = new FileOutputStream(tituloArquivo.concat(".xls"))) {
			wb.write(fileOut);
		}

		return wb;
	}

	public Workbook exportCSVDespesa(String tituloArquivo, String[] tituloColunas, List<Despesa> listarDespesas) throws IOException {
		
		final Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet(tituloArquivo);

		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue(tituloArquivo + ": " + dateUtils.dateTimeStampFormat());
		
		Row row1 = sheet.createRow(2);
		for (int i = 0; i < tituloColunas.length; i++) {
			row1.createCell(i).setCellValue(tituloColunas[i].concat(";"));
		}
		
        int rowIndex = 3;
        for (Despesa despesa : listarDespesas) {
            Row row2 = sheet.createRow(rowIndex++);

            row2.createCell(0).setCellValue(despesa.getNome().concat(";"));
            row2.createCell(1).setCellValue(String.valueOf(despesa.getValor()).concat(";"));
            row2.createCell(2).setCellValue(dateUtils.transformaDataSimplesString(despesa.getDataCusto()).concat(";"));
            row2.createCell(3).setCellValue(String.valueOf(despesa.getMesGasto()).concat(";"));
            row2.createCell(4).setCellValue(String.valueOf(despesa.getQuantidade()).concat(";"));
            row2.createCell(5).setCellValue(despesa.getTipoPagamentos().toString().concat(";"));
            row2.createCell(6).setCellValue(despesa.getDescricao().concat(";"));
        }

		try (OutputStream fileOut = new FileOutputStream(tituloArquivo.concat(".csv"))) {
			wb.write(fileOut);
		}

		return wb;
	}

	 public void exportPDFDespesa(String tituloArquivo, String[] tituloColunas, List<Despesa> listarDespesas) throws FileNotFoundException, DocumentException {
	        
	        Document document = new Document(PageSize.A4.rotate());
	        
	        PdfWriter.getInstance(document, new FileOutputStream(tituloArquivo.concat(".pdf")));
	        document.open();

	        document.add(new Paragraph(tituloArquivo + ": " + dateUtils.dateTimeStampFormat()));
	        document.add(new Paragraph(" "));
	        
	        PdfPTable table = new PdfPTable(tituloColunas.length);
	        table.setWidthPercentage(100);

	        for (String tituloColuna : tituloColunas) {
	            PdfPCell cell = new PdfPCell(new Phrase(tituloColuna));
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell.setBorder(Rectangle.NO_BORDER);
	            table.addCell(cell);
	        }
	     
	        for (Despesa despesa : listarDespesas) {
	        	
	            PdfPCell cellNome = new PdfPCell(new Phrase(despesa.getNome()));
	            cellNome.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cellNome.setBorder(Rectangle.NO_BORDER);
	            table.addCell(cellNome);

	            PdfPCell cellValor = new PdfPCell(new Phrase(String.valueOf(despesa.getValor())));
	            cellValor.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cellValor.setBorder(Rectangle.NO_BORDER);
	            table.addCell(cellValor);

	            PdfPCell cellData = new PdfPCell(new Phrase(dateUtils.transformaDataSimplesString(despesa.getDataCusto())));
	            cellData.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cellData.setBorder(Rectangle.NO_BORDER);
	            table.addCell(cellData);

	            PdfPCell cellMes = new PdfPCell(new Phrase(String.valueOf(despesa.getMesGasto())));
	            cellMes.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cellMes.setBorder(Rectangle.NO_BORDER);
	            table.addCell(cellMes);

	            PdfPCell cellQuantidade = new PdfPCell(new Phrase(String.valueOf(despesa.getQuantidade())));
	            cellQuantidade.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cellQuantidade.setBorder(Rectangle.NO_BORDER);
	            table.addCell(cellQuantidade);

	            PdfPCell cellTipoPagamento = new PdfPCell(new Phrase(despesa.getTipoPagamentos().toString()));
	            cellTipoPagamento.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cellTipoPagamento.setBorder(Rectangle.NO_BORDER);
	            table.addCell(cellTipoPagamento);

	            PdfPCell cellDescricao = new PdfPCell(new Phrase(despesa.getDescricao()));
	            cellDescricao.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cellDescricao.setBorder(Rectangle.NO_BORDER);
	            table.addCell(cellDescricao);
	        }
	        
	        document.add(table);

	        document.close();
	    }
}
