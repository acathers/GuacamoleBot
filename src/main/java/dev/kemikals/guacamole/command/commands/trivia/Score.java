package dev.kemikals.guacamole.command.commands.trivia;

import net.dv8tion.jda.core.entities.Member;

public class Score {
    Member member;
    private int score;

    public Score(Member member) {
        this.member = member;
    }

    public void increaseScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((member == null) ? 0 : member.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member) {
            return ((Member) obj) == this.member;
        }

        return false;
    }

}
