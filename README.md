# Emergency Informations Bangladesh

This is an emergency information management system for my System Analysis and Design lab project.

Here every citizen will have a unique id and QR code.

With the id or QR, random citizens will be able to view emergency information(name, blood group, emergency contact) of a citizen. Also, hospitals and police stations will be able to create, read and edit medical records and criminal records of every citizen.

Multi-user access, image uploads, text formatting, viewing data in graphs, QR code generations and many other features are implemented in this project.

## Build and Run

1. Clone the repo and open with your favorite IDE

2. Let your IDE automatically download the dependencies

3. Create a MySql database by name **eib**

4. Import the **database-backup/eib-bk.sql** file into your MySQL server

5. Open the application.properties file and set your database credentials

6. Run the app and goto [http://localhost](http://localhost/)

### Demo Email and Passwords
 **Admin**
_ad@a.c_
_a_
**Hospital**
_h@a.c_
 _a_
**Police**
 _p@a.c_
 _a_
**Citizen**
Use citizen ID: 2 for search
_a@a.c_
 _a_
