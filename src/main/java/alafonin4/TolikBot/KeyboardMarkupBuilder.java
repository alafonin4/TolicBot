package alafonin4.TolikBot;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardMarkupBuilder {
    private static final String ADD_FUNDS = EmojiParser.parseToUnicode(":money_with_wings:" + "Пополнить баланс");
    private static final String HISTORY = EmojiParser.parseToUnicode(":calendar:" + "История пополнений");
    private static final String FEEDBACK = EmojiParser.parseToUnicode(":speech_balloon:" + "Отзыв на бота");
    private static final String SUPPORT = EmojiParser.parseToUnicode(":sos:" + "Поддержка");
    private static final String AGREEMENT = EmojiParser.parseToUnicode(":page_facing_up:" + "Оферта");
    public static InlineKeyboardMarkup setKeyboardForHistory(int orderIndex, int size) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        var prButton = new InlineKeyboardButton();
        prButton.setText("Предыдущая страница");
        prButton.setCallbackData("Previous Page");

        var nButton = new InlineKeyboardButton();
        nButton.setText("Следующая страница");
        nButton.setCallbackData("Next Page");

        if (orderIndex - 3 + 1 > 0) {
            row.add(prButton);
        }

        if (orderIndex + 3 - 1 < size - 1) {
            row.add(nButton);
        }

        rowList.add(row);
        markup.setKeyboard(rowList);
        return markup;
    }
    public static InlineKeyboardMarkup setKeyboard(List<Button> buttons) {
        InlineKeyboardMarkup marup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rList = new ArrayList<>();
        List<InlineKeyboardButton> r = new ArrayList<>();

        for (var button:
             buttons) {
            var newButton = new InlineKeyboardButton();
            newButton.setText(button.getText());
            newButton.setCallbackData(button.getCallBack());
            if (button.getUrl() != null) {
                newButton.setUrl(button.getUrl());
            }
            r.add(newButton);
        }
        rList.add(r);
        marup.setKeyboard(rList);
        return marup;
    }
    public static InlineKeyboardMarkup setKeyboardWithRaw(List<List<Button>> buttons) {
        InlineKeyboardMarkup marup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rList = new ArrayList<>();
        List<InlineKeyboardButton> r = new ArrayList<>();

        for (var button:
                buttons) {
            r = new ArrayList<>();
            for (var but:
                    button) {
                var newButton = new InlineKeyboardButton();
                newButton.setText(but.getText());
                newButton.setCallbackData(but.getCallBack());
                if (but.getUrl() != null) {
                    newButton.setUrl(but.getUrl());
                }
                r.add(newButton);
            }
            rList.add(r);
        }
        marup.setKeyboard(rList);
        return marup;
    }
    public static ReplyKeyboardMarkup setReplyKeyboard() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows =new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(ADD_FUNDS));
        row1.add(new KeyboardButton(HISTORY));
        rows.add(row1);
        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(FEEDBACK));
        rows.add(row2);
        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton(SUPPORT));
        rows.add(row3);
        KeyboardRow row4 = new KeyboardRow();
        row4.add(new KeyboardButton(AGREEMENT));
        rows.add(row4);
        markup.setKeyboard(rows);
        return markup;
    }
    public static ReplyKeyboardMarkup setReplyKeyboardWithRaw(List<List<Button>> buttons) {
        ReplyKeyboardMarkup marup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows =new ArrayList<>();

        for (var button:
                buttons) {
            KeyboardRow row = new KeyboardRow();
            for (var but:
                    button) {
                var newButton = new KeyboardButton();
                newButton.setText(but.getText());
                row.add(newButton);
            }
            rows.add(row);
        }
        marup.setKeyboard(rows);
        return marup;
    }
}
