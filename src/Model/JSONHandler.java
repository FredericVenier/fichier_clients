package Model;

import java.io.*;
import java.text.SimpleDateFormat;
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

    public static void saveAllClients(List<Client> clients) {
        JSONObject obj = new JSONObject();

        //on remplit un JSONArray avec les noms/prenoms des clients
        JSONArray jsonClients = new JSONArray();
        for(int i=0; i<clients.size(); i++) {
            Client client = clients.get(i);
            JSONObject objClient = new JSONObject();
            objClient.put("firstname", client.getFirstname());
            objClient.put("lastname", client.getLastname());
            objClient.put("hashcode", client.getHashCode());

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
        String filePath_client = filePath + "clients" + File.separator + client.getHashCode() + ".json";

        //on remplit un JSONArray avec les infos des clients (email, prestations, ...)
        JSONObject objClient = new JSONObject();
        objClient.put("email", client.getEmail());
        objClient.put("phonenumber", client.getPhoneNumber());
        objClient.put("address", client.getAddress());

        try (FileWriter file = new FileWriter(filePath_client)) {
            file.write(objClient.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void savePrestations(Client client) {
        String filePath_clientPrestations = filePath + "clients" + File.separator + "prestations" + File.separator + client.getHashCode() + ".json";

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        //on remplit un JSONArray avec les infos des clients (email, prestations, ...)
        JSONObject objClient = new JSONObject();

        JSONArray jsonPrestations = new JSONArray();
        for(int i=0; i<client.getPrestations().size(); i++) {
            Prestation prestation = client.getPrestations().get(i);

            JSONObject objPrestation = new JSONObject();
            objPrestation.put("date", formatter.format(prestation.getDate()));
            objPrestation.put("description", prestation.getDescription());
            objPrestation.put("price", prestation.getPrice());

            jsonPrestations.add(objPrestation);
        }

        objClient.put("prestations", jsonPrestations);

        try (FileWriter file = new FileWriter(filePath_clientPrestations)) {
            file.write(objClient.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Client> loadAllClients() {
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
                if(innerObj.get("hashcode") != null) client.setHashCode((String) innerObj.get("hashcode"));

                clients.add(client);
            }

        } catch (FileNotFoundException ex) {
            return null;
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public static void loadClient(Client client) {
        String filePath_client = filePath + "clients" + File.separator + client.getHashCode() + ".json";

        try {
            //on ouvre le json
            FileReader reader = new FileReader(filePath_client);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            //on recupere l'email
            if(jsonObject.get("email") != null) client.setEmail((String) jsonObject.get("email"));
            if(jsonObject.get("address") != null) client.setAddress((String) jsonObject.get("address"));
            if(jsonObject.get("phonenumber") != null) client.setPhoneNumber((String) jsonObject.get("phonenumber"));

        } catch (FileNotFoundException ex) {
            return;
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadPrestations(Client client) {
        String filePath_clientPrestations = filePath + "clients" + File.separator + "prestations" + File.separator + client.getHashCode() + ".json";

        try {
            //on ouvre le json
            FileReader reader = new FileReader(filePath_clientPrestations);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            JSONArray jsonPrestations = (JSONArray) jsonObject.get("prestations");
            Iterator i = jsonPrestations.iterator();
            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();

                Prestation prestation = new Prestation();

                if(innerObj.get("date") != null) prestation.setDate((String) innerObj.get("date"));
                if(innerObj.get("description") != null) prestation.setDescription((String) innerObj.get("description"));
                if(innerObj.get("price") != null) prestation.setPrice((float)(double) innerObj.get("price"));

                client.addPrestation(prestation);
            }

        } catch (FileNotFoundException ex) {
            return;
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteClient(Client client) {
        String filePath_client = filePath + "clients" + File.separator + client.getHashCode() + ".json";
        File fileClient = new File(filePath_client);
        fileClient.delete();

        String filePath_clientPrestations = filePath + "clients" + File.separator + "prestations" + File.separator + client.getHashCode() + ".json";
        File filePrestations = new File(filePath_clientPrestations);
        filePrestations.delete();
    }
}
