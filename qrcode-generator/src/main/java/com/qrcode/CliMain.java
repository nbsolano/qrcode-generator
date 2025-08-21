package com.qrcode;

import com.qrcode.service.QRCodeService;

import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;


public class CliMain {
    private static final String QR_CODES_DIR = "qrcodes";


    public static void main(String[] args) {
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
            qrCodeService.generateQRCode(text, filePath, 300, 300);
            System.out.println("QR Code gerado com sucesso em: " + filePath.toAbsolutePath());
        } catch (Exception e) {
            System.err.println("Erro ao gerar o QR Code no modo CLI: " + e.getMessage());
            e.printStackTrace();
        }
        scanner.close();
    }
}


