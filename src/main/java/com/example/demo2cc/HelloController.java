package com.example.demo2cc;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    Dictionary dictionary = new Dictionary(Dictionary.PATH);
    public HelloController() throws FileNotFoundException {}
    @FXML
    private TextField textField;
    @FXML
    private TextArea textArea;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textField.setOnKeyReleased(keyEvent -> {
            String text = textField.getText();
            if (text.length() > 0) {
                Search_ListView.getItems().clear();
                List<Word> sameWord = dictionary.sameWord(text);
                for (Word w:sameWord) {
                    Search_ListView.getItems().add(w.getWord());
                }
            }
        });
        Search_ListView.setOnMouseClicked(mouseEvent -> {
            String chosenWord = Search_ListView.getSelectionModel().getSelectedItem();
            textField.setText(chosenWord);
            textArea.clear();
            textArea.setText(dictionary.findWord(chosenWord));
        });
    }
    //tim kiem
    @FXML
    protected void onClick() throws IOException {
        String s = textField.getText();
        String s1 = dictionary.findWord(s);
        if(Objects.equals(s1, "dont found")) {
            thongbao();
        } else {
            textArea.setText(s1);
            System.out.println("datimthay");
        }
    }
    //phat am
    @FXML
    protected void onClickSound() {
        String s = textField.getText();
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice;//Creating object of Voice class
        voice = VoiceManager.getInstance().getVoice("kevin");//Getting voice
        if (voice != null) {
            voice.allocate();//Allocating Voice
        }
        try {
            assert voice != null;
            voice.setRate(190);//Setting the rate of the voice
            voice.setPitch(150);//Setting the Pitch of the voice
            voice.setVolume(3);//Setting the volume of the voice
            voice.speak(s);//Calling speak() method
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    //thong bao
    @FXML
    protected void thongbao() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Không có từ bạn cần tìm. Vào phần thêm từ để thêm từ mới");
        ButtonType bt2 = new ButtonType("Đã rõ", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(bt2);
        Optional<ButtonType> res = alert.showAndWait();
    }
    //Scence them tu
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField tf1;
    @FXML
    private TextField tf2;
    @FXML
    private TextField tf3;
    @FXML
    private Button b1;
    @FXML
    protected void addWordForm() throws IOException {
        System.out.println("add");
        openAdd();
    }
    @FXML
    protected void openAdd() {
        anchorPane2.setVisible(false);
        anchorPane3.setVisible(false);
        anchorPane.setVisible(true);
    }
    @FXML
    protected void submitadd() throws IOException {
        System.out.println("ok");
        Word w = new Word();
        String s1 = tf1.getText();
        w.setWord(s1);
        String s2 = tf2.getText();
        w.setPronounce(s2);
        String s3 = tf3.getText();
        w.setData(s3);
        System.out.println(w.showWord());
        dictionary.addWord(w);
        thongbaoAdd();
    }
    @FXML
    protected void closeadd() {
        anchorPane.setVisible(false);
        tf1.setText("");
        tf2.setText("");
        tf3.setText("");
    }
    protected void thongbaoAdd() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Đã thêm từ thành công");
        ButtonType bt2 = new ButtonType("Đã rõ", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(bt2);
        Optional<ButtonType> res = alert.showAndWait();
    }

    ///Scence Xoa tu
    @FXML
    private AnchorPane anchorPane2;
    @FXML
    private TextField removetf1;
    @FXML
    private ListView<String> Search_ListView;
    @FXML
    protected void removeWordForm() throws IOException {
        System.out.println("remove");
        openRemove();
    }
    @FXML
    protected void openRemove() {
        anchorPane.setVisible(false);
        anchorPane2.setVisible(true);
        anchorPane3.setVisible(false);
        anchorPane4.setVisible(false);
    }
    @FXML
    protected void submitRemove() throws IOException {
        System.out.println("ok");
        String s1 = removetf1.getText();
        if(dictionary.checkWord(s1)==true) {
            dictionary.remove(s1);
            thongbaoRemove();
        } else {
            thongbaoRemoveF();
        }
    }
    @FXML
    protected void closeRemove() {
        anchorPane2.setVisible(false);
    }
    protected void thongbaoRemove() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Đã xóa từ thành công");
        ButtonType bt2 = new ButtonType("Đã rõ", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(bt2);
        Optional<ButtonType> res = alert.showAndWait();
    }
    protected void thongbaoRemoveF() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Không thấy từ bạn cần");
        ButtonType bt2 = new ButtonType("Đã rõ", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(bt2);
        Optional<ButtonType> res = alert.showAndWait();
    }

    ///Scence Sua tu
    @FXML
    private AnchorPane anchorPane3;
    @FXML
    private TextField fixtf1;
    @FXML
    private TextField fixtf2;
    @FXML
    private TextField fixtf3;
    @FXML
    protected void fixWordForm() throws IOException {
        System.out.println("fix");
        openFix();
    }
    @FXML
    protected void openFix() {
        anchorPane.setVisible(false);
        anchorPane2.setVisible(false);
        anchorPane3.setVisible(true);
        anchorPane4.setVisible(false);
    }
    @FXML
    protected void submitFix() throws IOException {
        System.out.println("ok");
        String s1 = fixtf1.getText();
        String s2 = fixtf2.getText();
        String s3 = fixtf3.getText();
        Word w = new Word(s1,s2,s3);
        if(dictionary.checkWord(s1)==true) {
            thongbaoFixF(w);
        }
        else {
            dictionary.addWord(w);
            thongbaoFix();
        }
    }
    @FXML
    protected void closeFix() {
        anchorPane3.setVisible(false);
        fixtf1.setText("");
        fixtf2.setText("");
        fixtf3.setText("");
    }
    protected void thongbaoFix() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Đã sửa từ thành công");
        ButtonType bt2 = new ButtonType("Đã rõ", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(bt2);
        Optional<ButtonType> res = alert.showAndWait();
    }
    protected void thongbaoFixF(Word w) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Từ bạn cần đã có");
        ButtonType bt1 = new ButtonType("Sửa lại", ButtonBar.ButtonData.NO);
        ButtonType bt2 = new ButtonType("Đóng", ButtonBar.ButtonData.YES);
        alert.getButtonTypes().setAll(bt1,bt2);
        Optional<ButtonType> res = alert.showAndWait();
        if(!res.equals(bt1)) {
            dictionary.remove(w.getWord());
            dictionary.addWord(w);
            dictionary.fixword(w.getWord(),w.getPronounce(),w.getData());
            anchorPane3.setVisible(false);
        }
    }
    //scence tim kiem online
    @FXML
    private AnchorPane anchorPane4;
    @FXML
    private TextField tftsol1;
    @FXML
    private TextArea tfrres;
    @FXML
    protected void FindOnline() throws IOException {
        System.out.println("findonline");
        openFOL();
    }
    @FXML
    protected void openFOL() {
        anchorPane.setVisible(false);
        anchorPane2.setVisible(false);
        anchorPane3.setVisible(false);
        anchorPane4.setVisible(true);
    }
    @FXML
    protected void submitSOL() throws IOException {}
    @FXML
    protected void closeSOL() {
        anchorPane4.setVisible(false);
    }
    protected void thongbaoSOL() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Đã thêm từ thành công");
        ButtonType bt2 = new ButtonType("Đã rõ", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(bt2);
        Optional<ButtonType> res = alert.showAndWait();
    }
    protected void thongbaoSOLF() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Từ bạn cần đã có");
        ButtonType bt1 = new ButtonType("Sửa lại", ButtonBar.ButtonData.NO);
        ButtonType bt2 = new ButtonType("Đóng", ButtonBar.ButtonData.YES);
        alert.getButtonTypes().setAll(bt1,bt2);
        Optional<ButtonType> res = alert.showAndWait();
        if(!res.equals(bt1)) {
            anchorPane3.setVisible(false);
        }
    }
    //SCENCE update
    @FXML
    protected void Update() throws IOException {
        dictionary.ExportFile();
        thongbaoUD();
    }
    protected void thongbaoUD() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Đã thêm từ thành công");
        ButtonType bt2 = new ButtonType("Đã rõ", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(bt2);
        Optional<ButtonType> res = alert.showAndWait();
    }
    @FXML
    protected void backtomain() {
        anchorPane.setVisible(false);
        anchorPane2.setVisible(false);
        anchorPane3.setVisible(false);
        anchorPane4.setVisible(false);
    }
}