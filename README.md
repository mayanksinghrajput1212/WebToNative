# WebToNative – Android Assignment

## 📱 Project Overview
The app allows users to open a website inside a WebView, maintain a local history of opened URLs, and upload that history to a dummy API.

---

## 🛠 Tech Stack
- **Language:** Kotlin  
- **UI:** XML (Material Components)  
- **Architecture:** Simple MVVM  
- **Navigation:** Navigation Component  
- **Local Storage:** Room Database  
- **Web Content:** WebView  
- **Networking:** Retrofit  
- **Dummy API:** Beeceptor  

---

## 📱 Screens & Features

### 1️⃣ Home Screen
- Top App Bar with **History button**
- URL input field
- **Open** button
- Image carousel (ViewPager2 – UI only)
- Dot indicators
- URL validation:
  - Empty input check
  - Invalid URL check
  - Trims spaces
  - Automatically adds `https://` if missing
- Saves every opened URL with timestamp into local database

---

### 2️⃣ WebView Screen
- Top App Bar with **Back** and **Close** buttons
- Displays currently loaded URL
- Loads the URL inside a WebView
- URL display updates as WebView navigates
- **Back:** returns to Home (URL retained)
- **Close:** returns to Home (URL cleared)

---

### 3️⃣ History Screen
- Top App Bar with **Back**, **Delete**, and **Upload** buttons
- RecyclerView showing:
  - URL
  - Timestamp
- **Delete:** clears all stored history
- **Upload:** uploads history data to a Beeceptor API endpoint

---

## 🗄 Local Database
- Implemented using **Room**
- Stores:
  - URL
  - Timestamp
- Automatically sorted by latest first

---

## 🌐 API Integration
- Uses **Retrofit**
- Dummy endpoint created using **Beeceptor**
- Uploads stored URL history as JSON

---

## 📂 Project Structure
com.example.webtonative
│
├── data
│ ├── db
│ │ ├── UrlEntity.kt
│ │ ├── UrlDao.kt
│ │ └── AppDatabase.kt
│ └── api
│ ├── ApiService.kt
│ └── RetrofitClient.kt
│
├── ui
│ ├── home
│ │ └── HomeFragment.kt
│ ├── webview
│ │ └── WebViewFragment.kt
│ └── history
│ ├── HistoryFragment.kt
│ └── HistoryAdapter.kt
│
├── utils
│ └── UrlUtils.kt
│
└── MainActivity.kt


---

## ▶️ How to Run the Project
1. Clone the repository  
2. Open the project in **Android Studio**
3. Sync Gradle
4. Run the app on an emulator or physical device (API 24+)

---

## 🧪 Edge Cases Handled
- Empty URL input
- Invalid URL formats
- Leading/trailing spaces
- Automatic `https://` prefix
- Proper back and close navigation handling

---

## 📸 Screenshots
(Add screenshots of Home, WebView, and History screens here)


