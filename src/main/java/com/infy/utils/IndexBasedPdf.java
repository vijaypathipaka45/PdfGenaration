package com.infy.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.infy.dto.AMSDto;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;


public class IndexBasedPdf {

	
	public static ByteArrayInputStream pdfGenerator(List<AMSDto> amslist){
		Document document = null;
        ByteArrayOutputStream out = null;
        ByteArrayOutputStream os=null;

        try {
        	out = new ByteArrayOutputStream();
        	document = new Document();
            PdfWriter contentWriter = PdfWriter.getInstance(document, out);
    		ContentEvent event = new ContentEvent();
    		contentWriter.setPageEvent(event);
    		
            document.open();
     
            List<Chapter> chapterList = new ArrayList<Chapter>();
            
    		for (int i = 1; i <= 3; i++) {
    			Chunk chapTitle = new Chunk("AMS Schedules "+i);
    			Chapter chapter = new Chapter(new Paragraph(chapTitle), i);
    			chapter.add(AMSScheduleTableGenerator(amslist));
    			chapTitle.setLocalDestination(chapter.getTitle().getContent());
    			document.add(chapter);    			
    			chapterList.add(chapter);
    		}
    		
             document.close();
            
        	Document document1 = new Document(PageSize.A4, 50, 50, 50, 50);
        	
        	
//        	//PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HeaderFooter.pdf"));
           Rectangle rect = new Rectangle(30, 30, 550, 800);
//            writer.setBoxSize("art", rect);
//            HeaderFooterPageEvent event = new HeaderFooterPageEvent();
//            writer.setPageEvent(event);
//        	
        	
    		
        	os=new ByteArrayOutputStream();
    		PdfWriter writer = PdfWriter.getInstance(document1, os);
    		IndexEvent indexEvent = new IndexEvent();
    		writer.setPageEvent(indexEvent);
    		writer.setBoxSize("art", rect);
    		 HeaderFooterPageEvent headerFooterEvent = new HeaderFooterPageEvent();
             writer.setPageEvent(headerFooterEvent);
    		
    		document1.open();
    		
    		Chapter indexChapter = new Chapter("Index", 1);
    		indexChapter.setNumberDepth(-1); // not show number style
    		PdfPTable table1 = new PdfPTable(2);
    		for(Map.Entry<String, Integer> index: event.index.entrySet()) {
    			PdfPCell left = new PdfPCell(new Phrase(index.getKey()));
    			left.setBorder(Rectangle.NO_BORDER);
    			
    			Chunk pageno = new Chunk(index.getValue()+"");
    			pageno.setLocalGoto(index.getKey());
    			PdfPCell right = new PdfPCell(new Phrase(pageno));
    			right.setHorizontalAlignment(Element.ALIGN_RIGHT);
    			right.setBorder(Rectangle.NO_BORDER);
    			
    			table1.addCell(left);
    			table1.addCell(right);
    		}
    		
    		
    		indexChapter.add(table1);
    		document1.add(indexChapter);
    		
    		// add content chapter
    		for(Chapter c : chapterList) {
    			document1.add(c);
    			indexEvent.body = true;
    		}

    		document1.close();
    		os.close();
    		//return new ByteArrayInputStream(os.toByteArray());

        } catch (DocumentException ex) {

        	ex.printStackTrace();
            
        }
	catch (Exception ex) {

    	ex.printStackTrace();
       
    }

        return new ByteArrayInputStream(os.toByteArray());
	}
	
	private static PdfPTable  AMSScheduleTableGenerator(List<AMSDto> amslist) throws DocumentException {
		PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        float[] columnWidths = new float[]{10f, 30f, 15f, 15f,16f,10f,5f,14f};
        table.setWidths(columnWidths);
       
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        headFont.setSize(9);
        PdfPCell hcell;
        hcell = new PdfPCell(new Phrase("AMS No", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setFixedHeight(0);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("DESCRIPTION", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setFixedHeight(0);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("THRESHOULD/INTERVAL", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
        
     
        hcell = new PdfPCell(new Phrase("SOURCE CODE/SAMPLE", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("REFERENCE", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
        
        
        hcell = new PdfPCell(new Phrase("EFFECTIVITY", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("FEC", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
        
        hcell = new PdfPCell(new Phrase("REVISION REFERENCE", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
        
        for (AMSDto dto : amslist) {

        	Font font = FontFactory.getFont(FontFactory.defaultEncoding);
        	font.setSize(9);
            PdfPCell cell;

            cell = new PdfPCell(new Phrase(dto.getAMSNo().toString(),font));            
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(dto.getDescription(),font));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(dto.getThreesholdInterval(),font));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setPaddingRight(5);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(dto.getSorceCode(),font));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dto.getReference(),font));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dto.getEffectivity(),font));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dto.getFEC(),font));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dto.getRevisionReference(),font));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }

		
		return table;
	}
	
	/**
	 * EventListner for Content
	 */
	private static class ContentEvent extends PdfPageEventHelper {
		private int page;
		Map<String, Integer> index = new LinkedHashMap<String, Integer>();
		
		@Override
		public void onStartPage(PdfWriter writer, Document document) {
			page++;
		}
		
		@Override
		public void onChapter(PdfWriter writer, Document document,
				float paragraphPosition, Paragraph title) {
			
			index.put(title.getContent(), page);
		}
		
		@Override
		public void onSection(PdfWriter writer, Document document,
				float paragraphPosition, int depth, Paragraph title) {
			onChapter(writer, document, paragraphPosition, title);
		}
	}
	
	/**
	 * EventListner for Index
	 */
	private static class IndexEvent extends PdfPageEventHelper {
		private int page;
		private boolean body;
		@Override
		public void onEndPage(PdfWriter writer, Document document) {
			// set page number on content
			if(body) {
				page++;
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, 
						new Phrase(page+""), (document.right() + document.left())/2 , document.bottom() - 18, 0);
			}
		}
		
	}

}

class HeaderFooterPageEvent extends PdfPageEventHelper {
    public void onStartPage(PdfWriter writer,Document document) {
    	String dest = "D:\\STS\\Practice\\PdfGenerator\\src\\main\\resources\\cathay.jpg"; 
    	Rectangle rect = writer.getBoxSize("art");
        ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase("Top Left"), rect.getLeft(), rect.getTop(), 0);
        ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase(dest), rect.getRight(), rect.getTop(), 0);
    }
/*    public void onEndPage(PdfWriter writer,Document document) {
    	Rectangle rect = writer.getBoxSize("art");
        ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase("Bottom Left"), rect.getLeft(), rect.getBottom(), 0);
        ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase("Bottom Right"), rect.getRight(), rect.getBottom(), 0);
    }*/
}