package automation.dataloader.landingpage;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LandingPageDataLoader
{
    public static List<LandingPageDataObject> loadData()
    {
        List<LandingPageDataObject> landingPageDataObjects = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(getReportPath());
             Workbook workbook = new XSSFWorkbook(fileInputStream))
        {
            Sheet sheet = workbook.getSheetAt(0);

            boolean isFirstRow = true;
            for (Row row : sheet)
            {
                if (isFirstRow)
                {
                    isFirstRow = false;
                    continue;
                }

                if (row == null || row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK)
                    continue;

                if (!"Y".equalsIgnoreCase(getCellStringValue(row.getCell(0))))
                    continue;

                if (row.getLastCellNum() < 3)
                    continue;

                String scenario = getCellStringValue(row.getCell(1));
                String url = getCellStringValue(row.getCell(2));
                String expectedResults = getCellStringValue(row.getCell(3));

                LandingPageDataObject landingPageDataObject = new LandingPageDataObject.Builder()
                        .withScenario(scenario)
                        .withUrl(url)
                        .withExpectedResults(expectedResults)
                        .build();

                landingPageDataObjects.add(landingPageDataObject);
            }
        } catch (IOException ex)
        {
            ex.printStackTrace();
            throw new RuntimeException("Error loading data from Excel file: " + ex.getMessage());
        }
        return landingPageDataObjects;
    }

    private static String getCellStringValue(Cell cell)
    {
        return cell.getStringCellValue().trim();
    }

    private static String getReportPath()
    {
        return "src/test/java/automation/testdata/landingpage/landingpage_td.xlsx";
    }

    private LandingPageDataLoader(){}
}
