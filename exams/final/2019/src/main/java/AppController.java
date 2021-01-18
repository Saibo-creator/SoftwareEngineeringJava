// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// You CAN change the bodies of existing methods/constructors
// You CAN add new private methods/constructors
// You CANNOT add interface implementations unless explicitly instructed to do so
// You CANNOT add new public, package-private or protected methods/constructors
// You CANNOT edit the names, parameters, checked exceptions or return types of existing methods/constructors
// You CANNOT delete existing methods/constructors
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

import java.util.Arrays;
import java.util.List;

final class AppController {
    private final Directory directory;
    private List<String> commands = Arrays.asList("search","show");
    private AppView mainView = new AppView("Welcome!", commands);

    /**
     * Creates a new controller that uses the given directory.
     *
     * @throws IllegalArgumentException if and only if the directory is null.
     */
    AppController(Directory directory) {
        if (directory == null) {
            throw new IllegalArgumentException("Directory cannot be null.");
        }

        this.directory = directory;
    }

    /**
     * Handles the given user input, returning the corresponding view.
     * Ignores leading and trailing spaces in the input.
     * If the input is invalid, returns null.
     * --
     * The input can either be empty, which leads to the main view, or non-empty, in which case it must be a command followed by text.
     * The command and the text are separated by one or more spaces; the text itself can contain spaces.
     * If the text is empty even though the input is non-empty, the input is invalid.
     * If the command is unknown, the input is invalid.
     *
     * @throws IllegalArgumentException if and only if the input is null.
     */
    AppView handle(String input) {
        if (input==null) throw new IllegalArgumentException("the input is null");
        if (input.equals(""))  return mainView;
        if (input.startsWith("search")){
            String[] splits= input.split("\\s+");
            String nameToSearch = splits[splits.length-1];

            List<Person> personList = directory.search(nameToSearch);

            if (personList==null){
                return new AppView("The search returned no results.",commands);
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <personList.size() ; i++) {
                sb.append(personList.get(i).name);
                sb.append("\n");
            }

            return new AppView(sb.toString(),commands);
        }

        return null;
    }
}
