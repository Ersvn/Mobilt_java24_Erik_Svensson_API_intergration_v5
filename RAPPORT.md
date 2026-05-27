## CVEye – Rapport

### Kort om projektet

Det här projektet är en Android-app byggd i Java. Appen använder ett CVE API för att hämta information om IT-säkerhet och sårbarheter. Syftet var att skapa en app som använder extern data och visar den på ett tydligt sätt i mobilen.

Jag valde CVE-data eftersom det är ett relevant område inom IT-säkerhet och passar bra för en app där man hämtar information från ett API.

### Funktioner i appen

Appen hämtar CVE-information från ett API och visar resultatet i en RecyclerView. Datan laddas in asynkront, vilket gör att appen inte låser sig medan informationen hämtas.

Projektet innehåller också flera fragment, vilket gör att appen blir mer uppdelad och inte bara består av en enda vy. Appen har även en horisontell vy på vissa delar av gränssnittet.

Efter feedbacken lade jag även till en enklare loading-indikator, så att användaren ser att appen håller på att ladda data.

### Tekniker som används

- Java
- Android Studio
- RecyclerView
- Fragment
- API-anrop
- Asynkron datahämtning

### Det jag har lärt mig

I det här projektet har jag fått träna mer på hur man bygger en Android-app som använder data från ett externt API. Jag har också fått mer förståelse för hur RecyclerView fungerar och hur man kan dela upp en app med fragments.

En viktig del var också att hantera att data inte finns direkt när appen startar, utan måste laddas in från internet först. Därför behövde appen hantera laddning och sedan uppdatera gränssnittet när datan var hämtad.

### Förbättringar

Det som hade kunnat förbättras mest är designen. Appen fungerar, men gränssnittet och appikonen hade kunnat göras mer genomarbetade.

Med mer tid hade jag även velat lägga till sökning eller filtrering så att användaren lättare kan hitta specifika CVE-posts.

#### APK

APK-filen är bifogad separat med namnet:

`CVEye_Erik_Svensson.apk`

Den är byggd som en debug APK för inlämning.