import java.util.LinkedList;
import java.util.List;

public class Document {

    private  List<DocumentPart> parts;

    public Document() {
        parts = new LinkedList<>();
    }

    public void add(DocumentPart part) {
        parts.add(part);
    }

    public List<String>  accept(TextConverter textConverter){
        StringBuffer result = new StringBuffer();
        for (DocumentPart part: parts) {
//            textConverter.turn(part);
            part.accept(textConverter);
        }
        return textConverter.getOutput();
    }


}
