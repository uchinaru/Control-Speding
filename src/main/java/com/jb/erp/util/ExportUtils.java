package com.jb.erp.util;

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


}
