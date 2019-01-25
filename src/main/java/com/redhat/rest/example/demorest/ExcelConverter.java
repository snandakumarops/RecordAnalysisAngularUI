package com.redhat.rest.example.demorest;


import com.citigroup.transaction_analysis.facts.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Uses POI to convert an Excel spreadsheet to the desired JAXB XML format.
 */

public class ExcelConverter {
    private static final Log log = LogFactory.getLog(ExcelConverter.class);

    public List<TransactionObj> process(InputStream body) {
        List<TransactionObj> caseDetails = new ArrayList<>();
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
                TransactionObj caseData = new TransactionObj();
                for(Iterator cit = row.cellIterator(); cit.hasNext(); ++colNum) {
                    HSSFCell cell = (HSSFCell) cit.next();


                    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

                    switch(colNum) {
                        case 0: // customerName
                            caseData.setTransactionReferenceNumber(cell.getStringCellValue());
                            break;
                        case 1: // customerName
                            caseData.setPartitionDate(df.format(cell.getDateCellValue()));
                            break;

                        case 8: // customerAddress
                            caseData.setField52(cell.getStringCellValue());
                            break;

                        case 4: // complaintsDescription
                            caseData.setSenderBIC(cell.getStringCellValue());
                            break;
                        case 5: // category
                            caseData.setIntermediataryBank(cell.getStringCellValue());
                            break;
                        case 6: // businessUnit
                            caseData.setBeneficiaryBank(cell.getStringCellValue());
                            break;

                        case 9: // businessUnit
                            caseData.setNested(cell.getStringCellValue());
                            break;
                        case 10: // businessUnit
                            caseData.setRule1(cell.getStringCellValue());
                            break;
                        case 11: // businessUnit
                            caseData.setRule2(cell.getStringCellValue());
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