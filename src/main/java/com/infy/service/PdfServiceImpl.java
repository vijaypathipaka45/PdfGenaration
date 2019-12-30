package com.infy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.infy.dto.AMSDto;

@Service
public class PdfServiceImpl implements PdfServiceI {

	@Override
	public List<AMSDto> getAllAMSDetails() {
		List<AMSDto> amsList=new ArrayList<>();
		
		AMSDto dto1=new AMSDto();
		dto1.setAMSNo("3290000");
		dto1.setDescription("EXTERNAL-ZONAL(GV): Nose landing Gear and Landing Gear Doors-Form the Groound  Perform an external zonal inspection(GV)of the Nose landing gear and nose landing gear doors-As viewed from the ground EXTERNAL-ZONAL(GV): Nose landing Gear and Landing Gear Doors-Form the Groound  Perform an external zonal inspection(GV)of the Nose landing gear and nose landing gear doors-As viewed from the ground EXTERNAL-ZONAL(GV): Nose landing Gear and Landing Gear Doors-Form the Groound  Perform an external zonal inspection(GV)of the Nose landing gear and nose landing gear doors-As viewed from the ground EXTERNAL-ZONAL(GV): Nose landing Gear and Landing Gear Doors-Form the Groound  Perform an external zonal inspection(GV)of the Nose landing gear and nose landing gear doors-As viewed from the ground EXTERNAL-ZONAL(GV): Nose landing Gear and Landing Gear Doors-Form the Groound  Perform an external zonal inspection(GV)of the Nose landing gear and nose landing gear doors-As viewed from the ground EXTERNAL-ZONAL(GV): Nose landing Gear and Landing Gear Doors-Form the Groound  Perform an external zonal inspection(GV)of the Nose landing gear and nose landing gear doors-As viewed from the ground EXTERNAL-ZONAL(GV): Nose landing Gear and Landing Gear Doors-Form the Groound  Perform an external zonal inspection(GV)of the Nose landing gear and nose landing gear doors-As viewed from the ground EXTERNAL-ZONAL(GV): Nose landing Gear and Landing Gear Doors-Form the Groound  Perform an external zonal inspection(GV)of the Nose landing gear and nose landing gear doors-As viewed from the ground EXTERNAL-ZONAL(GV): Nose landing Gear and Landing Gear Doors-Form the Groound  Perform an external zonal inspection(GV)of the Nose landing gear and nose landing gear doors-As viewed from the ground EXTERNAL-ZONAL(GV): Nose landing Gear and Landing Gear Doors-Form the Groound  Perform an external zonal inspection(GV)of the Nose landing gear and nose landing gear doors-As viewed from the ground EXTERNAL-ZONAL(GV): Nose landing Gear and Landing Gear Doors-Form the Groound  Perform an external zonal inspection(GV)of the Nose landing gear and nose landing gear doors-As viewed from the ground EXTERNAL-ZONAL(GV): Nose landing Gear and Landing Gear Doors-Form the Groound  Perform an external zonal inspection(GV)of the Nose landing gear and nose landing gear doors-As viewed from the ground EXTERNAL-ZONAL(GV): Nose landing Gear and Landing Gear Doors-Form the Groound  Perform an external zonal inspection(GV)of the Nose landing gear and nose landing gear doors-As viewed from the ground");
		dto1.setThreesholdInterval("T : 2800FC \nT:700 DY \nI : 2800 FC \nI : 700 DY");
		dto1.setSorceCode("MRB");
		dto1.setReference("MRB 32-800-00 \n W/C 777 32-800-00-01");
		dto1.setEffectivity("ALL");
		dto1.setFEC("");
		dto1.setRevisionReference("MTG 17.001 \n MTG 18.008 \n MTG 19.042 \n MTG 21.048 \n MTG 23.026 \n MTG 27.040 \n MTG 29.042 \n MTG 35.105");
		amsList.add(dto1);
		 for(int i=0;i<5;i++) {
			 AMSDto d=new AMSDto();
			 d.setAMSNo("AMS"+(i+1));
			 d.setDescription(dto1.getDescription());
			 d.setEffectivity(dto1.getEffectivity());
			 d.setFEC(dto1.getFEC());
			 d.setReference(dto1.getReference());
			 d.setRevisionReference(dto1.getRevisionReference());
			 d.setSorceCode(dto1.getSorceCode());
			 d.setThreesholdInterval(dto1.getThreesholdInterval());
			 
			 amsList.add(d);
			 d=null;
			
		 }
	System.out.println("total list is "+amsList);
    	return amsList;
	}

	 
}
