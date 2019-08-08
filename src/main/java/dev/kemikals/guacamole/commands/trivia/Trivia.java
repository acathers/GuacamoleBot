package dev.kemikals.guacamole.commands.trivia;

import com.google.gson.Gson;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Trivia extends ListenerAdapter {

    TextChannel channel;
    Questions questions;
    Gson gson = new Gson();
    String hint = "";
    List<Score> scores = new ArrayList<>();
    String answer;
    boolean started = false;
    List<Result> results;
    int currentQuestion = 0;
    Timer timer = null;
    boolean unanswered = true;

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        if (event.getMessage().getContentDisplay().equals("!trivia start")) {

            started = true;
            channel = event.getChannel();
            channel.sendMessage("Starting trivia game in 10 seconds... get ready!").queue();
            URL url = null;
            try {
                url = new URL("https://opentdb.com/api.php?amount=2");
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Scanner sc = null;
            try {
                sc = new Scanner(url.openStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String inline = "";
            while (sc.hasNext()) {
                inline += sc.nextLine();
            }
            sc.close();
            questions = gson.fromJson(inline, Questions.class);

            results = questions.getResults();
        }

        if (started) {

            if (timer == null) {
                timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        if (currentQuestion > results.size() - 1) {

                            channel.sendMessage(("game over!, type !trivia start to start a new round")).queue();
                            timer.cancel();
                            timer = null;
                            started = false;
                            currentQuestion = 0;
                            return;
                        }
                        displayQuestion(currentQuestion);
                    }
                }, 10000, 15000);
            }
        }

        if (event.getMessage().getContentDisplay().equalsIgnoreCase(answer) && unanswered) {
            unanswered = false;
            channel.sendMessage(event.getAuthor().getName() + " got it right!!!").queue();
            Score score = null;

            for (Score currentScore : scores) {
                if (currentScore.equals(event.getMember())) {
                    System.out.println("Am i here?");
                    score = currentScore;
                }
            }
            if (score == null) {
                score = new Score(event.getMember());
                scores.add(score);
            }
            score.increaseScore();
            channel.sendMessage(event.getAuthor().getName() + " now has " + score.getScore() + " points!").queue();
            currentQuestion++;

        }
    }

    public String createHint(String answer) { // TODO: redo this because it's horrid
        String hint = "";
        System.out.println(answer);

        for (char ch : answer.toCharArray()) {
            if (Character.isWhitespace(ch)) {
                hint += " ";
            } else {
                hint += "\\_ ";
            }
        }
        return hint;
    }

    public String updateHint(String hint, String answer) {
        return "";
    }

    private void displayQuestion(int number) {
        unanswered = true;
        channel.sendMessage("Category: " + results.get(0).getCategory()).queue();
        String question = results.get(number).getQuestion().replaceAll("&#039", "\"");
        channel.sendMessage("Question: " + question).queue();

        answer = results.get(number).getCorrectAnswer();

        channel.sendMessage("Hint: " + createHint(answer)).queue();
    }

}
