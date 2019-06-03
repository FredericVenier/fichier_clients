package Controleur;

import FichierClients.MenuViews;

import java.awt.*;

public class ViewClient {

    public ViewClient() {

    }

    public void retour() {
        MenuViews.setViewSearch();
    }

    public void nouveau() {
        MenuViews.setViewNewPrestation();
    }
}
