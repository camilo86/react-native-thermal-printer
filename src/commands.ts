export interface Command {
  type: string;
}

export class TextCommand implements Command {
  type = 'text';
  text: string;

  public constructor(text: string) {
    this.text = text;
  }
}
