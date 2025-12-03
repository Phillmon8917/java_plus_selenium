package automation.pages;

import automation.basepage.PIMBasePage;
import automation.strategy.DriverSingleton;
import automation.utils.WaitUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Slf4j
@Component
public class PIMPage extends PIMBasePage
{

    public void goToPIMSection()
    {
        try
        {
            WaitUtil.waitUntilClickable(pimNav);
            pimNav.click();
        } catch (Exception ex)
        {
            log.error("An error has occurred ", ex);
        }
    }

    public void clickAddEmployeeButton()
    {
        try
        {
            WaitUtil.waitUntilClickable(addEmployeeButton);
            addEmployeeButton.click();
        } catch (Exception ex)
        {
            log.error("An error has occurred ", ex);
        }
    }

    public void enter_firstname_middleName_lastname(String firstname, String middleName, String lastname)
    {
        try
        {
            WaitUtil.waitUntilVisible(firstnameInput);
            firstnameInput.clear();
            firstnameInput.sendKeys(firstname.trim());

            WaitUtil.waitUntilVisible(middleNameInput);
            middleNameInput.clear();
            middleNameInput.sendKeys(middleName.trim());

            WaitUtil.waitUntilVisible(lastnameInput);
            lastnameInput.clear();
            lastnameInput.sendKeys(lastname.trim());
        } catch (Exception ex)
        {
            log.error("An error has occurred ", ex);
        }
    }

    private static String extractFileName(String fullPath)
    {
        if (fullPath == null || fullPath.isEmpty())
        {
            throw new IllegalArgumentException("Path cannot be null or empty");
        }

        //Am covering both windows and linux
        fullPath = fullPath.replace("\\", "/");

        int lastSlash = fullPath.lastIndexOf("/");
        if (lastSlash == -1 || lastSlash == fullPath.length() - 1)
        {
            throw new IllegalArgumentException("No valid file name found in path: " + fullPath);
        }

        return fullPath.substring(lastSlash + 1);
    }

    public void clickAddEmployeeSaveButton()
    {
        try
        {
            WaitUtil.waitUntilClickable(saveButton);
            saveButton.click();
        } catch (Exception ex)
        {
            log.error("An error has occurred ", ex);
        }
    }

    public boolean isSuccessPopUpDisplayed()
    {
        try
        {
            WaitUtil.waitUntilVisible(successfullySavedPopUp);
            return successfullySavedPopUp.isDisplayed();
        } catch (Exception ex)
        {
            log.error("An error has occurred ", ex);
            return false;
        }
    }

    public String getEmployeeId()
    {
        try
        {
            WaitUtil.waitUntilVisible(employeeId);
            String employeeIdText = employeeId.getAttribute("value");

            if (!employeeIdText.isBlank())
            {
                return employeeIdText;
            } else
            {
                return null;
            }
        } catch (Exception ex)
        {
            log.error("An error has occurred", ex);
            return null;
        }
    }

    public void selectNationality(String nationality)
    {
        WebDriver driver = DriverSingleton.getDriver();

        By listboxLocator = By.xpath("//div[@role='listbox']");
        By optionsLocator = By.xpath("//div[@role='listbox']//div[contains(@class,'oxd-select-option')]");

        try
        {
            WebElement dropdown = selectsDropdownInput.getFirst();

            List<WebElement> loaders = driver.findElements(By.cssSelector(".oxd-form-loader"));
            if (!loaders.isEmpty()) WaitUtil.waitUntilVisible(loaders.get(0));

            WaitUtil.waitUntilClickable(dropdown);
            dropdown.click();

            WaitUtil.waitUntilVisible(driver.findElement(listboxLocator));

            WaitUtil.waitUntilVisible(driver.findElement(optionsLocator));

            List<WebElement> options = driver.findElements(optionsLocator);
            boolean selected = false;

            for (WebElement option : options)
            {
                if (option.getText().trim().equalsIgnoreCase(nationality))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);
                    WaitUtil.waitUntilClickable(option);
                    option.click();
                    selected = true;
                    break;
                }
            }

