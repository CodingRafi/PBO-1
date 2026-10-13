package com.cashier;

import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    private Stage primaryStage;
    private Karyawan karyawanLogin;
    private Transaksi TransaksiNow;
    
    @Override
    public void start(Stage primaryStage) {
        // Inisiasi Data
        Data.seedData();
        
        this.primaryStage = primaryStage;
        
        LoginPage();
    }
    
    private void LoginPage(){
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        
        Label lblTitle = new Label("Login Kasir");
        lblTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TextField inputUsername = new TextField();
        inputUsername.setPromptText("Username (contoh: kasir1)");

        PasswordField inputPassword = new PasswordField();
        inputPassword.setPromptText("Password (contoh: 12345)");

        Button btnLogin = new Button("Login");
        Label lblError = new Label();
        lblError.setStyle("-fx-text-fill: red;");

        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String username = inputUsername.getText();
                String password = inputPassword.getText();

                boolean cekLogin = loginProses(username, password);

                if(cekLogin){
                    DashboardPage();
                }else {
                    lblError.setText("Username atau Password salah!");
                }
            }
        });

        root.getChildren().addAll(lblTitle, inputUsername, inputPassword, btnLogin, lblError);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Aplikasi Kasir - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private boolean loginProses(String username, String password){
        for (int i = 0; i < Data.karyawan.size(); i++) {
            if(Data.karyawan.get(i).getUsername().equals(username) & Data.karyawan.get(i).getPassword().equals(password)){
                Data.karyawanLogin = Data.karyawan.get(i);
                karyawanLogin = Data.karyawanLogin;
                return true;
            }
        }
        
        return false;
    }
    
    private BorderPane LayoutPage(){
        BorderPane root = new BorderPane();
        
        // Header
        Label headerLabel = new Label("  Aplikasi KasirKu");
        headerLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");

        Button btnLogout = new Button("Logout");
        // Memperbaiki typo -fx-aligment menjadi -fx-alignment atau menghapusnya jika diatur oleh HBox
        btnLogout.setStyle("-fx-background-color: #ffffff; -fx-cursor: hand;");

        // Aksi Logout kembali ke halaman login
        btnLogout.setOnAction(e -> {
            Data.karyawanLogin = null; // Hapus sesi aktif
            LoginPage(); // Kembali ke login
        });

        // HBox dengan headerLabel di kiri, spacer di tengah, dan btnLogout di kanan
        HBox topBar = new HBox(15, headerLabel, btnLogout);
        topBar.setPrefHeight(50);
        topBar.setStyle("-fx-alignment: center-left; -fx-background-color: #2c3e50; -fx-padding: 0 15 0 15;");

        root.setTop(topBar);
        
        return root;
    }
    
    private void DashboardPage(){
        BorderPane root = LayoutPage();
        
        VBox contentArea = new VBox(15);
        contentArea.setPadding(new Insets(20));
        
        Label lblWelcome = new Label("Selamat datang, " + karyawanLogin.getNama());
        lblWelcome.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        
        Label lblTableTransaksi = new Label("List Transaksi");
        lblTableTransaksi.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        
        Separator separator = new Separator();
        
        Button btnTambahTransaksi = new Button("Tambah Transaksi");
        
        btnTambahTransaksi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                TransaksiNow = new Transaksi("1", "13-10-2026");
                TambahTransaksiPage();
            }
        });
        
        HBox titleTable = new HBox(15, lblTableTransaksi, btnTambahTransaksi);
        
        contentArea.getChildren().addAll(lblWelcome, separator, titleTable);
        root.setCenter(contentArea);
        
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Aplikasi Kasir - Dashboard");
        primaryStage.setScene(scene);
    }
    
    private void TambahTransaksiPage(){
        BorderPane root = LayoutPage();
        
        HBox inputBox = new HBox(10);
        TextField tfKodeBarang = new TextField();
        tfKodeBarang.setPromptText("Kode Barang");
        TextField tfQty = new TextField();
        tfQty.setPromptText("QTY");
        Button btnTambah = new Button("Tambah");

        inputBox.getChildren().addAll(tfKodeBarang, tfQty, btnTambah);
        
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Aplikasi Kasir - Tambah Transaksi");
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}