module subsc.smart_electronic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires fontawesomefx;
    requires com.microsoft.sqlserver.jdbc;

    opens subsc.smart_electronic to javafx.fxml;
    exports subsc.smart_electronic;
    opens subsc.smart_electronic.controllers;
    exports subsc.smart_electronic.controllers;
}
