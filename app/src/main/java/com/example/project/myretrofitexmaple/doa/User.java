package com.example.project.myretrofitexmaple.doa;

import java.io.Serializable;

/**
 * Created by admin on 19-Jul-17.
 */

public class User implements Serializable {

    private String playerName;
    private String playerMobile;
    private String playerPassword;
    private String playerId;

    public User() {
    }

    public User(String playerName, String playerId) {
        setPlayerName(playerName);
        setPlayerId(playerId);
    }

    public User(String text, String text1, String text2) {
        setPlayerName(text);
        setPlayerPassword(text1);
        setPlayerMobile(text2);
    }

    public String getPlayerMobile() {
        return playerMobile;
    }

    public void setPlayerMobile(String playerMobile) {
        this.playerMobile = playerMobile;
    }

    public String getPlayerPassword() {
        return playerPassword;
    }

    public void setPlayerPassword(String playerPassword) {
        this.playerPassword = playerPassword;
    }

    @Override
    public String toString() {
        return playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
