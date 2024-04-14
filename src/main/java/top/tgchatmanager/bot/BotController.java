package top.tgchatmanager.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class BotController {
    @Autowired
    private TgWebhookBot tgWebhookBot;

    @CrossOrigin("*")
    @PostMapping("/tgchatmanagerput")
    public void onWebhookUpdateReceived(@RequestBody Update update) {
        tgWebhookBot.onWebhookUpdateReceived(update);
    }
}
