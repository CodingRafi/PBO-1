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
import java.util.ArrayList;

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

    private void LoginPage() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        Label lblTitle = new Label("Login Kasir");
        lblTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TextField inputUsername = new TextField();
        inputUsername.setPromptText("Username");

        PasswordField inputPassword = new PasswordField();
        inputPassword.setPromptText("Password");

        Button btnLogin = new Button("Login");
        Label lblError = new Label();
        lblError.setStyle("-fx-text-fill: red;");

        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String username = inputUsername.getText();
                String password = inputPassword.getText();

                boolean cekLogin = loginProses(username, password);

                if (cekLogin) {
                    DashboardPage();
                } else {
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

    private boolean loginProses(String username, String password) {
        for (int i = 0; i < Data.karyawan.size(); i++) {
            if (Data.karyawan.get(i).getUsername().equals(username) & Data.karyawan.get(i).getPassword().equals(password)) {
                Data.karyawanLogin = Data.karyawan.get(i);
                karyawanLogin = Data.karyawanLogin;
                return true;
            }
        }

        return false;
    }

    private BorderPane LayoutPage() {
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

    private void DashboardPage() {
        BorderPane root = LayoutPage();

        Label lblMsg = new Label();

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
                TransaksiNow = new Transaksi(Data.incrementTransaksi, "13-10-2026", karyawanLogin);
                TambahTransaksiPage();
            }
        });

        HBox titleTable = new HBox(15, lblTableTransaksi, btnTambahTransaksi);

        TableView<Transaksi> table = new TableView<>();
        table.setPrefHeight(200);

        TableColumn<Transaksi, String> colTgl = new TableColumn<>("Tanggal");
        colTgl.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTanggal()));

        TableColumn<Transaksi, String> colMember = new TableColumn<>("Member");
        colMember.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getMember().getNama()));

        TableColumn<Transaksi, String> colMemberBadge = new TableColumn<>("Member Badge");
        colMemberBadge.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getBadgeMember().getLevelMember()));

        TableColumn<Transaksi, Double> colTotalKotor = new TableColumn<>("Total Kotor");
        colTotalKotor.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getTotalKotor()));

        TableColumn<Transaksi, Double> colDiskonMember = new TableColumn<>("Diskon Member");
        colDiskonMember.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getDiskonMember()));

        TableColumn<Transaksi, Double> colDiskonProduk = new TableColumn<>("Diskon Produk");
        colDiskonProduk.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getDiskonProduk()));

        TableColumn<Transaksi, Double> colTotalDiskon = new TableColumn<>("Total Diskon");
        colTotalDiskon.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getTotalDiskon()));

        TableColumn<Transaksi, Double> colTotalBayar = new TableColumn<>("Total Bayar");
        colTotalBayar.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getTotalBayar()));

        TableColumn<Transaksi, Void> colAksi = new TableColumn<>("Aksi");
        colAksi.setCellFactory(param -> new TableCell<>() {
            private final Button btnHapus = new Button("Hapus");

            {
                btnHapus.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-cursor: hand;");
                btnHapus.setOnAction(event -> {
                    Transaksi t = getTableView().getItems().get(getIndex());
                    int idDelTransaksi = t.getId();
                    ArrayList<Transaksi> allTransaksi = Data.transaksi;

                    for (int j = 0; j < allTransaksi.size(); j++) {
                        Transaksi tLoop = allTransaksi.get(j);

                        if (idDelTransaksi == tLoop.getId()) {
                            allTransaksi.remove(j);
                            break;
                        }
                    }

                    lblMsg.setText("Transaksi berhasil dihapus!");
                    lblMsg.setStyle("-fx-text-fill: green;");
                    
                    ArrayList<Transaksi> transaksiKaryawan = GetTransaksiKaryawan();
                    table.setItems(javafx.collections.FXCollections.observableArrayList(transaksiKaryawan));
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnHapus);
                }
            }
        });

        table.getColumns().addAll(colTgl, colMember, colMemberBadge, colTotalKotor, colDiskonMember, colDiskonProduk, colTotalDiskon, colTotalBayar, colAksi);
        ArrayList<Transaksi> transaksiKaryawan = GetTransaksiKaryawan();
        table.setItems(javafx.collections.FXCollections.observableArrayList(transaksiKaryawan));

        contentArea.getChildren().addAll(lblWelcome, separator, lblMsg, titleTable, table);
        root.setCenter(contentArea);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Aplikasi Kasir - Dashboard");
        primaryStage.setScene(scene);
    }
    
    private ArrayList<Transaksi> GetTransaksiKaryawan(){
        ArrayList<Transaksi> transaksiKaryawan = new ArrayList<>();

        for (int k = 0; k < Data.transaksi.size(); k++) {
            Transaksi tran = Data.transaksi.get(k);
            if (tran.getKaryawan().getNip() == karyawanLogin.getNip()) {
                transaksiKaryawan.add(tran);
            }
        }
        
        return transaksiKaryawan;
    }

    private void TambahTransaksiPage() {
        BorderPane root = LayoutPage();

        VBox contentArea = new VBox(15);
        contentArea.setPadding(new Insets(20));

        Label lblMsg = new Label();

        VBox summaryHargaBox = new VBox(10);

        HBox hargaKotorBox = new HBox(10);

        Label hargaKotorLabel = new Label("Harga kotor : ");
        hargaKotorLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label hargaKotorValueLabel = new Label("Rp " + TransaksiNow.getTotalKotor());
        hargaKotorValueLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        hargaKotorBox.getChildren().addAll(hargaKotorLabel, hargaKotorValueLabel);

        HBox diskonProdukBox = new HBox(10);

        Label diskonProdukLabel = new Label("Diskon Produk : ");
        diskonProdukLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label diskonProdukValueLabel = new Label("Rp " + TransaksiNow.getDiskonProduk());
        diskonProdukValueLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        diskonProdukBox.getChildren().addAll(diskonProdukLabel, diskonProdukValueLabel);

        HBox diskonMemberBox = new HBox(10);

        Label diskonMemberLabel = new Label("Diskon Member : ");
        diskonMemberLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label diskonMemberValueLabel = new Label("Rp " + TransaksiNow.getDiskonMember());
        diskonMemberValueLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        diskonMemberBox.getChildren().addAll(diskonMemberLabel, diskonMemberValueLabel);

        HBox totalDiskonBox = new HBox(10);

        Label totalDiskonLabel = new Label("Total Diskon : ");
        totalDiskonLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label totalDiskonValueLabel = new Label("Rp " + TransaksiNow.getTotalDiskon());
        totalDiskonValueLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        totalDiskonBox.getChildren().addAll(totalDiskonLabel, totalDiskonValueLabel);

        HBox totalBayarBox = new HBox(10);

        Label totalBayarLabel = new Label("Total Bayar : ");
        totalBayarLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label totalBayarValueLabel = new Label("Rp " + TransaksiNow.getTotalKotor());
        totalBayarValueLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        totalBayarBox.getChildren().addAll(totalBayarLabel, totalBayarValueLabel);

        summaryHargaBox.getChildren().addAll(hargaKotorBox, diskonProdukBox, diskonMemberBox, totalDiskonBox, totalBayarBox);

        TableView<TransaksiProduk> table = new TableView<>();
        table.setPrefHeight(200);

        TableColumn<TransaksiProduk, String> colNama = new TableColumn<>("Nama Produk");
        colNama.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getProduk().getNama()));

        TableColumn<TransaksiProduk, Double> colHarga = new TableColumn<>("Harga");
        colHarga.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getProduk().getHarga()));

        TableColumn<TransaksiProduk, Integer> colQty = new TableColumn<>("QTY");
        colQty.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getQty()));

        TableColumn<TransaksiProduk, Double> colDiskon = new TableColumn<>("Diskon");
        colDiskon.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getPersenDiskonProduk()));

        TableColumn<TransaksiProduk, Double> colTotalDiskon = new TableColumn<>("Harga Diskon");
        colTotalDiskon.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getTotalDiskonProduk()));

        TableColumn<TransaksiProduk, Double> colSubtotal = new TableColumn<>("Subtotal");
        colSubtotal.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getTotalBayar()));

        TableColumn<TransaksiProduk, Void> colAksi = new TableColumn<>("Aksi");
        colAksi.setCellFactory(param -> new TableCell<>() {
            private final Button btnHapus = new Button("Hapus");

            {
                btnHapus.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-cursor: hand;");
                btnHapus.setOnAction(event -> {
                    TransaksiProduk tp = getTableView().getItems().get(getIndex());

                    TransaksiNow.HapusTransaksiProduk(tp.getId());

                    table.setItems(javafx.collections.FXCollections.observableArrayList(TransaksiNow.GetTransaksiProduk()));

                    hargaKotorValueLabel.setText("Rp " + TransaksiNow.getTotalKotor());
                    diskonProdukValueLabel.setText("Rp " + TransaksiNow.getDiskonProduk());
                    diskonMemberValueLabel.setText("Rp " + TransaksiNow.getDiskonMember());
                    totalDiskonValueLabel.setText("Rp " + TransaksiNow.getTotalDiskon());
                    totalBayarValueLabel.setText("Rp " + TransaksiNow.getTotalBayar());

                    lblMsg.setText("Produk berhasil dihapus dari transaksi!");
                    lblMsg.setStyle("-fx-text-fill: orange;");
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnHapus);
                }
            }
        });

        table.getColumns().addAll(colNama, colHarga, colQty, colDiskon, colTotalDiskon, colSubtotal, colAksi);

        HBox inputBox = new HBox(10);
        TextField tfKodeBarang = new TextField();
        tfKodeBarang.setPromptText("Kode Barang");
        TextField tfQty = new TextField();
        tfQty.setPromptText("QTY");
        Button btnTambah = new Button("Tambah");

        btnTambah.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String kode = tfKodeBarang.getText();
                String qtyStr = tfQty.getText();

                if (kode.isEmpty() || qtyStr.isEmpty()) {
                    lblMsg.setText("Kode barang dan QTY harus diisi!");
                    lblMsg.setStyle("-fx-text-fill: red;");
                    return;
                }

                try {
                    int qty = Integer.parseInt(qtyStr);

                    // Cari produk berdasarkan kode di Data
                    Produk produkDitemukan = null;
                    for (int i = 0; i < Data.produk.size(); i++) {
                        if (Data.produk.get(i).getKode().equals(kode)) {
                            produkDitemukan = Data.produk.get(i);
                            break;
                        }
                    }

                    if (produkDitemukan != null) {
                        TransaksiProduk tp = new TransaksiProduk(produkDitemukan, qty);

                        TransaksiNow.TambahTranskasiProduk(tp);

                        hargaKotorValueLabel.setText("Rp " + TransaksiNow.getTotalKotor());
                        diskonProdukValueLabel.setText("Rp " + TransaksiNow.getDiskonProduk());
                        diskonMemberValueLabel.setText("Rp " + TransaksiNow.getDiskonMember());
                        totalDiskonValueLabel.setText("Rp " + TransaksiNow.getTotalDiskon());
                        totalBayarValueLabel.setText("Rp " + TransaksiNow.getTotalBayar());

                        table.setItems(javafx.collections.FXCollections.observableArrayList(TransaksiNow.GetTransaksiProduk()));

                        // Bersihkan input
                        tfKodeBarang.clear();
                        tfQty.clear();
                        lblMsg.setText("Produk berhasil ditambahkan!");
                        lblMsg.setStyle("-fx-text-fill: green;");
                    } else {
                        lblMsg.setText("Kode barang tidak ditemukan!");
                        lblMsg.setStyle("-fx-text-fill: red;");
                    }
                } catch (NumberFormatException ex) {
                    lblMsg.setText("QTY harus berupa angka!");
                    lblMsg.setStyle("-fx-text-fill: red;");
                }
            }
        });

        inputBox.getChildren().addAll(tfKodeBarang, tfQty, btnTambah);

        HBox memberBox = new HBox(10);
        TextField tfKodeMember = new TextField();
        tfKodeMember.setPromptText("Kode Member : ");
        Button btnMember = new Button("Tambah");

        btnMember.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String kode = tfKodeMember.getText();

                for (int i = 0; i < Data.member.size(); i++) {
                    Member m = Data.member.get(i);
                    if (m.getKode().equals(kode)) {
                        TransaksiNow.SetMember(m);

                        diskonMemberValueLabel.setText("Rp " + TransaksiNow.getDiskonMember());
                        totalDiskonValueLabel.setText("Rp " + TransaksiNow.getTotalDiskon());
                        totalBayarValueLabel.setText("Rp " + TransaksiNow.getTotalBayar());

                        lblMsg.setText("Berhasil menambahkan member");
                        lblMsg.setStyle("-fx-text-fill: green;");
                        break;
                    }
                }

                if (TransaksiNow.getMember() == null) {
                    lblMsg.setText("Member tidak ditemukan!");
                    lblMsg.setStyle("-fx-text-fill: red;");
                }
            }
        });

        memberBox.getChildren().addAll(tfKodeMember, btnMember);

        Button btnSimpan = new Button("Simpan Transaksi");

        btnSimpan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (TransaksiNow.GetTransaksiProduk().size() > 0) {
                    Data.transaksi.add(TransaksiNow);
                    TransaksiNow = null;
                    Data.incrementTransaksi++;
                    DashboardPage();
                } else {
                    lblMsg.setText("Tidak ada produk pada transaksi ini!");
                    lblMsg.setStyle("-fx-text-fill: red;");
                }
            }
        });

        contentArea.getChildren().addAll(inputBox, lblMsg, table, memberBox, summaryHargaBox, btnSimpan);
        root.setCenter(contentArea);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Aplikasi Kasir - Tambah Transaksi");
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
