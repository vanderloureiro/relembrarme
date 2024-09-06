package dev.vanderloureiro;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Path("/mail")
public class MailResource {

    @Inject
    Mailer mailer;

    @GET
    @Transactional
    @Blocking
    public void send() {
        List<Message> messages = Message.getAllUnsent();
        if (messages.isEmpty()) {
            return;
        }

        var random = new Random();
        int selected = random.nextInt(messages.size());
        var message = messages.get(selected);
        mailer.send(
                Mail.withText(message.receiver,"Relembrar-me", message.content)
        );
        message.registerDispatch();
        message.persist();
    }
}
