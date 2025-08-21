module qrcode.generator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires com.google.zxing;
    requires com.google.zxing.javase;

    opens com.qrcode to javafx.fxml;
    exports com.qrcode;
    exports com.qrcode.service;
}

