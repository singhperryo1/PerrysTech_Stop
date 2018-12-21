package perrytechstop;

import javafx.application.Application;

import javafx.geometry.Insets; 
import javafx.scene.Scene ; 
import javafx.geometry.Pos ; 
import java.io.BufferedReader ;
import java.io.FileReader ; 
import java.io.IOException ; 
import javafx.scene.layout.BorderPane ; 
import javafx.scene.control.TextField;
import javafx.scene.control.Label ; 
import javafx.scene.control.TextArea ; 
import javafx.scene.layout.ColumnConstraints ; 
import javafx.scene.layout.GridPane ; 
import javafx.scene.layout.HBox ; 
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight ; 
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class PerryTechStop extends Application {
    private final String SHOP_NAME = "Perry's Tech Stop" ; 
    private final double TAX_RATE = 0.075 ;   //7.5% 
    
    private  final Item [] ItemList = 
    {
      new Item("Desktop Computer", 349.99, true),
        new Item("Notebook Computer", 449.99, true),
        new Item("Monitor", 99.99, true),
        new Item("Keyboard", 14.98, true),
        new Item("External HD", 87.31, true ),
        new Item("System Tune Up", 100, false),
        new Item("Virus/Spyware Removal", 110, false),
        new Item("Onsite service", 100.00, false),
        new Item("Web design", 120.00, false)
    };
    private final int ItemCount = ItemList.length ;   //no of items in the list
    
    private TextArea receipt ; 
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane () ; 
        root.setTop(Title());
        root.setLeft(SalesServices()) ;
        root.setBottom (Buttons());
        root.setRight (Receipt()) ; 
       root.setStyle("-fx-background-color:lightyellow"); 
        root.setPadding (new Insets (5,10,0,10));       //spacing between nodes 
    BorderPane.setMargin(receipt, new Insets(5,10,0,5));
                 Scene scene = new Scene (root);
                 primaryStage.setTitle(SHOP_NAME);
        primaryStage.setScene(scene); 
        primaryStage.show(); 

    }
    
    /**
    returns HBox that contains Title at the top of the border pane in the scene
*/    
  
    
    
    private HBox Title () 
    {
        HBox hbox = new HBox(); 
        hbox.setAlignment(Pos.CENTER);
        Font font36B = Font.font("Verdana",FontWeight.BOLD,36); //title
        Label lblTitle = new Label(SHOP_NAME) ; 
        lblTitle.setFont(font36B);
        hbox.getChildren().add(lblTitle); 
        return hbox ; 
    }
     /** returns VBox that contains the list of services provided in the left of border pane in the scene
    */
  
    private VBox SalesServices () 
    {
        VBox vbox = new VBox() ; 
        GridPane grid = new GridPane() ; 
        grid.setHgap(10.0);
        grid.setVgap(5.0);
        ColumnConstraints column1 = new ColumnConstraints (); 
        column1.setPrefWidth(50.0);
        grid.getColumnConstraints().add(column1); 
        
        for (int row = 0; row<ItemList.length ; row++)
        {
            grid.add(ItemList[row].getQuantity(),0, row);
            grid.add(ItemList[row].getItemID(),1,row); 
            Label lblPrice = new Label(); 
            Font fontCourierNew = Font.font("Courier New", FontWeight.NORMAL,16); 
            lblPrice.setFont(fontCourierNew);
            lblPrice.setText(String.format("%6.2f", ItemList[row].getPrice()));
            grid.add(lblPrice, 2, row);

        }
    vbox.getChildren().add(grid); 
return vbox ;     
    }
    
    private TextArea Receipt()
    {
        receipt = new TextArea(); 
        Font singh = Font.font("Courier New", FontWeight.BOLD,14);
        receipt.setFont(singh);
        receipt.setPrefWidth(460.0); 
        return receipt ; 
    }
  
    /** HBox buttons 
     * returns HBox that contains buttons, set in the bottom of border pane in the scene 
     */
    
