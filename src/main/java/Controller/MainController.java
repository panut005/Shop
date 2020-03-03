package Controller;

import ConnectDatabase.ProductDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Item;
import model.Product;

import javax.swing.text.TabableView;
import java.io.IOException;
import java.util.ArrayList;

public class MainController {
    @FXML
    private Button buttonBack,Submit,Delete,add;
    @FXML
    private TableView<Item> tableView;
    @FXML
    private  TableColumn Item,Price,Quantity,Amount;
    @FXML
    private  Text total;
    @FXML
    private TextField QuantityTF,search;

    private ProductDataBase productDataBase =new ProductDataBase();
    ArrayList<Item> arrayList=new ArrayList<>();

    @FXML
    public void initialize(){
        Item.setCellValueFactory(new PropertyValueFactory<Product,String>("item"));
        Price.setCellValueFactory(new PropertyValueFactory<Product,Double>("price"));
        Quantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        Amount.setCellValueFactory(new PropertyValueFactory<Product,Double>("amount"));
        Item.setStyle("-fx-alignment: CENTER;");
        Price.setStyle("-fx-alignment: center-right;");
        Quantity.setStyle("-fx-alignment: center-right;");
        Amount.setStyle("-fx-alignment: center-right;");
        arrayList.add(new Item("-",0,0,0));
        showTable(arrayList);
        tableView.setEditable(true);
    }

    public ObservableList<Item> addData(ArrayList<Item> data){
        ObservableList<Item> temp= FXCollections.observableArrayList();
        for (Item i:data){
            temp.add(i);
        }
        return temp;
    }
    void showTable(ArrayList<Item> arrayList){
        tableView.setItems(addData(arrayList));
        double t=0;
        for (Item i:arrayList){
            t=t+i.getAmount();
        }
        total.setText("total price : "+String.format("%,.2f",t)+" bath");
    }


    @FXML
    public void handlebuttonAdd(ActionEvent event) throws IOException{
        String s= search.getText();
        int q = Integer.parseInt(QuantityTF.getText());
        Product product =productDataBase.getProduct(s);
        if(arrayList.get(0).getItem().equals("-")){
            arrayList.remove(0);
        }
        double p =product.getPrice();
        double a =p*q;
        arrayList.add(new Item(product.getName(),p,q,a));
        showTable(arrayList);

    }

    @FXML
    public void deletHandle(ActionEvent event)throws Exception{
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        arrayList.remove(selectedItem);
        tableView.getItems().remove(selectedItem);
        showTable(arrayList);
    }


    @FXML
    public void handlebuttonBack(ActionEvent event) throws IOException {
        buttonBack= (Button) event.getSource();
        Stage stage = (Stage)buttonBack.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();
    }


}
