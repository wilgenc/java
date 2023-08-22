package ipd.factorymethod.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
//import oracle.jdbc.OracleDriver;
import ipd.factorymethod.IDBAdapter;
import ipd.factorymethod.util.PropertiesUtil;

public class OracleDBAdapter implements IDBAdapter {
    
    private static final String DB_PROPERTIES = "ipd/factorymethod/properties/DBOracle.properties";

    private static final String DB_SERVICE_PROP ="service";
    private static final String DB_HOST_PROP = "host";
    private static final String DB_PASSWORD_PROP ="password";
    private static final String DB_PORT_PROP ="port";
    private static final String DB_USER_PROP ="user";

    static {
        // Bloqueo para resgistrar el Driver de Oracle
        try {
            new org.oracle.OracleDriver();
        }catch (Exception e){
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
        String service = prop.getProperty(DB_SERVICE_PROP);
        String user = prop.getProperty(DB_USER_PROP);
        String password = prop.getProperty(DB_PASSWORD_PROP);

        String connectionString = "jbdc:oracle:thin:" 
                + user + "/" + password + "@//" +host+ ":" +port + "/" + service;
        System.out.println("ConnectionString ==> " + connectionString);
        return connectionString;        

    }
} // fin de la clase
