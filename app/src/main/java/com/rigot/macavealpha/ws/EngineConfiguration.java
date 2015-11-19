package com.rigot.macavealpha.ws;

import org.restlet.engine.Engine;
import org.restlet.engine.connector.HttpClientHelper;
import org.restlet.ext.jackson.JacksonConverter;


/**
 * Auteur : Seb
 * Date de creation : 27/09/2015
 */
public class EngineConfiguration {

    public final static String gae_path = "http://apicaveavin.appspot.com/";
    private static EngineConfiguration ourInstance = new EngineConfiguration();

    public EngineConfiguration() {
        Engine.getInstance().getRegisteredConverters().add(new JacksonConverter());
        Engine.getInstance().getRegisteredClients().add(new HttpClientHelper(null));
    }

    public static EngineConfiguration getInstance() {
        return ourInstance;
    }

}
