package com.redhat.rest.example.demorest;

import javafx.scene.control.Alert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Uses POI to convert an Excel spreadsheet to the desired JAXB XML format.
 */

public class ExcelConverterAlerts {
    private static final Log log = LogFactory.getLog(ExcelConverterAlerts.class);

    public List<Alerts> process(InputStream body) {
        List<Alerts> caseDetails = new ArrayList<>();
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(body);
            HSSFSheet sheet = workbook.getSheetAt(0);
            boolean headersFound = false;
            int colNum;
            for(Iterator rit = sheet.rowIterator(); rit.hasNext();) {
                HSSFRow row = (HSSFRow) rit.next();
                if(!headersFound) {  // Skip the first row with column headers
                    headersFound = true;
                    continue;
                }
                colNum = 0;
                Alerts caseData = new Alerts();
                for(Iterator cit = row.cellIterator(); cit.hasNext(); ++colNum) {
                    HSSFCell cell = (HSSFCell) cit.next();


                    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

                    switch(colNum) {
                        case 0: // customerName
                            caseData.setTransaction(cell.getStringCellValue());
                            break;
                        case 1: // customerName
                            caseData.setOriginalAmount(cell.getStringCellValue());
                            break;

                        case 2: // customerPhone
                            caseData.setThresholdAmount(cell.getStringCellValue());
                            break;
                        case 3: // customerAddress
                            caseData.setMessage(cell.getStringCellValue());
                            break;



                        default:
                            continue;

                    }

                }
                caseDetails.add(caseData);

            }
        } catch (Exception e) {
            log.error("Unable to import Excel invoice", e);
            throw new IllegalArgumentException("Unable to import Excel invoice", e);
        }
        return caseDetails;
    }
}