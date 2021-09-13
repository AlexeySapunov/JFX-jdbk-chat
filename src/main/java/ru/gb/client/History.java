package ru.gb.client;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class History {

    @FXML
    private static TextArea textArea;

    private static final File history = new File("C:\\Java\\gb-chatJava2Lsn7\\history.txt");

    public static void saveHistory() {
        try (final PrintWriter fileWriter = new PrintWriter(new FileWriter(history, false));
             final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(textArea.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadHistory() {
        if (!history.exists()) {
            try {
                history.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(history))) {
            writer.write(textArea.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        final List<String> historyList = new ArrayList<>();
        try (final FileInputStream in = new FileInputStream(history);
             final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                historyList.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
