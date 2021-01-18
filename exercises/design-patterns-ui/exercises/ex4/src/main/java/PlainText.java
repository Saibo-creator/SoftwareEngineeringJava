public class PlainText extends DocumentPart {

    public PlainText(String text) {
        super(text);
    }

    @Override
    public void accept(TextConverter textConverter) {
        textConverter.turn(this);
    }

}
