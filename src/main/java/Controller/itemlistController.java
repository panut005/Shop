package Controller;

import ConnectDatabase.ProductDataBase;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Product;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.swing.*;


public class itemlistController {
    @FXML
    private TableView<Product> tableView;
    @FXML
    private TableColumn ID,name,quantity,price;
    private ProductDataBase productDataBase =new ProductDataBase();
    @FXML
    private TextField textID,textName,textAmount, textPrice;
    @FXML
    private Button addBtn,deletBtn;
    @FXML
    private Label eID,eName,eAmount,ePrice;
    @FXML private ImageView upload;

    @FXML
    private AnchorPane webcamPane;
    private Webcam webCam = null;

    private File file;
    private String srcImage="";
    private Button buttonBack;
    final SwingNode swingNode = new SwingNode();


    LuminanceSource source =null;
    @FXML
    public void initialize(){
        ID.setCellValueFactory(new PropertyValueFactory<Product,String>("id"));
        ID.setStyle("-fx-alignment: CENTER;");
        name.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
        quantity.setCellValueFactory(new PropertyValueFactory<Product, String>("quantitys"));
        price.setCellValueFactory(new PropertyValueFactory<Product,String>("prices"));
        price.setStyle("-fx-alignment: center-right;");
        quantity.setStyle("-fx-alignment: center-right;");
        showTable();
        tableView.setEditable(true);
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        quantity.setCellFactory(TextFieldTableCell.forTableColumn());
        price.setCellFactory(TextFieldTableCell.forTableColumn());
        webcamPane =new AnchorPane();
        Webcam webcam = Webcam.getDefault();   //Generate Webcam Object
        webcam.setViewSize(new Dimension(640,480));
        WebcamPanel webcamPanel = new WebcamPanel(webcam,true);
        webcamPanel.setMirrored(false);
//        SwingUtilities.invokeLater(() -> {
//            if(webcamPanel != null) {
//                swingNode.setContent(webcamPanel);
//            }
//        });
//        Platform.runLater(() -> {
//            webcamPane.getChildren().clear();
//            webcamPane.getChildren().add(swingNode);
//        });
        JFrame jFrame = new JFrame();
        jFrame.add(webcamPanel);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        do {
            try {
                BufferedImage image = webcam.getImage();
                source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                Result result = new MultiFormatReader().decode(bitmap);
                if(result.getText() != null) {
                    System.out.println(result.getText());
                    webcam.close();
                    break;
                }

            }catch (NotFoundException e ) {
                //pass
            }

        } while(true);

    }









    public ObservableList<Product> addData(ArrayList<Product> data){
        ObservableList<Product> temp= FXCollections.observableArrayList();
        for (Product i:data){
            temp.add(i);
        }
        return temp;
    }
    void showTable(){
        tableView.setItems(addData(productDataBase.getAllProductS()));
    }