            if (!selected)
            {
                throw new NoSuchElementException("Nationality '" + nationality + "' not found");
            }

        } catch (StaleElementReferenceException e)
        {
            selectNationality(nationality);
        } catch (NoSuchElementException e)
        {
            throw e;
        } catch (TimeoutException e)
        {
            throw new TimeoutException("Timed out waiting for dropdown options for '" + nationality + "'", e);
        } catch (Exception ex)
        {
            log.error("Error selecting nationality '" + nationality + "'", ex);
        }
    }

    public void selectMaritalStatus(String maritalStatus)
    {
        WebDriver driver = DriverSingleton.getDriver();

        By listboxLocator = By.xpath("//div[@role='listbox']");
        By optionsLocator = By.xpath("//div[@role='listbox']//div[contains(@class,'oxd-select-option')]");

        try
        {
            WebElement dropdown = selectsDropdownInput.get(0);

            List<WebElement> loaders = driver.findElements(By.cssSelector(".oxd-form-loader"));
            if (!loaders.isEmpty()) WaitUtil.waitUntilVisible(loaders.get(0));

            WaitUtil.waitUntilClickable(dropdown);
            dropdown.click();

            WaitUtil.waitUntilVisible(driver.findElement(listboxLocator));
            WaitUtil.waitUntilVisible(driver.findElement(optionsLocator));

            List<WebElement> options = driver.findElements(optionsLocator);
            boolean selected = false;

            for (WebElement option : options)
            {
                if (option.getText().trim().equalsIgnoreCase(maritalStatus))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);
                    WaitUtil.waitUntilClickable(option);
                    option.click();
                    selected = true;
                    break;
                }
            }

            if (!selected)
            {
                throw new NoSuchElementException("Marital status '" + maritalStatus + "' not found");
            }

        } catch (StaleElementReferenceException e)
        {
            selectMaritalStatus(maritalStatus);
        } catch (NoSuchElementException e)
        {
            throw e;
        } catch (TimeoutException e)
        {
            throw new TimeoutException("Timed out waiting for dropdown options for '" + maritalStatus + "'", e);
        } catch (Exception ex)
        {
            throw new RuntimeException("Error selecting marital status '" + maritalStatus + "'", ex);
        }
    }

    public void attachAFIle(String filePath)
    {
        try
        {
            WaitUtil.waitUntilClickable(addAttachmentButton);
            addAttachmentButton.click();

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.display='block';", inputFile);

            File file = new File(filePath);
            if (!file.exists())
            {
                throw new RuntimeException("File not found: " + file.getAbsolutePath());
            }

            inputFile.sendKeys(file.getAbsolutePath());

            personalDetailsSaveButtons.getLast().click();
        } catch (Exception ex)
        {
            log.error("An error has occurred", ex);
        }
    }

    public boolean isFileAttached(String filePath)
    {
        try
        {
            WaitUtil.waitUntilVisible(fileAttachmentTableRows.getFirst());
            String fileName = PIMPage.extractFileName(filePath);

            return fileAttachmentTableRows.stream()
                    .anyMatch(row -> row.getText().trim().contains(fileName.trim()));

        } catch (Exception ex)
        {
            log.error("An error has occurred", ex);
            return false;
        }
    }

    public void selectDate(String date)
    {
        if (!date.matches("^\\d{4}-\\d{2}-\\d{2}$"))
        {
            throw new IllegalArgumentException("Invalid date format: " + date + ". Expected format is yyyy-dd-mm");
        }

        try
        {
            WebElement dateOfBirthInput = dateInputs.getLast();
            WaitUtil.waitUntilVisible(dateOfBirthInput);
            dateOfBirthInput.sendKeys(date);

            driver.findElement(By.tagName("body")).click();
        } catch (Exception ex)
        {
            log.error("An error has occurred ", ex);
        }
    }

    public void selectGender(String gender)
    {
        WebDriver driver = DriverSingleton.getDriver();
        gender = gender.toLowerCase();

        List<WebElement> loaders = driver.findElements(By.cssSelector(".oxd-form-loader"));
        if (!loaders.isEmpty()) WaitUtil.waitUntilVisible(loaders.get(0));

        try
        {
            WebElement radio;
            if (gender.contains("fe"))
            {
                radio = femaleRadio;
            } else
            {
                radio = genderRadioMale;
            }

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", radio);
            WaitUtil.waitUntilClickable(radio);
            radio.click();

        } catch (StaleElementReferenceException e)
        {
            selectGender(gender);
        } catch (TimeoutException e)
        {
            throw new TimeoutException("Timed out waiting for gender radio button for '" + gender + "'", e);
        } catch (Exception ex)
        {
            throw new RuntimeException("Error selecting gender '" + gender + "'", ex);
        }
    }

    private PIMPage()
    {
    }
}
