package com.infy.restcontroller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.AMSDto;
import com.infy.service.PdfServiceI;
import com.infy.utils.IndexBasedPdf;

@RestController
public class PdfController {

	
	@Autowired
    private PdfServiceI pdfService;

    @RequestMapping(value = "/pdfreport",method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> amsPdfReport() throws Exception {

        List<AMSDto> amsDto =  pdfService.getAllAMSDetails();

        //ByteArrayInputStream bis = PdfUtil.amsReport(amsDto);

    	ByteArrayInputStream bis=IndexBasedPdf.pdfGenerator(amsDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=AMSReport.pdf");
      

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
