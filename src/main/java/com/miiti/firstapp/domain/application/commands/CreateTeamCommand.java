package com.miiti.firstapp.domain.application.commands;

import com.miiti.firstapp.domain.model.user.UserId;

public class CreateTeamCommand extends UserCommand {

  private String name;

  public CreateTeamCommand(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
