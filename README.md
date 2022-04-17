# PoliCalendar
BOT para la gestión de recordatorios de actividades del aula virtual,
a través de mensajes detallados con la informacion de la actividad donde 
se detalla la actividad a ser entregada, la
asignatura y la fecha de entrega de la misma. Ademas de permitir al
usuario establecer su propia configuración de intervalos en los
recordatorios, agregando el sistema a grupos de trabajo de materias
especificas, con el objetivo de mejorar la productividad de los estudiantes
de la [Escuela Politecnica Nacional](https://www.epn.edu.ec/).

---
# [PoliCalendarBot](https://t.me/PoliCalendarBot)  

[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/Afkerian/PoliCalendarBot/blob/main/LICENSE) 
![GitHub top language](https://img.shields.io/github/languages/top/Afkerian/PoliCalendarBot)
![GitHub repo size](https://img.shields.io/github/repo-size/Afkerian/PoliCalendarBot)
![Lines of code](https://img.shields.io/tokei/lines/github/Afkerian/PoliCalendarBot)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/Afkerian/PoliCalendarBot)
![GitHub forks](https://img.shields.io/github/forks/Afkerian/PoliCalendarBot)
![GitHub Repo stars](https://img.shields.io/github/stars/Afkerian/PoliCalendarBot)


PoliCalendar es un proyecto desarrollado por estudiantes de la AEIS para
estudiantes de la Escuela Politecnica Nacional.

* **Proposito:** Satisfacer las necesidades actuales de los estudiantes,
mejorando la productividad y el trabajo colectivo. 
* **Objetivo:** Revolucionar la forma en la cual los estudiantes universitarios
de Ecuador interactuan con las redes sociales y la educacion, una forma
divertida de cumplir con tus actividades.



---

# PoliCalendar - Telegram Bot Java

## Documentacion

Puede encontrar la documentación de PoliCalendar [en su sitio web]().

Consulte la página [Introducción]() para obtener una descripción general rápida.


La documentación se divide en varias secciones:

* [Tutorial]()
* [Conceptos principales]()
* [Guías avanzadas]()
* [Referencia de la API]()
* [Dónde obtener soporte]()
* [Guía de contribución]()

Puede mejorarlo enviando issues [este repositorio](https://github.com/Afkerian/PoliCalendarBot).

## Contribuciones
Siéntase libre de bifurcar este proyecto, trabajar en él y luego 
hacer una solicitud de extracción contra la rama **DEV**. 
La mayoría de las veces los aceptaré si agregan algo valioso al 
código.

Por favor, **NO EMPUJE NINGÚN TOKEN O CLAVE DE API**, nunca 
aceptaré una solicitud de extracción con ese contenido.

---
## Uso

Registro del bot:

```java

    // Principal 
    public class Main {
        public static void main(String[] args) {
            try {
                TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
                botsApi.registerBot(new PoliCalendarBot());
                System.out.println("Hello, World!"); //Bots run
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }
```
## Ejemplo

```java
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PoliCalendarBot extends TelegramLongPollingBot {


    @Override
    public void onUpdateReceived(Update update) {
        
        SendMessage message = new SendMessage();
    
        if (update.hasMessage() && update.getMessage().hasText()) {
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText("¡Hola! " + update.getMessage().getFrom().getUserName() + ".\n\nPoliCalendar  te da la bienvenida!");
            try{
                execute(message);
            }catch (TelegramApiException e){
                e.printStackTrace();
            }
        }
    }


    public String getBotUsername() {
        return TokenReader.readUserName();
    }

    public String getBotToken() {
        return TokenReader.readToken();
    }

}
```

## Bots de Ejemplo 

Ábralos y envíeles el comando */help* para obtener información sobre sus capacidades:

https://telegram.me/weatherbot (**Use custom keyboards**)

https://telegram.me/directionsbot (**Basic messages**)

https://telegram.me/filesbot (**Send files by file_id**)

https://telegram.me/TGlanguagesbot (**Send files uploding them**)

https://telegram.me/RaeBot (**Inline support**)

https://telegram.me/SnowcrashBot (**Webhook support**)


Puede ver el código de esos bots en 
[TelegramBotsExample](https://github.com/rubenlagus/TelegramBotsExample) 
proyectos.
---
## Telegram Bot API
Esta biblioteca usa [Telegram bot API](https://core.telegram.org/bots), puede encontrar más información siguiendo el enlace.

## Preguntas o Sugerencias
Siéntase libre de crear tantos [issues](https://github.com/Afkerian/PoliCalendarBot/issues) como necesites o únete al chat 
[chat](https://t.me/+kLJhsEi0URA5ZGMx).

## Powered by Intellij
<p align="center">
   <a href="https://www.jetbrains.com/"><img src="https://github.com/rubenlagus/TelegramBots/blob/master/jetbrains.png?raw=true" width="75"></a>
</p>

---


## Contribucion

El propósito principal de este repositorio es continuar evolucionando 
el núcleo y las funciones de PoliCalendar, haciéndolo más ameno y fácil de usar. 
El desarrollo de PoliCalendar ocurre abiertamente en GitHub, y estamos 
agradecidos con la comunidad por contribuir con correcciones de 
errores y mejoras. Lea a continuación para saber cómo puede 
participar en la mejora continua de este bot.

### [Código de Conducta](https://github.com/Afkerian/PoliCalendarBot/blob/main/CODE_OF_CONDUCT.md)


PoliCalendar ha adoptado un Código de conducta que esperamos que 
cumplan los participantes del proyecto. 
Lea [el texto completo](https://github.com/Afkerian/PoliCalendarBot/blob/main/CODE_OF_CONDUCT.md) para que pueda comprender qué acciones se tolerarán y cuáles no.

### [Guía de Contribución]()

Lea nuestra [guía de contribución]() 
para obtener información sobre nuestro proceso de desarrollo, 
cómo proponer correcciones de errores y mejoras, y cómo crear y 
probar sus cambios en PoliCalendar.


### [License](https://github.com/Afkerian/PoliCalendarBot/blob/main/LICENSE)


PoliCalendar es [MIT licensed](./LICENSE).

