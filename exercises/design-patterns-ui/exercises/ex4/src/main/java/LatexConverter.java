import java.util.ArrayList;
import java.util.List;

public class LatexConverter implements TextConverter{
//    public String accept(Document document) {
//        List<DocumentPart> parts = document.visit(this);
//        StringBuffer result = new StringBuffer();
//        for (DocumentPart part: parts) {
//            result.append(part.toLatex());
//        }
//        return result.toString();
//    }

    public List<String> converted = new ArrayList<>();

    @Override
    public List<String> getOutput() {
        return converted;
    }

    public void turn(HyperLink hyperLink) {
        converted.add(String.format("<latex\"%s\">%s<latex", hyperLink.getURL(), hyperLink.getText()));
    }

    public void turn(PlainText plainText){
        converted.add(plainText.getText());
    }

    public void turn(BoldText boldText){
        converted.add(String.format("latex%slatex", boldText.getText()));
    }

}
