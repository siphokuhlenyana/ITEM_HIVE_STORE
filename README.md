# ADP3-Assignment1-2026
UML class diagram which will be saved in the Readme.md file of the project solution.

<img width="1351" height="1362" alt="class-diagram png" src="https://github.com/user-attachments/assets/f429870e-ca86-4d26-a85c-17f6907ad71b" />


# ITEMHIVE – Student Marketplace Application

**Connecting Students Through Buying and Selling**

## 1. Project Overview

ITEMHIVE is a Student Marketplace Application developed to provide students with a centralised, convenient and secure online platform where they can buy and sell products within the student community.

The application aims to simplify the process of buying and selling by allowing students to create accounts, browse available products, advertise their own items and complete purchases through an integrated checkout and payment system.

The platform is designed with both students and administrators in mind. Students will have access to marketplace features that allow them to manage their buying and selling activities, while administrators will have access to management tools for monitoring user activity, overseeing product listings and helping prevent fraudulent behaviour.

ITEMHIVE aims to create a reliable digital marketplace that encourages student interaction, accessibility, convenience and safe online transactions.

## 2. Project Purpose

The purpose of ITEMHIVE is to address the need for an accessible marketplace where students can conveniently exchange products and services within their student community.

Students often need affordable second-hand products, textbooks, electronic devices, stationery and other essential items. ITEMHIVE provides a platform where students can advertise unwanted items and connect with other students who may be interested in purchasing them.

By bringing buyers and sellers together in one application, ITEMHIVE aims to promote affordability, convenience and a more connected student environment.

## 3. Project Objectives

The main objectives of ITEMHIVE are to:

- Develop a user-friendly online marketplace specifically for students.
- Provide secure registration and login functionality for students and administrators.
- Allow students to browse products and advertise items for sale.
- Provide an organised product catalogue with available listings.
- Implement an OTP verification system to improve account security.
- Provide a checkout and payment process for completing purchases.
- Develop an administration panel for monitoring and managing marketplace activities.
- Improve user safety by supporting the identification and reporting of suspicious activities.
- Create a responsive and accessible application that is easy to navigate.

## 4. System Users and Roles

ITEMHIVE will support different user roles, each with specific permissions and responsibilities.

### 4.1 Students

Students are the primary users of the application. They will be able to:

- Register for a new account.
- Log in securely using their account credentials.
- Verify their accounts using an OTP sent to their student email.
- Create and manage their profiles.
- Browse available products.
- View product information and prices.
- List their own products for sale.
- Manage their sales and purchases.
- Add products to their shopping cart.
- Proceed to checkout and complete payments.

### 4.2 Administrators

Administrators are responsible for monitoring and managing the marketplace. Their functionality will include:

- Accessing a dedicated administrator account.
- Monitoring user activity.
- Managing registered users.
- Reviewing and managing product listings.
- Monitoring reported or suspicious activities.
- Helping prevent fraudulent transactions.
- Overseeing marketplace operations.
- Maintaining a safe and reliable environment for users.

## 5. Functional Requirements and Features

### 5.1 User Registration and Login

The application will provide registration and login pages for new and existing users.

- New users will be able to create accounts.
- Registered users will be able to log in.
- Students and administrators will have appropriate access permissions.
- OTP verification will be sent to the student's email address to improve account security.

### 5.2 User Profile and Navigation

A navigation taskbar will provide access to the main marketplace sections.

Users will be able to access:

- Their personal profiles.
- Buying options.
- Selling options.
- Product catalogue.
- Shopping cart and checkout.

### 5.3 Product Catalogue

The product catalogue will allow students to explore products listed by other users.

Features include:

- Displaying available products.
- Viewing product descriptions and prices.
- Browsing different product listings.
- Accessing information about items available for purchase.
- Supporting students who want to advertise their own products.

### 5.4 Buying and Selling

ITEMHIVE will support both buying and selling activities.

Students will be able to:

- Advertise products they want to sell.
- Manage their listed products.
- Browse items advertised by other students.
- Select products they wish to purchase.
- Monitor their buying and selling activities.

### 5.5 Shopping Cart and Checkout

The checkout functionality will provide a process for students to review their selected products before completing a purchase.

This section will support:

- Reviewing selected products.
- Checking product prices.
- Confirming purchase details.
- Proceeding to payment.

### 5.6 Payment Integration

A payment system will be incorporated to allow users to complete transactions through the application.

The payment functionality will aim to provide a convenient and secure purchasing experience.

### 5.7 Administrator Panel

The administrator panel will provide management and monitoring functionality.

Administrators will be able to oversee marketplace activities, manage users and listings, and identify potentially suspicious behaviour.

## 6. Security Requirements

Security is an important consideration in the development of ITEMHIVE.

The application aims to incorporate the following security measures:

- Secure user authentication.
- OTP email verification.
- Role-based access for students and administrators.
- Protection of user account information.
- Monitoring of suspicious marketplace activities.
- Administrative oversight to help prevent fraud.
- Secure handling of payment-related information.

## 7. Technologies and Development Tools

The project uses a combination of technologies for frontend development, backend development, database management and version control.

| Technology | Purpose |
|---|---|
| Java | Backend programming |
| Spring Boot | Backend application framework |
| React | Frontend development |
| SQL | Database management |
| IntelliJ IDEA | Development environment |
| Git | Version control |
| GitHub | Source code hosting and collaboration |

## 8. System Architecture

ITEMHIVE follows a frontend and backend application structure.

**Frontend:**

The React application provides the user interface through which students and administrators interact with the system. It includes the login and registration pages, product catalogue, user profiles, buying and selling functionality, and other marketplace features.

**Backend:**

The Spring Boot application handles business logic, user authentication, product management, marketplace operations and communication between the application and the database.

**Database:**

The SQL database stores and manages application information, including user accounts, product listings and other relevant marketplace data.

**API Communication:**

The frontend communicates with the backend through API endpoints. Axios is used to send requests and receive responses between the React frontend and Spring Boot backend.

## 9. Development Methodology

ITEMHIVE is developed through a structured software development process that includes:

1. **Requirements Gathering:** Identifying the needs of students and administrators and documenting the functional requirements.
2. **System Analysis:** Analysing the requirements and determining how the application should operate.
3. **System Design:** Preparing system diagrams, database structures and interface mock-ups where required.
4. **Frontend Development:** Developing the user interface and marketplace pages.
5. **Backend Development:** Implementing the application logic, APIs and database operations.
6. **Integration:** Connecting the frontend and backend components.
7. **Testing:** Identifying and correcting errors while ensuring that the application meets its requirements.
8. **Feedback and Improvements:** Using participant feedback to improve the application's usability and functionality.

## 10. Current Development Focus

The development team is focusing on finalising the functional requirements, ensuring that administrative functionality is included, preparing system diagrams and mock-ups where necessary, and following up on feedback from participants.

The development process will continue through implementation, integration and testing to ensure that the final application meets its intended objectives.

## 11. Project Vision

ITEMHIVE aims to become a convenient and trusted digital marketplace for students, making it easier to find affordable products, sell unused items and connect with other members of the student community.

**ITEMHIVE – Buy Smart. Sell Easy. Connect Through Campus.**
