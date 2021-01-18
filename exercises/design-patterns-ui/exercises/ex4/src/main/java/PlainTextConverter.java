import java.util.ArrayList;
import java.util.List;

public class PlainTextConverter implements TextConverter{
//    public String accept(Document document) {
//        List<DocumentPart> parts = document.visit(this);
//    }
//
//
    public List<String> converted = new ArrayList<>();

    @Override
    public List<String> getOutput() {
        return converted;
    }

    public void turn(HyperLink hyperLink) {
        converted.add(String.format("[%s](%s)", hyperLink.getText(), hyperLink.getURL()));

    }

    public void turn(PlainText plainText){
        converted.add(plainText.getText());
    }

    public void turn(BoldText boldText){
        converted.add(String.format("**%s**", boldText.getText()));
    }

//    @Override
//    public Void visit(DocumentPart documentPart) {
//        return null;
//    }
}
