import { Command, TextCommand } from './commands';

export class Design {
  commands: Command[] = [];

  text(text: string) {
    this.commands.push(new TextCommand(text));
  }
}
