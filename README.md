```markdown
# Java plus Selenium project with Spring Boot

Automation tests for the OrangeHRM application.  
The project checks login, landing page, and full end-to-end workflows.  
Reports are generated after each test run and can be sent automatically by email.

## ⚙️ Project Setup

1. **Open the project folder** in your IDE.  
   IntelliJ is preferred, but Eclipse or any Java IDE will work.

2. **Check** that a file named `.env.example` exists in the project root.

3. **Create a `.env` file** using `.env.example` as a guide, and fill in your details as shown on the file.

4. **Open Command Prompt or PowerShell** in the project folder and run:

   ```bash
   mvn install
   ```

   This command will download all required dependencies and build the project.

---

## 🛠 Configuration

All main settings, including email details, are stored in:

```
src/main/resources/configs/config.properties
```

> **Note:**  
> Email sending might fail when using a company VPN or internal network.  
> Disconnect from VPN or switch to a personal connection before running tests.

---

## 🧪 Available Tests

| Test Name             | Description                                                                                               |
| --------------------- | --------------------------------------------------------------------------------------------------------- |
| **End To End Test**   | Tests the complete system workflow based on the requirements.                                             |
| **Login Test**        | Verifies the login process. The reset link currently accepts any username without validation.             |
| **Landing Page Test** | Confirms that opening the application leads to the login page and that HTTP correctly redirects to HTTPS. |

---

## 📧 Sending Test Reports

* Test results are stored in the `Results` folder after execution.  
* The framework automatically zips the results folder and email a zip copy to the specified email.  
* Ensure your `.env` file contains valid email details before running tests.

---

## 📝 Notes

* Use `.env.example` as a safe template when setting up your own configuration.  
* For any build issues, try cleaning your Maven cache or re-running `mvn install`.
```
