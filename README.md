# Poke

## Panoramica
Questa applicazione Android mostra una lista ed un dettaglio dei __Pokemon__

## Architettura
L'applicazione è strtutturata con 2 Android Activity, una per la lista ed una per il dettaglio Pokemon; 
è stato utilizzato un pattern MVI mediante la libreria ([Uniflow](https://github.com/uniflow-kt/uniflow-kt)) 
ed il concetto di repository come sorgente dati.
Le varie componenti dell'applicazione sono iniettate mediante Hilt, in particolare i view models / data flow 
ed i moduli necessari alla configurazione del client di rete (Retrofit+OkHttp+Moshi).
Il caricamento delle immagini da URL avviene mediante la libreria Glide.

### Lista
La lista dei Pokemon è alimentata da una sorgente dati (REST) paginata. Questa è stata integrata
mediante [paging library di Google Jetpack](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) 
e offre quindi l'effetto di __infinte scroll__ sulla UX.

### Dettaglio
La vista dettaglio mostra una pagina scrollabile con effetto __collapsing toolbar__ in cui vengono mostrate le info di 
base del Pokemon, una lista delle tipologie (su lista orizzontale) , le statistiche di base ed un carosello con le altre immagini del Pokemon.
Per la visualizzazione della singola statistica è stato realizzato un __Widget__ custom.

## Test
A titolo esemplificativo, è stato implementato un __androidTest__ **Espresso** sulla prima schermata lista.
