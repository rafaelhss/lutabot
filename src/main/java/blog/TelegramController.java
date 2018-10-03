package blog;

import java.io.IOException;
import java.time.Instant;

import blog.telegram.TextoRepository;
import blog.telegram.config.TelegramConfig;
import blog.telegram.model.Update;
import blog.telegram.sender.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TelegramController {

    @Autowired
    private TextoRepository textoRepository;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return "teste";
    }

    @RequestMapping(method= RequestMethod.POST, value="/update")
    public void ReceberUpdate(@RequestBody Update update) throws IOException {
        try {
            String text = update.getMessage().getText();
            System.out.println("XXXXXXXXXX : " + text);

            if(text.toLowerCase().startsWith("link")) {
                    new Sender(TelegramConfig.BOT_ID).sendResponse(update.getMessage().getFrom().getId(), "http://lutabot.herokuapp.com/index.html");

            } else if(text.toLowerCase().startsWith("del")){
                String idtxt = text.substring(text.indexOf(" ")).trim();
                Integer id = Integer.parseInt(idtxt);
                textoRepository.deleteById(id);
                new Sender(TelegramConfig.BOT_ID).sendResponse(update.getMessage().getFrom().getId(), "deletou");


            } else {

                Texto txt = new Texto();
                txt.setDatahora(Instant.now());
                txt.setTexto(text);
                Integer id = textoRepository.save(txt).getId();
                new Sender(TelegramConfig.BOT_ID).sendResponse(update.getMessage().getFrom().getId(), "Salvo: " + id);

            }

        } catch (Exception e) {
            System.out.println("Excexxao ao processar coisa: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @RequestMapping(method= RequestMethod.GET, value="/textos")
    public Iterable<Texto> getTextos() throws IOException {
        try {
            System.out.println("GET XXXXXXXXXX ");
            return textoRepository.findAll();

        } catch (Exception e) {
            System.out.println("Excexxao ao processar coisa: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
