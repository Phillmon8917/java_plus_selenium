Feature: OrangeHRM End-to-End Employee Management
  This feature covers the end-to-end workflow of managing employees in the OrangeHRM demo application,
  including login, adding a new employee, updating details, uploading attachments, and verifying employee records.
  It also includes negative scenarios for validation and error handling.

  Background:
    Given the user navigates to "https://opensource-demo.orangehrmlive.com/"
    And the login page is displayed

  # --------------------------
  # POSITIVE SCENARIOS
  # --------------------------

  Scenario: Login with valid credentials
    When the user enters username "Admin" and password "admin123"
    And clicks the Login button
    Then the Dashboard page should be displayed

  Scenario: Add new employee with valid details
    Given the user is logged in and on the Dashboard
    When the user navigates to the "PIM" section
    And clicks on the "Add Employee" button
    And enters First Name "John", Middle Name "M", and Last Name "Doe"
    And clicks the Save button
    Then a success popup should appear confirming the employee was saved

  Scenario: Update personal details for the employee
    Given the user is on the employee’s Personal Details page
    When the user selects Nationality "South African"
    And sets Marital Status "Single"
    And selects Date of Birth "1995-05-12"
    And selects Gender "Male"
    And clicks Save
    Then the system should display a popup "Successfully Saved"

  Scenario: Upload a valid attachment
    Given the user is on the employee’s Attachments section
    When the user uploads file "sample.docx"
    And clicks Save
    Then the file should appear in the attachments table with a success message

  Scenario: Add job details for employee
    Given the user is on the Job tab
    When the user fills Job Title "QA Engineer"
    And sets Joined Date "2025-01-11"
    And selects Job Category "Professionals"
    And selects Sub Unit "Quality Assurance"
    And selects Employment Status "Full-Time Permanent"
    And selects Location "HQ"
    And clicks Save
    Then the system should confirm job details were saved successfully

  Scenario: Search for added employee
    Given the user navigates to the Employee List page
    When the user searches for employee by name "John Doe"
    Then the employee record should be displayed in the search results
    And all details such as Full Name, Job Title, and Location should match the entered data

  Scenario: Capture full-page screenshot after verification
    Given the search results are visible
    When the user captures a full-page screenshot
    Then the screenshot should be saved successfully for reporting

  # --------------------------
  # NEGATIVE SCENARIOS
  # --------------------------

  Scenario: Login with invalid credentials
    When the user enters username "Admin" and password "wrongpass"
    And clicks Login
    Then an error message "Invalid credentials" should be displayed

  Scenario: Add employee with missing mandatory fields
    Given the user navigates to Add Employee page
    When the user leaves First Name empty and clicks Save
    Then the system should display a validation message for missing mandatory fields

  Scenario: Upload an invalid file type
    Given the user is on the Attachments section
    When the user uploads file "malware.exe"
    Then the system should show an error "Invalid file type"

  Scenario: Add job details with invalid date
    Given the user is on the Job tab
    When the user enters Joined Date "2025/13/40"
    And clicks Save
    Then the system should show a date format validation error

  Scenario: Search for a non-existent employee
    Given the user is on the Employee List page
    When the user searches for employee name "Ghost User"
    Then the system should display "No Records Found"

  Scenario: Session timeout due to inactivity
    Given the user is logged in
    When the user remains idle for the session timeout period
    Then the system should automatically log out and redirect to the login page
