package ipd.factorymethod.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import ipd.factorymethod.IDBAdapter;
import ipd.factorymethod.util.PropertiesUtil;

public class PostgresDBAdapter implements IDBAdapter {
    
    private static final String DB_PROPERTIES = "ipd/factorymethod/properties/DBPostgres.properties";

    private static final String DB_NAME_PROP ="dbname";
    private static final String DB_HOST_PROP = "host";
    private static final String DB_PASSWORD_PROP ="password";
    private static final String DB_PORT_PROP ="port";
    private static final String DB_USER_PROP ="user";

    static {
        // Bloqueo para registrar el Driver de postgres
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            System.out.println("Error al registrar el driver de PostgresSQL: " + e);
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection(){
        try {
            String connectionString = createConnectionString();
            Connection connection = DriverManager.getConnection(connectionString);
            System.out.println("Connection class ==>" + connection.getClass().getName());
            return connection;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private String createConnectionString(){
        Properties prop = PropertiesUtil.loadProperty(DB_PROPERTIES);
        String host = prop.getProperty(DB_HOST_PROP);
        String port = prop.getProperty(DB_PORT_PROP);
        String dbname = prop.getProperty(DB_NAME_PROP);
        String user = prop.getProperty(DB_USER_PROP);
        String password = prop.getProperty(DB_PASSWORD_PROP);

        String connectionString = "jdbc:postgresql://" + host  + ":" + port + "/"  + dbname     
                + "?user=" + user + "&password=" + password; // + "&ssl=true";
        System.out.println("ConnectionString ==> " + connectionString);
        return connectionString;    
        
    }
} // fin de la clase