    @FXML
    public void handleAddbtn(ActionEvent event) throws Exception {
        if((textID.getText().isEmpty()||textName.getText().isEmpty()||textAmount.getText().isEmpty()||textPrice.getText().isEmpty())||srcImage.isEmpty()){
            if(textID.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR, "ID is Empty",ButtonType.OK);
                alert.showAndWait();
//               eID.setText("ID is Empty");
            } if (textName.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Name is Empty",ButtonType.OK);
                alert.showAndWait();
                //eName.setText("Amount is Empty");
            }if(textAmount.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Amount is Empty",ButtonType.OK);
                alert.showAndWait();
                //  eAmount.setText("Amount is Empty");
            }if(textPrice.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Price is Empty",ButtonType.OK);
                alert.showAndWait();
                //  ePrice.setText("Price is Empty");
            }if(srcImage.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Picture is Empty",ButtonType.OK);
                alert.showAndWait();
            }
        }else if(checkIDsame(textID.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR, "ID is Same",ButtonType.OK);
            alert.showAndWait();
            // eID.setText("ID is Same");
        }else if (checkNamesame(textName.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Name is Same",ButtonType.OK);
            alert.showAndWait();
            //eName.setText("ID is Same");
        }else if (!isAllNumberint(textAmount.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Amount is invaild",ButtonType.OK);
            alert.showAndWait();

        }else if (!isAllNumber(textPrice)){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Price is invaild",ButtonType.OK);
            alert.showAndWait();
        }else if(Integer.parseInt(textAmount.getText())<0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Amount is invaild",ButtonType.OK);
            alert.showAndWait();
        }else if(Double.parseDouble(textPrice.getText())<=0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Price is invaild",ButtonType.OK);
            alert.showAndWait();
        }
        else {
            String id=textID.getText();
            String name=textName.getText();
            int quantity=Integer.parseInt(textAmount.getText());
            double price=Double.parseDouble(textPrice.getText());
            Product product=new Product(id,name,price,quantity);

            productDataBase.addProductToDB(product);
            textID.clear();
            textName.clear();
            textAmount.clear();
            textPrice.clear();
            srcImage="";
            upload.setImage(null);
            showTable();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Complete",ButtonType.OK);
            alert.showAndWait();
        }
    }


    public void deletHandle(ActionEvent event)throws Exception{
        Product selectedItem = tableView.getSelectionModel().getSelectedItem();
        productDataBase.deleteProduct(selectedItem);
        tableView.getItems().remove(selectedItem);
        showTable();
    }


    public void onEditName(TableColumn.CellEditEvent cellEditEvent) {
        Product selectedItem = tableView.getSelectionModel().getSelectedItem();
        selectedItem.setName(cellEditEvent.getNewValue()+"");
        productDataBase.update(selectedItem);
        showTable();

    }
    public void onEditAmount(TableColumn.CellEditEvent cellEditEvent) {

        if(isAllNumberint(String.valueOf(cellEditEvent.getNewValue()))){
            Product selectedItem = tableView.getSelectionModel().getSelectedItem();
            System.out.println("kkkkkkkkkkkkkkkkkkkkk");
            if(Integer.valueOf(cellEditEvent.getNewValue()+"") <=0){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Amount is invaild",ButtonType.OK);
                alert.showAndWait();
            }else{
                selectedItem.setQuantity(Integer.valueOf(detecom(cellEditEvent.getNewValue()+"")));
                selectedItem.setPrice(Double.parseDouble(detecom(selectedItem.getPrices())));
                productDataBase.update(selectedItem);
            }
        }else{
            System.out.println("ssssssssssssss");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Amount is invaild",ButtonType.OK);
            alert.showAndWait();
        }
        showTable();
    }
    public void onEditPrice(TableColumn.CellEditEvent cellEditEvent) {
        if(isAllNumber(cellEditEvent.getNewValue()+"")){
            Product selectedItem = tableView.getSelectionModel().getSelectedItem();
            if(Double.valueOf(cellEditEvent.getNewValue()+"") <=0||Double.valueOf(cellEditEvent.getNewValue()+"")>=1000000){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Price is invaild",ButtonType.OK);
                alert.showAndWait();
            }else {
                selectedItem.setPrice(Double.valueOf(detecom(cellEditEvent.getNewValue()+"")));
                selectedItem.setQuantity(Integer.parseInt(detecom(selectedItem.getQuantitys())));
                productDataBase.update(selectedItem);
            }

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Price is invaild",ButtonType.OK);
            alert.showAndWait();
        }
        showTable();
    }
    Boolean checkNamesame(String name){
        ArrayList<Product>a= productDataBase.getAllProduct();
        for (Product product : a) {
            if(product.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    Boolean checkIDsame(String id){
        ArrayList<Product>a= productDataBase.getAllProduct();
        for (Product product : a) {
            if(product.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    @FXML
    public void loadPicture(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Choose \".png\" and \".jpg\" file", "*.png", "*.jpg"));
        file = fileChooser.showOpenDialog(upload.getScene().getWindow());
        String[] tmp = file.getAbsolutePath().split("\\\\");
        String path ="";
        for (int i = 0; i < tmp.length-1; i++) {
            path+=tmp[i]+"/";
            System.out.println(tmp[i]);
        }
        path+=tmp[tmp.length-1];
        System.out.println(path);
        upload.setImage(new Image("file:///"+path));
        System.out.println("file:///"+path);
        srcImage="file:///"+path;
    }
    public static boolean isAllNumber(TextField textField) {
        boolean isCorrect = true;
        for (int i = 0; i < textField.getText().length(); i++) {
            if (isCorrect) {
                if ((textField.getText().charAt(i) + "").matches("[0-9.]+")) {
                } else {
                    isCorrect = false;
                    textField.setStyle("-fx-border-color: red");
                    return isCorrect;
                }
            }
        }
        textField.setStyle("");
        return isCorrect;
    }
    public static boolean isAllNumber(String textField) {
        boolean isCorrect = true;
        for (int i = 0; i < textField.length(); i++) {
            if (isCorrect) {
                if ((textField.charAt(i) + "").matches("[0-9.]+")) {
                } else {
                    isCorrect = false;
                    return isCorrect;
                }
            }
        }
        return isCorrect;
    }
    @FXML
    public void handlebuttonBack(ActionEvent event) throws IOException {
        buttonBack= (Button) event.getSource();
        Stage stage = (Stage)buttonBack.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();
    }
    public static boolean isAllNumberint(TextField textField) {
        boolean isCorrect = true;
        for (int i = 0; i < textField.getText().length(); i++) {
            if (isCorrect) {
                if ((textField.getText().charAt(i) + "").matches("[0-9]+")) {
                } else {
                    isCorrect = false;
                    textField.setStyle("-fx-border-color: red");
                    return isCorrect;
                }
            }
        }
        textField.setStyle("");
        return isCorrect;
    }
    public static boolean isAllNumberint(String textField) {
        boolean isCorrect = true;
        for (int i = 0; i < textField.length(); i++) {
            if (isCorrect) {
                if (!(textField.charAt(i) + "").matches("[0-9]+")) {
                    isCorrect = false;
                    return isCorrect;
                } else {
                    System.out.println(textField.charAt(i));
                }
            }
        }
        return isCorrect;
    }
    public String detecom(String a){
        String string="";
        for (int i = 0; i < a.length(); i++) {
            if(a.charAt(i)==','){
            }else {
                string+=a.charAt(i);
            }
        }
        return string;
    }

}
