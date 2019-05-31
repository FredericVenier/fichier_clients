package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONHandler {
    private static final String filePath = "res" + File.separator+ "data" +File.separator+ "json" + File.separator;
    private static final String filePath_clients = filePath + "clients.json";

    public JSONHandler() {

    }

    public static void saveClients(List<Client> clients) {
        JSONObject obj = new JSONObject();

        //on remplit un JSONArray avec les noms/prenoms des clients
        JSONArray jsonClients = new JSONArray();
        for(int i=0; i<clients.size(); i++) {
            Client client = clients.get(i);
            JSONObject objClient = new JSONObject();
            objClient.put("firstname", client.getFirstname());
            objClient.put("lastname", client.getLastname());

            jsonClients.add(objClient);
        }

        obj.put("clients", jsonClients);

        try (FileWriter file = new FileWriter(filePath_clients)) {
            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveClient(Client client) {
        String filePath_client = filePath + "clients" + File.separator + client.getFirstname() + "_" + client.getLastname() + ".json";

        //on remplit un JSONArray avec les infos des clients (email, prestations, ...)
        JSONObject objClient = new JSONObject();
        objClient.put("email", client.getEmail());

        JSONArray jsonPrestations = new JSONArray();
        for(int i=0; i<client.getPrestations().size(); i++) {
            Prestation prestation = client.getPrestations().get(i);

            JSONObject objPrestation = new JSONObject();
            objPrestation.put("date", prestation.getDate().toString());
            objPrestation.put("description", prestation.getDescription());
            objPrestation.put("price", prestation.getPrice());

            jsonPrestations.add(objPrestation);
        }

        objClient.put("prestations", jsonPrestations);

        try (FileWriter file = new FileWriter(filePath_client)) {
            file.write(objClient.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Client> loadClients() {
        List<Client> clients = new ArrayList<>();

        try {
            //on ouvre le json
            FileReader reader = new FileReader(filePath_clients);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            //on recupere les songs
            JSONArray jsonClients = (JSONArray) jsonObject.get("clients");
            Iterator i = jsonClients.iterator();
            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();

                Client client = new Client();

                if(innerObj.get("firstname") != null) client.setFirstname((String) innerObj.get("firstname"));
                if(innerObj.get("lastname") != null) client.setLastname((String) innerObj.get("lastname"));

                clients.add(client);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public static void loadClient(Client client) {
        String filePath_client = filePath + "clients" + File.separator + client.getFirstname() + "_" + client.getLastname() + ".json";

        try {
            //on ouvre le json
            FileReader reader = new FileReader(filePath_client);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            //on recupere l'email
            if(jsonObject.get("email") != null) client.setEmail((String) jsonObject.get("emaim"));

            JSONArray jsonPrestations = (JSONArray) jsonObject.get("prestations");
            Iterator i = jsonPrestations.iterator();
            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();

                Prestation prestation = new Prestation();

                if(innerObj.get("date") != null) prestation.setDate((String) innerObj.get("date"));
                if(innerObj.get("description") != null) prestation.setDescription((String) innerObj.get("description"));
                if(innerObj.get("price") != null) prestation.setPrice((float) innerObj.get("price"));

                client.addPrestation(prestation);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
