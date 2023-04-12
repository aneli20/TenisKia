package org.teniskia.util.reportes;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.teniskia.entity.Catalogo;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

public class TeniExporterPDF {
	
	private List<Catalogo> listaTenis;

	public TeniExporterPDF(List<Catalogo> listaTenis) {
		super();
		this.listaTenis = listaTenis;
	}
	
	private void escribirCabeceraDelaTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();
		
		celda.setBackgroundColor(Color.blue);
		celda.setPadding(5);
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		
		celda.setPhrase(new Phrase("ID",fuente));
		tabla.addCell(celda);
		

		celda.setPhrase(new Phrase("nombre",fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("descripcion",fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("fecha",fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("precio",fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("estatus",fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("destacado",fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("imagen",fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("detalles",fuente));
		tabla.addCell(celda);		
		 
	}
	
	private void escribirDatosDeLaTabla(PdfPTable tabla) {
		for (Catalogo catalogo : listaTenis) {
			tabla.addCell(String.valueOf(catalogo.getId()));
			tabla.addCell(catalogo.getNombre());
			tabla.addCell(catalogo.getDescripcion());
			tabla.addCell(catalogo.getFecha().toString());
			tabla.addCell(String.valueOf(catalogo.getPrecio()));
			tabla.addCell(catalogo.getEstatus());
			tabla.addCell(String.valueOf(catalogo.getDestacado()));
			tabla.addCell(catalogo.getImagen());
			tabla.addCell(catalogo.getDetalles());
			
			
		}
	}
	
	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());
		
		documento.open();
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.blue);
		fuente.setSize(18);
		
		Paragraph titulo = new Paragraph("Compra de tenis",fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);
		
		PdfPTable tabla = new PdfPTable(9);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float [] {1f,2.3f,2.3f,6f,2.9f,3.5f,2f,2.2f,2.3f});
		tabla.setWidthPercentage(110);
		
		escribirCabeceraDelaTabla(tabla);
		escribirDatosDeLaTabla(tabla);
		
		documento.add(tabla);
		documento.close();
		
		
		
	}
	

}
