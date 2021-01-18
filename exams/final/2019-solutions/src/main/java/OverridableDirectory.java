// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// You CAN change the bodies of existing methods/constructors
// You CAN add new private methods/constructors
// You CANNOT add interface implementations unless explicitly instructed to do so
// You CANNOT add new public, package-private or protected methods/constructors
// You CANNOT edit the names, parameters, checked exceptions or return types of existing methods/constructors
// You CANNOT delete existing methods/constructors
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Directory with overrides for specific searches; searches are passed through the wrapped Directory,
 * and if any override matches the given name, the associated person is returned at the beginning of the results.
 * --
 * Name comparisons for the overrides are case-insensitive, and ignore leading and trailing spaces.
 */
final class OverridableDirectory implements Directory {
    private final Directory wrapped;
    private final Map<String, Person> overrides;

    /**
     * Creates a new OverridableDirectory wrapping the given Directory, with the given overrides.
     *
     * @throws IllegalArgumentException if and only if at least one of the following issues arises:
     *                                  the wrapped directory is null, or if the overrides map is null,
     *                                  or the overrides map contains null values,
     *                                  or the overrides map contains multiple keys that are equal given the comparison criteria.
     */
    OverridableDirectory(Directory wrapped, Map<String, Person> overrides) {
        if (wrapped == null) {
            throw new IllegalArgumentException("The wrapped directory cannot be null.");
        }
        if (overrides == null) {
            throw new IllegalArgumentException("Overrides cannot be null.");
        }
        if (overrides.values().contains(null)) {
            throw new IllegalArgumentException("Overrides cannot contain null values.");
        }

        this.wrapped = wrapped;
        this.overrides = new TreeMap<>(new SearchTextComparator());
        this.overrides.putAll(overrides);
        if (this.overrides.size() != overrides.size()) {
            throw new IllegalArgumentException("Overrides contains keys considered equal.");
        }
    }

    @Override
    public List<Person> search(String name) {
        List<Person> results = wrapped.search(name);
        Person person = overrides.getOrDefault(name, null);
        if (person != null) {
            results.add(0, person);
        }
        return results;
    }
}
