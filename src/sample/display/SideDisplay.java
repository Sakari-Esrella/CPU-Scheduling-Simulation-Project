package sample.display;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import sample.Controller;

class SideDisplay extends FlowPane{

    private Button runBtn = new Button("Run");
    private ComboBox<String> stratBox = new ComboBox<>();
    private TextField speedFld = new TextField("1000");
    private CheckBox ranBox = new CheckBox();

    SideDisplay(Controller controller, DisplayController display){

        String[] strats = {"Shortest", "Round Robin", "First", "Priority"};

        stratBox.setValue(strats[0]);
        stratBox.getItems().addAll(FXCollections.observableArrayList(strats));

        Label stratLbl = new Label("Strategy");
        Label ranLbl = new Label("Random");
        Label speedLbl = new Label("Sim Speed");

        stratBox.setPrefWidth(100);
        speedFld.setPrefWidth(100);
        runBtn.setPrefWidth(100);

        this.getChildren().addAll(stratLbl, stratBox, ranLbl, ranBox, speedLbl, speedFld, runBtn);

        this.setPadding(new Insets(5));
        this.setMaxWidth(125);
        this.setHgap(5);
        this.setVgap(5);

        //Allows the user to run and stop the simulation using the same button
        runBtn.setOnAction(e->{
            if(runBtn.getText().equals("Run")){
                display.resetDataDisplay();
                controller.runSimulation();
                runBtn.setText("Stop");
            }else{
                controller.stopSimulation();
                runBtn.setText("Run");
            }
        });

        ranBox.setOnAction(e->{
            if(ranBox.isSelected()) {
                display.displayRandom();
            }else {
                display.displayManual();
            }
        });
    }

    Button getRunBtn(){ return runBtn; }

    String getStrategy(){ return stratBox.getSelectionModel().getSelectedItem();}

    String getSpeed(){ return speedFld.getText(); }

    boolean getRandom(){ return ranBox.isSelected(); }
}
