package uz.pdp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class DictionaryBot extends TelegramLongPollingBot {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String lang = null;

    @Override
    public void onUpdateReceived(Update update) {

        SendMessage sendMessage = new SendMessage();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        List<InlineKeyboardButton> buttonList1 = new ArrayList<>();
        List<InlineKeyboardButton> buttonList2 = new ArrayList<>();
        List<InlineKeyboardButton> buttonList3 = new ArrayList<>();

//
        if (update.hasCallbackQuery()) {
            String callbackText = update.getCallbackQuery().getData();
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            lang = update.getCallbackQuery().getData();
            sendMessage.setText("So'z kiriting");



                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

        }  if (update.hasMessage()) {
            String inputText = update.getMessage().getText();
            sendMessage.setChatId(update.getMessage().getChatId());

            if (inputText.equals("/start")) {
                sendMessage.setText(update.getMessage().getFrom().getFirstName() + " Welcome to Dictionary bot");
                InlineKeyboardButton tr_ru = new InlineKeyboardButton("Turkish ➡️ Russian").setCallbackData("tr-ru");
                InlineKeyboardButton ru_tr = new InlineKeyboardButton("Russian ➡️ Turkish").setCallbackData("ru-tr");
                InlineKeyboardButton en_ru = new InlineKeyboardButton("English ➡️ Russian").setCallbackData("en-ru");
                InlineKeyboardButton ru_en = new InlineKeyboardButton("Russian ➡️ English").setCallbackData("ru-en");
                InlineKeyboardButton en_tr = new InlineKeyboardButton("English ➡️ Turkish").setCallbackData("en-tr");
                InlineKeyboardButton tr_en = new InlineKeyboardButton("Turkish ➡️ English").setCallbackData("tr-en");

                buttonList1.add(tr_ru);
                buttonList1.add(ru_tr);
                buttonList2.add(en_ru);
                buttonList2.add(ru_en);
                buttonList3.add(en_tr);
                buttonList3.add(tr_en);

                list.add(buttonList1);
                list.add(buttonList2);
                list.add(buttonList3);
                inlineKeyboardMarkup.setKeyboard(list);
                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }else {


                String text=update.getMessage().getText();
                sendMessage.setChatId(update.getMessage().getChatId());
                try {
                    URL url = new URL("https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=dict.1.1.20210605T185527Z.dc9ac6320fda0ef9.f57cc4a2cfcc69bf1a23dff7da65811354123eb7&lang="+lang+"&text="+text);
                    URLConnection connection = url.openConnection();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                   Yandex yandex = gson.fromJson(reader,Yandex.class);
                    String result="";
                    for (DefItem defItem : yandex.getDef()) {
                        for (TrItem trItem : defItem.getTr()) {
                            result+=trItem.getText()+" ( "+trItem.getPos() +" )\n";
                        }
                    }
                    sendMessage.setText(result);
                    reader.close();



                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }


        }

    }

    @Override
    public String getBotUsername() {
        return "BootcampDictionarybot";
    }
    // TODO: telegram--> @BootcampDictionarybot

    @Override
    public String getBotToken() {
        return "1791680647:AAHMKuC0YZJnZufhNPZkpDxF1iMAWwDpMWY";
    }
}
