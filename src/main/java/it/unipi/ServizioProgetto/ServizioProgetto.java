package it.unipi.ServizioProgetto;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServizioProgetto {
    
    private static final Logger logger = LogManager.getLogger(ServizioProgetto.class);
    
    public void initializeDatabase(){  
        try(Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306/564817", "root", "root");
            Statement st = co.createStatement();){
            //stabilisco una connessione col db

            //dal momento che non stiamo ancora gestendo richieste provenienti dall'applicazione ma stiamo
            //svolgendo operazioni di inizializzazione, utilizzo un linguaggio di più basso livello per gestire le query
            
            //controllo se ci sono già dei dati nel db. Nel caso fosse così, posso anche fermarmi.
            ResultSet rs = st.executeQuery("SELECT * FROM anime;");
            
            if(!rs.next()){
                //devo fare un ciclo di 4 perché il sito ha un limite massimo di 25 entrate per pagina,
                //per cui faccio 4 connessioni a 4 pagine diverse per poter prendere 100 record.
                long id = 1;
                for(int i = 1; i < 5; i++){
                    //Stabilisco la connessione con il sito che fornisce l'API
                    URL url = new URL("https://api.jikan.moe/v4/top/anime?page=" + i + "&type=tv&sfw=true");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");

                    //Mi attrezzo per leggere i dati in input
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String inputLine;
                    StringBuffer content = new StringBuffer();
                    while((inputLine = in.readLine()) != null){
                        content.append(inputLine);
                    }
                    in.close();
                    
                    //Creo un nuovo elemento json in modo da poter formattare i dati nel modo corretto
                    Gson gson = new Gson();

                    JsonElement json = gson.fromJson(content.toString(), JsonElement.class);
                    JsonObject rootObject = json.getAsJsonObject();
                    JsonArray anime = rootObject.get("data").getAsJsonArray();

                    PreparedStatement ps = co.prepareStatement("INSERT INTO anime (id, name) VALUES (?, ?)");   
                    
                    //Inserisco 25 record nel database
                    for(int j = 0; j < anime.size(); j++){
                        JsonObject jo = anime.get(j).getAsJsonObject();
                        ps.setLong(1, id++);
                        ps.setString(2, jo.get("title").getAsString());
                        ps.executeUpdate();
                    }
                    
                    //Quando arrivo all'ultimo ciclo, ossia ho inserito 100 record, cambio il valore del prossimo
                    //id da inserire in modo che da questo momento in poi, il processo sia automatizzato
                    if(i == 4){
                        ps = co.prepareStatement("UPDATE hibernate_sequence SET next_val="+id);
                        ps.executeUpdate();
                    }                       
                }
                logger.info("Il database inizialmente vuoto è stato inizializzato");
                return;
            }                   
        }catch(IOException|SQLException e){
                logger.error(e.getMessage());
        }                      
        logger.info("Il database è già inizializzato, perciò non è stato generato");
    }

    public static void main(String[] args) {
        SpringApplication.run(ServizioProgetto.class, args);
        //all'avvio del servizio, controllo sul database se sono presenti dei dati, e se non lo sono, li inizializzo.
        ServizioProgetto server = new ServizioProgetto();
        server.initializeDatabase();
    }

}