private HBox Buttons () 
{
    HBox hbox = new HBox(); 
    hbox.setSpacing(20.0);
    hbox.setPrefHeight(70.0);
    hbox.setAlignment(Pos.CENTER);
    
    Button btnTotal = new Button("Total"); 
    btnTotal.setStyle("-fx-background-radius: 5em; "+
            "-fx-min-width: 50px; " +
            "-fx-min-height: 50px; " + 
            "-fx-max-width: 50px; " + 
            "-fx-man-height: 50px; " +
            "-fx-background-color:lightpink;"
            ); 

btnTotal.setOnAction (e ->computeTotal()); 
btnTotal.setOnKeyPressed(e ->computeTotal());

Button btnClear = new Button ("Clear"); 
btnClear.setStyle("-fx-background-radius: 5em; "+
            "-fx-min-width: 50px; " +
            "-fx-min-height: 50px; " + 
            "-fx-max-width: 50px; " + 
            "-fx-man-height: 50px; " +
            "-fx-background-color:lightpink;"
            ); 
btnClear.setOnAction(e -> Clear());
btnClear.setOnKeyPressed(e -> Clear());

Button btnExit = new Button("Exit"); 
btnExit.setStyle("-fx-background-radius: 5em; "+
            "-fx-min-width: 50px; " +
            "-fx-min-height: 50px; " + 
            "-fx-max-width: 50px; " + 
            "-fx-man-height: 50px; " +
            "-fx-background-color:lightpink;"
            ); 
btnExit.setOnAction ( e -> System.exit(0));
btnExit.setOnKeyPressed(e -> System.exit(0));

hbox.getChildren().addAll(btnTotal, btnClear, btnExit); 
return hbox ; 
}
    
//defining buttons 

private void Clear ()
{
    for (int i=0; i<ItemCount; i++) 
    {  
     ItemList[i].setQuantity("");    
    }
receipt.clear(); //clears the computed reciept

}

private void computeTotal ()
{
    double subTotal = 0 ; 
    double taxableTotal = 0 ; 
    double tax ; 
    double total ; 
    String itemReceipt ;   //receipt for a single item 
    try {
receipt.setText(String.format("Thank you! Please come again at %15s \n\n",SHOP_NAME));
for (int row=0; row<ItemCount; row++)
{
    if(! ItemList[row].getQuantity().getText().equals(""))
    {
        int quantity = Integer.valueOf(ItemList[row].getQuantity().getText());
        if(quantity <0 || quantity >999)
         throw new IllegalArgumentException ("Inputs must be between 0 and 999") ; 
         
         double price = ItemList[row].getPrice(); 
         double quantityXprice = quantity * price ; 
         subTotal += quantityXprice ; 
            
         if(ItemList[row].isTaxable())
         {
             taxableTotal += quantityXprice ; 
             itemReceipt = String.format("%9.2f T   %2d %s @ %.2f each\n",
                     quantityXprice,
                     quantity,
                     ItemList[row].getItemID().getText(), 
                     price                  
                    );
         }
         else 
         {
          itemReceipt = String.format("%9.2f     %s %2d hr(s) @ %.2f/hr \n",
                   quantityXprice,
                     ItemList[row].getItemID().getText(),
                     quantity, 
                     price
          );
         } 
         receipt.setText(receipt.getText() + itemReceipt);
         
         
        }  //end of found an item with quantity <>0 
    } // end of for loop to display each item purchased
  
tax = taxableTotal * TAX_RATE ; 
total = subTotal + tax ; 

receipt.setText(receipt.getText()+ "------------------------------------------------------------\n");
receipt.setText(receipt.getText()+ String.format("$%9.2f     Subtotal  \n",subTotal)); 
receipt.setText(receipt.getText()+ String.format("$%9.2f     Tax \n", tax)) ; 
receipt.setText(receipt.getText()+ "=============================================================\n"); 
receipt.setText(receipt.getText()+ String.format("$%9.2f     Total\n\n\n",total));
} //end of try 

catch (NumberFormatException e)
        {
        receipt.setText("Values for qunatity must be whole numbers"); 
        }
        
catch (IllegalArgumentException e)
        {
        receipt.setText(e.getMessage()); 
        }

    }



    public static void main(String[] args) {        
    launch(args);
    }
}
