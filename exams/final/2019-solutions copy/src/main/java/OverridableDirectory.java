// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// You CAN change the bodies of existing methods/constructors
// You CAN add new private methods/constructors
// You CANNOT add interface implementations unless explicitly instructed to do so
// You CANNOT add new public, package-private or protected methods/constructors
// You CANNOT edit the names, parameters, checked exceptions or return types of existing methods/constructors
// You CANNOT delete existing methods/constructors
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

import java.util.*;

/**
 * Directory with overrides for specific searches; searches are passed through the wrapped Directory,
 * and if any override matches the given name, the associated person is returned at the beginning of the results.
 * --
 * Name comparisons for the overrides are case-insensitive, and ignore leading and trailing spaces.
 */
final class OverridableDirectory implements Directory {

    private Directory wrapped;
    private Map<String, Person> overrides;

    /**
     * Creates a new OverridableDirectory wrapping the given Directory, with the given overrides.
     *
     * @throws IllegalArgumentException if and only if at least one of the following issues arises:
     *                                  the wrapped directory is null, or if the overrides map is null,
     *                                  or the overrides map contains null values,
     *                                  or the overrides map contains multiple keys that are equal given the comparison criteria.
     */
    OverridableDirectory(Directory wrapped, Map<String, Person> overrides) {
        if (wrapped==null) throw new IllegalArgumentException("wrapped directory is null");
        if (overrides==null) throw new IllegalArgumentException("overrides map is null");
        if (overrides.values().contains(null)) throw new IllegalArgumentException("overrides map contains null values");
        if (!isOverridesKeysUnique(overrides.keySet())) throw new IllegalArgumentException("overrides map contains multiple keys that are equal given the comparison criteria.");
        this.wrapped = wrapped;
        Map<String,Person> overrides_copy = new HashMap<>();
        overrides.forEach((k,v)-> overrides_copy.put(normlize(k),v));
        this.overrides = overrides_copy;
    }

    @Override
    public List<Person> search(String name) {
        List<Person> res = new ArrayList<>();
        String name_nor = normlize(name);

        if (overrides.containsKey(name_nor)) res.add(overrides.get(name_nor));

        List<Person> searchRes = wrapped.search(name);
        if (searchRes!=null){
            res.addAll(searchRes);
        }

        return res;

    }

    private String normlize(String name){
        return name.toLowerCase().strip();
    }
    private List<String> normlizeNames(List<String> list){
        List<String> res= new ArrayList<>();
        list.forEach(name -> res.add(normlize((String)name)));
        return res;
    }
    private boolean isUnique(List<String> names){
        return names.size()==names.stream().distinct().count();
    }

    private boolean isOverridesKeysUnique(Set<String> keys){

        return isUnique(normlizeNames(new ArrayList<>(keys)));

    }
}
