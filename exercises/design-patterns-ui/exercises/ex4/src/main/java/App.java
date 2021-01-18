public class App {
    public static void main(String[] args) {
        DocumentPart plainText = new PlainText("some word");
        DocumentPart boldText = new BoldText("this part is bolded!");
        DocumentPart url = new HyperLink("this is a link", "http://google.com");

        Document document = new Document();
        document.add(plainText);
        document.add(boldText);
        document.add(url);

        LatexConverter latexConverter = new LatexConverter();

        System.out.println(document.accept(latexConverter));

        HTMLConverter htmlConverter = new HTMLConverter();
        System.out.println(document.accept(htmlConverter));

        PlainTextConverter plainTextConverter = new PlainTextConverter();
        System.out.println(document.accept(plainTextConverter));

    }
}
