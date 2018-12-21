// class for item 
package perrytechstop;

import javafx.scene.control.Label ; 
import javafx.scene.control.TextField ; 

public class Item {
    //private data memebers
    private  TextField quantity ; 
    private  Label itemID ; 
    private double price ; 
    private boolean taxable ; 
    
    //non-default constructor 
    
    Item(String ID, double price, boolean taxable)
    {
quantity = new TextField() ; 
itemID = new Label(); 
setQuantity(""); 
setItemID(ID) ;
setPrice(price); 
setTaxable(taxable); 
  }

//getters 
public TextField getQuantity (){ return quantity ;}  
public Label getItemID () { return itemID ;}  
public double getPrice() { return price; } 
public boolean isTaxable () { return taxable ; } 



// setters 

public void setQuantity (String quantity) { this.quantity.setText(quantity);} 
public void setItemID (String itemID) { this.itemID.setText(itemID); } 
public void setPrice (double price) {this.price = price ; } 
public void setTaxable (boolean taxable){ this.taxable = taxable; }  
    
    
    
} // end of class Item
