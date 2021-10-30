module com.example.demo2cc {
    requires javafx.controls;
    requires javafx.fxml;
    requires freetts;
    requires okhttp3;
    requires client.sdk;


    opens com.example.demo2cc to javafx.fxml;
    exports com.example.demo2cc;
}