package com.tameofthrones.ballot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tameofthrones.constants.Script;
import com.tameofthrones.validator.TextInput;


public class MessageBox {

    private List<String> messageList;
    private TextInput input;

    private MessageBox() {
        input = TextInput.instance();
    }

    private static class Holder {
        private static final MessageBox INSTANCE = new MessageBox();
    }

    public static MessageBox instance() {
        return Holder.INSTANCE;
    }

    public List<String> messages() {
        messageList = new ArrayList<String>();
        File file = getFileFromResources();
        return messagesFromFile(file);
    }

    private File getFileFromResources() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(Script.FILE_NAME)
                                        .getFile());
        return file;
    }

    private List<String> messagesFromFile(File file) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!input.empty(line)) {
                    messageList.add(line);
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messageList;
    }
}
