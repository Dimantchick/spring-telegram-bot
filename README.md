# Spring boot telegram bot
## Usage
1. Register bot, using BotFather https://t.me/BotFather
2. Change bot token application.yml or set env var BOT_TOKEN `set BOT_TOKEN=<token_from_BotFather>`
3. Build `gradle clean build`
4. Run application
## Run in docker
Use example Docker-compose.yml
1. Register bot, using BotFather https://t.me/BotFather
2. Change set env var BOT_TOKEN `set BOT_TOKEN=<token_from_BotFather>`
3. Run `docker-compose up`
## Supported env variables
- BOT_TOKEN - token from BotFather
- BOT_NAME - name of your bot
- ENABLE_LICENSE_AGREEMENT - enable only accepted licanse users can use bot - default true
- LICENSE_VERSION - version of actual licanse (on update license increment it to reaccept for all users)
## Adding command handlers
- Copy com/github/dimantchick/telegrambot/handlers/commands/StartCommand.java with your name in same package
- Change COMMAND_IDENTIFIER to your command
- Change COMMAND_DESCRIPTION to description of your command (displayed on /help)
- Implement logic in public void execute method
- Command will be registered on application startup and executed, when user send it to bot
## Adding callback update handlers
- Copy com/github/dimantchick/telegrambot/handlers/callbacks/DefaultCallbackUpdateHandler.java with your name in same package
- Change order @Order(Integer.MAX_VALUE) `Important! CallbackUpdateRegistry search only first applicable Handler!`
- Implement isApplicable method - basically its check that update.getCallbackQuery().getData() equals your button callbackData
- Implement handle method with your callback logic
## Additional info
This is sample implementation of telegram bot in Spring boot.

[Original library](https://github.com/rubenlagus/TelegramBots) have only LongPollingSingleThreadUpdateConsumer. In this repo you can see implementation of bot with LongPollingMultiThreadUpdateConsumer with [spring ThreadPoolTaskExecutor](https://docs.spring.io/spring-framework/reference/integration/scheduling.html).
## Links
- Original library https://github.com/rubenlagus/TelegramBots
- Original library documentation https://rubenlagus.github.io/TelegramBotsDocumentation/telegram-bots.html 
- Spring documentation https://docs.spring.io/spring-framework/reference/