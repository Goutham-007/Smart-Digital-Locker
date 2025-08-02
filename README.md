Smart Digital Locker

Smart Digital Locker is a secure web application built using React (Frontend) and Spring Boot (Backend) that allows users to safely upload, manage, and access their personal documents digitally. It aims to replace physical lockers and streamline document accessibility with modern cloud storage and authentication.

Author

J Goutham Raj – [GitHub Profile](https://github.com/Goutham-007)


Features

Secure user registration and login
Role-based access (Admin and User)
Upload, download, and delete personal documents
Categorized digital storage
Audit trails and activity logs
PDF preview and metadata extraction
JWT-based authentication
RESTful API design

Tech Stack

Frontend
React.js
Axios
React Router
Material UI / Tailwind (your choice)

Backend
Spring Boot
Spring Security
JWT Authentication
Hibernate + JPA
 MySQL(Database)


Folder Structure

Smart-Digital-Locker/
|
  Backend # Spring Boot backend
  |
  src/main/java/... # Java source code
  
  
| Frontend # React frontend
    src/ # React components

Backend Setup

cd Backend
Configure `application.properties` with your DB credentials
./mvnw spring-boot:run

Frontend Setup

cd Frontend
npm install
npm start

API Endpoints

| Method | Endpoint                      | Description                               | Access   |
|--------|-------------------------------|-------------------------------------------|----------|
| POST   | `/api/users/register`         | Register a new user                       | Public   |
| POST   | `/api/users/login`            | Authenticate and login user               | Public   |
| POST   | `/api/files/upload`           | Upload a file to digital locker           | Authenticated |
| GET    | `/api/files`                  | Get all files of the logged-in user       | Authenticated |
| GET    | `/api/files/download/{id}`    | Download a file by ID                     | Authenticated |
| DELETE | `/api/files/{id}`             | Delete a file by ID                       | Authenticated |


Snapshots

Register
<img width="700" height="540" alt="image" src="https://github.com/user-attachments/assets/759b7a50-4454-401b-86cb-b2b6b91f2785" />

Login
<img width="706" height="501" alt="image" src="https://github.com/user-attachments/assets/0fc3e862-f34b-42c3-b0c7-72fd802ac5b9" />

Dashboard
<img width="706" height="417" alt="image" src="https://github.com/user-attachments/assets/d368d066-5e5b-42c1-b6d6-6cfeae60e68e" />




