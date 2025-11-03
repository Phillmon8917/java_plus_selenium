package automation.dataloader.login;

import com.github.javafaker.Faker;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class LoginDataLoader
{
    private static final Faker faker = new Faker();

    public static List<LoginDataObject> loadData()
    {
        List<LoginDataObject> records = new ArrayList<>();
        String filePath = getTestDataPath();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis))
        {
            Sheet sheet = workbook.getSheetAt(0);
            boolean firstRow = true;
            for (Row row : sheet)
            {
                if (firstRow)
                {
                    firstRow = false;
                    continue;
                }
                if (row == null) continue;

                String execute = getCellStringSafe(row.getCell(0));
                if (execute == null || execute.trim().isEmpty()) continue;
                if (!"Y".equalsIgnoreCase(execute.trim())) continue;

                String scenario = getCellStringSafe(row.getCell(1));
                String username = getCellStringSafe(row.getCell(2));
                String passwordRaw = getCellStringSafe(row.getCell(3));
                String encodedFlag = getCellStringSafe(row.getCell(4));
                String expected = getCellStringSafe(row.getCell(5));

                boolean passwordEncoded = "TRUE".equalsIgnoreCase(encodedFlag);

                String password = null;
                if (passwordRaw != null && !"N/A".equalsIgnoreCase(passwordRaw.trim()))
                {
                    if (passwordEncoded)
                    {
                        try
                        {
                            byte[] decoded = Base64.getDecoder().decode(passwordRaw.trim());
                            password = new String(decoded);
                        } catch (IllegalArgumentException e)
                        {
                            password = passwordRaw;
                        }
                    } else
                    {
                        password = passwordRaw;
                    }
                }

                if (username != null && username.contains("Random"))
                {
                    username = faker.name().lastName();
                } else if (username == null || username.isEmpty())
                {
                    username = "";
                }

                if (password != null && password.contains("Random"))
                {
                    password = faker.internet().password(8, 12, true, true, true);
                } else if (password == null || password.isEmpty())
                {
                    password = "";
                }


                LoginDataObject obj = new LoginDataObject.Builder()
                        .withScenarioName(safeTrim(scenario))
                        .withUsername(safeTrim(username))
                        .withPassword(password == null ? "" : password)
                        .withPasswordEncoded(passwordEncoded)
                        .withExpectedResult(safeTrim(expected))
                        .build();

                records.add(obj);
            }
        } catch (IOException e)
        {
            throw new RuntimeException("Failed to load login data from Excel: " + e.getMessage(), e);
        }
        return records;
    }

    private static String getCellStringSafe(Cell cell)
    {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) return cell.getStringCellValue().trim();
        if (cell.getCellType() == CellType.BOOLEAN) return Boolean.toString(cell.getBooleanCellValue()).trim();
        if (cell.getCellType() == CellType.NUMERIC)
        {
            if (DateUtil.isCellDateFormatted(cell)) return cell.getDateCellValue().toString().trim();
            double d = cell.getNumericCellValue();
            long l = (long) d;
            if (Double.compare(d, (double) l) == 0) return Long.toString(l);
            return Double.toString(d);
        }
        if (cell.getCellType() == CellType.FORMULA)
        {
            try
            {
                return cell.getStringCellValue().trim();
            } catch (IllegalStateException ex)
            {
                return Double.toString(cell.getNumericCellValue()).trim();
            }
        }
        return "";
    }

    private static String safeTrim(String s)
    {
        return s == null ? "" : s.trim();
    }

    private static String getTestDataPath()
    {
        return "src/test/java/automation/testdata/loginpage/login_td.xlsx";
    }
}
