package ipd.factorymethod;

import java.sql.SQLException;
import java.util.List;

import ipd.factorymethod.dao.ProductDAO;
import ipd.factorymethod.entity.Product;

public class FactoryMain {
    public static void main(String[] args) throws SQLException {
        //Creamos los nuevos productos a registrar.
        Product productA = new Product(1L, "Producto Maria A", 100);
        Product productB = new Product(2L, "Producto Maria B", 100);
        Product productC = new Product(3L, "Producto Maria C", 200);                
        
        //Creamos una instancia del DAO
        ProductDAO productDAO = new ProductDAO();

        //Persistimos los productos
        productDAO.saveProduct(productA);
        productDAO.saveProduct(productB);
        productDAO.saveProduct(productC);
        


        //Consultamos nuevamente los productos
        List<Product> products = productDAO.findAllProducts();
        System.out.println("Product size ==>" + products.size());
        for (Product product:products){
            System.out.println("ID: " + product.getIdProduct());
            System.out.println("Nombre: " + product.getProductName());
            System.out.println("Precio: " + product.getPrice());
            System.out.println("\n" );
        }
    }
} //End class
