package ipd.factorymethod;

import java.util.Properties;
import ipd.factorymethod.impl.MySQLDBAdapter;
import ipd.factorymethod.impl.OracleDBAdapter;
import ipd.factorymethod.impl.PostgresDBAdapter;
import ipd.factorymethod.util.PropertiesUtil;


public class DBFactory {
    private static final String DB_FACTORY_PROPERTY_URL = "ipd/factorymethod/properties/DBFactory.properties";
    private static final String DEFAULT_DB_CLASS_PROP = "defaultDBClass";

    public static IDBAdapter getDBadapter(DBType dbType){
        switch (dbType){
            case MySQL:
                return new MySQLDBAdapter();
            case Oracle:
                return new OracleDBAdapter();
            case Postgres:
                return new PostgresDBAdapter();                
            default:
                throw new IllegalArgumentException("No soportado");
        }
    }

    public static IDBAdapter getDefaultDBAdapter(){
        try{
            Properties prop = PropertiesUtil.loadProperty(DB_FACTORY_PROPERTY_URL);
            String defaultDBClass = prop.getProperty(DEFAULT_DB_CLASS_PROP);
            System.out.println("DefaultClass ===>" + defaultDBClass);
            return (IDBAdapter)Class.forName(defaultDBClass).newInstance();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
} // fin clase

