package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class Main extends Application {
    private static String[] ar;
    private Stage window;
    private Scene scene1, scene2;

    public static void main(String[] args) {
        ar=args;
        launch(args);
    }

    private int players;
    private PlayerData[] data;

    //for run
    static CheckMenuItem characterListItem;
    public static boolean setCharacterListItemCheck(){
        if(characterListItem.isSelected()){
            characterListItem.setSelected(false);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("10min background");

        window.setOnCloseRequest(event -> {
            event.consume();
            closeProgram();
        });
        //Separator
        SeparatorMenuItem sep=new SeparatorMenuItem();


        //File menu Items
        MenuItem saveItem=new MenuItem("_Save...");
        saveItem.setOnAction(event -> {
            Save.displey(data,players);
        });
        MenuItem savePreviewItem=new MenuItem("Save _Preview...");

        MenuItem exitItem= new MenuItem("E_xit");
        exitItem.setOnAction(event -> closeProgram());
        //File menu
        Menu file=new Menu("_File");
        file.getItems().addAll(saveItem,sep,exitItem);


        //View menu Items
        characterListItem = new CheckMenuItem("Character's List display");
        characterListItem.setOnAction(event -> {
            if(characterListItem.isSelected()){
                PlayersList.displey(players,data);
            }else {
                PlayersList.closewindow();
            }
        });
        characterListItem.setSelected(true);
        //View menu
        Menu View= new Menu("_View");
        View.getItems().addAll(characterListItem);

        //Help menu Items
        MenuItem helpItem=new MenuItem("Help");
        helpItem.setOnAction(event -> Hepl.displey());
        //Help menu
        Menu help=new Menu("_Help");
        help.getItems().addAll(helpItem);



        //Menu menu bar
        MenuBar menuBar=new MenuBar();
        menuBar.setVisible(false);
            menuBar.getMenus().addAll(file,View,help);

        Label playersInfo=new Label("number of players: ");
        TextField inputplayers= new TextField("key in number of players");


        HBox playerBox = new HBox(0);
        playerBox.getChildren().addAll(playersInfo ,inputplayers);

        Label q1text = new Label("idea for step 1: ");
        TextField inputq1= new TextField("");
        inputq1.setPrefSize(300,80);
        inputq1.setOnKeyPressed(event -> {
            if(event.getCode()==KeyCode.ENTER){
                System.out.println("q1 answer: "+inputq1.getText());
                String  s=inputq1.getText();
                int k=PlayerChose.displey(players,data);
                data[k].adds1(s);
                System.err.println(data[0].gets1());
                inputq1.setText("");
            }
        });
        HBox q1 = new HBox();
        q1.getChildren().addAll(q1text,inputq1);

        Label q2text = new Label("idea for step 2: ");
        TextField inputq2= new TextField();
        inputq2.setPrefSize(300,80);
        inputq2.setOnKeyPressed(event -> {
            if(event.getCode()==KeyCode.ENTER){
                System.out.println("q2 answer: "+inputq2.getText());
                data[PlayerChose.displey(players,data)].adds2(inputq2.getText());
                inputq2.setText("");
            }
        });
        HBox q2 = new HBox();
        q2.getChildren().addAll(q2text,inputq2);

        Label q3text = new Label("idea for step 3: ");
        TextField inputq3= new TextField();
        inputq3.setPrefSize(300,80);
        inputq3.setOnKeyPressed(event -> {
            if(event.getCode()==KeyCode.ENTER){
                System.out.println("q3 answer: "+inputq3.getText());
                data[PlayerChose.displey(players,data)].adds3(inputq3.getText());
                inputq3.setText("");
            }
        });
        HBox q3 = new HBox();
        q3.getChildren().addAll(q3text,inputq3);

        Label q4text = new Label("idea for step 4: ");
        TextField inputq4= new TextField();
        inputq4.setPrefSize(300,80);
        inputq4.setOnKeyPressed(event -> {
            if(event.getCode()==KeyCode.ENTER){
                System.out.println("q4 answer: "+inputq4.getText());
                data[PlayerChose.displey(players,data)].adds4(inputq4.getText());
                inputq4.setText("");
            }
        });
        HBox q4 = new HBox();
        q4.getChildren().addAll(q4text,inputq4);

        Label q5text = new Label("idea for step 5: ");
        TextField inputq5= new TextField();
        inputq5.setPrefSize(300,80);
        inputq5.setOnKeyPressed(event -> {
            if(event.getCode()==KeyCode.ENTER){
                System.out.println("q5 answer: "+inputq5.getText());
                data[PlayerChose.displey(players,data)].adds5(inputq5.getText());
                inputq5.setText("");
            }
        });
        HBox q5 = new HBox();
        q5.getChildren().addAll(q5text,inputq5);

        VBox base = new VBox(16);
        base.getChildren().addAll(q1,q2,q3,q4,q5);
        base.setVisible(false);


        Button preview=new Button("preview");
        preview.setVisible(false);

        inputplayers.setOnKeyPressed(event ->{
            if(event.getCode()==KeyCode.ENTER)
                if(isInt(inputplayers.getText())){
                    inputplayers.setVisible(false);
                    base.setVisible(true);
                    players=Integer.parseInt(inputplayers.getText());
                    players--;
                    playersInfo.setText(playersInfo.getText()+(players+1));
                    data=new PlayerData[players+1];
                    for(int i=0;i<=players;i++)data[i]=new PlayerData(ChoseName.displey(i));
                    PlayersList.displey(players,data);
                    preview.setVisible(true);
                    menuBar.setVisible(true);
                }
                else AlertBox.displey("Player number","Players number must be intiger number");
        });
        HBox dawn =new HBox(8);
        preview.setOnAction(event -> Preview.displey(Chose.displey(players,data),data));

        dawn.getChildren().addAll(preview);
        VBox layout = new VBox(8);
        layout.getChildren().addAll(menuBar,playerBox,base,dawn);

        scene1 = new Scene(layout, 480, 400);
        window.setScene(scene1);
        window.show();
    }

    private boolean isInt(String text) {
        try{
            int i= Integer.parseInt(text);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    private void closeProgram(){
        if(Confirmbox.displey("close","Sure you want to exit?")){

            System.out.println("Sefe Exit");
        window.close();
        System.exit(0);}


    }


}
