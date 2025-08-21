package com.qrcode;

import com.qrcode.service.QRCodeService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;


public class App extends Application {

    private QRCodeService qrCodeService = new QRCodeService();
    private static final String QR_CODES_DIR = "qrcodes";


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerador de QR Code");

        Label instructionLabel = new Label("Digite o texto ou URL para gerar o QR Code:");
        TextField inputTextField = new TextField();
        inputTextField.setPromptText("Texto ou URL");

        Label fileNameLabel = new Label("Nome do arquivo de saída (ex: meu_qrcode.png):");
        TextField fileNameTextField = new TextField();
        fileNameTextField.setPromptText("nome_do_arquivo.png");

        Button generateButton = new Button("Gerar QR Code");
        ImageView qrCodeImageView = new ImageView();
        qrCodeImageView.setFitWidth(200);
        qrCodeImageView.setFitHeight(200);

        generateButton.setOnAction(event -> {
            String text = inputTextField.getText();
            String fileName = fileNameTextField.getText();

            if (text.isEmpty()) {
                System.err.println("O texto/URL não pode estar vazio.");
                return;
            }

            if (fileName.isEmpty()) {
                fileName = "qrcode_" + System.currentTimeMillis() + ".png"; // Nome padrão baseado no timestamp
            }

            File dir = new File(QR_CODES_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            java.nio.file.Path filePath = Paths.get(QR_CODES_DIR, fileName);

            try {
                qrCodeService.generateQRCode(text, filePath, 200, 200);
                System.out.println("QR Code gerado com sucesso em: " + filePath.toAbsolutePath());

                try {
                    Image qrImage = new Image(new FileInputStream(filePath.toFile()));
                    qrCodeImageView.setImage(qrImage);
                } catch (FileNotFoundException e) {
                    System.err.println("Erro ao carregar a imagem do QR Code: " + e.getMessage());
                }

            } catch (Exception e) {
                System.err.println("Erro ao gerar o QR Code: " + e.getMessage());
                e.printStackTrace();
            }
        });

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(instructionLabel, inputTextField, fileNameLabel, fileNameTextField, generateButton, qrCodeImageView);

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--cli")) {
            runCliMode();
        } else {
            launch(args);
        }
    }


    private static void runCliMode() {
        Scanner scanner = new Scanner(System.in);
        QRCodeService qrCodeService = new QRCodeService();

        System.out.println("\n--- Modo CLI de Geração de QR Code ---");
        System.out.print("Digite o texto ou URL: ");
        String text = scanner.nextLine();

        System.out.print("Digite o nome do arquivo de saída (ex: meu_qrcode.png, deixe em branco para padrão): ");
        String fileName = scanner.nextLine();

        if (text.isEmpty()) {
            System.err.println("Erro: O texto/URL não pode estar vazio.");
            scanner.close();
            return;
        }

        if (fileName.isEmpty()) {
            fileName = "qrcode_cli_" + System.currentTimeMillis() + ".png";
        }

        File dir = new File(QR_CODES_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        java.nio.file.Path filePath = Paths.get(QR_CODES_DIR, fileName);

        try {
            qrCodeService.generateQRCode(text, filePath, 200, 200);
            System.out.println("QR Code gerado com sucesso em: " + filePath.toAbsolutePath());
        } catch (Exception e) {
            System.err.println("Erro ao gerar o QR Code no modo CLI: " + e.getMessage());
            e.printStackTrace();
        }
        scanner.close();
    }
}


