package ConnectDatabase;
//panut
import model.Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductDataBase {
    private static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";

    public  ArrayList<Product> getAllProduct(){
        ArrayList<Product>products=new ArrayList<>();
        try{
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "select * from Product";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    String id =resultSet.getString("ID");
                    String name=resultSet.getString("Name");
                    int a = resultSet.getInt("Quantity");
                    double p = resultSet.getDouble("Price");
                    String url = resultSet.getString("urlImage");
                    products.add(new Product(id,name,p,a,url));
                }
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void addProductToDB(Product product){
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "insert into Product (ID,Name,Quantity,Price,urlImage) values " +
                        "('"+product.getId()+"','"+product.getName()+"',"+product.getQuantity()+","+product.getPrice()+",'"+product.getUrlImage()+"')";
                Statement p = connection.createStatement();
                p.executeUpdate(query);
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void deleteProduct(Product product){
        try{
            Class.forName(dbName);
            Connection connection = DriverManager .getConnection(dbURL);
            if(connection != null){
                String query  = "Delete from Product where ID == '" + product.getId() + "'";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void update(Product product){
        try {
            Class.forName(dbName);
            Connection connection = DriverManager .getConnection(dbURL);
            if(connection != null){
                String query  = " UPDATE Product SET Name= '"+product.getName()+"',Quantity = "+product.getQuantity()
                        +",Price = "+product.getPrice()+" WHERE ID = "+product.getId()+";";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
                System.out.println("update complete");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  Product getProduct(String productID){

        Product product = null;
        try{
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "select * from Product WHERE Product.ID='"+productID+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                String id =resultSet.getString("ID");
                String name=resultSet.getString("Name");
                int a = resultSet.getInt("Quantity");
                double p = resultSet.getDouble("Price");
                String url = resultSet.getString("urlImage");
                product=new Product(id,name,p,a,url);
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }


    public ArrayList<Product> getAllProductS() {
        ArrayList<Product>products=new ArrayList<>();
        try{
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "select * from Product";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    String id =resultSet.getString("ID");
                    String name=resultSet.getString("Name");
                    int a = resultSet.getInt("Quantity");
                    double p = resultSet.getDouble("Price");
                    String url = resultSet.getString("urlImage");
                    products.add(new Product(id,name,String.format("%,.2f",p),String.format("%,d",a),url));
                }
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}