package com.example.adrianpanaderogonzalez.pruebasbd.BasedeDatos;

/**
 * Created by adrianpanaderogonzalez on 22/3/18.
 */

public class QueryBuilder {

    /**
     * Specify your database name here
     * @return
     */
    public String getDatabaseName() {
        return "noticias";
    }

    /**
     * Specify your MongoLab API here
     * @return
     */
    public String getApiKey() {
        return "AkF2jtWisCYb4wZoibvcPO60HUUQRKC1";
    }

    /**
     * This constructs the URL that allows you to manage your database,
     * collections and documents
     * @return
     */
    public String getBaseUrl()
    {
        return "https://api.mongolab.com/api/1/databases/" + getDatabaseName() + "/collections/";
    }

    /**
     * Completes the formating of your URL and adds your API key at the end
     * @return
     */
    public String docApiKeyUrl()
    {
        return "?apiKey="+getApiKey();
    }

    /**
     * Returns the alertas collection
     * @return
     */
    public String documentRequest()
    {
        return "alertas";
    }

    /**
     * Builds a complete URL using the methods specified above
     * @return
     */
    public String buildAlertasGetURL()
    {
        return getBaseUrl()+documentRequest()+docApiKeyUrl();
    }

    /**
     * Formats the contact details for MongoHQ Posting
     * @param contact: Details of the person
     * @return
     */
    /*public String createContact(MyContact contact)
    {
        return String
                .format("{\"document\"  : {\"first_name\": \"%s\", "
                                + "\"last_name\": \"%s\", \"email\": \"%s\", "
                                + "\"phone\": \"%s\"}, \"safe\" : true}",
                        contact.first_name, contact.last_name, contact.email, contact.phone);
    }*/



}
