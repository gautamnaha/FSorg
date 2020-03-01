/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author gautamnaha
 */
public class Team {

    int teamId;
    String name;

    public Team(int teamId, String name) {
        this.teamId = teamId;
        this.name = name;
    }

    /**
     * @return the teamId
     */
    public int getTeamId() {
        return teamId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

}
