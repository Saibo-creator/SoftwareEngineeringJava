public class BoldText extends DocumentPart {

    public BoldText(String text) {
        super(text);
    }

    @Override
    public void accept(TextConverter textConverter) {
        textConverter.turn(this);
    }

}
