# ADP3-Assignment1-2026
UML class diagram which will be saved in the Readme.md file of the project solution.

<img width="1351" height="1362" alt="class-diagram png" src="https://github.com/user-attachments/assets/f429870e-ca86-4d26-a85c-17f6907ad71b" />


# ITEMHIVE – Student Marketplace Application

**Connecting Students Through Buying and Selling**

## 1. Project Overview

ITEMHIVE is a Student Marketplace Application developed to provide students with a centralised, convenient and secure online platform where they can buy and sell products within the student community.

The application aims to simplify the process of buying and selling by allowing students to create accounts, browse available products, advertise their own items and complete purchases through an integrated checkout process.

The platform is designed with both students and administrators in mind. Students will have access to marketplace features that allow them to manage their buying and selling activities, while administrators will be able to monitor and manage the platform.

ITEMHIVE aims to create a reliable digital marketplace that encourages student interaction, accessibility, convenience and safe online transactions.

## 2. Project Purpose

The purpose of ITEMHIVE is to address the need for an accessible marketplace where students can conveniently exchange products and services within their student community.

Students often need affordable second-hand products, textbooks, electronic devices, stationery and other essential items. ITEMHIVE provides a platform where students can advertise unwanted items and connect with potential buyers.

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

ITEMHIVE supports different user roles, each with specific permissions and responsibilities.

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

The application provides registration and login pages for new and existing users.

- New users can create accounts.
- Registered users can log in.
- Students and administrators have appropriate access permissions.
- OTP verification is sent to the student's email address to improve account security.

### 5.2 User Profile and Navigation

A navigation taskbar provides access to the main marketplace sections.

Users can access their personal profiles, buying options, selling options, product catalogue, shopping cart and checkout.

### 5.3 Product Catalogue

The product catalogue allows students to explore products listed by other users, view product descriptions and prices, browse listings, and advertise their own products.

### 5.4 Buying and Selling

Students can advertise and manage products for sale, browse items from other students, select products to purchase, and monitor their buying and selling activities.

### 5.5 Shopping Cart and Checkout

The checkout functionality allows students to review selected products and prices, confirm purchase details, and proceed to payment.

### 5.6 Payment Integration

A payment system allows users to complete transactions through the application.

### 5.7 Administrator Panel

The administrator panel provides management and monitoring functionality for users, listings, and potentially suspicious behaviour.

## 6. Security Requirements

Security is an important consideration in the development of ITEMHIVE. The application aims to incorporate secure user authentication, OTP email verification, role-based access, protection of user account information, monitoring of suspicious activity, and secure handling of payment-related information.

## 7. Technologies and Development Tools

| Technology | Purpose |
|---|---|
| Java | Backend programming |
| Spring Boot | Backend application framework |
| React | Frontend development |
| SQL | Database management |
| IntelliJ IDEA | Development environment |
| Git | Version control |
| GitHub | Source code hosting and collaboration |

## 8. Email OTP setup

The backend sends registration and resend verification codes to the email address supplied by the user. Configure SMTP credentials as environment variables before starting the backend; the code is never printed to the terminal.

For Gmail, enable 2-Step Verification and create an **App Password** (use the app password, not your normal account password). Set:

- `MAIL_USERNAME` — the Gmail address used to send OTP emails.
- `MAIL_PASSWORD` — the Gmail App Password.
- `MAIL_FROM` — optional sender address; defaults to `MAIL_USERNAME`.

Example for macOS/Linux:

```bash
export MAIL_USERNAME="your-email@gmail.com"
export MAIL_PASSWORD="your-16-character-app-password"
export MAIL_FROM="$MAIL_USERNAME"
./mvnw spring-boot:run
```

Keep these values out of source control. For production, configure them as secrets in the hosting provider's environment settings. The frontend displays the address entered during registration and provides a resend-code option; login requires the account to be verified first.

## 9. System Architecture

ITEMHIVE follows a frontend and backend application structure. The React frontend provides the user interface; Spring Boot handles business logic, authentication, product management, and APIs; SQL stores application data; Axios communicates with backend endpoints.

## 10. Development Methodology

The project follows requirements gathering, system analysis and design, frontend/backend implementation, integration, testing, and feedback-driven improvements.

## 11. Project Vision

ITEMHIVE aims to become a convenient and trusted digital marketplace for students, making it easier to find affordable products, sell unused items and connect with other members of the student community.

**ITEMHIVE – Buy Smart. Sell Easy. Connect Through Campus.**
