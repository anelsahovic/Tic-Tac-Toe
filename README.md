# 🕹️ Tic Tac Toe Desktop App

A modern desktop-based **Tic Tac Toe** application built using **JavaFX**. This app includes a profile system, game modes (Player vs Player, Player vs Computer), match analytics, and full profile CRUD functionality.

## ✨ Features

- 👤 **User Profiles**: Create, edit, and delete player profiles.
- 🎮 **Game Modes**:
    - Player vs Player
    - Player vs Computer (basic AI)
- 📊 **Analytics**: View stats and history of played matches.
- 🗃️ **Database Integration** using MySQL and Flyway.
- 🎯 Clean architecture with Flyway migration and dummy data for testing.

---

## 🛠 Tech Stack

- Java 22
- JavaFX 22 (FXML & Controls)
- MySQL 8
- Flyway for DB migrations
- Maven for build management
- JUnit 5 for testing

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/tictactoe-app.git
cd tictactoe-app
```
### 2. Set Up Your Environment
- Open the project in your favorite IDE (e.g., IntelliJ IDEA or VS Code).

- Set the module SDK to Java 22.

- Ensure JavaFX SDK 22 is properly configured.

### 3. Create Local Database
   Make sure you have MySQL running locally and create a database named:
   
```bash
CREATE DATABASE tictactoe;
```
#### The default DB config uses:

Username: root

Password: 12345678

You can change these in the Database class .

## 🧰 Dependencies
#### Some key dependencies from pom.xml:

- mysql-connector-j (8.0.32)

- flyway-core (8.2.0)

- javafx-controls, javafx-fxml (22-ea+11)

- junit-jupiter-api, junit-jupiter-engine (5.10.0)

## 📷 Screenshots


![Screenshot](/public/image1.png)
![Screenshot](/public/image2.png)
![Screenshot](/public/image3.png)
![Screenshot](/public/image4.png)
![Screenshot](/public/image5.png)
![Screenshot](/public/image6.png)
![Screenshot](/public/image7.png)
![Screenshot](/public/image8.png)
![Screenshot](/public/image9.png)

